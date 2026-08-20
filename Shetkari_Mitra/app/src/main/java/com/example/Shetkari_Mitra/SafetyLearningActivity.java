package com.example.Shetkari_Mitra;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SafetyLearningActivity extends AppCompatActivity {

    private Button btnWatchBig4, btnWatchFirstAid;
    private Button btnQ1True, btnQ1False, btnQ2True, btnQ2False;
    private TextView tvQ1Feedback, tvQ2Feedback;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_learning);

        View btnBack = findViewById(R.id.btnBack);
        if (btnBack != null) btnBack.setOnClickListener(v -> finish());

        btnWatchBig4 = findViewById(R.id.btnWatchBig4);
        btnWatchFirstAid = findViewById(R.id.btnWatchFirstAid);

        btnQ1True = findViewById(R.id.btnQ1True);
        btnQ1False = findViewById(R.id.btnQ1False);
        tvQ1Feedback = findViewById(R.id.tvQ1Feedback);

        btnQ2True = findViewById(R.id.btnQ2True);
        btnQ2False = findViewById(R.id.btnQ2False);
        tvQ2Feedback = findViewById(R.id.tvQ2Feedback);

        if (btnWatchBig4 != null) {
            btnWatchBig4.setOnClickListener(v -> openVideoUrl("https://www.youtube.com/results?search_query=big+4+venomous+snakes+india+identification"));
        }

        if (btnWatchFirstAid != null) {
            btnWatchFirstAid.setOnClickListener(v -> openVideoUrl("https://www.youtube.com/results?search_query=snake+bite+first+aid+doctor+protocol+india"));
        }

        // Q1 Logic
        if (btnQ1True != null) {
            btnQ1True.setOnClickListener(v -> {
                if (tvQ1Feedback != null) {
                    tvQ1Feedback.setText("❌ WRONG: Tight tourniquets stop blood flow and cause gangrene or limb loss. Keep the limb immobilized gently!");
                    tvQ1Feedback.setTextColor(getColor(R.color.color_emergency));
                }
            });
        }

        if (btnQ1False != null) {
            btnQ1False.setOnClickListener(v -> {
                if (tvQ1Feedback != null) {
                    tvQ1Feedback.setText("✅ CORRECT! Never tie a tight tourniquet. Simply immobilize the limb and reach a hospital immediately.");
                    tvQ1Feedback.setTextColor(getColor(R.color.color_success));
                }
            });
        }

        // Q2 Logic
        if (btnQ2True != null) {
            btnQ2True.setOnClickListener(v -> {
                if (tvQ2Feedback != null) {
                    tvQ2Feedback.setText("❌ WRONG: Tantriks, herbal stones, or quacks cannot neutralize snake venom. Only Anti-Snake Venom (ASV) saves lives.");
                    tvQ2Feedback.setTextColor(getColor(R.color.color_emergency));
                }
            });
        }

        if (btnQ2False != null) {
            btnQ2False.setOnClickListener(v -> {
                if (tvQ2Feedback != null) {
                    tvQ2Feedback.setText("✅ CORRECT! Only Anti-Snake Venom (ASV) at a certified hospital can neutralize venom.");
                    tvQ2Feedback.setTextColor(getColor(R.color.color_success));
                }
            });
        }
    }

    private void openVideoUrl(String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Cannot launch video player", Toast.LENGTH_SHORT).show();
        }
    }
}
