package com.example.Shetkari_Mitra;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
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
    private static final long TOTAL_SPLASH_TIME = 2600;

    private View viewGlowOuter, viewGlowInner;
    private View logoWrapper;
    private ImageView ivShieldLogo, ivAppIconOverlay, ivInfographics;
    private TextView tvAppName, tvAppMarathiName, tvTagline, tvInfographicLabel, tvTrustBadge;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        initViews();
        startInfographicAnimation();

        new Handler(Looper.getMainLooper()).postDelayed(this::navigateNext, TOTAL_SPLASH_TIME);
    }

    private void initViews() {
        viewGlowOuter = findViewById(R.id.viewGlowOuter);
        viewGlowInner = findViewById(R.id.viewGlowInner);
        logoWrapper = findViewById(R.id.logoWrapper);
        ivShieldLogo = findViewById(R.id.ivShieldLogo);
        ivAppIconOverlay = findViewById(R.id.ivAppIconOverlay);
        ivInfographics = findViewById(R.id.ivInfographics);
        tvAppName = findViewById(R.id.tvAppName);
        tvAppMarathiName = findViewById(R.id.tvAppMarathiName);
        tvTagline = findViewById(R.id.tvTagline);
        tvInfographicLabel = findViewById(R.id.tvInfographicLabel);
        tvTrustBadge = findViewById(R.id.tvTrustBadge);
    }

    private void startInfographicAnimation() {
        if (logoWrapper == null) return;

        // Initial setup for entrance
        logoWrapper.setScaleX(0.2f);
        logoWrapper.setScaleY(0.2f);
        logoWrapper.setAlpha(0f);

        if (tvAppName != null) {
            tvAppName.setAlpha(0f);
            tvAppName.setTranslationY(30f);
        }
        if (tvAppMarathiName != null) {
            tvAppMarathiName.setAlpha(0f);
            tvAppMarathiName.setTranslationY(20f);
        }
        if (tvTagline != null) {
            tvTagline.setAlpha(0f);
            tvTagline.setTranslationY(20f);
        }
        if (ivInfographics != null) {
            ivInfographics.setAlpha(0f);
            ivInfographics.setScaleX(0.6f);
            ivInfographics.setScaleY(0.6f);
        }
        if (tvInfographicLabel != null) {
            tvInfographicLabel.setAlpha(0f);
        }
        if (tvTrustBadge != null) {
            tvTrustBadge.setAlpha(0f);
        }

        // 1. Logo Pop & Shield Reveal (0 - 700ms)
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 0.2f, 1.08f, 1.0f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 0.2f, 1.08f, 1.0f);
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0f, 1.0f);
        ObjectAnimator logoAnim = ObjectAnimator.ofPropertyValuesHolder(logoWrapper, scaleX, scaleY, alpha);
        logoAnim.setDuration(750);
        logoAnim.setInterpolator(new OvershootInterpolator(1.2f));

        // 2. Pulse Glow Background Rings
        ObjectAnimator glowAnim = ObjectAnimator.ofPropertyValuesHolder(viewGlowInner,
                PropertyValuesHolder.ofFloat("scaleX", 0.6f, 1.25f, 1.0f),
                PropertyValuesHolder.ofFloat("scaleY", 0.6f, 1.25f, 1.0f),
                PropertyValuesHolder.ofFloat("alpha", 0.1f, 0.45f, 0.25f)
        );
        glowAnim.setDuration(900);
        glowAnim.setInterpolator(new AccelerateDecelerateInterpolator());

        // 3. Title & Marathi Name Fade Up (400ms - 900ms)
        ObjectAnimator titleAnim = ObjectAnimator.ofPropertyValuesHolder(tvAppName,
                PropertyValuesHolder.ofFloat("alpha", 0f, 1f),
                PropertyValuesHolder.ofFloat("translationY", 30f, 0f)
        );
        titleAnim.setDuration(500);
        titleAnim.setStartDelay(350);

        ObjectAnimator marathiAnim = ObjectAnimator.ofPropertyValuesHolder(tvAppMarathiName,
                PropertyValuesHolder.ofFloat("alpha", 0f, 1f),
                PropertyValuesHolder.ofFloat("translationY", 20f, 0f)
        );
        marathiAnim.setDuration(450);
        marathiAnim.setStartDelay(450);

        // 4. Tagline Reveal (600ms - 1100ms)
        ObjectAnimator taglineAnim = ObjectAnimator.ofPropertyValuesHolder(tvTagline,
                PropertyValuesHolder.ofFloat("alpha", 0f, 1f),
                PropertyValuesHolder.ofFloat("translationY", 20f, 0f)
        );
        taglineAnim.setDuration(500);
        taglineAnim.setStartDelay(650);

        // 5. Infographic Trio (Identify -> Respond -> Get Help) (900ms - 1500ms)
        ObjectAnimator infoAnim = ObjectAnimator.ofPropertyValuesHolder(ivInfographics,
                PropertyValuesHolder.ofFloat("alpha", 0f, 1f),
                PropertyValuesHolder.ofFloat("scaleX", 0.6f, 1.05f, 1.0f),
                PropertyValuesHolder.ofFloat("scaleY", 0.6f, 1.05f, 1.0f)
        );
        infoAnim.setDuration(600);
        infoAnim.setStartDelay(950);
        infoAnim.setInterpolator(new OvershootInterpolator(1.1f));

        ObjectAnimator infoLabelAnim = ObjectAnimator.ofFloat(tvInfographicLabel, "alpha", 0f, 1f);
        infoLabelAnim.setDuration(400);
        infoLabelAnim.setStartDelay(1150);

        ObjectAnimator trustAnim = ObjectAnimator.ofFloat(tvTrustBadge, "alpha", 0f, 1f);
        trustAnim.setDuration(400);
        trustAnim.setStartDelay(1300);

        AnimatorSet mainSet = new AnimatorSet();
        mainSet.playTogether(logoAnim, glowAnim, titleAnim, marathiAnim, taglineAnim, infoAnim, infoLabelAnim, trustAnim);
        mainSet.start();
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
