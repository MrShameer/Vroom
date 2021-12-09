package com.example.vroom.database.VehicleDetails;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.vroom.database.User.User;
import com.example.vroom.database.User.UserDAO;
import com.example.vroom.database.User.UserRepository;

import java.util.List;

public class VehicleDetailsRepository {
    private VehicleDetailsDAO vehicleDetailsDAO;
    private LiveData<List<VehicleDetails>> getAllVehicleDetails;

    public VehicleDetailsRepository(Application application){

        VehicleDetailsDatabase database=VehicleDetailsDatabase.getInstance(application);
        vehicleDetailsDAO= database.vehicleDetailsDAO();
        getAllVehicleDetails= vehicleDetailsDAO.getAllVehicleDetails();
    }

    public void insert(VehicleDetails vehicleDetails){
        new VehicleDetailsRepository.InsertVehicleDetailsAsyncTask(vehicleDetailsDAO).execute(vehicleDetails);
    }
    private static class InsertVehicleDetailsAsyncTask extends AsyncTask<VehicleDetails,Void,Void>
    {
        private VehicleDetailsDAO vehicleDetailsDAO;
        private  InsertVehicleDetailsAsyncTask(VehicleDetailsDAO vehicleDetailsDAO){
            this.vehicleDetailsDAO=vehicleDetailsDAO;
        }
        @Override
        protected Void doInBackground(VehicleDetails... vehicleDetails) {
            vehicleDetailsDAO.insert(vehicleDetails[0]);
            return null;
        }
    }

    public  void update(VehicleDetails vehicleDetails){
        new VehicleDetailsRepository.UpdateVehicleDetailsAsyncTask(vehicleDetailsDAO).execute(vehicleDetails);

    }
    private static class UpdateVehicleDetailsAsyncTask extends AsyncTask<VehicleDetails,Void,Void>
    {
        private VehicleDetailsDAO vehicleDetailsDAO;
        private  UpdateVehicleDetailsAsyncTask(VehicleDetailsDAO vehicleDetailsDAO){
            this.vehicleDetailsDAO=vehicleDetailsDAO;
        }
        @Override
        protected Void doInBackground(VehicleDetails... vehicleDetails) {
            vehicleDetailsDAO.update(vehicleDetails[0]);
            return null;
        }
    }


    public  void delete(VehicleDetails vehicleDetails){
        new VehicleDetailsRepository.DeleteVehicleDetailsAsyncTask(vehicleDetailsDAO).execute(vehicleDetails);
    }
    private static class DeleteVehicleDetailsAsyncTask extends AsyncTask<VehicleDetails,Void,Void>
    {
        private VehicleDetailsDAO vehicleDetailsDAO;
        private  DeleteVehicleDetailsAsyncTask(VehicleDetailsDAO vehicleDetailsDAO){
            this.vehicleDetailsDAO=vehicleDetailsDAO;
        }
        @Override
        protected Void doInBackground(VehicleDetails... vehicleDetails) {
            vehicleDetailsDAO.delete(vehicleDetails[0]);
            return null;
        }
    }

    public  void deleteAllVehicleDetails(){
        new VehicleDetailsRepository.DeleteAllVehicleDetailsAsyncTask(vehicleDetailsDAO).execute();
    }
    private static class DeleteAllVehicleDetailsAsyncTask extends AsyncTask<VehicleDetails,Void,Void>
    {
        private VehicleDetailsDAO vehicleDetailsDAO;
        private  DeleteAllVehicleDetailsAsyncTask(VehicleDetailsDAO vehicleDetailsDAO){
            this.vehicleDetailsDAO=vehicleDetailsDAO;
        }
        @Override
        protected Void doInBackground(VehicleDetails... vehicleDetails) {
            vehicleDetailsDAO.deleteAllVehicleDetails();
            return null;
        }
    }

    public LiveData<List<VehicleDetails>> getAllVehicleDetails(){
        return getAllVehicleDetails;
    }




}
