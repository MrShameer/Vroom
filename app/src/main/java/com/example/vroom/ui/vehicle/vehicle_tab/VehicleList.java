package com.example.vroom.ui.vehicle.vehicle_tab;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vroom.R;
import com.example.vroom.api.Request;
import com.example.vroom.database.VehicleDetails.VehicleDetails;
import com.example.vroom.ui.vehicle.adapter.Vehicle_adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class VehicleList extends Fragment {
    RecyclerView recyclerViewCar;
    Request request = new Request();
    Vehicle_adapter vehicle_adapter;
    List<VehicleDetails> vehicleDetails= new ArrayList<>();

    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount, currentPage=0, lastPage=1;
    String type;

    public VehicleList(String type) {
        this.type=type;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root =inflater.inflate(R.layout.fragment_vehicle_list, container, false);
        recyclerViewCar= root.findViewById(R.id.rc_car);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewCar.setLayoutManager(llm);
        vehicle_adapter=new Vehicle_adapter();
        recyclerViewCar.setAdapter(vehicle_adapter);
        currentPage++;
        new send().execute();
        recyclerViewCar.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    visibleItemCount = llm.getChildCount();
                    totalItemCount = llm.getItemCount();
                    pastVisiblesItems = llm.findFirstVisibleItemPosition();
                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            if (currentPage<lastPage){
                                currentPage++;
                                new send().execute();
                            }
                            loading = true;
                        }
                    }
                }
            }
        });
        return root;
    }

    private class send extends AsyncTask<Void,Void,Void> {
        String respond;
        JSONObject jsonObject;
        RequestBody requestBody;
        @Override
        protected Void doInBackground(Void... voids) {
            requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("type", type)
                    .build();
            respond = request.RequestPost(requestBody,getString(R.string.vehiclelist)+"?page="+currentPage);
            try {
                jsonObject = new JSONObject(respond);
                lastPage = Integer.parseInt(jsonObject.getString("last_page"));
                JSONArray jsonArray = new JSONArray(jsonObject.getString("data"));
                for (int i=0; i<jsonArray.length(); i++){
                    jsonObject = jsonArray.getJSONObject(i);
                    vehicleDetails.add(new VehicleDetails(jsonObject.getJSONObject("owner").getString("name"),jsonObject.getJSONObject("owner").getString("id"), jsonObject.getString("plat"), jsonObject.getString("brand"), jsonObject.getString("model"),jsonObject.getString("insurance"), jsonObject.getString("age"),jsonObject.getString("passanger"), jsonObject.getString("door"), jsonObject.getString("luggage"), jsonObject.getString("gallon"), jsonObject.getString("rent")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            vehicle_adapter.setVehicleDetails(vehicleDetails);
        }
    }
}