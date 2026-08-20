package com.example.Shetkari_Mitra;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SafetyCenterActivity extends AppCompatActivity {

    private static final String PREF_NAME = "safety_checklist_prefs";
    private CheckBox cbItem1, cbItem2, cbItem3, cbItem4, cbItem5;
    private TextView tvChecklistScore;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_center);

        View btnBack = findViewById(R.id.btnBack);
        if (btnBack != null) btnBack.setOnClickListener(v -> finish());

        cbItem1 = findViewById(R.id.cbItem1);
        cbItem2 = findViewById(R.id.cbItem2);
        cbItem3 = findViewById(R.id.cbItem3);
        cbItem4 = findViewById(R.id.cbItem4);
        cbItem5 = findViewById(R.id.cbItem5);
        tvChecklistScore = findViewById(R.id.tvChecklistScore);

        loadChecklistState();

        View.OnClickListener checkListener = v -> {
            saveChecklistState();
            updateScoreDisplay();
        };

        if (cbItem1 != null) cbItem1.setOnClickListener(checkListener);
        if (cbItem2 != null) cbItem2.setOnClickListener(checkListener);
        if (cbItem3 != null) cbItem3.setOnClickListener(checkListener);
        if (cbItem4 != null) cbItem4.setOnClickListener(checkListener);
        if (cbItem5 != null) cbItem5.setOnClickListener(checkListener);
    }

    private void loadChecklistState() {
        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        if (cbItem1 != null) cbItem1.setChecked(prefs.getBoolean("cb_1", true));
        if (cbItem2 != null) cbItem2.setChecked(prefs.getBoolean("cb_2", true));
        if (cbItem3 != null) cbItem3.setChecked(prefs.getBoolean("cb_3", true));
        if (cbItem4 != null) cbItem4.setChecked(prefs.getBoolean("cb_4", false));
        if (cbItem5 != null) cbItem5.setChecked(prefs.getBoolean("cb_5", true));

        updateScoreDisplay();
    }

    private void saveChecklistState() {
        SharedPreferences.Editor editor = getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit();
        if (cbItem1 != null) editor.putBoolean("cb_1", cbItem1.isChecked());
        if (cbItem2 != null) editor.putBoolean("cb_2", cbItem2.isChecked());
        if (cbItem3 != null) editor.putBoolean("cb_3", cbItem3.isChecked());
        if (cbItem4 != null) editor.putBoolean("cb_4", cbItem4.isChecked());
        if (cbItem5 != null) editor.putBoolean("cb_5", cbItem5.isChecked());
        editor.apply();
    }

    private void updateScoreDisplay() {
        int count = 0;
        if (cbItem1 != null && cbItem1.isChecked()) count++;
        if (cbItem2 != null && cbItem2.isChecked()) count++;
        if (cbItem3 != null && cbItem3.isChecked()) count++;
        if (cbItem4 != null && cbItem4.isChecked()) count++;
        if (cbItem5 != null && cbItem5.isChecked()) count++;

        if (tvChecklistScore != null) {
            tvChecklistScore.setText(count + " / 5 Completed");
        }
    }
}
