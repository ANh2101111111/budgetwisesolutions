package com.example.budgetwisesolutions.activity;

<<<<<<< HEAD
import android.content.Intent;
=======
>>>>>>> origin/Tu
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.budgetwisesolutions.R;
<<<<<<< HEAD
import com.google.android.material.bottomnavigation.BottomNavigationView;
=======
>>>>>>> origin/Tu

public class IncomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
<<<<<<< HEAD

        // Tìm kiếm BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

        // Đặt menu hiện tại được chọn là expenses_menu
        bottomNavigationView.setSelectedItemId(R.id.expenses_menu);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId(); // Lấy ID mục được chọn

            if (itemId == R.id.home_menu) {
                startActivity(new Intent(IncomeActivity.this, HomeActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (itemId == R.id.expenses_menu) {
                // Đã ở trang hiện tại
                return true;
            } else if (itemId == R.id.income_menu) {
                startActivity(new Intent(IncomeActivity.this, ExpensesActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (itemId == R.id.budget_menu) {
                startActivity(new Intent(IncomeActivity.this, BudgetActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }

            return false;
        });
=======
>>>>>>> origin/Tu
    }
}