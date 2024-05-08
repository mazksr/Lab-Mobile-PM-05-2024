package com.azka.praktikum7;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceHelper {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPreferenceHelper(Context context) {
        sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
    }

    public void register(String NIM, String password) {
        editor = sharedPreferences.edit();
        editor.putString("nim", NIM);
        editor.putString("password", password);
        editor.apply();
    }

    public boolean isLoggedIn() {
        if (sharedPreferences.getBoolean("is_logged_in", false)) {
            return true;
        } else {
            return false;
        }
    }

    public void setIsLoggedIn(Boolean isLoggedIn) {
        editor = sharedPreferences.edit();
        editor.putBoolean("is_logged_in", isLoggedIn);
        editor.apply();
    }

    public String getNIM() {
        String NIM = sharedPreferences.getString("nim", "");
        return NIM;
    }

    public String getPassword() {
        String password = sharedPreferences.getString("password", "");
        return password;
    }

    public void setIsNight(Boolean isNight) {
        editor = sharedPreferences.edit();
        editor.putBoolean("is_night", isNight);
        editor.apply();
    }

    public boolean isNight() {
        Boolean isNight = sharedPreferences.getBoolean("is_night", false);
        return isNight;
    }
}
