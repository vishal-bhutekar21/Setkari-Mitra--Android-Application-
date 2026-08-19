package com.example.Shetkari_Mitra;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private static final String PREF_NAME = "shetkari_prefs";
    private static final String KEY_LOGGED_IN = "is_logged_in";
    private static final String KEY_ONBOARDING_DONE = "is_onboarding_completed";
    private static final long SPLASH_DURATION_MS = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView appLogo = findViewById(R.id.applogo);
        TextView appName = findViewById(R.id.appName);
        TextView marathiText = findViewById(R.id.marathiText);

        ScaleAnimation scaleAnim = new ScaleAnimation(
                0.8f, 1.0f,
                0.8f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        scaleAnim.setDuration(600);
        scaleAnim.setFillAfter(true);

        AlphaAnimation fadeIn = new AlphaAnimation(0f, 1f);
        fadeIn.setDuration(800);
        fadeIn.setFillAfter(true);

        if (appLogo != null) appLogo.startAnimation(scaleAnim);
        if (appName != null) appName.startAnimation(fadeIn);
        if (marathiText != null) marathiText.startAnimation(fadeIn);

        new Handler(Looper.getMainLooper()).postDelayed(this::navigateNext, SPLASH_DURATION_MS);
    }

    private void navigateNext() {
        if (isFinishing()) return;

        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        boolean onboardingDone = prefs.getBoolean(KEY_ONBOARDING_DONE, false);
        boolean isLoggedIn = prefs.getBoolean(KEY_LOGGED_IN, false);

        Intent intent;
        if (!onboardingDone) {
            intent = new Intent(this, OnboardingActivity.class);
        } else if (isLoggedIn) {
            intent = new Intent(this, HomeActivity.class);
        } else {
            intent = new Intent(this, Start_Activity.class);
        }
        startActivity(intent);
        finish();
    }
}
