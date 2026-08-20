package com.example.Shetkari_Mitra;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private static final String PREF_NAME = "shetkari_prefs";
    private static final String KEY_LOGGED_IN = "is_logged_in";
    private static final String KEY_ONBOARDING_DONE = "is_onboarding_completed";
    private static final long TOTAL_SPLASH_TIME = 1500;

    private View centerStage, tvTrustBadge;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        centerStage = findViewById(R.id.centerStage);
        tvTrustBadge = findViewById(R.id.tvTrustBadge);

        startMinimalCalmAnimation();

        new Handler(Looper.getMainLooper()).postDelayed(this::navigateNext, TOTAL_SPLASH_TIME);
    }

    private void startMinimalCalmAnimation() {
        if (centerStage == null) return;

        centerStage.setAlpha(0f);
        centerStage.setTranslationY(20f);

        if (tvTrustBadge != null) {
            tvTrustBadge.setAlpha(0f);
        }

        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(centerStage, "alpha", 0f, 1f);
        ObjectAnimator transAnim = ObjectAnimator.ofFloat(centerStage, "translationY", 20f, 0f);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(alphaAnim, transAnim);
        set.setDuration(700);
        set.setInterpolator(new DecelerateInterpolator());
        set.start();

        if (tvTrustBadge != null) {
            tvTrustBadge.animate().alpha(1f).setDuration(600).setStartDelay(300).start();
        }
    }

    private void navigateNext() {
        if (isFinishing() || isDestroyed()) return;

        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        boolean isOnboardingCompleted = prefs.getBoolean(KEY_ONBOARDING_DONE, false);
        boolean isLoggedIn = prefs.getBoolean(KEY_LOGGED_IN, false);

        Intent intent;
        if (!isOnboardingCompleted) {
            intent = new Intent(SplashScreenActivity.this, OnboardingActivity.class);
        } else if (!isLoggedIn) {
            intent = new Intent(SplashScreenActivity.this, Start_Activity.class);
        } else {
            intent = new Intent(SplashScreenActivity.this, HomeActivity.class);
        }

        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}
