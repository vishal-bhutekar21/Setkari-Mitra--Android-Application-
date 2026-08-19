package com.example.Shetkari_Mitra;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EmergencyContactDao {

    @Query("SELECT * FROM emergency_contacts ORDER BY name ASC")
    List<EmergencyContactEntity> getAll();

    @Insert
    void insert(EmergencyContactEntity contact);

    @Update
    void update(EmergencyContactEntity contact);

    @Delete
    void delete(EmergencyContactEntity contact);

    @Query("DELETE FROM emergency_contacts WHERE id = :id")
    void deleteById(int id);

    @Query("SELECT COUNT(*) FROM emergency_contacts")
    int count();
}
