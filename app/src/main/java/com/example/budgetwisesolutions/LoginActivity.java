package com.example.budgetwisesolutions;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.budgetwisesolutions.database.UserDb;

import java.io.FileInputStream;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail, edtPassword;
    private TextView tvRegister;
    private Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.loginButton);
        tvRegister = findViewById(R.id.tvRegister);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    edtEmail.setError("Email cannot be empty");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    edtPassword.setError("Password cannot be empty");
                    return;
                }

                try {
                    FileInputStream fileInputStream = openFileInput("account.txt");
                    int read = -1;
                    StringBuilder builder = new StringBuilder();
                    while ((read = fileInputStream.read()) != -1) {
                        builder.append((char) read);
                    }
                    boolean checkLogin = false;
                    String[] infoAccount = builder.toString().trim().split("\n");
                    for (String account : infoAccount) {
                        String Email = account.substring(0, account.indexOf("|")).trim();
                        String Pass = account.substring(account.indexOf("|") + 1).trim();
                        if (email.equals(email) && password.equals(password)) {
                            checkLogin = true;
                            break;
                        }
                    }
                    if (checkLogin) {
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("MY_NAME", email);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Account Invalid !!!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
