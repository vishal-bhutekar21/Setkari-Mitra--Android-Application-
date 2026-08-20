package com.example.Shetkari_Mitra;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class OnboardingActivity extends AppCompatActivity {

    private static final String PREF_NAME = "shetkari_prefs";
    private static final String KEY_ONBOARDING_DONE = "is_onboarding_completed";

    private ViewPager2 viewPager;
    private LinearLayout layoutDots;
    private Button btnNext, btnSkip;
    private List<OnboardingItem> onboardingItems;
    private OnboardingAdapter adapter;
    private String currentLanguage = LocaleHelper.LANGUAGE_ENGLISH;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentLanguage = LocaleHelper.getLanguage(this);
        setContentView(R.layout.activity_onboarding);

        viewPager = findViewById(R.id.viewPagerOnboarding);
        layoutDots = findViewById(R.id.layoutDots);
        btnNext = findViewById(R.id.btnNext);
        btnSkip = findViewById(R.id.btnSkip);

        setupOnboardingItems();

        adapter = new OnboardingAdapter(onboardingItems, currentLanguage, this::onLanguageChanged);
        viewPager.setAdapter(adapter);

        setupIndicators();
        setCurrentIndicator(0);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(position);
                if (position == onboardingItems.size() - 1) {
                    btnNext.setText(R.string.get_started);
                } else {
                    btnNext.setText(R.string.next);
                }
            }
        });

        btnNext.setOnClickListener(v -> {
            if (viewPager.getCurrentItem() + 1 < onboardingItems.size()) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            } else {
                finishOnboarding();
            }
        });

        btnSkip.setOnClickListener(v -> finishOnboarding());
    }

    private void onLanguageChanged(String langCode) {
        currentLanguage = langCode;
        LocaleHelper.setLocale(this, langCode);
        adapter.updateSelectedLanguage(langCode);
        recreate();
    }

    private void setupOnboardingItems() {
        onboardingItems = new ArrayList<>();

        // Screen 1: Stay Safe Around Snakes
        onboardingItems.add(new OnboardingItem(
                getString(R.string.onboarding_title_1),
                getString(R.string.onboarding_subtitle_1),
                getString(R.string.onboarding_desc_1),
                getString(R.string.onboarding_badge_1),
                R.drawable.ic_feature_snakes
        ));

        // Screen 2: Know What To Do in an Emergency
        onboardingItems.add(new OnboardingItem(
                getString(R.string.onboarding_title_2),
                getString(R.string.onboarding_subtitle_2),
                getString(R.string.onboarding_desc_2),
                getString(R.string.onboarding_badge_2),
                R.drawable.ic_feature_first_aid
        ));

        // Screen 3: Find Hospitals and Snake Rescuers Near You
        onboardingItems.add(new OnboardingItem(
                getString(R.string.onboarding_title_3),
                getString(R.string.onboarding_subtitle_3),
                getString(R.string.onboarding_desc_3),
                getString(R.string.onboarding_badge_3),
                R.drawable.ic_hospital_shield_24
        ));

        // Screen 4: Choose Language
        onboardingItems.add(new OnboardingItem(
                getString(R.string.onboarding_title_4),
                getString(R.string.onboarding_subtitle_4),
                getString(R.string.onboarding_desc_4),
                getString(R.string.onboarding_badge_4),
                R.drawable.ic_language_24,
                true
        ));
    }

    private void setupIndicators() {
        layoutDots.removeAllViews();
        ImageView[] indicators = new ImageView[onboardingItems.size()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);

        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(this);
            indicators[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dot_inactive));
            indicators[i].setLayoutParams(layoutParams);
            layoutDots.addView(indicators[i]);
        }
    }

    private void setCurrentIndicator(int position) {
        int childCount = layoutDots.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutDots.getChildAt(i);
            if (i == position) {
                imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dot_active));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dot_inactive));
            }
        }
    }

    private void finishOnboarding() {
        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        prefs.edit().putBoolean(KEY_ONBOARDING_DONE, true).apply();

        boolean isLoggedIn = prefs.getBoolean("is_logged_in", false);
        Intent intent;
        if (isLoggedIn) {
            intent = new Intent(this, HomeActivity.class);
        } else {
            intent = new Intent(this, Start_Activity.class);
        }
        startActivity(intent);
        finish();
    }
}
