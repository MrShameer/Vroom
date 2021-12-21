package com.example.vroom.database.VehicleDetails;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.vroom.Login;
import com.example.vroom.MainActivity;
import com.example.vroom.R;
import com.example.vroom.api.Request;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;


@Database(entities = {VehicleDetails.class},version = 1)
public abstract class VehicleDetailsDatabase extends RoomDatabase {

    //singleton class
    private static VehicleDetailsDatabase instance;

    public abstract VehicleDetailsDAO vehicleDetailsDAO();

    //to ensure the database doesnt have any duplicates
    static synchronized VehicleDetailsDatabase getInstance(Context context){
        //instance.clearAllTables();
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
        Request request = new Request();
        private PopulateDbAsyncTask(VehicleDetailsDatabase db){
            System.out.println("HJSDASASDASDASDASDASDASDASD");
            vehicleDetailsDAO=db.vehicleDetailsDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
                String respond;
                JSONObject jsonObject = null;
                RequestBody requestBody = RequestBody.create(null, new byte[0]);

                respond = request.RequestPost(requestBody, String.valueOf(R.string.carlist));
                System.out.println(respond);
//                    try {
//                        jsonObject = new JSONObject(respond);
//                        if (jsonObject.has("access_token")){
//                            System.out.println(jsonObject.getString("access_token"));//NI TOKEN EHH SO STORE MANE2
//                            System.out.println(jsonObject.getString("role"));//NI ROLE EHH
//                            Intent intent = new Intent(Login.this, MainActivity.class);
//                            startActivity(intent);
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    return null;
            //table user (username, full name, email, current address, phone number, password)
//            vehicleDetailsDAO.insert(new VehicleDetails("BND 3834","Mohamad Anwar","No.19 Jalan Surada 2","4.9","5","5","5","Perodua","Bezza","Silver","3","Alliance","50","4.1","60"));
//            vehicleDetailsDAO.insert(new VehicleDetails("BND 1991","Mohamad Shameer","No.19 Jalan Surada 2","4.9","4","4","4","Perodua","Myvi","Silver","3","Alliance","40","4.1","90"));
//            vehicleDetailsDAO.insert(new VehicleDetails("BND 3111","Mohamad Chong","No.19 Jalan Surada 2","4.9","6","6","6","Perodua","Saga","Silver","3","Alliance","60","4.1","100"));


            return null;
        }
    }
}
