package com.example.Shetkari_Mitra;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LatLng currentLocation;
    private final int DEFAULT_RADIUS = 5000; // Default radius to show nearby hospitals
    private List<Marker> hospitalMarkers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Enable user's location
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // If permission not granted, request it
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            return;
        }
        // Get user's current location
        Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();
        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));
                    // Show nearby hospitals by default
                    fetchNearbyHospitals(currentLocation, DEFAULT_RADIUS);
                }
            }
        });

        // Set onMarkerClickListener to handle hospital marker clicks
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                // Get the position of the clicked marker
                LatLng position = marker.getPosition();
                // Show the name of the place or area as the title of the marker
                marker.setTitle(getPlaceName(position));
                // Show the info window for the marker to display its title
                marker.showInfoWindow();
                return true;
            }
        });
    }

    public void onButtonClicked(View view) {
        if (currentLocation == null) {
            Toast.makeText(this, "Unable to fetch current location.", Toast.LENGTH_SHORT).show();
            return;
        }
        int id = view.getId();
        int radius;
        if (id == R.id.button_2km) {
            radius = 2000;
        } else if (id == R.id.button_5km) {
            radius = 5000;
        } else if (id == R.id.button_10km) {
            radius = 10000;
        } else {
            return;
        }
        // Fetch nearby hospitals based on the selected radius
        fetchNearbyHospitals(currentLocation, radius);
    }

    private void fetchNearbyHospitals(final LatLng location, final int radius) {
        mMap.clear(); // Clear previous markers
        hospitalMarkers.clear(); // Clear previous hospital markers

        // Example: Fetch hospital locations from a database or API within the specified radius
        // Here, we're just adding some dummy hospital locations for demonstration
        List<LatLng> hospitals = new ArrayList<>();
        hospitals.add(new LatLng(location.latitude + 0.01, location.longitude + 0.01));
        hospitals.add(new LatLng(location.latitude - 0.01, location.longitude + 0.01));
        hospitals.add(new LatLng(location.latitude, location.longitude - 0.01));

        // Add markers for nearby hospitals
        for (LatLng hospital : hospitals) {
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(hospital)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            hospitalMarkers.add(marker); // Add hospital marker to the list
        }

        // You should replace the above example with your actual data retrieval logic
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // If location permission granted, enable user's location
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
        }
    }

    private String getPlaceName(LatLng position) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(position.latitude, position.longitude, 1);
            if (addresses != null && addresses.size() > 0) {
                Address address = addresses.get(0);
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                    stringBuilder.append(address.getAddressLine(i)).append("\n");
                }
                return stringBuilder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Unknown Location";
    }
}
