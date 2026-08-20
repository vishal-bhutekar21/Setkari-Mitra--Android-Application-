package com.example.Shetkari_Mitra;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String PREF_NAME = "shetkari_prefs";
    private static final String KEY_SAVED_USERNAME = "saved_username";
    private static final String KEY_SAVED_EMAIL = "saved_email";

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    private TextView locationTextView;
    private TextView userNameTextView;
    private TextView userEmailTextView;
    private TextView tvGreeting;

    private View cardSnakeLib, cardNearHospital;
    private View cardResRegistration, cardSnakeRescuers;
    private View cardMythsFacts, cardInsectsCreatures;
    private View cardGovtCompensation, cardGovtPortals, cardSafetyLearning, cardEmergencyContacts, cardStatusProtection;
    private ImageButton btnMenuDrawer, btnVoiceAssistant;

    private final ActivityResultLauncher<Intent> speechRecognitionLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    java.util.ArrayList<String> matches = result.getData().getStringArrayListExtra(android.speech.RecognizerIntent.EXTRA_RESULTS);
                    if (matches != null && !matches.isEmpty()) {
                        String spokenText = matches.get(0);
                        Toast.makeText(this, "Voice: \"" + spokenText + "\"", Toast.LENGTH_SHORT).show();
                        VoiceAssistantHelper.VoiceIntent voiceIntent = VoiceAssistantHelper.parseSpokenQuery(spokenText);
                        VoiceAssistantHelper.executeVoiceIntent(this, voiceIntent);
                    }
                }
            });

    private FusedLocationProviderClient fusedLocationClient;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    private final ActivityResultLauncher<String[]> locationPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
                Boolean fineGranted = result.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false);
                Boolean coarseGranted = result.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false);
                if ((fineGranted != null && fineGranted) || (coarseGranted != null && coarseGranted)) {
                    fetchCurrentLocation();
                } else {
                    locationTextView.setText(R.string.location_permission_denied);
                }
            });

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();
        setupNavigationHeader();
        setupCardClickListeners();
        setupBottomNavigation();
        playDashboardCascadingAnimations();

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        checkAndRequestLocation();
    }

    private void playDashboardCascadingAnimations() {
        View[] dashboardViews = {
                cardStatusProtection,
                cardSnakeLib,
                cardSafetyLearning,
                cardNearHospital,
                cardEmergencyContacts,
                cardSnakeRescuers,
                cardResRegistration,
                cardGovtCompensation,
                cardMythsFacts,
                cardInsectsCreatures,
                cardGovtPortals
        };

        long delay = 40;
        for (View view : dashboardViews) {
            if (view != null) {
                view.setAlpha(0f);
                view.setTranslationY(35f);

                view.animate()
                        .alpha(1f)
                        .translationY(0f)
                        .setDuration(400)
                        .setStartDelay(delay)
                        .setInterpolator(new DecelerateInterpolator(1.3f))
                        .start();

                delay += 45;
            }
        }
    }

    private void initViews() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        locationTextView = findViewById(R.id.locationTextView);
        tvGreeting = findViewById(R.id.tvGreeting);

        btnMenuDrawer = findViewById(R.id.btnMenuDrawer);
        btnVoiceAssistant = findViewById(R.id.btnVoiceAssistant);

        cardNearHospital = findViewById(R.id.nearhospital);
        cardSnakeRescuers = findViewById(R.id.snake_rescuer);

        cardSnakeLib = findViewById(R.id.snake_library);
        cardMythsFacts = findViewById(R.id.myths_fact);
        cardInsectsCreatures = findViewById(R.id.cardHarmfulCreatures);
        cardResRegistration = findViewById(R.id.cardResRegistration);
        cardGovtCompensation = findViewById(R.id.cardGovtCompensation);
        cardGovtPortals = findViewById(R.id.cardGovtPortals);
        cardSafetyLearning = findViewById(R.id.cardSafetyLearning);
        cardEmergencyContacts = findViewById(R.id.cardEmergencyContacts);
        cardStatusProtection = findViewById(R.id.cardStatusProtection);

        if (btnMenuDrawer != null) {
            btnMenuDrawer.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        }

        if (btnVoiceAssistant != null) {
            btnVoiceAssistant.setOnClickListener(v -> launchVoiceAssistant());
        }

        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setupNavigationHeader() {
        View headerView = navigationView.getHeaderView(0);
        userNameTextView = headerView.findViewById(R.id.user_name);
        userEmailTextView = headerView.findViewById(R.id.user_email);
        View btnDrawerClose = headerView.findViewById(R.id.btnDrawerClose);

        if (btnDrawerClose != null) {
            btnDrawerClose.setOnClickListener(v -> drawerLayout.closeDrawer(GravityCompat.START));
        }

        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String username = prefs.getString(KEY_SAVED_USERNAME, "Shetkari Mitra");
        String email = prefs.getString(KEY_SAVED_EMAIL, "Farmer Companion");

        if (userNameTextView != null) userNameTextView.setText(username);
        if (userEmailTextView != null) userEmailTextView.setText(email);
        if (tvGreeting != null) tvGreeting.setText(getString(R.string.home_greeting_namaste) + ", " + username);
    }

    private void setupCardClickListeners() {
        if (cardNearHospital != null) cardNearHospital.setOnClickListener(v -> animateAndLaunch(v, Near_By_Hospitals.class));
        if (cardSnakeRescuers != null) cardSnakeRescuers.setOnClickListener(v -> animateAndLaunch(v, RescuerDatabaseActivity.class));
        if (cardSnakeLib != null) cardSnakeLib.setOnClickListener(v -> animateAndLaunch(v, MainActivity.class));
        if (cardMythsFacts != null) cardMythsFacts.setOnClickListener(v -> animateAndLaunch(v, Activity_Myths_Facts.class));
        if (cardInsectsCreatures != null) cardInsectsCreatures.setOnClickListener(v -> animateAndLaunch(v, HarmfulCreaturesActivity.class));
        if (cardResRegistration != null) cardResRegistration.setOnClickListener(v -> animateAndLaunch(v, Registration_example.class));

        if (cardGovtCompensation != null) cardGovtCompensation.setOnClickListener(v -> animateAndLaunch(v, GovtCompensationActivity.class));
        if (cardGovtPortals != null) cardGovtPortals.setOnClickListener(v -> animateAndLaunch(v, GovtPortalsActivity.class));
        if (cardSafetyLearning != null) cardSafetyLearning.setOnClickListener(v -> animateAndLaunch(v, SafetyLearningActivity.class));
        if (cardEmergencyContacts != null) cardEmergencyContacts.setOnClickListener(v -> animateAndLaunch(v, nav_Emergency_Contacts.class));
        if (cardStatusProtection != null) cardStatusProtection.setOnClickListener(v -> animateAndLaunch(v, Near_By_Hospitals.class));
    }

    private void animateAndLaunch(View view, Class<?> targetActivity) {
        if (view == null) {
            startActivity(new Intent(this, targetActivity));
            return;
        }

        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.96f, 1.02f, 1.0f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.96f, 1.02f, 1.0f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, scaleX, scaleY);
        animator.setDuration(280);
        animator.setInterpolator(new OvershootInterpolator(1.8f));
        animator.start();

        view.postDelayed(() -> startActivity(new Intent(this, targetActivity)), 120);
    }

    private void openEmergencyMode() {
        Intent intent = new Intent(this, EmergencyActivity.class);
        startActivity(intent);
    }

    private void launchVoiceAssistant() {
        Intent voiceIntent = new Intent(android.speech.RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        voiceIntent.putExtra(android.speech.RecognizerIntent.EXTRA_LANGUAGE_MODEL, android.speech.RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        voiceIntent.putExtra(android.speech.RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        voiceIntent.putExtra(android.speech.RecognizerIntent.EXTRA_PROMPT, "Speak command (e.g., 'Hospital', 'Schemes', 'Emergency')");
        try {
            speechRecognitionLauncher.launch(voiceIntent);
        } catch (Exception e) {
            Toast.makeText(this, "Voice recognition not supported on this device", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupBottomNavigation() {
        bottomNavigationView.setSelectedItemId(R.id.bottom_nav_home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.bottom_nav_home) {
                return true;
            } else if (id == R.id.bottom_nav_emergency) {
                openEmergencyMode();
                return true;
            } else if (id == R.id.bottom_nav_safety) {
                startActivity(new Intent(HomeActivity.this, SafetyCenterActivity.class));
                return true;
            } else if (id == R.id.bottom_nav_profile) {
                startActivity(new Intent(HomeActivity.this, nav_Emergency_Contacts.class));
                return true;
            }
            return false;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (bottomNavigationView != null) {
            bottomNavigationView.setSelectedItemId(R.id.bottom_nav_home);
        }
    }

    private void checkAndRequestLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fetchCurrentLocation();
        } else {
            locationPermissionLauncher.launch(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            });
        }
    }

    private void fetchCurrentLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        fusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
            if (location != null) {
                resolveAddressFromLocation(location);
            } else {
                locationTextView.setText(R.string.location_jalna_default);
            }
        }).addOnFailureListener(e -> locationTextView.setText(R.string.location_jalna_default));
    }

    private void resolveAddressFromLocation(Location location) {
        executorService.execute(() -> {
            String addressText = getString(R.string.location_jalna_default);
            try {
                Geocoder geocoder = new Geocoder(HomeActivity.this, Locale.getDefault());
                List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                if (addresses != null && !addresses.isEmpty()) {
                    Address addr = addresses.get(0);
                    StringBuilder sb = new StringBuilder();
                    if (addr.getLocality() != null) {
                        sb.append(addr.getLocality());
                    } else if (addr.getSubAdminArea() != null) {
                        sb.append(addr.getSubAdminArea());
                    }
                    if (addr.getAdminArea() != null) {
                        if (sb.length() > 0) sb.append(", ");
                        sb.append(addr.getAdminArea());
                    }
                    if (sb.length() > 0) {
                        addressText = sb.toString();
                    }
                }
            } catch (IOException e) {
                // Fallback to coordinates
                addressText = String.format(Locale.US, "Lat: %.3f, Lng: %.3f", location.getLatitude(), location.getLongitude());
            }

            final String finalText = addressText;
            mainHandler.post(() -> locationTextView.setText(finalText));
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_language) {
            showLanguageDialog();
        } else if (id == R.id.nav_theme) {
            showThemeDialog();
        } else if (id == R.id.nav_share) {
            shareApp();
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(this, Activity_About_Us.class));
        } else if (id == R.id.nav_logout) {
            showLogoutDialog();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showLogoutDialog() {
        new com.google.android.material.dialog.MaterialAlertDialogBuilder(this)
                .setTitle(R.string.logout)
                .setMessage(R.string.logout_confirm_msg)
                .setPositiveButton(R.string.logout, (dialog, which) -> performLogout())
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    private void performLogout() {
        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        prefs.edit()
                .remove(KEY_SAVED_USERNAME)
                .remove(KEY_SAVED_EMAIL)
                .putBoolean("is_logged_in", false)
                .apply();

        Toast.makeText(this, R.string.logout, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(HomeActivity.this, Start_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void showLanguageDialog() {
        String[] languages = {"English", "मराठी (Marathi)", "हिंदी (Hindi)"};
        String[] langCodes = {LocaleHelper.LANGUAGE_ENGLISH, LocaleHelper.LANGUAGE_MARATHI, LocaleHelper.LANGUAGE_HINDI};

        new com.google.android.material.dialog.MaterialAlertDialogBuilder(this)
                .setTitle("Select Language / भाषा निवडा")
                .setItems(languages, (dialog, which) -> {
                    String selectedLang = langCodes[which];
                    LocaleHelper.setLocale(this, selectedLang);
                    recreate();
                })
                .show();
    }

    private void showThemeDialog() {
        String[] themes = {"Light Mode", "Dark Mode", "System Default"};
        new com.google.android.material.dialog.MaterialAlertDialogBuilder(this)
                .setTitle("Select Theme")
                .setItems(themes, (dialog, which) -> {
                    if (which == 0) {
                        androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO);
                    } else if (which == 1) {
                        androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES);
                    } else {
                        androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                    }
                })
                .show();
    }

    private void shareApp() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Download Shetkari Mitra - Farmer Companion and Snakebite Safety App: https://github.com/vishal-bhutekar21/Setkari-Mitra--Android-Application-");
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}
