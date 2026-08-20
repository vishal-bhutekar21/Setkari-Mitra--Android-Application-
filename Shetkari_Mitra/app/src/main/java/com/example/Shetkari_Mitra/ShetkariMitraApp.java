package com.example.Shetkari_Mitra;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
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
            
            // Set compliant, unique User-Agent adhering to OpenStreetMap Tile Usage Policy
            String userAgent = getPackageName() + "/2.0 (Linux; Android " + android.os.Build.VERSION.RELEASE + "; +https://github.com/vishal-bhutekar21/Setkari-Mitra--Android-Application-)";
            Configuration.getInstance().setUserAgentValue(userAgent);

            // Configure dedicated tile cache directory
            File osmBase = new File(ctx.getCacheDir(), "osmdroid");
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
