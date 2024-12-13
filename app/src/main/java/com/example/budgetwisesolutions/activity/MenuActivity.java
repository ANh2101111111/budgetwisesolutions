<<<<<<< HEAD
package com.example.budgetwisesolutions.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.budgetwisesolutions.R;
import com.example.budgetwisesolutions.activity.BudgetActivity;
import com.example.budgetwisesolutions.activity.ExpensesActivity;
import com.example.budgetwisesolutions.activity.HomeActivity;
import com.example.budgetwisesolutions.activity.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MenuActivity extends AppCompatActivity  {
    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main); // Đảm bảo sử dụng layout này

//        // Khởi tạo các thành phần giao diện
//        bottomNavigationView = findViewById(R.id.bottomNavigation);
//        drawerLayout = findViewById(R.id.drawer_layout);
//        toolbar = findViewById(R.id.toolbar);
//        navigationView = findViewById(R.id.nav_view);
//
//        // Thiết lập Toolbar
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//
//        // Thiết lập Navigation Drawer
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//
//        // Thiết lập NavigationView
//        navigationView.setNavigationItemSelectedListener(this);
//        Menu menu = navigationView.getMenu();
//        MenuItem logout = menu.findItem(R.id.nav_logout);
//
//        // Xử lý sự kiện logout
//        logout.setOnMenuItemClickListener(item -> {
//            Intent intentLogout = new Intent(MenuActivity.this, LoginActivity.class);
//            startActivity(intentLogout);
//            finish();
//            return false;
//        });

        // Xử lý sự kiện click cho BottomNavigationView
//        bottomNavigationView.setOnItemSelectedListener(item -> {
//            switch (item.getItemId()) {
//                case R.id.home_menu:
//                    startActivity(new Intent(MenuActivity.this, HomeActivity.class));
//                    break;
//                case R.id.budget_menu:
//                    startActivity(new Intent(MenuActivity.this, BudgetActivity.class));
//                    break;
//                case R.id.expenses_menu:
//                    startActivity(new Intent(MenuActivity.this, ExpensesActivity.class));
//                    break;
//                default:
//                    startActivity(new Intent(MenuActivity.this, HomeActivity.class));
//            }
//            return true;
//        });
//    }
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.home_menu:
//                startActivity(new Intent(MenuActivity.this, HomeActivity.class));
//                break;
//            case R.id.budget_menu:
//                startActivity(new Intent(MenuActivity.this, BudgetActivity.class));
//                break;
//            case R.id.expenses_menu:
//                startActivity(new Intent(MenuActivity.this, ExpensesActivity.class));
//                break;
//        }
//        drawerLayout.closeDrawer(GravityCompat.START);
//        return true;
//    }
=======
package com.example.budgetwisesolutions.activity;;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import com.example.budgetwisesolutions.R;
import com.google.android.material.navigation.NavigationView;
public class MenuActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main); // Your layout file
>>>>>>> origin/Tu
    }
}