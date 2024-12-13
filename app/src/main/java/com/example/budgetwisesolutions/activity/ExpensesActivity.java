package com.example.budgetwisesolutions.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.budgetwisesolutions.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ExpensesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        // Tìm kiếm BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

        // Đặt menu hiện tại được chọn là expenses_menu
        bottomNavigationView.setSelectedItemId(R.id.expenses_menu);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId(); // Lấy ID mục được chọn

            if (itemId == R.id.home_menu) {
                startActivity(new Intent(ExpensesActivity.this, HomeActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (itemId == R.id.expenses_menu) {
                // Đã ở trang hiện tại
                return true;
            } else if (itemId == R.id.income_menu) {
                startActivity(new Intent(ExpensesActivity.this, IncomeActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (itemId == R.id.budget_menu) {
                startActivity(new Intent(ExpensesActivity.this, BudgetActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }

            return false;
        });

    }

}
