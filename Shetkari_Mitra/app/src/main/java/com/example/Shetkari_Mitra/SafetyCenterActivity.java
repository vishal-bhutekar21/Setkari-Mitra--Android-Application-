package com.example.Shetkari_Mitra;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.progressindicator.LinearProgressIndicator;

public class SafetyCenterActivity extends AppCompatActivity {

    private static final String PREF_NAME = "safety_checklist_prefs";

    private MaterialCardView cardProgressHeader;
    private MaterialCardView cardTask1, cardTask2, cardTask3, cardTask4, cardTask5;
    private ImageView ivCheck1, ivCheck2, ivCheck3, ivCheck4, ivCheck5;
    private TextView tvTitleTask1, tvTitleTask2, tvTitleTask3, tvTitleTask4, tvTitleTask5;
    private TextView tvChecklistScore, tvChecklistSubtitle;
    private LinearProgressIndicator progressSafety;

    private View tvSectionTasks, tvSectionCategories;
    private MaterialCardView cardCat1, cardCat2, cardCat3, cardCat4;

    private boolean[] taskStates = new boolean[5];
    private int currentProgress = 0;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_center);

        initViews();
        loadChecklistState();
        setupClickListeners();
        playStaggeredEntranceAnimation();

        BottomNavigationHelper.setupBottomNavigation(this, findViewById(R.id.bottom_navigation), R.id.bottom_nav_safety);
    }

    private void initViews() {
        ImageButton btnBack = findViewById(R.id.btnBack);
        if (btnBack != null) btnBack.setOnClickListener(v -> finish());

        cardProgressHeader = findViewById(R.id.cardProgressHeader);
        tvChecklistScore = findViewById(R.id.tvChecklistScore);
        tvChecklistSubtitle = findViewById(R.id.tvChecklistSubtitle);
        progressSafety = findViewById(R.id.progressSafety);

        tvSectionTasks = findViewById(R.id.tvSectionTasks);
        cardTask1 = findViewById(R.id.cardTask1);
        cardTask2 = findViewById(R.id.cardTask2);
        cardTask3 = findViewById(R.id.cardTask3);
        cardTask4 = findViewById(R.id.cardTask4);
        cardTask5 = findViewById(R.id.cardTask5);

        ivCheck1 = findViewById(R.id.ivCheck1);
        ivCheck2 = findViewById(R.id.ivCheck2);
        ivCheck3 = findViewById(R.id.ivCheck3);
        ivCheck4 = findViewById(R.id.ivCheck4);
        ivCheck5 = findViewById(R.id.ivCheck5);

        tvTitleTask1 = findViewById(R.id.tvTitleTask1);
        tvTitleTask2 = findViewById(R.id.tvTitleTask2);
        tvTitleTask3 = findViewById(R.id.tvTitleTask3);
        tvTitleTask4 = findViewById(R.id.tvTitleTask4);
        tvTitleTask5 = findViewById(R.id.tvTitleTask5);

        tvSectionCategories = findViewById(R.id.tvSectionCategories);
        cardCat1 = findViewById(R.id.cardCat1);
        cardCat2 = findViewById(R.id.cardCat2);
        cardCat3 = findViewById(R.id.cardCat3);
        cardCat4 = findViewById(R.id.cardCat4);
    }

    private void setupClickListeners() {
        if (cardTask1 != null) cardTask1.setOnClickListener(v -> toggleTask(0, cardTask1, ivCheck1, tvTitleTask1));
        if (cardTask2 != null) cardTask2.setOnClickListener(v -> toggleTask(1, cardTask2, ivCheck2, tvTitleTask2));
        if (cardTask3 != null) cardTask3.setOnClickListener(v -> toggleTask(2, cardTask3, ivCheck3, tvTitleTask3));
        if (cardTask4 != null) cardTask4.setOnClickListener(v -> toggleTask(3, cardTask4, ivCheck4, tvTitleTask4));
        if (cardTask5 != null) cardTask5.setOnClickListener(v -> toggleTask(4, cardTask5, ivCheck5, tvTitleTask5));
    }

    private void loadChecklistState() {
        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        taskStates[0] = prefs.getBoolean("cb_1", true);
        taskStates[1] = prefs.getBoolean("cb_2", true);
        taskStates[2] = prefs.getBoolean("cb_3", true);
        taskStates[3] = prefs.getBoolean("cb_4", false);
        taskStates[4] = prefs.getBoolean("cb_5", true);

        updateTaskVisuals(ivCheck1, tvTitleTask1, taskStates[0]);
        updateTaskVisuals(ivCheck2, tvTitleTask2, taskStates[1]);
        updateTaskVisuals(ivCheck3, tvTitleTask3, taskStates[2]);
        updateTaskVisuals(ivCheck4, tvTitleTask4, taskStates[3]);
        updateTaskVisuals(ivCheck5, tvTitleTask5, taskStates[4]);

        updateScoreDisplay(false);
    }

    private void saveChecklistState() {
        SharedPreferences.Editor editor = getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit();
        editor.putBoolean("cb_1", taskStates[0]);
        editor.putBoolean("cb_2", taskStates[1]);
        editor.putBoolean("cb_3", taskStates[2]);
        editor.putBoolean("cb_4", taskStates[3]);
        editor.putBoolean("cb_5", taskStates[4]);
        editor.apply();
    }

    private void toggleTask(int index, MaterialCardView card, ImageView ivCheck, TextView tvTitle) {
        taskStates[index] = !taskStates[index];
        saveChecklistState();

        // 1. Tactile spring bounce animation on the card
        animateCardBounce(card);

        // 2. Pop checkmark icon animation
        animateCheckIcon(ivCheck, taskStates[index]);

        // 3. Update title visual (color / styling)
        updateTaskVisuals(ivCheck, tvTitle, taskStates[index]);

        // 4. Smooth animated progress bar & score update
        updateScoreDisplay(true);
    }

    private void updateTaskVisuals(ImageView ivCheck, TextView tvTitle, boolean isChecked) {
        if (ivCheck != null) {
            ivCheck.setImageResource(isChecked ? R.drawable.ic_check_circle_24 : R.drawable.ic_circle_outline_24);
        }
        if (tvTitle != null) {
            tvTitle.setTextColor(getResources().getColor(isChecked ? R.color.color_primary : R.color.color_text_primary));
        }
    }

    private void animateCardBounce(View card) {
        if (card == null) return;
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.96f, 1.02f, 1.0f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.96f, 1.02f, 1.0f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(card, scaleX, scaleY);
        animator.setDuration(320);
        animator.setInterpolator(new OvershootInterpolator(1.8f));
        animator.start();
    }

    private void animateCheckIcon(ImageView ivCheck, boolean isChecked) {
        if (ivCheck == null) return;
        ivCheck.setScaleX(0.4f);
        ivCheck.setScaleY(0.4f);
        ivCheck.setRotation(-45f);

        ivCheck.animate()
                .scaleX(1.0f)
                .scaleY(1.0f)
                .rotation(0f)
                .setDuration(300)
                .setInterpolator(new OvershootInterpolator(2.2f))
                .start();
    }

    private void updateScoreDisplay(boolean animate) {
        int completedCount = 0;
        for (boolean state : taskStates) {
            if (state) completedCount++;
        }

        int targetPercentage = (completedCount * 100) / taskStates.length;

        if (tvChecklistScore != null) {
            tvChecklistScore.setText(targetPercentage + "% Protected (" + completedCount + "/5)");
            if (completedCount == 5) {
                tvChecklistScore.setTextColor(getResources().getColor(R.color.color_success));
                tvChecklistScore.setText("100% Fully Protected ✓");
            } else {
                tvChecklistScore.setTextColor(getResources().getColor(R.color.color_primary));
            }
        }

        if (progressSafety != null) {
            if (animate) {
                ObjectAnimator progressAnim = ObjectAnimator.ofInt(progressSafety, "progress", currentProgress, targetPercentage);
                progressAnim.setDuration(450);
                progressAnim.setInterpolator(new DecelerateInterpolator());
                progressAnim.start();
            } else {
                progressSafety.setProgress(targetPercentage);
            }
        }
        currentProgress = targetPercentage;
    }

    private void playStaggeredEntranceAnimation() {
        View[] animatedViews = {
                cardProgressHeader,
                tvSectionTasks,
                cardTask1,
                cardTask2,
                cardTask3,
                cardTask4,
                cardTask5,
                tvSectionCategories,
                cardCat1,
                cardCat2,
                cardCat3,
                cardCat4
        };

        long delay = 50;
        for (View view : animatedViews) {
            if (view != null) {
                view.setAlpha(0f);
                view.setTranslationY(40f);

                view.animate()
                        .alpha(1f)
                        .translationY(0f)
                        .setDuration(420)
                        .setStartDelay(delay)
                        .setInterpolator(new DecelerateInterpolator(1.4f))
                        .start();

                delay += 55;
            }
        }
    }
}
