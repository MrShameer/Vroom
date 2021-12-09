package com.example.vroom.database.VehicleDetails;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.vroom.database.User.User;

import java.util.List;

@Dao
public interface VehicleDetailsDAO {
    //CRUD
    @Insert
    void insert(VehicleDetails vehicleDetails);

    @Update
    void update(VehicleDetails vehicleDetails);

    @Delete
    void delete(VehicleDetails vehicleDetails);

    @Query("DELETE FROM VehicleDetails_table")
    void deleteAllVehicleDetails();

    @Query("SELECT * FROM VehicleDetails_table")
    LiveData<List<VehicleDetails>> getAllVehicleDetails();
}
