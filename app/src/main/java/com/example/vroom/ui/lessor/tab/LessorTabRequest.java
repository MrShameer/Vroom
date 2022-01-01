package com.example.vroom.ui.lessor.tab;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroom.R;
import com.example.vroom.api.Request;
import com.example.vroom.database.TokenHandler;
import com.example.vroom.ui.lessor.adapter.LessorRentalStatusAdapter;
import com.example.vroom.ui.lessor.adapter.VehicleMyVehicleAdapter;
import com.example.vroom.ui.lessor.model.MyRentalStatusData;
import com.example.vroom.ui.lessor.model.MyVehicleListData;
import com.example.vroom.ui.vehicledetails.model.ReviewCard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class LessorTabRequest extends Fragment {
    RecyclerView rc_request;
    String status;
    LessorRentalStatusAdapter rentalStatusAdapter;
    Request request = new Request();
    ArrayList<MyRentalStatusData> myRentalStatusData;
    public LessorTabRequest(String status) {
        this.status=status;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root =inflater.inflate(R.layout.fragment_lessor_rental_accepted, container, false);
        rc_request=(RecyclerView) root.findViewById(R.id.rc_request);
        rc_request.setHasFixedSize(true);
        rc_request.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        myRentalStatusData=new ArrayList<MyRentalStatusData>();
//                myRentalStatusData.add(new MyRentalStatusData("BND 8478","Myvi","4.9","4","4","5","15","1500","pending","Mohamad Apan",
//                        "1410","online banking","1/1/2021","2/2/2021","Kajang","done","done"));
        rentalStatusAdapter=new LessorRentalStatusAdapter(myRentalStatusData);
        rc_request.setAdapter(rentalStatusAdapter);

        new mytask().execute();
        return root;
    }

    private class mytask extends AsyncTask<Void,Void,Void> {
        String respond;
        JSONObject jsonObject = null;
        JSONArray jsonArray;
        @Override
        protected Void doInBackground(Void... voids) {
            String token = TokenHandler.read(TokenHandler.USER_TOKEN, null);
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("status", status)
                    .build();
            respond = request.PostHeader(requestBody,getString(R.string.lessorrequest),token);
            try {
                jsonArray = new JSONArray(respond);
                for (int i=0; i<jsonArray.length(); i++){
                    jsonObject = jsonArray.getJSONObject(i);
                    JSONObject vehicle = jsonObject.getJSONObject("vehicle");
                    myRentalStatusData.add(new MyRentalStatusData(vehicle.getString("plat"),
                            vehicle.getString("brand"),"4.9",vehicle.getString("passanger"),
                            vehicle.getString("door"),vehicle.getString("luggage"),
                            vehicle.getString("gallon"),vehicle.getString("rent"),
                            status,
                            jsonObject.getJSONObject("lessee").getString("name"),
                            jsonObject.getString("total"),
                            jsonObject.getString("payment_type"),
                            jsonObject.getString("payment"),
                            jsonObject.getString("pickup"),
                            jsonObject.getString("return"),
                            jsonObject.getString("progress"),
                            jsonObject.getString("location")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            rentalStatusAdapter.notifyDataSetChanged();
        }
    }

}