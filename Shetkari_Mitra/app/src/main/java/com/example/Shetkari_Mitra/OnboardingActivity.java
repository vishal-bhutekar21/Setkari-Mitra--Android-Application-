package com.example.Shetkari_Mitra;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        viewPager = findViewById(R.id.viewPagerOnboarding);
        layoutDots = findViewById(R.id.layoutDots);
        btnNext = findViewById(R.id.btnNext);
        btnSkip = findViewById(R.id.btnSkip);

        setupOnboardingItems();

        OnboardingAdapter adapter = new OnboardingAdapter(onboardingItems);
        viewPager.setAdapter(adapter);

        setupIndicators();
        setCurrentIndicator(0);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(position);
                if (position == onboardingItems.size() - 1) {
                    btnNext.setText("Get Started");
                } else {
                    btnNext.setText("Next");
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

    private void setupOnboardingItems() {
        onboardingItems = new ArrayList<>();

        onboardingItems.add(new OnboardingItem(
                "Identify & Learn",
                "सर्पांची अचूक ओळख व माहिती",
                "Comprehensive guide to 16 native snake species across Maharashtra. Learn to instantly distinguish between venomous and harmless snakes.",
                "IDENTIFY & LEARN",
                R.drawable.p2
        ));

        onboardingItems.add(new OnboardingItem(
                "Instant Emergency Care",
                "तात्काळ प्रथमोपचार व मदत",
                "One-tap emergency assistance (112), step-by-step first aid manual, and instant direct connection to certified local snake rescuers.",
                "EMERGENCY & FIRST AID",
                R.drawable.first_aid_logo
        ));

        onboardingItems.add(new OnboardingItem(
                "Anti-Venom Hospitals",
                "जवळचे सर्पदंश उपचार केंद्र",
                "Live OpenStreetMap GPS navigation to 25+ government and private hospitals equipped with anti-venom across Jalna district.",
                "GPS & HOSPITALS",
                R.drawable.hospital_location_logo
        ));
    }

    private void setupIndicators() {
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
