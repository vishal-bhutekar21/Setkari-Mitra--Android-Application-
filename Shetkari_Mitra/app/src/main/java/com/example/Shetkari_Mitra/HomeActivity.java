package com.example.Shetkari_Mitra;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
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
import com.google.android.gms.location.Priority;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String PREF_NAME = "shetkari_prefs";
    private static final String KEY_LOGGED_IN = "is_logged_in";
    private static final String KEY_SAVED_USERNAME = "saved_username";
    private static final String KEY_SAVED_EMAIL = "saved_email";

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    private TextView locationTextView;
    private TextView userNameTextView;
    private TextView userEmailTextView;
    private TextView tvGreeting;

    private View cardSnakeLib, cardFirstAid, cardNearHospital;
    private View cardIdentifySnake, cardResRegistration, cardSnakeRescuers;
    private View cardEmergencyBtn, cardAboutUsBtn, cardMythsFacts, cardInsectsCreatures;
    private View btnStartEmergencyHelp;
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
        playDashboardAnimations();

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        checkAndRequestLocation();
    }

    private void playDashboardAnimations() {
        android.view.animation.Animation slideAnim = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.slide_up_fade_in);
        if (cardEmergencyBtn != null) {
            cardEmergencyBtn.startAnimation(slideAnim);
        }
        if (cardIdentifySnake != null) {
            cardIdentifySnake.startAnimation(slideAnim);
        }
        if (cardNearHospital != null) {
            cardNearHospital.startAnimation(slideAnim);
        }
        if (cardSnakeRescuers != null) {
            cardSnakeRescuers.startAnimation(slideAnim);
        }
        if (cardFirstAid != null) {
            cardFirstAid.startAnimation(slideAnim);
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

        cardEmergencyBtn = findViewById(R.id.emergency_btn);
        cardNearHospital = findViewById(R.id.nearhospital);
        cardSnakeRescuers = findViewById(R.id.snake_rescuer);

        cardSnakeLib = findViewById(R.id.snake_library);
        cardMythsFacts = findViewById(R.id.myths_fact);
        cardInsectsCreatures = findViewById(R.id.cardHarmfulCreatures);
        cardResRegistration = findViewById(R.id.cardResRegistration);
        View cardGovtCompensation = findViewById(R.id.cardGovtCompensation);
        View cardGovtPortals = findViewById(R.id.cardGovtPortals);
        View cardSafetyLearning = findViewById(R.id.cardSafetyLearning);
        View cardEmergencyContacts = findViewById(R.id.cardEmergencyContacts);
        View cardStatusProtection = findViewById(R.id.cardStatusProtection);

        if (btnMenuDrawer != null) {
            btnMenuDrawer.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        }

        if (btnVoiceAssistant != null) {
            btnVoiceAssistant.setOnClickListener(v -> launchVoiceAssistant());
        }

        navigationView.setNavigationItemSelectedListener(this);

        if (cardGovtCompensation != null) cardGovtCompensation.setOnClickListener(v -> startActivity(new Intent(this, GovtCompensationActivity.class)));
        if (cardGovtPortals != null) cardGovtPortals.setOnClickListener(v -> startActivity(new Intent(this, GovtPortalsActivity.class)));
        if (cardSafetyLearning != null) cardSafetyLearning.setOnClickListener(v -> startActivity(new Intent(this, SafetyLearningActivity.class)));
        if (cardEmergencyContacts != null) cardEmergencyContacts.setOnClickListener(v -> startActivity(new Intent(this, nav_Emergency_Contacts.class)));
        if (cardStatusProtection != null) cardStatusProtection.setOnClickListener(v -> startActivity(new Intent(this, Near_By_Hospitals.class)));
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
        if (cardEmergencyBtn != null) cardEmergencyBtn.setOnClickListener(v -> openEmergencyMode());

        if (cardNearHospital != null) cardNearHospital.setOnClickListener(v -> startActivity(new Intent(this, Near_By_Hospitals.class)));
        if (cardSnakeRescuers != null) cardSnakeRescuers.setOnClickListener(v -> startActivity(new Intent(this, RescuerDatabaseActivity.class)));

        if (cardSnakeLib != null) cardSnakeLib.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));
        if (cardMythsFacts != null) cardMythsFacts.setOnClickListener(v -> startActivity(new Intent(this, Activity_Myths_Facts.class)));
        if (cardInsectsCreatures != null) cardInsectsCreatures.setOnClickListener(v -> startActivity(new Intent(this, HarmfulCreaturesActivity.class)));
        if (cardResRegistration != null) cardResRegistration.setOnClickListener(v -> startActivity(new Intent(this, Registration_example.class)));
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
        if (bottomNavigationView == null) return;
        bottomNavigationView.setSelectedItemId(R.id.bottom_nav_home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.bottom_nav_home) {
                return true;
            } else if (id == R.id.bottom_nav_hospitals) {
                startActivity(new Intent(HomeActivity.this, Near_By_Hospitals.class));
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

        locationTextView.setText(R.string.fetching_location);

        fusedLocationClient.getCurrentLocation(Priority.PRIORITY_BALANCED_POWER_ACCURACY, null)
                .addOnSuccessListener(this, location -> {
                    if (location != null) {
                        reverseGeocode(location);
                    } else {
                        fusedLocationClient.getLastLocation().addOnSuccessListener(this, lastLoc -> {
                            if (lastLoc != null) {
                                reverseGeocode(lastLoc);
                            } else {
                                locationTextView.setText(R.string.location_jalna_default);
                            }
                        });
                    }
                })
                .addOnFailureListener(e -> locationTextView.setText(R.string.location_jalna_default));
    }

    private void reverseGeocode(Location location) {
        executorService.execute(() -> {
            Geocoder geocoder = new Geocoder(HomeActivity.this, Locale.getDefault());
            String addressText = String.format(Locale.getDefault(), "Lat: %.4f, Long: %.4f", location.getLatitude(), location.getLongitude());
            try {
                List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                if (addresses != null && !addresses.isEmpty()) {
                    Address addr = addresses.get(0);
                    StringBuilder sb = new StringBuilder();
                    if (addr.getLocality() != null) sb.append(addr.getLocality()).append(", ");
                    if (addr.getSubAdminArea() != null) sb.append(addr.getSubAdminArea()).append(", ");
                    if (addr.getAdminArea() != null) sb.append(addr.getAdminArea());
                    if (sb.length() > 0) {
                        addressText = sb.toString();
                    }
                }
            } catch (IOException ignored) {
            }

            final String result = addressText;
            mainHandler.post(() -> {
                if (!isFinishing() && locationTextView != null) {
                    locationTextView.setText(result);
                }
            });
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_language) {
            showLanguageDialog();
        } else if (id == R.id.nav_theme) {
            showThemeDialog();
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(this, Activity_About_Us.class));
        } else if (id == R.id.nav_share) {
            shareApp();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showLanguageDialog() {
        String[] languages = {"English", "मराठी (Marathi)", "हिंदी (Hindi)"};
        String currentLang = LocaleHelper.getLanguage(this);
        int checkedItem = 0;
        if (LocaleHelper.LANGUAGE_MARATHI.equals(currentLang)) checkedItem = 1;
        else if (LocaleHelper.LANGUAGE_HINDI.equals(currentLang)) checkedItem = 2;

        new MaterialAlertDialogBuilder(this)
                .setTitle("Select Language / भाषा निवडा")
                .setSingleChoiceItems(languages, checkedItem, (dialog, which) -> {
                    String selectedLang = LocaleHelper.LANGUAGE_ENGLISH;
                    if (which == 1) selectedLang = LocaleHelper.LANGUAGE_MARATHI;
                    else if (which == 2) selectedLang = LocaleHelper.LANGUAGE_HINDI;

                    dialog.dismiss();

                    // Show smooth loading dialog
                    android.app.ProgressDialog progressDialog = new android.app.ProgressDialog(HomeActivity.this);
                    progressDialog.setMessage("Applying language / भाषा लागू करत आहे...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    final String finalLang = selectedLang;
                    new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(() -> {
                        LocaleHelper.setLocale(HomeActivity.this, finalLang);
                        try {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                        } catch (Exception ignored) {}
                        recreate();
                    }, 400);
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    private void showThemeDialog() {
        String[] themes = {"Light Mode (प्रकाश मोड)", "Dark Mode (गडद मोड)", "System Default (सिस्टम डीफॉल्ट)"};
        int currentTheme = ThemeHelper.getSavedThemeMode(this);
        int checkedItem = 0;
        if (currentTheme == ThemeHelper.THEME_DARK) checkedItem = 1;
        else if (currentTheme == ThemeHelper.THEME_SYSTEM) checkedItem = 2;

        new MaterialAlertDialogBuilder(this)
                .setTitle("Theme / थीम")
                .setSingleChoiceItems(themes, checkedItem, (dialog, which) -> {
                    int selectedTheme = ThemeHelper.THEME_LIGHT;
                    if (which == 1) selectedTheme = ThemeHelper.THEME_DARK;
                    else if (which == 2) selectedTheme = ThemeHelper.THEME_SYSTEM;

                    ThemeHelper.setThemeMode(HomeActivity.this, selectedTheme);
                    dialog.dismiss();
                    recreate();
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    private void shareApp() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
        String shareMsg = "Shetkari Mitra (शेतकरी मित्र) — Maharashtra's Rural Snake Safety, Emergency First Aid & Rescuer Network App.\n\n" +
                "Emergency Helplines: 108 / 112\n" +
                "Download & Guide: https://github.com/vishal-bhutekar21/Setkari-Mitra--Android-Application-";
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMsg);
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_via)));
    }

    private void showLogoutDialog() {
        new MaterialAlertDialogBuilder(this)
                .setTitle(R.string.logout)
                .setMessage(R.string.logout_confirm_msg)
                .setPositiveButton(R.string.logout, (dialog, which) -> {
                    SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
                    prefs.edit().putBoolean(KEY_LOGGED_IN, false).apply();

                    Intent intent = new Intent(HomeActivity.this, Start_Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }
}
