package com.example.Shetkari_Mitra;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

public class ThemeHelper {

    private static final String PREF_NAME = "shetkari_mitra_theme_pref";
    private static final String KEY_THEME_MODE = "theme_mode";

    public static final int THEME_SYSTEM = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
    public static final int THEME_LIGHT = AppCompatDelegate.MODE_NIGHT_NO;
    public static final int THEME_DARK = AppCompatDelegate.MODE_NIGHT_YES;

    public static void applyTheme(Context context) {
        int themeMode = getSavedThemeMode(context);
        AppCompatDelegate.setDefaultNightMode(themeMode);
    }

    public static void setThemeMode(Context context, int themeMode) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().putInt(KEY_THEME_MODE, themeMode).apply();
        AppCompatDelegate.setDefaultNightMode(themeMode);
    }

    public static int getSavedThemeMode(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(KEY_THEME_MODE, THEME_LIGHT); // Default to clean light mode
    }
}
