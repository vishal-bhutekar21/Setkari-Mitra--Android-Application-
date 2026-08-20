package com.example.Shetkari_Mitra;

import android.app.Application;
import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

import org.osmdroid.config.Configuration;

import java.io.File;

public class ShetkariMitraApp extends Application {

    private static final String TAG = "ShetkariMitraApp";
    private static ShetkariMitraApp instance;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, LocaleHelper.LANGUAGE_ENGLISH));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        // Initialize OSMDroid globally before any MapView is created or layout inflated
        initOsmConfiguration();
    }

    public static ShetkariMitraApp getInstance() {
        return instance;
    }

    private void initOsmConfiguration() {
        try {
            Context ctx = getApplicationContext();
            Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
            
            // Set distinct legitimate User-Agent conforming strictly to OpenStreetMap Tile Usage Policy
            String userAgent = "ShetkariMitraSafetyApp/3.0.0 (Android; Maharashtra Farmer Rescue Network; vishal.bhutekar21@gmail.com)";
            Configuration.getInstance().setUserAgentValue(userAgent);

            // Configure dedicated tile cache directory and clear any stale 403 error files
            File osmBase = new File(ctx.getCacheDir(), "osmdroid_v2");
            if (!osmBase.exists()) {
                osmBase.mkdirs();
            }
            File osmTileCache = new File(osmBase, "tiles");
            if (!osmTileCache.exists()) {
                osmTileCache.mkdirs();
            }
            Configuration.getInstance().setOsmdroidBasePath(osmBase);
            Configuration.getInstance().setOsmdroidTileCache(osmTileCache);
            Configuration.getInstance().setTileFileSystemCacheMaxBytes(100L * 1024 * 1024);
            Configuration.getInstance().setTileFileSystemCacheTrimBytes(80L * 1024 * 1024);
            Configuration.getInstance().setMapViewHardwareAccelerated(true);

            Log.i(TAG, "OSMDroid configuration initialized with User-Agent: " + userAgent);
        } catch (Exception e) {
            Log.e(TAG, "Error initializing OSMDroid config", e);
        }
    }
}
