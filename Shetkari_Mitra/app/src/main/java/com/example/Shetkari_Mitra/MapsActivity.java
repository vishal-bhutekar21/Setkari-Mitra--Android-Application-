package com.example.Shetkari_Mitra;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.CopyrightOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity {

    // High-performance, unrestricted OpenStreetMap tile provider (CARTO Voyager)
    public static final OnlineTileSourceBase CARTO_VOYAGER = new XYTileSource(
            "CartoVoyager",
            0, 20, 256, ".png",
            new String[] {
                    "https://a.basemaps.cartocdn.com/rastertiles/voyager/",
                    "https://b.basemaps.cartocdn.com/rastertiles/voyager/",
                    "https://c.basemaps.cartocdn.com/rastertiles/voyager/",
                    "https://d.basemaps.cartocdn.com/rastertiles/voyager/"
            },
            "© OpenStreetMap contributors, © CARTO"
    );

    private MapView mapView;
    private MyLocationNewOverlay locationOverlay;
    private FusedLocationProviderClient fusedLocationClient;
    private GeoPoint currentUserLocation;

    // Center on Jalna district (19.8410 N, 75.8864 E)
    private static final GeoPoint JALNA_CENTER = new GeoPoint(19.8410, 75.8864);

    private final ActivityResultLauncher<String[]> locationPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
                Boolean fineGranted = result.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false);
                Boolean coarseGranted = result.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false);
                if ((fineGranted != null && fineGranted) || (coarseGranted != null && coarseGranted)) {
                    enableLiveLocation();
                } else {
                    Toast.makeText(this, R.string.location_permission_denied, Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // OSMDroid configuration
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        String userAgent = "ShetkariMitraSafetyApp/3.0.0 (Android; Maharashtra Farmer Rescue Network; vishal.bhutekar21@gmail.com)";
        Configuration.getInstance().setUserAgentValue(userAgent);
        
        File osmBase = new File(ctx.getCacheDir(), "osmdroid_v3");
        if (!osmBase.exists()) osmBase.mkdirs();
        File osmTiles = new File(osmBase, "tiles");
        if (!osmTiles.exists()) osmTiles.mkdirs();

        Configuration.getInstance().setOsmdroidBasePath(osmBase);
        Configuration.getInstance().setOsmdroidTileCache(osmTiles);
        Configuration.getInstance().setMapViewHardwareAccelerated(true);

        setContentView(R.layout.activity_maps);

        View btnGlassBack = findViewById(R.id.btnGlassBack);
        if (btnGlassBack != null) {
            btnGlassBack.setOnClickListener(v -> finish());
        }

        mapView = findViewById(R.id.map);
        mapView.setTileSource(CARTO_VOYAGER);
        mapView.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.ALWAYS);
        mapView.setMultiTouchControls(true);

        // Add OpenStreetMap Copyright & Attribution Overlay
        CopyrightOverlay copyrightOverlay = new CopyrightOverlay(this);
        copyrightOverlay.setTextSize(11);
        copyrightOverlay.setAlignRight(true);
        copyrightOverlay.setAlignBottom(true);
        mapView.getOverlays().add(copyrightOverlay);

        mapView.getController().setZoom(12.5);
        mapView.getController().setCenter(JALNA_CENTER);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        chipAllLayers = findViewById(R.id.chipAllLayers);
        chipHospitals = findViewById(R.id.chipHospitals);
        chipRescuers = findViewById(R.id.chipRescuers);
        chipSightings = findViewById(R.id.chipSightings);

        setupRadiusButtons();
        setupCategoryChips();
        loadAllMarkers();
        checkLocationPermissions();

        boolean showRescuersFirst = getIntent().getBooleanExtra("SHOW_RESCUERS", false);
        if (showRescuersFirst && chipRescuers != null) {
            chipRescuers.performClick();
        }
    }

    private com.google.android.material.button.MaterialButton chipAllLayers, chipHospitals, chipRescuers, chipSightings;
    private final List<Marker> hospitalMarkers = new ArrayList<>();
    private final List<Marker> rescuerMarkers = new ArrayList<>();
    private final List<Marker> sightingMarkers = new ArrayList<>();

    private void setupCategoryChips() {
        if (chipAllLayers == null || chipHospitals == null || chipRescuers == null || chipSightings == null) return;

        chipAllLayers.setOnClickListener(v -> {
            resetChipStyles();
            setActiveChipStyle(chipAllLayers);
            showMarkers(true, true, true);
        });

        chipHospitals.setOnClickListener(v -> {
            resetChipStyles();
            setActiveChipStyle(chipHospitals);
            showMarkers(true, false, false);
        });

        chipRescuers.setOnClickListener(v -> {
            resetChipStyles();
            setActiveChipStyle(chipRescuers);
            showMarkers(false, true, false);
        });

        chipSightings.setOnClickListener(v -> {
            resetChipStyles();
            setActiveChipStyle(chipSightings);
            showMarkers(false, false, true);
        });
    }

    private void resetChipStyles() {
        com.google.android.material.button.MaterialButton[] chips = {chipAllLayers, chipHospitals, chipRescuers, chipSightings};
        for (com.google.android.material.button.MaterialButton chip : chips) {
            if (chip != null) {
                chip.setBackgroundTintList(ContextCompat.getColorStateList(this, android.R.color.transparent));
                chip.setTextColor(ContextCompat.getColor(this, R.color.color_text_primary));
            }
        }
    }

    private void setActiveChipStyle(com.google.android.material.button.MaterialButton active) {
        if (active != null) {
            active.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.color_primary));
            active.setTextColor(ContextCompat.getColor(this, R.color.color_on_primary));
        }
    }

    private void showMarkers(boolean showHospitals, boolean showRescuers, boolean showSightings) {
        for (Marker m : hospitalMarkers) {
            if (showHospitals) {
                if (!mapView.getOverlays().contains(m)) mapView.getOverlays().add(m);
            } else {
                mapView.getOverlays().remove(m);
            }
        }

        for (Marker m : rescuerMarkers) {
            if (showRescuers) {
                if (!mapView.getOverlays().contains(m)) mapView.getOverlays().add(m);
            } else {
                mapView.getOverlays().remove(m);
            }
        }

        for (Marker m : sightingMarkers) {
            if (showSightings) {
                if (!mapView.getOverlays().contains(m)) mapView.getOverlays().add(m);
            } else {
                mapView.getOverlays().remove(m);
            }
        }

        mapView.invalidate();
    }

    private void setupRadiusButtons() {
        Button btn2km = findViewById(R.id.button_2km);
        Button btn5km = findViewById(R.id.button_5km);
        Button btn10km = findViewById(R.id.button_10km);
        Button btnAll = findViewById(R.id.button_all);

        View.OnClickListener radiusListener = v -> {
            int id = v.getId();
            double zoomLevel = 13.0;
            if (id == R.id.button_2km) {
                zoomLevel = 15.5;
            } else if (id == R.id.button_5km) {
                zoomLevel = 14.0;
            } else if (id == R.id.button_10km) {
                zoomLevel = 12.5;
            } else if (id == R.id.button_all) {
                zoomLevel = 11.0;
            }

            GeoPoint center = currentUserLocation != null ? currentUserLocation : JALNA_CENTER;
            mapView.getController().animateTo(center);
            mapView.getController().setZoom(zoomLevel);
        };

        if (btn2km != null) btn2km.setOnClickListener(radiusListener);
        if (btn5km != null) btn5km.setOnClickListener(radiusListener);
        if (btn10km != null) btn10km.setOnClickListener(radiusListener);
        if (btnAll != null) btnAll.setOnClickListener(radiusListener);
    }

    private void checkLocationPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            enableLiveLocation();
        } else {
            PermissionEducationDialog.newInstance(PermissionEducationDialog.PermissionType.LOCATION, () -> {
                locationPermissionLauncher.launch(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                });
            }).show(getSupportFragmentManager(), "perm_location_map");
        }
    }

    private void enableLiveLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        locationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(this), mapView);
        locationOverlay.enableMyLocation();
        locationOverlay.enableFollowLocation();
        mapView.getOverlays().add(locationOverlay);

        fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
                .addOnSuccessListener(this, location -> {
                    if (location != null) {
                        currentUserLocation = new GeoPoint(location.getLatitude(), location.getLongitude());
                        mapView.getController().animateTo(currentUserLocation);
                        mapView.getController().setZoom(14.0);
                    }
                });
    }

    private void loadAllMarkers() {
        // Hospitals
        List<HospitalLocation> hospitals = new ArrayList<>();
        hospitals.add(new HospitalLocation("GMCH & Hospital Jalna", "02482-222000", 19.8465, 75.8755, "Jalna-Aurangabad Road"));
        hospitals.add(new HospitalLocation("District Civil Hospital Jalna", "02482-222100", 19.8398, 75.8821, "Collector Office Road, Jalna"));
        hospitals.add(new HospitalLocation("Sub District Hospital Ambad", "02484-222200", 19.6105, 75.7901, "Main Road, Ambad"));
        hospitals.add(new HospitalLocation("Sub District Hospital Badnapur", "02481-234500", 19.8690, 75.7230, "Hospital Road, Badnapur"));
        hospitals.add(new HospitalLocation("Sub District Hospital Partur", "02485-222300", 19.5932, 76.2135, "Civil Lines, Partur"));
        hospitals.add(new HospitalLocation("Sub District Hospital Bhokardan", "02486-222100", 20.2562, 75.7681, "Bus Stand Road, Bhokardan"));
        hospitals.add(new HospitalLocation("Rural Hospital Jafrabad", "02483-222400", 20.2185, 75.9890, "Hospital Colony, Jafrabad"));
        hospitals.add(new HospitalLocation("Rural Hospital Mantha", "02487-222100", 19.6740, 76.3980, "Mantha, Jalna"));
        hospitals.add(new HospitalLocation("Rural Hospital Ghansavangi", "02482-265000", 19.5280, 75.9920, "Ghansavangi Market, Jalna"));
        hospitals.add(new HospitalLocation("Sai Hospital Jalna", "02482-224000", 19.8350, 75.8900, "Main Road, Jalna"));
        hospitals.add(new HospitalLocation("Yashoda Hospital Jalna", "02482-225000", 19.8412, 75.8840, "Station Road, Jalna"));
        hospitals.add(new HospitalLocation("Ashirwad Hospital Jalna", "02482-226000", 19.8450, 75.8800, "College Road, Jalna"));
        hospitals.add(new HospitalLocation("Kranti Hospital Jalna", "02482-227000", 19.8510, 75.8710, "Aurangabad Road, Jalna"));

        for (HospitalLocation hl : hospitals) {
            Marker m = new Marker(mapView);
            m.setPosition(new GeoPoint(hl.lat, hl.lng));
            m.setTitle("🏥 " + hl.name);
            m.setSnippet("ASV Available | Phone: " + hl.phone + "\n" + hl.address + "\nTap to Call");
            m.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            m.setOnMarkerClickListener((marker, mv) -> {
                marker.showInfoWindow();
                showContactPrompt(hl.name, hl.phone);
                return true;
            });
            hospitalMarkers.add(m);
            mapView.getOverlays().add(m);
        }

        // Rescuers
        List<RescuerLocation> rescuers = new ArrayList<>();
        rescuers.add(new RescuerLocation("Rahul Shinde (Sarpa Mitra)", "9822114455", 19.8390, 75.8810, "Old Jalna"));
        rescuers.add(new RescuerLocation("Vikas Rathod (Sarpa Mitra)", "9422336677", 19.6105, 75.7876, "Ambad City"));
        rescuers.add(new RescuerLocation("Anil Jadhav (Sarpa Mitra)", "9977884411", 19.8690, 75.7250, "Badnapur"));
        rescuers.add(new RescuerLocation("Gajanan Kale (Sarpa Mitra)", "9850123456", 20.2580, 75.7680, "Bhokardan"));
        rescuers.add(new RescuerLocation("Sachin Pawar (Sarpa Mitra)", "9766554433", 19.5960, 76.2160, "Partur"));
        rescuers.add(new RescuerLocation("Dnyaneshwar Gaikwad", "9890112233", 20.2180, 75.9870, "Jafrabad"));
        rescuers.add(new RescuerLocation("Mahesh Deshmukh", "9404556677", 19.7020, 76.3810, "Mantha"));
        rescuers.add(new RescuerLocation("Santosh Chavan", "9823445566", 19.5280, 75.9920, "Ghansavangi"));
        rescuers.add(new RescuerLocation("Akash More (Wildlife NGO)", "9822001122", 19.8762, 75.3433, "Chh. Sambhajinagar"));

        for (RescuerLocation rl : rescuers) {
            Marker m = new Marker(mapView);
            m.setPosition(new GeoPoint(rl.lat, rl.lng));
            m.setTitle("🐍 " + rl.name);
            m.setSnippet("Certified Sarpmitra | Phone: " + rl.phone + "\n" + rl.area + "\nTap to Call");
            m.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            m.setOnMarkerClickListener((marker, mv) -> {
                marker.showInfoWindow();
                showContactPrompt(rl.name, rl.phone);
                return true;
            });
            rescuerMarkers.add(m);
            mapView.getOverlays().add(m);
        }

        // Community Sightings (Generalized Coordinates for wildlife protection)
        List<SightingLocation> sightings = new ArrayList<>();
        sightings.add(new SightingLocation("Indian Rat Snake (धामण)", "Farmland Bund • Verified", 19.8520, 75.8690));
        sightings.add(new SightingLocation("Russell's Viper (घोणस)", "Cattle Shed Area • Rescued", 19.6150, 75.7920));
        sightings.add(new SightingLocation("Spectacled Cobra (नाग)", "Well vicinity • High Caution", 19.8720, 75.7190));

        for (SightingLocation sl : sightings) {
            Marker m = new Marker(mapView);
            m.setPosition(new GeoPoint(sl.lat, sl.lng));
            m.setTitle("⚠️ " + sl.species);
            m.setSnippet(sl.details + "\nApproximate vicinity (Wildlife Protected)");
            m.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            sightingMarkers.add(m);
            mapView.getOverlays().add(m);
        }

        mapView.invalidate();
    }

    private static class SightingLocation {
        String species, details;
        double lat, lng;

        SightingLocation(String species, String details, double lat, double lng) {
            this.species = species;
            this.details = details;
            this.lat = lat;
            this.lng = lng;
        }
    }

    private void showContactPrompt(String title, String phone) {
        new com.google.android.material.dialog.MaterialAlertDialogBuilder(this)
                .setTitle(title)
                .setMessage("Contact: " + phone)
                .setPositiveButton("Call Now", (d, w) -> {
                    Intent dial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
                    startActivity(dial);
                })
                .setNegativeButton("Close", null)
                .show();
    }

    private static class HospitalLocation {
        String name, phone, address;
        double lat, lng;

        HospitalLocation(String name, String phone, double lat, double lng, String address) {
            this.name = name;
            this.phone = phone;
            this.lat = lat;
            this.lng = lng;
            this.address = address;
        }
    }

    private static class RescuerLocation {
        String name, phone, area;
        double lat, lng;

        RescuerLocation(String name, String phone, double lat, double lng, String area) {
            this.name = name;
            this.phone = phone;
            this.lat = lat;
            this.lng = lng;
            this.area = area;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mapView != null) mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mapView != null) mapView.onPause();
    }
}
