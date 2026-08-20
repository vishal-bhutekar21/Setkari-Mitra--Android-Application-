package com.example.Shetkari_Mitra;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationHelper {

    public static void setupBottomNavigation(@NonNull final Activity activity,
                                             BottomNavigationView bottomNav,
                                             int selectedItemId) {
        if (bottomNav == null) return;

        bottomNav.setSelectedItemId(selectedItemId);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == selectedItemId) {
                return true; // Already on this page
            }

            if (id == R.id.bottom_nav_home) {
                if (!(activity instanceof HomeActivity)) {
                    Intent intent = new Intent(activity, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    activity.startActivity(intent);
                    activity.overridePendingTransition(0, 0);
                    if (!(activity instanceof HomeActivity)) {
                        activity.finish();
                    }
                }
                return true;
            } else if (id == R.id.bottom_nav_hospitals) {
                if (!(activity instanceof Near_By_Hospitals)) {
                    Intent intent = new Intent(activity, Near_By_Hospitals.class);
                    activity.startActivity(intent);
                    activity.overridePendingTransition(0, 0);
                    if (!(activity instanceof HomeActivity)) {
                        activity.finish();
                    }
                }
                return true;
            } else if (id == R.id.bottom_nav_emergency) {
                if (!(activity instanceof EmergencyActivity)) {
                    Intent intent = new Intent(activity, EmergencyActivity.class);
                    activity.startActivity(intent);
                    activity.overridePendingTransition(0, 0);
                }
                return true;
            } else if (id == R.id.bottom_nav_safety) {
                if (!(activity instanceof SafetyCenterActivity)) {
                    Intent intent = new Intent(activity, SafetyCenterActivity.class);
                    activity.startActivity(intent);
                    activity.overridePendingTransition(0, 0);
                    if (!(activity instanceof HomeActivity)) {
                        activity.finish();
                    }
                }
                return true;
            } else if (id == R.id.bottom_nav_profile) {
                if (!(activity instanceof nav_Emergency_Contacts)) {
                    Intent intent = new Intent(activity, nav_Emergency_Contacts.class);
                    activity.startActivity(intent);
                    activity.overridePendingTransition(0, 0);
                    if (!(activity instanceof HomeActivity)) {
                        activity.finish();
                    }
                }
                return true;
            }

            return false;
        });
    }
}
