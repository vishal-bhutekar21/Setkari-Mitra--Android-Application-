package com.example.Shetkari_Mitra;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SafetyChecklistDao {

    @Query("SELECT * FROM safety_checklists WHERE category = :category ORDER BY id ASC")
    List<SafetyChecklistItemEntity> getItemsByCategory(String category);

    @Query("SELECT * FROM safety_checklists ORDER BY id ASC")
    List<SafetyChecklistItemEntity> getAllItems();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<SafetyChecklistItemEntity> items);

    @Update
    void updateItem(SafetyChecklistItemEntity item);

    @Query("SELECT COUNT(*) FROM safety_checklists WHERE category = :category")
    int getTotalCountByCategory(String category);

    @Query("SELECT COUNT(*) FROM safety_checklists WHERE category = :category AND isCompleted = 1")
    int getCompletedCountByCategory(String category);
}
