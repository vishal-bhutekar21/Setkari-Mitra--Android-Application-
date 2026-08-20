package com.example.Shetkari_Mitra;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class GovtCompensationActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_govt_compensation);

        ImageButton btnGlassBack = findViewById(R.id.btnGlassBack);
        if (btnGlassBack != null) {
            btnGlassBack.setOnClickListener(v -> finish());
        }

        View btnOpenMahaDbtScheme = findViewById(R.id.btnOpenMahaDbtScheme);
        if (btnOpenMahaDbtScheme != null) {
            btnOpenMahaDbtScheme.setOnClickListener(v -> {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mahadbt.maharashtra.gov.in"));
                startActivity(browserIntent);
            });
        }

        View btnOpenPmfby = findViewById(R.id.btnOpenPmfby);
        if (btnOpenPmfby != null) {
            btnOpenPmfby.setOnClickListener(v -> {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://pmfby.gov.in"));
                startActivity(browserIntent);
            });
        }

        View btnOpenMjpjay = findViewById(R.id.btnOpenMjpjay);
        if (btnOpenMjpjay != null) {
            btnOpenMjpjay.setOnClickListener(v -> {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://jeevandayee.gov.in"));
                startActivity(browserIntent);
            });
        }

        View btnCallForestHelpline = findViewById(R.id.btnCallForestHelpline);
        if (btnCallForestHelpline != null) {
            btnCallForestHelpline.setOnClickListener(v -> {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:1926"));
                startActivity(callIntent);
            });
        }

        View btnCallPashuHelpline = findViewById(R.id.btnCallPashuHelpline);
        if (btnCallPashuHelpline != null) {
            btnCallPashuHelpline.setOnClickListener(v -> {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:1962"));
                startActivity(callIntent);
            });
        }

        MaterialButton btnCallKisanCallCenter = findViewById(R.id.btnCallKisanCallCenter);
        if (btnCallKisanCallCenter != null) {
            btnCallKisanCallCenter.setOnClickListener(v -> {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:18001801551"));
                startActivity(callIntent);
            });
        }

        BottomNavigationHelper.setupBottomNavigation(this, findViewById(R.id.bottom_navigation), R.id.bottom_nav_schemes);
    }
}
