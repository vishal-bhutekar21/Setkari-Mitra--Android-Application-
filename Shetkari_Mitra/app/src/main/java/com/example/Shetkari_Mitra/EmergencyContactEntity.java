package com.example.Shetkari_Mitra;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "emergency_contacts")
public class EmergencyContactEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String name;

    @NonNull
    public String phoneNumber;

    public EmergencyContactEntity(@NonNull String name, @NonNull String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
