package com.example.Shetkari_Mitra;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RescuerRequestDao {

    @Query("SELECT * FROM rescuer_requests ORDER BY id DESC")
    List<RescuerRequestEntity> getAllRequests();

    @Insert
    long insertRequest(RescuerRequestEntity request);

    @Update
    void updateRequest(RescuerRequestEntity request);
}
