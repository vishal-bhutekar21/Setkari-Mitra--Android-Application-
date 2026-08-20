package com.example.Shetkari_Mitra;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {
        EmergencyContactEntity.class,
        SafetyChecklistItemEntity.class,
        RescuerRequestEntity.class
}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;
    private static final String DB_NAME = "shetkari_mitra_db";

    public abstract EmergencyContactDao emergencyContactDao();
    public abstract SafetyChecklistDao safetyChecklistDao();
    public abstract RescuerRequestDao rescuerRequestDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            DB_NAME
                    )
                    .fallbackToDestructiveMigration()
                    .build();
                }
            }
        }
        return INSTANCE;
    }
}
