package com.azka.praktikum7;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    private EditText nimField, passField;
    private AppCompatButton loginButton;
    private TextView registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        nimField = findViewById(R.id.nim_field);
        passField = findViewById(R.id.password_field);
        loginButton = findViewById(R.id.button_login);
        registerButton = findViewById(R.id.button_register);

        SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper(this);

        registerButton.setOnClickListener(v -> startActivity(new Intent(this, RegisterActivity.class)));

        loginButton.setOnClickListener(v -> {
            if (nimField.getText().toString().trim().isEmpty()) {
                nimField.setError("NIM harus diisi");
            } else if (passField.getText().toString().trim().isEmpty()) {
                passField.setError("Password harus diisi");
            } else {
                String nim = nimField.getText().toString();
                String password = nimField.getText().toString();

                String hashedStoredPassword = sharedPreferenceHelper.getPassword();
                String hashedPassword = HashManager.hashCredentials(password);

                if (nim.equals(sharedPreferenceHelper.getNIM()) && hashedStoredPassword.equals(hashedPassword)) {
                    Intent intent = new Intent(this, UserActivity.class);
                    sharedPreferenceHelper.setIsLoggedIn(true);
                    startActivity(intent);
                    nimField.setText(null);
                    passField.setText(null);
                    finish();
                } else {
                    Toast.makeText(this, "NIM atau Password Salah", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}