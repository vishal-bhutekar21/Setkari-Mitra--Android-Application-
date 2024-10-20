package com.example.Shetkari_Mitra;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class Activity_About_Us extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        // Initialize views
        ImageView appLogo = findViewById(R.id.app_logo);
        TextView appInfo = findViewById(R.id.app_info);
        TextView contactUs = findViewById(R.id.contact_us);

        // Set app logo
        appLogo.setImageResource(R.drawable.app_logo);

        // Set app info
        String appInfoText = "Shetkari Mitra\n\nVersion 1.0\n\n\nDeveloped by:\n 1) Vishal Bhutekar\n2) Sonali Jadhav\n 3) Gaurav Sapkal";
        appInfo.setText(appInfoText);

        // Set contact info
        String contactText = "Contact Us:\n sapkalgaurav98@gmail.com\n vishalbhutekar1@gmail.com";
        contactUs.setText(contactText);

        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                composeEmail(contactText);
            }
        });
    }

    // Method to compose an email
    private void composeEmail(String emailAddress) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + emailAddress)); // only email apps should handle this
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
