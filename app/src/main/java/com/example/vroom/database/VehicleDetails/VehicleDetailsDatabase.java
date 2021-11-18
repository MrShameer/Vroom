package com.example.vroom.database.VehicleDetails;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {VehicleDetails.class},version = 1)
public abstract class VehicleDetailsDatabase extends RoomDatabase {

    //singleton class
    private static VehicleDetailsDatabase instance;

    public abstract VehicleDetailsDAO vehicleDetailsDAO();

    //to ensure the database doesnt have any duplicates
    static synchronized VehicleDetailsDatabase getInstance(Context context){
        if (instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    VehicleDetailsDatabase.class,"VehicleDetails_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();

        }
        return instance;

    }
    private static RoomDatabase.Callback roomCallback=new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new VehicleDetailsDatabase.PopulateDbAsyncTask(instance).execute();
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private VehicleDetailsDAO vehicleDetailsDAO;

        private PopulateDbAsyncTask(VehicleDetailsDatabase db){
            vehicleDetailsDAO=db.vehicleDetailsDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //table user (username, full name, email, current address, phone number, password)
            vehicleDetailsDAO.insert(new VehicleDetails("BND 3834","Mohamad Anwar","No.19 Jalan Surada 2","4.9","5","4","5","Perodua","Bezza","Silver","3","Alliance","39","4.1","60"));
            vehicleDetailsDAO.insert(new VehicleDetails("BND 1991","Mohamad Shameer","No.19 Jalan Surada 2","4.9","4","4","5","Perodua","Myvi","Silver","3","Alliance","39","4.1","90"));
            vehicleDetailsDAO.insert(new VehicleDetails("BND 3111","Mohamad Chong","No.19 Jalan Surada 2","4.9","5","4","5","Perodua","Saga","Silver","3","Alliance","39","4.1","100"));


            return null;
        }
    }
}
