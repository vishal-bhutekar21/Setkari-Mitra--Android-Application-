package com.example.Shetkari_Mitra;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class EmergencyActivity extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationClient;
    private Location lastKnownLocation;

    private MaterialCardView cardGoldenHour, cardStep1, cardStep2, cardStep3, cardStep4, cardDoNot;
    private View tvSectionSteps, persistentEmergencyActions;
    private MaterialButton btnCallAmbulance;

    private final ActivityResultLauncher<String[]> locationPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
                Boolean fineGranted = result.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false);
                Boolean coarseGranted = result.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false);
                if ((fineGranted != null && fineGranted) || (coarseGranted != null && coarseGranted)) {
                    fetchLocationAndShare();
                } else {
                    shareFallbackLocation();
                }
            });

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        initViews();
        setupClickListeners();
        playStaggeredEntranceAnimation();
    }

    private void initViews() {
        View btnBack = findViewById(R.id.btnBack);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        btnCallAmbulance = findViewById(R.id.btnCallAmbulance);
        if (btnCallAmbulance != null) {
            btnCallAmbulance.setOnClickListener(v -> showAmbulanceDialOptions());
        }

        View btnFindHospitals = findViewById(R.id.btnFindHospitals);
        if (btnFindHospitals != null) {
            btnFindHospitals.setOnClickListener(v -> {
                Intent intent = new Intent(this, Near_By_Hospitals.class);
                startActivity(intent);
            });
        }

        View btnShareSosLocation = findViewById(R.id.btnShareSosLocation);
        if (btnShareSosLocation != null) {
            btnShareSosLocation.setOnClickListener(v -> handleShareLocation());
        }

        cardGoldenHour = findViewById(R.id.cardGoldenHour);
        tvSectionSteps = findViewById(R.id.tvSectionSteps);
        cardStep1 = findViewById(R.id.cardStep1);
        cardStep2 = findViewById(R.id.cardStep2);
        cardStep3 = findViewById(R.id.cardStep3);
        cardStep4 = findViewById(R.id.cardStep4);
        cardDoNot = findViewById(R.id.cardDoNot);
        persistentEmergencyActions = findViewById(R.id.persistentEmergencyActions);
    }

    private void setupClickListeners() {
        View.OnClickListener bounceListener = this::animateCardBounce;
        if (cardStep1 != null) cardStep1.setOnClickListener(bounceListener);
        if (cardStep2 != null) cardStep2.setOnClickListener(bounceListener);
        if (cardStep3 != null) cardStep3.setOnClickListener(bounceListener);
        if (cardStep4 != null) cardStep4.setOnClickListener(bounceListener);
        if (cardDoNot != null) cardDoNot.setOnClickListener(bounceListener);
        if (cardGoldenHour != null) cardGoldenHour.setOnClickListener(bounceListener);
    }

    private void animateCardBounce(View view) {
        if (view == null) return;
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.96f, 1.02f, 1.0f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.96f, 1.02f, 1.0f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, scaleX, scaleY);
        animator.setDuration(300);
        animator.setInterpolator(new OvershootInterpolator(1.8f));
        animator.start();
    }

    private void playStaggeredEntranceAnimation() {
        View[] animatedViews = {
                cardGoldenHour,
                tvSectionSteps,
                cardStep1,
                cardStep2,
                cardStep3,
                cardStep4,
                cardDoNot,
                persistentEmergencyActions
        };

        long delay = 50;
        for (View view : animatedViews) {
            if (view != null) {
                view.setAlpha(0f);
                view.setTranslationY(40f);

                view.animate()
                        .alpha(1f)
                        .translationY(0f)
                        .setDuration(400)
                        .setStartDelay(delay)
                        .setInterpolator(new DecelerateInterpolator(1.4f))
                        .start();

                delay += 50;
            }
        }

        // Gentle breathing pulse on ambulance button
        if (btnCallAmbulance != null) {
            btnCallAmbulance.postDelayed(() -> {
                PropertyValuesHolder pulseX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 1.03f, 1.0f);
                PropertyValuesHolder pulseY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 1.03f, 1.0f);
                ObjectAnimator pulseAnim = ObjectAnimator.ofPropertyValuesHolder(btnCallAmbulance, pulseX, pulseY);
                pulseAnim.setDuration(1200);
                pulseAnim.setRepeatCount(ObjectAnimator.INFINITE);
                pulseAnim.setRepeatMode(ObjectAnimator.REVERSE);
                pulseAnim.start();
            }, 600);
        }
    }

    private void showAmbulanceDialOptions() {
        new MaterialAlertDialogBuilder(this)
                .setTitle(R.string.emergency_call_title)
                .setMessage("Directly connect to National Medical Emergency:")
                .setPositiveButton("Call 108 (Ambulance)", (d, w) -> dialNumber("108"))
                .setNeutralButton("Call 112 (Police & Emergency)", (d, w) -> dialNumber("112"))
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    private void dialNumber(String number) {
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        dialIntent.setData(Uri.parse("tel:" + number));
        startActivity(dialIntent);
    }

    private void handleShareLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fetchLocationAndShare();
        } else {
            locationPermissionLauncher.launch(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            });
        }
    }

    private void fetchLocationAndShare() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            shareFallbackLocation();
            return;
        }

        fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
                .addOnSuccessListener(this, location -> {
                    if (location != null) {
                        lastKnownLocation = location;
                        dispatchSosMessage(location.getLatitude(), location.getLongitude());
                    } else {
                        fusedLocationClient.getLastLocation().addOnSuccessListener(this, lastLoc -> {
                            if (lastLoc != null) {
                                lastKnownLocation = lastLoc;
                                dispatchSosMessage(lastLoc.getLatitude(), lastLoc.getLongitude());
                            } else {
                                shareFallbackLocation();
                            }
                        });
                    }
                })
                .addOnFailureListener(e -> shareFallbackLocation());
    }

    private void dispatchSosMessage(double lat, double lng) {
        String mapUrl = "https://maps.google.com/?q=" + lat + "," + lng;
        String sosMessage = "🚨 EMERGENCY: Possible Snakebite Reported!\n" +
                "Location: " + mapUrl + "\n" +
                "Coordinates: " + lat + ", " + lng + "\n" +
                "Please arrange urgent anti-venom hospital transport or rescuer help immediately!\n" +
                "- Sent via Shetkari Mitra";

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "EMERGENCY: Snakebite Alert");
        shareIntent.putExtra(Intent.EXTRA_TEXT, sosMessage);
        startActivity(Intent.createChooser(shareIntent, "Share Emergency Location via"));
    }

    private void shareFallbackLocation() {
        String sosMessage = "🚨 EMERGENCY: Possible Snakebite Reported!\n" +
                "Please arrange urgent anti-venom hospital transport or rescuer help immediately!\n" +
                "- Sent via Shetkari Mitra";

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "EMERGENCY: Snakebite Alert");
        shareIntent.putExtra(Intent.EXTRA_TEXT, sosMessage);
        startActivity(Intent.createChooser(shareIntent, "Share Emergency SOS via"));
    }
}
