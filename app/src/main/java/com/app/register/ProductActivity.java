package com.app.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.*;

public class ProductActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayout paginationLayout;
    EditText etSearch;
    Spinner spinnerStatus;
    Button btn6;

    List<Product> allProducts = new ArrayList<>();
    List<Product> filteredList = new ArrayList<>();

    int currentPage = 1;
    int ITEMS_PER_PAGE = 10;


    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_product);
        recyclerView = findViewById(R.id.recyclerView);
        paginationLayout = findViewById(R.id.paginationLayout);
        etSearch = findViewById(R.id.etSearch);
        spinnerStatus = findViewById(R.id.spinnerStatus);
        btn6 = findViewById(R.id.btn6);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadProducts();
        setupSpinner();
        applyFilters();

        etSearch.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int st, int b, int c) {
                applyFilters();
            }
            public void beforeTextChanged(CharSequence s, int st, int c, int a) {}
            public void afterTextChanged(Editable s) {}
        });

        btn6.setOnClickListener(v -> {
            Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ProductActivity.this,Dashboard.class));
            finish();
        });
    }

    void loadProducts() {
        for (int i = 1; i <= 30; i++) {
            allProducts.add(new Product(
                    "Product " + i,
                    i * 150,
                    "Category " + (i % 3),
                    i * 2,
                    (i % 2 == 0) ? "Active" : "Inactive"
            ));
        }
    }

    void setupSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,
                new String[]{"All", "Active", "Inactive"});
        spinnerStatus.setAdapter(adapter);

        spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> p, android.view.View v, int i, long l) {
                applyFilters();
            }
            public void onNothingSelected(AdapterView<?> p) {}
        });
    }

    void applyFilters() {
        filteredList.clear();
        String search = etSearch.getText().toString().toLowerCase();
        String status = spinnerStatus.getSelectedItem().toString();

        for (Product p : allProducts) {
            boolean matchSearch = p.name.toLowerCase().contains(search);
            boolean matchStatus = status.equals("All") || p.status.equals(status);

            if (matchSearch && matchStatus) {
                filteredList.add(p);
            }
        }
        currentPage = 1;
        setupPagination();
    }

    void setupPagination() {
        paginationLayout.removeAllViews();
        int pageCount = (int) Math.ceil((double) filteredList.size() / ITEMS_PER_PAGE);

        for (int i = 1; i <= pageCount; i++) {
            Button btn = new Button(this);
            btn.setText(String.valueOf(i));
            int page = i;
            btn.setOnClickListener(v -> {
                currentPage = page;
                displayPage();
            });
            paginationLayout.addView(btn);
        }
        displayPage();
    }

    void displayPage() {
        int start = (currentPage - 1) * ITEMS_PER_PAGE;
        int end = Math.min(start + ITEMS_PER_PAGE, filteredList.size());
        recyclerView.setAdapter(
                new ProductAdapter(filteredList.subList(start, end))
        );
    }

}
