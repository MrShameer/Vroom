package com.example.vroom.ui.vehicle.vehicle_tab;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroom.R;
import com.example.vroom.api.Request;
import com.example.vroom.database.TokenHandler;
import com.example.vroom.database.VehicleDetails.VehicleDetails;
import com.example.vroom.ui.vehicle.adapter.Explore_adapter;
import com.example.vroom.ui.vehicle.adapter.Wishlist_adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import okhttp3.RequestBody;

public class VehicleExplore extends Fragment {
    Request request = new Request();
    ArrayList<VehicleDetails> vehicleDetails;
    Wishlist_adapter adapterWish;
    private static final String ARG_SECTION_NUMBER = "section_number";
    RecyclerView recycleryoumayalsolike,recyclerwishlist;
    public static VehicleExplore newInstance(int index) {
        VehicleExplore fragment = new VehicleExplore();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_vehicle_explore, container, false);

        recycleryoumayalsolike=(RecyclerView) root.findViewById(R.id.rc_youmayalsolike);
        recycleryoumayalsolike.setHasFixedSize(true);
        recycleryoumayalsolike.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));

        recyclerwishlist=(RecyclerView) root.findViewById(R.id.rc_wishlist);
        recyclerwishlist.setHasFixedSize(true);
        recyclerwishlist.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));

         Explore_adapter adapterExplore=new Explore_adapter();
         adapterWish=new Wishlist_adapter();
        recycleryoumayalsolike.setAdapter(adapterExplore);
        recyclerwishlist.setAdapter(adapterWish);
        vehicleDetails=new ArrayList<VehicleDetails>();

        new mytask().execute();
        return root;
    }

    private class mytask extends AsyncTask<Void,Void,Void> {
        String respond;
        JSONArray jsonArray = null;
        @Override
        protected Void doInBackground(Void... voids) {
            String token = TokenHandler.read(TokenHandler.USER_TOKEN, null);
            RequestBody requestBody = RequestBody.create(null, new byte[0]);
            respond = request.PostHeader(requestBody,getString(R.string.wishlist),token);
            try {
                jsonArray=new JSONArray(respond);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    JSONObject vehicle = jsonObject.getJSONObject("vehicle");
                    vehicleDetails.add(new VehicleDetails(vehicle.getJSONObject("owner").getString("name"),
                            vehicle.getJSONObject("owner").getString("id"),
                            jsonObject.getString("plat"),
                            vehicle.getString("brand"),
                            vehicle.getString("model"),
                            vehicle.getString("insurance"),
                            "WTH",
                            vehicle.getString("passanger"),
                            vehicle.getString("door"),
                            vehicle.getString("luggage"),
                            vehicle.getString("gallon"),
                            vehicle.getString("rent")
                    ));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapterWish.setWishlistDetails(vehicleDetails);
        }
    }
}