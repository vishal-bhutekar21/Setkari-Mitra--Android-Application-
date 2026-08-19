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
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
    private View cardEmergencyBtn, cardAboutUsBtn, cardMythsFacts;
    private ImageButton btnMenuDrawer, btnQuickEmergency;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();
        setupNavigationHeader();
        setupCardClickListeners();
        setupBottomNavigation();

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        checkAndRequestLocation();
    }

    private void initViews() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        locationTextView = findViewById(R.id.locationTextView);
        tvGreeting = findViewById(R.id.tvGreeting);

        btnMenuDrawer = findViewById(R.id.btnMenuDrawer);
        btnQuickEmergency = findViewById(R.id.btnQuickEmergency);

        cardSnakeLib = findViewById(R.id.Snake_Lib);
        cardFirstAid = findViewById(R.id.first_Aid);
        cardNearHospital = findViewById(R.id.nearhospital);
        cardIdentifySnake = findViewById(R.id.Snake_identify);
        cardResRegistration = findViewById(R.id.res_registration);
        cardSnakeRescuers = findViewById(R.id.snake_rescuer);
        cardEmergencyBtn = findViewById(R.id.emergency_btn);
        cardAboutUsBtn = findViewById(R.id.about_btn);
        cardMythsFacts = findViewById(R.id.cardMythsFacts);

        if (btnMenuDrawer != null) {
            btnMenuDrawer.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        }

        if (btnQuickEmergency != null) {
            btnQuickEmergency.setOnClickListener(v -> showEmergencyCallDialog());
        }

        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setupNavigationHeader() {
        View headerView = navigationView.getHeaderView(0);
        userNameTextView = headerView.findViewById(R.id.user_name);
        userEmailTextView = headerView.findViewById(R.id.user_email);

        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String username = prefs.getString(KEY_SAVED_USERNAME, "Shetkari Mitra");
        String email = prefs.getString(KEY_SAVED_EMAIL, "Farmer Companion");

        if (userNameTextView != null) userNameTextView.setText(username);
        if (userEmailTextView != null) userEmailTextView.setText(email);
        if (tvGreeting != null) tvGreeting.setText("Namaste, " + username + " 🙏");
    }

    private void setupCardClickListeners() {
        if (cardFirstAid != null) cardFirstAid.setOnClickListener(v -> startActivity(new Intent(this, First_Aid.class)));
        if (cardSnakeLib != null) cardSnakeLib.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));
        if (cardNearHospital != null) cardNearHospital.setOnClickListener(v -> startActivity(new Intent(this, Near_By_Hospitals.class)));
        if (cardIdentifySnake != null) cardIdentifySnake.setOnClickListener(v -> startActivity(new Intent(this, Acitivity_identify_snake.class)));
        if (cardResRegistration != null) cardResRegistration.setOnClickListener(v -> startActivity(new Intent(this, Registration_example.class)));
        if (cardSnakeRescuers != null) cardSnakeRescuers.setOnClickListener(v -> startActivity(new Intent(this, RescuerDatabaseActivity.class)));
        if (cardAboutUsBtn != null) cardAboutUsBtn.setOnClickListener(v -> startActivity(new Intent(this, Activity_About_Us.class)));
        if (cardMythsFacts != null) cardMythsFacts.setOnClickListener(v -> startActivity(new Intent(this, Activity_Myths_Facts.class)));
        if (cardEmergencyBtn != null) cardEmergencyBtn.setOnClickListener(v -> showEmergencyCallDialog());
    }

    private void setupBottomNavigation() {
        if (bottomNavigationView == null) return;
        bottomNavigationView.setSelectedItemId(R.id.bottom_nav_home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.bottom_nav_home) {
                return true;
            } else if (id == R.id.bottom_nav_guide) {
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                return true;
            } else if (id == R.id.bottom_nav_hospitals) {
                startActivity(new Intent(HomeActivity.this, Near_By_Hospitals.class));
                return true;
            } else if (id == R.id.bottom_nav_rescuers) {
                startActivity(new Intent(HomeActivity.this, RescuerDatabaseActivity.class));
                return true;
            } else if (id == R.id.bottom_nav_sos) {
                showEmergencyCallDialog();
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

    private void showEmergencyCallDialog() {
        new MaterialAlertDialogBuilder(this)
                .setTitle(R.string.emergency_call_title)
                .setMessage(R.string.emergency_call_confirm_msg)
                .setIcon(R.drawable.call_logo)
                .setPositiveButton(R.string.call_112, (dialog, which) -> {
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                    dialIntent.setData(Uri.parse("tel:112"));
                    startActivity(dialIntent);
                })
                .setNeutralButton(R.string.call_rescuer, (dialog, which) -> {
                    startActivity(new Intent(HomeActivity.this, RescuerDatabaseActivity.class));
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
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
                    } else if (addr.getAddressLine(0) != null) {
                        addressText = addr.getAddressLine(0);
                    }
                }
            } catch (IOException ignored) {}

            final String displayText = addressText;
            mainHandler.post(() -> locationTextView.setText(displayText));
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_contacts) {
            startActivity(new Intent(this, nav_Emergency_Contacts.class));
        } else if (id == R.id.nav_hos) {
            startActivity(new Intent(this, MapsActivity.class));
        } else if (id == R.id.nav_myths) {
            startActivity(new Intent(this, Activity_Myths_Facts.class));
        } else if (id == R.id.nav_Admin) {
            startActivity(new Intent(this, Admin_Activity.class));
        } else if (id == R.id.nav_share) {
            shareApp();
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(this, Activity_About_Us.class));
        } else if (id == R.id.nav_logout) {
            performLogout();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void shareApp() {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
        sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_app_description));
        startActivity(Intent.createChooser(sendIntent, getString(R.string.share_via)));
    }

    private void performLogout() {
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

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}
