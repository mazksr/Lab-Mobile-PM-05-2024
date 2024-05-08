package com.azka.praktikum7;

import android.app.UiModeManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ma.apps.widgets.daynightswitch.DayNightSwitch;
import ma.apps.widgets.daynightswitch.OnSwitchListener;


public class SettingsActivity extends AppCompatActivity {
    private DayNightSwitch dayNightSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);

        dayNightSwitch = findViewById(R.id.switch_1);
        SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper(this);

        dayNightSwitch.setDayChecked(!sharedPreferenceHelper.isNight(), false);

        dayNightSwitch.setOnSwitchListener((dayNightSwitch, isDay) -> {
            if (!isDay) {
                sharedPreferenceHelper.setIsNight(true);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                sharedPreferenceHelper.setIsNight(false);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });
    }
}