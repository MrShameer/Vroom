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
import com.example.vroom.database.VehicleDetails.VehicleDetails;
import com.example.vroom.ui.lessor.adapter.VehicleMyVehicleAdapter;
import com.example.vroom.ui.lessor.model.MyVehicleListData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class LessorTabList extends Fragment {
    Request request = new Request();
    RecyclerView rc_unlisted;
    ArrayList<MyVehicleListData> myVehicleListData;
    VehicleMyVehicleAdapter vehicleMyVehicleAdapter;
    Boolean list;
    public LessorTabList(Boolean list) {
        this.list=list;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root =inflater.inflate(R.layout.fragment_lessor_myvehicle_list, container, false);

        rc_unlisted=(RecyclerView) root.findViewById(R.id.rc_list);
        rc_unlisted.setHasFixedSize(true);
        rc_unlisted.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        myVehicleListData=new ArrayList<MyVehicleListData>();
        vehicleMyVehicleAdapter=new VehicleMyVehicleAdapter(myVehicleListData);
        rc_unlisted.setAdapter(vehicleMyVehicleAdapter);
        new mytask().execute();
        return root;
    }

    private class mytask extends AsyncTask<Void,Void,Void> {
        String respond;
        JSONObject jsonObject;
        @Override
        protected Void doInBackground(Void... voids) {
            String token = TokenHandler.read(TokenHandler.USER_TOKEN, null);
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("list", String.valueOf(list))
                    .build();
            //FOR THIS PART I'M NOT GONNA PAGINATE
//            System.out.println("sSSDSADDSFSDFDSFSDF");
            respond = request.PostHeader(requestBody,getString(R.string.lessorlist),token);
            try {
                JSONArray jsonArray = new JSONArray(respond);
                for (int i=0; i<jsonArray.length(); i++){
                    jsonObject = jsonArray.getJSONObject(i);
                    myVehicleListData.add(new MyVehicleListData(jsonObject.getString("brand"),
                            "4.8",
                            jsonObject.getString("passanger"),
                            jsonObject.getString("door"),
                            jsonObject.getString("luggage"),
                            jsonObject.getString("gallon"),
                            jsonObject.getString("rent"),
                            list));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            vehicleMyVehicleAdapter.notifyDataSetChanged();
        }
    }

}