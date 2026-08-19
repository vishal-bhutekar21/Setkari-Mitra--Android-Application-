package com.example.Shetkari_Mitra;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Activity_About_Us extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        View btnGlassBack = findViewById(R.id.btnGlassBack);
        if (btnGlassBack != null) {
            btnGlassBack.setOnClickListener(v -> finish());
        }

        ImageView appLogo = findViewById(R.id.app_logo);
        TextView appInfo = findViewById(R.id.app_info);
        TextView contactUs = findViewById(R.id.contact_us);

        if (appLogo != null) {
            appLogo.setImageResource(R.drawable.app_logo);
        }

        if (appInfo != null) {
            appInfo.setText(R.string.about_us_info_text);
        }

        if (contactUs != null) {
            contactUs.setText(R.string.contact_us_emails);
            contactUs.setOnClickListener(v -> composeEmail("vishalbhutekar1@gmail.com"));
        }
    }

    private void composeEmail(String emailAddress) {
        try {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:" + emailAddress));
            intent.putExtra(Intent.EXTRA_SUBJECT, "Shetkari Mitra Safety App Feedback");
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Email: " + emailAddress, Toast.LENGTH_SHORT).show();
        }
    }
}
