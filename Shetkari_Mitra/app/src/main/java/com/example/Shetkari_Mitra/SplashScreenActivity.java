package com.example.Shetkari_Mitra;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class SplashScreenActivity extends AppCompatActivity {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 100;
    private boolean locationPermissionGranted = false;
    private static final String PREF_NAME = "MyPrefs";
    private static final String KEY_LOGGED_IN = "isLoggedIn";

    private ImageView appLogo;
    private TextView appName;
    private TextView marathiText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        checkLocationPermission();

        appLogo = findViewById(R.id.applogo);
        appName = findViewById(R.id.appName);
        marathiText = findViewById(R.id.marathiText);


        AnimationSet logoAnimationSet = new AnimationSet(true);
        ScaleAnimation zoomAnimation = new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        zoomAnimation.setDuration(1000);
        zoomAnimation.setInterpolator(new OvershootInterpolator());
        logoAnimationSet.addAnimation(zoomAnimation);
        appLogo.setVisibility(View.VISIBLE);
        appLogo.startAnimation(logoAnimationSet);

        appName.setTypeface(null, Typeface.BOLD);

        Animation marathiGlowAnimation = new AlphaAnimation(0.0f, 1.0f);
        marathiGlowAnimation.setDuration(1000);
        marathiGlowAnimation.setRepeatMode(Animation.REVERSE);
        marathiGlowAnimation.setRepeatCount(Animation.INFINITE);
        appName.setVisibility(View.VISIBLE);
        marathiText.setVisibility(View.VISIBLE);
        marathiText.startAnimation(marathiGlowAnimation);


    }


    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            // Location permission granted
            locationPermissionGranted = true;
            continueWithHandler();
        }
    }private void continueWithHandler() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean(KEY_LOGGED_IN, false);

        new Handler().postDelayed(() -> {
            Intent intent;
            if (isLoggedIn) {
                intent = new Intent(SplashScreenActivity.this, HomeActivity.class);
            } else {
                intent = new Intent(SplashScreenActivity.this, Start_Activity.class);
            }
            startActivity(intent);
            finish();
        }, 2500);

    }


    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Location permission granted
                locationPermissionGranted = true;
                continueWithHandler();
            } else {
                // Location permission denied, show dialog
                showLocationPermissionDialog();
            }
        }
    }




    private void showLocationPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_location_permission, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        Button btnGoToSettings = dialogView.findViewById(R.id.btnGoToSettings);
        btnGoToSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open app settings
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        Button btnCancel = dialogView.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Close the app
                finish();
            }
        });

        dialog.setCancelable(false);
        dialog.show();
    }


}
