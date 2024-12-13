package com.example.budgetwisesolutions.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.budgetwisesolutions.R;
import com.example.budgetwisesolutions.database.UserDb;
import com.example.budgetwisesolutions.model.UserModel;

import java.io.FileInputStream;

public class LoginActivity extends AppCompatActivity {

    TextView tvRegister, tvForgetPassword;
    EditText edtEmailLogin, edtPassLogin;
    Button btnSubmit;
    UserDb userDb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvRegister = findViewById(R.id.tvRegister);
        edtEmailLogin = findViewById(R.id.edtEmailLogin);
        edtPassLogin = findViewById(R.id.edtPassLogin);
        btnSubmit = findViewById(R.id.btnSubmit);
        tvForgetPassword = findViewById(R.id.tvForgetPassword);
        userDb = new UserDb(LoginActivity.this);

        tvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        LoginWithDataBaseSQLite();
    }

    private void LoginWithDataFile() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtEmailLogin.getText().toString().trim();
                String pass = edtPassLogin.getText().toString().trim();

                if (TextUtils.isEmpty(user)) {
                    edtEmailLogin.setError("Email cannot be empty");
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    edtPassLogin.setError("Password cannot be empty");
                    return;
                }

                // Read data from the local file
                try {
                    FileInputStream fileInputStream = openFileInput("account.txt");
                    int read;
                    StringBuilder builder = new StringBuilder();
                    while ((read = fileInputStream.read()) != -1) {
                        builder.append((char) read);
                    }
                    fileInputStream.close();

                    boolean checkLogin = false;
                    String[] infoAccount = builder.toString().trim().split("\n");
                    for (String account : infoAccount) {
                        String email = account.substring(0, account.indexOf("|")).trim();
                        String password = account.substring(account.indexOf("|") + 1).trim();
                        if (email.equals(user) && password.equals(pass)) {
                            checkLogin = true;
                            break;
                        }
                    }

                    if (checkLogin) {
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("MY_Email", user);
                        intent.putExtras(bundle); // Pass data to another activity
                        startActivity(intent); // Navigate to another activity
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Account Invalid", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(LoginActivity.this, "Error reading file", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void LoginWithDataBaseSQLite() {
        btnSubmit.setOnClickListener(view -> {
            String email = edtEmailLogin.getText().toString().trim();
            String pass = edtPassLogin.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                edtEmailLogin.setError("Email cannot be empty");
                return;
            }

            if (TextUtils.isEmpty(pass)) {
                edtPassLogin.setError("Password cannot be empty");
                return;
            }

            try {
                UserModel checkLogin = userDb.checkUserLogin(email, pass);

                if (checkLogin != null && checkLogin.getEmail() != null) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("ID_USER", checkLogin.getId());
                    bundle.putString("MY_Email", email);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid Account", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
