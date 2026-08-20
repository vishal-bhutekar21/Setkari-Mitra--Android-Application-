package com.example.Shetkari_Mitra;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class Activity_About_Us extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        View btnGlassBack = findViewById(R.id.btnGlassBack);
        if (btnGlassBack != null) {
            btnGlassBack.setOnClickListener(v -> finish());
        }

        TextView appInfo = findViewById(R.id.app_info);



        if (appInfo != null) {
            appInfo.setText(R.string.about_us_info_text);
        }

        View btnDevPortfolio = findViewById(R.id.btnDevPortfolio);
        if (btnDevPortfolio != null) {
            btnDevPortfolio.setOnClickListener(v -> openWebUrl("https://vishalbhutekar.netlify.app"));
        }

        View btnDevGithub = findViewById(R.id.btnDevGithub);
        if (btnDevGithub != null) {
            btnDevGithub.setOnClickListener(v -> openWebUrl("https://github.com/vishal-bhutekar21"));
        }

        View btnDevLinkedin = findViewById(R.id.btnDevLinkedin);
        if (btnDevLinkedin != null) {
            btnDevLinkedin.setOnClickListener(v -> openWebUrl("https://www.linkedin.com/in/vishal-bhutekar21/"));
        }

        View btnDevEmail = findViewById(R.id.btnDevEmail);
        if (btnDevEmail != null) {
            btnDevEmail.setOnClickListener(v -> composeEmail("vishalbhutekar33772@gmail.com"));
        }

        View btnViewPrivacyPolicy = findViewById(R.id.btnViewPrivacyPolicy);
        if (btnViewPrivacyPolicy != null) {
            btnViewPrivacyPolicy.setOnClickListener(v -> showPrivacyPolicyDialog());
        }
    }

    private void openWebUrl(String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
        }
    }

    private void composeEmail(String emailAddress) {
        try {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:" + emailAddress));
            intent.putExtra(Intent.EXTRA_SUBJECT, "Shetkari Mitra App Feedback & Connect");
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Email: " + emailAddress, Toast.LENGTH_SHORT).show();
        }
    }

    private void showPrivacyPolicyDialog() {
        new MaterialAlertDialogBuilder(this)
                .setTitle(R.string.privacy_policy_title)
                .setMessage(R.string.privacy_policy_content)
                .setIcon(R.drawable.ic_drawer_privacy)
                .setPositiveButton(R.string.btn_agree, null)
                .show();
    }
}
