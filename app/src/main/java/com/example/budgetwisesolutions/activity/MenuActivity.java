package com.example.budgetwisesolutions.activity;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.budgetwisesolutions.R;
import com.google.android.material.navigation.NavigationView;

public class MenuActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);

        // Initialize DrawerLayout and NavigationView
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        // Initialize ActionBarDrawerToggle
        drawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        // Enable the hamburger icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Handle NavigationView item clicks
        navigationView.setNavigationItemSelectedListener(this::handleNavigationItemSelected);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle drawer toggle click
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean handleNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.home_menu) {
            // Navigate to Home
            startActivity(new Intent(MenuActivity.this, HomeActivity.class));
        } else if (id == R.id.expenses_menu) {
            // Navigate to Expenses
            startActivity(new Intent(MenuActivity.this, ExpensesActivity.class));
        } else if (id == R.id.budget_menu) {
            // Navigate to Budget
            startActivity(new Intent(MenuActivity.this, BudgetActivity.class));
        } else if (id == R.id.nav_logout) {
            Intent intentLogout = new Intent(MenuActivity.this, LoginActivity.class);
            startActivity(intentLogout);
            finish();
            return false;

        } else {
            return false;
        }

        // Close drawer after selecting an item
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}