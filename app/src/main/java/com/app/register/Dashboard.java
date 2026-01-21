package com.app.register;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {
    Button btn5,btn7,btn8;
    Spinner spinnerFilter;
    TextView txtUsers, txtProducts, txtOrders, txtRevenue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btn5 = findViewById(R.id.btn5);
        btn7 = findViewById(R.id.btn7);
        spinnerFilter = findViewById(R.id.spinnerFilter);
        txtUsers = findViewById(R.id.txtUsers);
        txtProducts = findViewById(R.id.txtProducts);
        txtOrders = findViewById(R.id.txtOrders);
        txtRevenue = findViewById(R.id.txtRevenue);
        btn8 = findViewById(R.id.btn8);

        String[] filters = {"Today", "Yesterday", "Weekly", "Monthly", "Custom Range"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, filters);
        spinnerFilter.setAdapter(adapter);

        spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateDashboard(filters[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        btn5.setOnClickListener(v -> {
            Toast.makeText(this, "Product Catalog", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Dashboard.this,ProductActivity.class));
            finish();
        });
        btn7.setOnClickListener(v -> {
            Toast.makeText(this, "Logout successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Dashboard.this, MainActivity.class));
            finish();
        });
        btn8.setOnClickListener(v -> {
            Toast.makeText(this, "Upload Image", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Dashboard.this, ImageUpload.class));
            finish();
        });
    }

    private void updateDashboard(String filter) {
        switch (filter) {
            case "Today":
                setData(120, 45, 30, 15000);
                break;
            case "Yesterday":
                setData(110, 40, 25, 12000);
                break;
            case "Weekly":
                setData(500, 60, 150, 75000);
                break;
            case "Monthly":
                setData(2000, 80, 620, 320000);
                break;
            case "Custom Range":
                setData(900, 70, 280, 140000);
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    private void setData(int users, int products, int orders, int revenue) {
        txtUsers.setText("Total Users: " + users);
        txtProducts.setText("Total Products: " + products);
        txtOrders.setText("Total Orders: " + orders);
        txtRevenue.setText("Total Revenue: ₹" + revenue);
    }
}

