package com.example.Shetkari_Mitra;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {


    private static final String PREF_NAME = "MyPrefs";
    private static final String KEY_LOGGED_IN = "isLoggedIn";

    DrawerLayout drawerLayout;
    Toolbar toolbar;

    NavigationView navigationView;

    private LocationManager locationManager;
    private LocationListener locationListener;
    private static final int PERMISSION_REQUEST_CODE = 1001;

    private TextView userNameTextView;
    private TextView userEmailTextView;
    TextView locationTextView;


    CardView snake_lib, first_aid, near_hospital, identify_snake, snake_res_regis, snake_rescuiers;
    CardView emergency_Btn, about_us_btn;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        snake_lib = findViewById(R.id.Snake_Lib);
        first_aid = findViewById(R.id.first_Aid);
        emergency_Btn = findViewById(R.id.emergency_btn);
        about_us_btn = findViewById(R.id.about_btn);
        snake_rescuiers = findViewById(R.id.snake_rescuer);
        snake_res_regis = findViewById(R.id.res_registration);
        near_hospital = findViewById(R.id.nearhospital);
        locationTextView = findViewById(R.id.locationTextView);
        identify_snake = findViewById(R.id.Snake_identify);

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigation_view);
        View headerView = navigationView.getHeaderView(0);
        userNameTextView = headerView.findViewById(R.id.user_name);
        userEmailTextView = headerView.findViewById(R.id.user_email);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userEmail = currentUser.getEmail();
            if (userEmail != null && !userEmail.isEmpty()) {
                userEmailTextView.setText(userEmail);


                int atIndex = userEmail.indexOf('@');
                if (atIndex != -1) {
                    String userName = userEmail.substring(0, atIndex);
                    userNameTextView.setText(userName);
                } else {

                    userNameTextView.setText("User");
                }
            } else {
                userEmailTextView.setText("User");
                userNameTextView.setText("User");
            }
        } else {
            userEmailTextView.setText("Not logged in");
            userNameTextView.setText("Not logged in");
        }


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                HomeActivity.this, drawerLayout, toolbar,
                R.string.navigation_drawer_close,
                R.string.navigation_drawer_open
        );
        drawerLayout.addDrawerListener(toggle);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                 if (menuItem.getItemId() == R.id.nav_profile) {
                    Toast.makeText(HomeActivity.this, "This is Profile ", Toast.LENGTH_SHORT).show();
                    drawerLayout.close();
                } else if (menuItem.getItemId() == R.id.nav_contacts) {
                    Intent i = new Intent(HomeActivity.this, nav_Emergency_Contacts.class);
                    startActivity(i);
                    Toast.makeText(HomeActivity.this, "This is Contacts ", Toast.LENGTH_SHORT).show();
                    drawerLayout.close();
                } else if (menuItem.getItemId() == R.id.nav_logout) {
                    // Sign out the user from Firebase Authentication
                    FirebaseAuth.getInstance().signOut();

                    // Clear the login state from SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(KEY_LOGGED_IN, false);
                    editor.apply();

                    // Redirect to the login page
                    Intent intent = new Intent(HomeActivity.this, Start_Activity.class);
                    startActivity(intent);
                    finish(); // Close the current activity
                    drawerLayout.close();
                } else if (menuItem.getItemId() == R.id.nav_share) {
                    String appDescription = "ShetkariMitra App - Prevents Snake Bites\n" +
                            "\n" +"ShetkariMitra is your ultimate companion in staying safe from snake bites. Our app provides essential information, tips, and tools to help you minimize the risk of encountering snakes and handle snake encounters effectively. Whether you're a farmer, outdoor enthusiast, or simply concerned about snake safety, ShetkariMitra is here to support you.\n";
                    String appLink = "https://play.google.com/store/apps/details?id=com.example.snakebiteprevention";

                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_SUBJECT, "ShetkariMitra App - Prevents Snake Bites");
                    sendIntent.putExtra(Intent.EXTRA_TEXT, appDescription + "\nDownload the app:\n" + appLink);
                    sendIntent.setType("text/plain");

                    startActivity(Intent.createChooser(sendIntent, "Share ShetkariMitra App via"));
                    drawerLayout.close();


                } else if (menuItem.getItemId() == R.id.nav_Admin) {
                    Intent i=new Intent(HomeActivity.this, Admin_Activity.class);
                    startActivity(i);
                    drawerLayout.close();
                } else if (menuItem.getItemId()==R.id.nav_hos){
                     Intent i=new Intent(HomeActivity.this, MapsActivity.class);
                     startActivity(i);
                     drawerLayout.close();

                }

                return false;
            }
        });


        ArrayList<SlideModel> imageList = new ArrayList<>();

        imageList.add(new SlideModel(R.drawable.slider1, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slider2, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slider4, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slider5, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slider6, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slider7, ScaleTypes.CENTER_CROP));

        ImageSlider imageSlider = findViewById(R.id.image_slider);
        imageSlider.setImageList(imageList);

        first_aid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, First_Aid.class);
                startActivity(i);
            }
        });

        snake_lib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        near_hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, Near_By_Hospitals.class);
                startActivity(i);
            }
        });
        identify_snake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, Acitivity_identify_snake.class);
                startActivity(i);
            }
        });
        snake_res_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, Registration_example.class);
                startActivity(i);
            }
        });
        snake_rescuiers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, RescuerDatabaseActivity.class);
                startActivity(i);
            }
        });
        about_us_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, Activity_About_Us.class);
                startActivity(i);

            }
        });

        emergency_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent embtn1 = new Intent(Intent.ACTION_DIAL);
                embtn1.setData(Uri.parse("tel:112"));
                startActivity(embtn1);
            }
        });


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission not granted, request permissions
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSION_REQUEST_CODE);
            return;
        }

        getLocation();

    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, get location
                getLocation();
            } else {
                // Permission denied, show a message or handle accordingly
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getLocation() {
        if (locationManager != null) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    locationListener = new LocationListener() {
                        @Override
                        public void onLocationChanged(@NonNull Location location) {
                            updateLocation(location);
                        }

                        @Override
                        public void onProviderEnabled(@NonNull String provider) {
                        }

                        @Override
                        public void onProviderDisabled(@NonNull String provider) {
                            // If the GPS provider is disabled, prompt the user to enable it
                            showEnableLocationDialog();
                        }
                    };

                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
                } else {
                    // Prompt the user to enable GPS
                    showEnableLocationDialog();
                }
            } else {
                // Request location permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
            }
        }
    }

    // Replace your existing showEnableLocationDialog() method with this:
    private void showEnableLocationDialog() {
        EnableLocationDialogFragment dialogFragment = new EnableLocationDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "EnableLocationDialogFragment");
    }



    private void updateLocation(Location location) {
        if (location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            // Convert latitude and longitude into address using Geocoder
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            try {
                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                if (!addresses.isEmpty()) {
                    Address address = addresses.get(0);
                    String addressText = String.format("%s, %s, %s",
                            address.getAddressLine(0),
                            address.getLocality(),
                            address.getCountryName());
                    locationTextView.setText(addressText);
                } else {
                    locationTextView.setText("Address not found");
                }
            } catch (IOException e) {
                e.printStackTrace();
                locationTextView.setText("Error retrieving location");
            }
        } else {
            locationTextView.setText("Location is null");
        }

        // Stop listening for location updates once location is obtained
        if (locationManager != null && locationListener != null) {
            locationManager.removeUpdates(locationListener);
        }
    }
}











