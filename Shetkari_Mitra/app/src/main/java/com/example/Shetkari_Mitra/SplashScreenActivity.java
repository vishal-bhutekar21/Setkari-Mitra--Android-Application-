package com.example.Shetkari_Mitra;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private static final String PREF_NAME = "shetkari_prefs";
    private static final String KEY_LOGGED_IN = "is_logged_in";
    private static final String KEY_ONBOARDING_DONE = "is_onboarding_completed";
    private static final long TOTAL_SPLASH_TIME = 2800;

    private ImageView ivSplashSnake;
    private ImageView ivSplashShield;
    private View viewTorchBeam;
    private TextView tvStorySubtitle;
    private View brandContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ivSplashSnake = findViewById(R.id.ivSplashSnake);
        ivSplashShield = findViewById(R.id.ivSplashShield);
        viewTorchBeam = findViewById(R.id.viewTorchBeam);
        tvStorySubtitle = findViewById(R.id.tvStorySubtitle);
        brandContainer = findViewById(R.id.brandContainer);

        startNarrativeAnimation();

        new Handler(Looper.getMainLooper()).postDelayed(this::navigateNext, TOTAL_SPLASH_TIME);
    }

    private void startNarrativeAnimation() {
        if (ivSplashSnake == null || ivSplashShield == null || viewTorchBeam == null) return;

        // Initial setup
        ivSplashSnake.setTranslationX(-300f);
        ivSplashSnake.setAlpha(0f);
        ivSplashSnake.setScaleX(0.7f);
        ivSplashSnake.setScaleY(0.7f);

        if (brandContainer != null) {
            brandContainer.setAlpha(0f);
            brandContainer.setTranslationY(40f);
        }

        // Phase 1: Snake slithers into encounter area (0ms - 700ms)
        ObjectAnimator snakeInX = ObjectAnimator.ofFloat(ivSplashSnake, "translationX", -300f, 0f);
        ObjectAnimator snakeInAlpha = ObjectAnimator.ofFloat(ivSplashSnake, "alpha", 0f, 1f);
        ObjectAnimator snakeInScaleX = ObjectAnimator.ofFloat(ivSplashSnake, "scaleX", 0.7f, 1f);
        ObjectAnimator snakeInScaleY = ObjectAnimator.ofFloat(ivSplashSnake, "scaleY", 0.7f, 1f);

        AnimatorSet phase1 = new AnimatorSet();
        phase1.playTogether(snakeInX, snakeInAlpha, snakeInScaleX, snakeInScaleY);
        phase1.setDuration(700);
        phase1.setInterpolator(new AccelerateDecelerateInterpolator());

        // Phase 2: Farmer Torch Beam shines & Identification takes place (700ms - 1300ms)
        ObjectAnimator torchAlpha = ObjectAnimator.ofFloat(viewTorchBeam, "alpha", 0f, 0.9f, 0.4f);
        ObjectAnimator torchScaleX = ObjectAnimator.ofFloat(viewTorchBeam, "scaleX", 0.3f, 1.2f, 1.0f);
        ObjectAnimator torchScaleY = ObjectAnimator.ofFloat(viewTorchBeam, "scaleY", 0.3f, 1.2f, 1.0f);

        AnimatorSet phase2 = new AnimatorSet();
        phase2.playTogether(torchAlpha, torchScaleX, torchScaleY);
        phase2.setDuration(600);
        phase2.setStartDelay(650);

        // Phase 3: Farmer gives safe passage — Snake safely slithers away into nature (1300ms - 1900ms)
        ObjectAnimator snakeOutX = ObjectAnimator.ofFloat(ivSplashSnake, "translationX", 0f, 350f);
        ObjectAnimator snakeOutAlpha = ObjectAnimator.ofFloat(ivSplashSnake, "alpha", 1f, 0f);
        ObjectAnimator snakeOutRot = ObjectAnimator.ofFloat(ivSplashSnake, "rotation", 0f, -15f, 15f);

        AnimatorSet phase3 = new AnimatorSet();
        phase3.playTogether(snakeOutX, snakeOutAlpha, snakeOutRot);
        phase3.setDuration(650);
        phase3.setStartDelay(1300);

        // Phase 4: Shetkari Mitra Safety Shield Illuminates & Brand reveals (1800ms - 2600ms)
        ObjectAnimator shieldAlpha = ObjectAnimator.ofFloat(ivSplashShield, "alpha", 0f, 1f);
        ObjectAnimator shieldScaleX = ObjectAnimator.ofFloat(ivSplashShield, "scaleX", 0.3f, 1.15f, 1.0f);
        ObjectAnimator shieldScaleY = ObjectAnimator.ofFloat(ivSplashShield, "scaleY", 0.3f, 1.15f, 1.0f);

        ObjectAnimator brandAlpha = ObjectAnimator.ofFloat(brandContainer, "alpha", 0f, 1f);
        ObjectAnimator brandTransY = ObjectAnimator.ofFloat(brandContainer, "translationY", 40f, 0f);

        AnimatorSet phase4 = new AnimatorSet();
        phase4.playTogether(shieldAlpha, shieldScaleX, shieldScaleY, brandAlpha, brandTransY);
        phase4.setDuration(750);
        phase4.setInterpolator(new OvershootInterpolator());
        phase4.setStartDelay(1800);

        // Execute coordinated animation sequence
        AnimatorSet storyAnimation = new AnimatorSet();
        storyAnimation.playTogether(phase1, phase2, phase3, phase4);
        storyAnimation.start();

        // Subtitle dynamic text updates for storytelling
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (!isFinishing() && tvStorySubtitle != null) {
                tvStorySubtitle.setText("साप ओळखला • सुरक्षित मार्ग दिला");
            }
        }, 1100);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (!isFinishing() && tvStorySubtitle != null) {
                tvStorySubtitle.setText("साप ओळखा • सुरक्षित राहा • जीवन वाचवा");
            }
        }, 2000);
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
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}
