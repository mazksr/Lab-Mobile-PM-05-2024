package com.azka.praktikum7;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {
    private EditText nimField, passField;
    private AppCompatButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        nimField = findViewById(R.id.nim_field);
        passField = findViewById(R.id.password_field);
        loginButton = findViewById(R.id.button_register);

        SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper(this);

        loginButton.setOnClickListener(v -> {
            if (nimField.getText().toString().trim().isEmpty()) {
                nimField.setError("NIM harus diisi");
            } else if (passField.getText().toString().trim().isEmpty()) {
                passField.setError("Password harus diisi");
            } else {
                String nim = nimField.getText().toString();
                String password = nimField.getText().toString();

                sharedPreferenceHelper.register(nim, HashManager.hashCredentials(password));
                Toast.makeText(this, "Berhasil Membuat Akun!", Toast.LENGTH_SHORT).show();
                finish();
                nimField.setText(null);
                passField.setText(null);
            }
        });
    }
}