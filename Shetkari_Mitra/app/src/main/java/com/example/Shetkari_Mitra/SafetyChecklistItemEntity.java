package com.example.Shetkari_Mitra;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "safety_checklists")
public class SafetyChecklistItemEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String category;

    @NonNull
    public String taskTitle;

    public boolean isCompleted;

    public SafetyChecklistItemEntity(@NonNull String category, @NonNull String taskTitle, boolean isCompleted) {
        this.category = category;
        this.taskTitle = taskTitle;
        this.isCompleted = isCompleted;
    }
}
