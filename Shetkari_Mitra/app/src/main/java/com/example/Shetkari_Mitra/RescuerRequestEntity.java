package com.example.Shetkari_Mitra;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "rescuer_requests")
public class RescuerRequestEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String situation;

    @NonNull
    public String locationText;

    public String rescuerName;

    public String status; // "Request Sent", "Accepted", "On the Way", "Resolved"

    public long timestamp;

    public RescuerRequestEntity(@NonNull String situation, @NonNull String locationText, String rescuerName, String status, long timestamp) {
        this.situation = situation;
        this.locationText = locationText;
        this.rescuerName = rescuerName;
        this.status = status;
        this.timestamp = timestamp;
    }
}
