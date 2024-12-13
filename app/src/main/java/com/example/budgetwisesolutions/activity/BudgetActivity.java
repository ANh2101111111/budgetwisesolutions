package com.example.budgetwisesolutions.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budgetwisesolutions.adapter.BudgetAdapter;
import com.example.budgetwisesolutions.database.BudgetDatabaseHelper;
import com.example.budgetwisesolutions.model.Budget;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.budgetwisesolutions.R;
import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.app.AlertDialog;

public class BudgetActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BudgetAdapter adapter;
    private BudgetDatabaseHelper databaseHelper;
    private FloatingActionButton addBudgetButton;
    private List<Budget> budgetList = new ArrayList<>(); // Dữ liệu của RecyclerView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_budget);
// Tìm kiếm BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Đặt menu hiện tại được chọn là expenses_menu
        bottomNavigationView.setSelectedItemId(R.id.expenses_menu);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
                    int itemId = item.getItemId(); // Lấy ID mục được chọn

                    if (itemId == R.id.home_menu) {
                        startActivity(new Intent(BudgetActivity.this, HomeActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    } else if (itemId == R.id.budget_menu) {
                        // Đã ở trang hiện tại
                        return true;
                    } else if (itemId == R.id.income_menu) {
                        startActivity(new Intent(BudgetActivity.this, IncomeActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    } else if (itemId == R.id.expenses_menu) {
                        startActivity(new Intent(BudgetActivity.this, ExpensesActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    }
            return false;
        });


        recyclerView = findViewById(R.id.budgetRecyclerView);
        addBudgetButton = findViewById(R.id.addBudgetButton);

        databaseHelper = new BudgetDatabaseHelper(this);
        loadBudgets(); // Load danh sách ban đầu

        addBudgetButton.setOnClickListener(v -> showAddBudgetDialog());
    }

    private void loadBudgets() {
        budgetList.clear();
        budgetList.addAll(databaseHelper.getAllBudgets());
        if (adapter == null) {
            adapter = new BudgetAdapter(this, budgetList, new BudgetAdapter.OnItemClickListener() {
                @Override
                public void onEditClick(Budget budget) {
                    showUpdateBudgetDialog(budget);
                }

                @Override
                public void onDeleteClick(Budget budget) {
                    showDeleteConfirmationDialog(budget);
                }
            });
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged(); // Làm mới danh sách hiển thị
        }
    }

    private void showAddBudgetDialog() {
        AddBudgetDialog dialog = new AddBudgetDialog(this, newBudget -> {
            long id = databaseHelper.addBudget(newBudget); // Thêm mới vào database
            if (id > 0) {
                newBudget.setId((int) id); // Cập nhật ID sau khi thêm
                budgetList.add(0, newBudget); // Thêm vào đầu danh sách
                adapter.notifyItemInserted(0); // Cập nhật RecyclerView
                recyclerView.scrollToPosition(0); // Cuộn tới mục đầu tiên
                Toast.makeText(this, "Budget added", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

    private void showUpdateBudgetDialog(Budget budget) {
        UpdateBudgetDialog dialog = new UpdateBudgetDialog(this, budget, new UpdateBudgetDialog.OnBudgetUpdateListener() {
            @Override
            public void onBudgetUpdate(Budget updatedBudget) {
                if (updatedBudget != null) { // Cập nhật
                    databaseHelper.updateBudget(updatedBudget);
                    int index = budgetList.indexOf(budget);
                    if (index != -1) {
                        budgetList.set(index, updatedBudget);
                        adapter.notifyItemChanged(index);
                    }
                    Toast.makeText(BudgetActivity.this, "Budget updated", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onBudgetDelete(Budget budgetToDelete) { // Xóa
                deleteBudget(budgetToDelete); // Gọi trực tiếp hàm xóa
            }
        });
        dialog.show();
    }

    private void showDeleteConfirmationDialog(Budget budget) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Budget")
                .setMessage("Are you sure you want to delete this budget?")
                .setPositiveButton("Yes", (dialog, which) -> deleteBudget(budget))
                .setNegativeButton("No", null)
                .show();
    }

    private void deleteBudget(Budget budget) {
        if (budget == null) {
            Toast.makeText(this, "Error: Budget not found", Toast.LENGTH_SHORT).show();
            return;
        }

        int rowsAffected = databaseHelper.deleteBudget(budget.getId());
        if (rowsAffected > 0) {
            int position = budgetList.indexOf(budget);
            if (position != -1) {
                budgetList.remove(position); // Xóa khỏi danh sách hiện tại
                adapter.notifyItemRemoved(position); // Cập nhật RecyclerView
            } else {
                Toast.makeText(this, "Error: Budget position not found", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, "Budget deleted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to delete budget", Toast.LENGTH_SHORT).show();
        }
    }
}
