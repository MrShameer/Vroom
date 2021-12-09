package com.example.vroom.database.VehicleDetails;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class VehicleViewModel extends AndroidViewModel {

    private VehicleDetailsRepository vehicleDetailsRepository;
    private LiveData<List<VehicleDetails>> getAllVehicleDetails;

    public VehicleViewModel(@NonNull Application application) {

        super(application);
        vehicleDetailsRepository=new VehicleDetailsRepository(application);
        getAllVehicleDetails= vehicleDetailsRepository.getAllVehicleDetails();
    }
    public void insert (VehicleDetails vehicleDetails){
        vehicleDetailsRepository.insert(vehicleDetails);
    }
    public void update (VehicleDetails vehicleDetails){
        vehicleDetailsRepository.update(vehicleDetails);
    }
    public void delete (VehicleDetails vehicleDetails){
        vehicleDetailsRepository.delete(vehicleDetails);
    }
    public void deleteAll (VehicleDetails vehicleDetails){
        vehicleDetailsRepository.deleteAllVehicleDetails();
    }
    public LiveData<List<VehicleDetails>> getGetAllVehicleDetails() {
        return getAllVehicleDetails;
    }
}
