package com.app.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Register extends AppCompatActivity {

    EditText Name,Mobile,Lock;
    TextView textView5,textView6;
    Button  btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Name = findViewById(R.id.Name);
        Mobile = findViewById(R.id.Mobile);
        Lock = findViewById(R.id.Lock);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        btn3 = findViewById(R.id.btn3);

        Mobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 10){
                    textView5.setText("Mobile number must be 10 digits");
                } else {
                    textView5.setText("");
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}
        });

        btn3.setOnClickListener(v -> {
            String nam = Name.getText().toString();
            String phone = Mobile.getText().toString();
            String pass = Lock.getText().toString();
            if (nam.isEmpty() || phone.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "All fields required", Toast.LENGTH_SHORT).show();
            } else if (phone.length() != 10) {
                Toast.makeText(this, "Invalid mobile number", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        });
        textView6.setOnClickListener(v -> startActivity(new Intent(Register.this, MainActivity.class)));
    }
}