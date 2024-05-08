package com.azka.praktikum7;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UserActivity extends AppCompatActivity {
    private AppCompatButton logOutButton, settingsButton;
    TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user);

        logOutButton = findViewById(R.id.button_logout);
        settingsButton = findViewById(R.id.button_settings);
        welcomeText = findViewById(R.id.welcome_text);

        SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper(this);

        String text = "Selamat Datang " + sharedPreferenceHelper.getNIM();
        welcomeText.setText(text);

        settingsButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });

        logOutButton.setOnClickListener(v -> {
            sharedPreferenceHelper.setIsLoggedIn(false);
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}