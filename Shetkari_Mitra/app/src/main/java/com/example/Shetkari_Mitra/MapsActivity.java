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
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // OSMDroid configuration
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        Configuration.getInstance().setUserAgentValue(getPackageName());

        setContentView(R.layout.activity_maps);

        View btnGlassBack = findViewById(R.id.btnGlassBack);
        if (btnGlassBack != null) {
            btnGlassBack.setOnClickListener(v -> finish());
        }

        mapView = findViewById(R.id.map);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.ALWAYS);
        mapView.setMultiTouchControls(true);

        mapView.getController().setZoom(12.5);
        mapView.getController().setCenter(JALNA_CENTER);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        setupRadiusButtons();
        addJalnaHospitalMarkers();
        checkLocationPermissions();
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
            locationPermissionLauncher.launch(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            });
        }
    }

    private void enableLiveLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        // Live location overlay with OSMDroid
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

    private void addJalnaHospitalMarkers() {
        // Known coordinate mapping for hospitals across Jalna district
        List<HospitalLocation> locations = new ArrayList<>();

        locations.add(new HospitalLocation("GMCH & Hospital Jalna", "02482-222000", 19.8465, 75.8755, "Jalna-Aurangabad Road"));
        locations.add(new HospitalLocation("District Civil Hospital Jalna", "02482-222100", 19.8398, 75.8821, "Collector Office Road, Jalna"));
        locations.add(new HospitalLocation("Sub District Hospital Ambad", "02484-222200", 19.6105, 75.7901, "Main Road, Ambad"));
        locations.add(new HospitalLocation("Sub District Hospital Badnapur", "02481-234500", 19.8690, 75.7230, "Hospital Road, Badnapur"));
        locations.add(new HospitalLocation("Sub District Hospital Partur", "02485-222300", 19.5932, 76.2135, "Civil Lines, Partur"));
        locations.add(new HospitalLocation("Sub District Hospital Bhokardan", "02486-222100", 20.2562, 75.7681, "Bus Stand Road, Bhokardan"));
        locations.add(new HospitalLocation("Rural Hospital Jafrabad", "02483-222400", 20.2185, 75.9890, "Hospital Colony, Jafrabad"));
        locations.add(new HospitalLocation("Rural Hospital Mantha", "02487-222100", 19.6740, 76.3980, "Mantha, Jalna"));
        locations.add(new HospitalLocation("Rural Hospital Ghansavangi", "02482-265000", 19.5280, 75.9920, "Ghansavangi Market, Jalna"));
        locations.add(new HospitalLocation("Sai Hospital Jalna", "02482-224000", 19.8350, 75.8900, "Main Road, Jalna"));
        locations.add(new HospitalLocation("Yashoda Hospital Jalna", "02482-225000", 19.8412, 75.8840, "Station Road, Jalna"));
        locations.add(new HospitalLocation("Ashirwad Hospital Jalna", "02482-226000", 19.8450, 75.8800, "College Road, Jalna"));
        locations.add(new HospitalLocation("Kranti Hospital Jalna", "02482-227000", 19.8510, 75.8710, "Aurangabad Road, Jalna"));
        locations.add(new HospitalLocation("Narayani Hospital Jalna", "02482-228000", 19.8290, 75.8950, "Ambad Road, Jalna"));
        locations.add(new HospitalLocation("Siddhi Vinayak Hospital Jalna", "02482-229000", 19.8320, 75.8810, "Osmanabad Road, Jalna"));
        locations.add(new HospitalLocation("Shri Sai Baba Hospital Jalna", "02482-230000", 19.8370, 75.9010, "Nanded Road, Jalna"));

        for (HospitalLocation hl : locations) {
            Marker marker = new Marker(mapView);
            marker.setPosition(new GeoPoint(hl.lat, hl.lng));
            marker.setTitle(hl.name);
            marker.setSnippet("📞 " + hl.phone + "\n📍 " + hl.address);
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

            marker.setOnMarkerClickListener((m, mv) -> {
                m.showInfoWindow();
                return true;
            });

            mapView.getOverlays().add(marker);
        }

        mapView.invalidate();
    }

    private static class HospitalLocation {
        String name;
        String phone;
        double lat;
        double lng;
        String address;

        HospitalLocation(String name, String phone, double lat, double lng, String address) {
            this.name = name;
            this.phone = phone;
            this.lat = lat;
            this.lng = lng;
            this.address = address;
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
