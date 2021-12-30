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

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class VehicleExplore extends Fragment {
    Request request = new Request();
    ArrayList<VehicleDetails> vehicleDetailswish;
    ArrayList<VehicleDetails> vehicleDetailssuggest;
    Wishlist_adapter adapterWish;
    Explore_adapter adapterExplore;
    String token = TokenHandler.read(TokenHandler.USER_TOKEN, null);
    int pastVisiblesItems, visibleItemCount, totalItemCount, currentPage=0, lastPage=1;
    private boolean loading = true;
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

        recycleryoumayalsolike=root.findViewById(R.id.rc_youmayalsolike);
        recycleryoumayalsolike.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleryoumayalsolike.setLayoutManager(llm);
        adapterExplore=new Explore_adapter();
        recycleryoumayalsolike.setAdapter(adapterExplore);
        vehicleDetailssuggest=new ArrayList<VehicleDetails>();
        new suggesttask().execute();
        recycleryoumayalsolike.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dx > 0) {
                    visibleItemCount = llm.getChildCount();
                    totalItemCount = llm.getItemCount();
                    pastVisiblesItems = llm.findFirstVisibleItemPosition();
                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            if (currentPage<lastPage){
                                currentPage++;
                                new suggesttask().execute();
                            }
                            loading = true;
                        }
                    }
                }
            }
        });

        recyclerwishlist=(RecyclerView) root.findViewById(R.id.rc_wishlist);
        recyclerwishlist.setHasFixedSize(true);
        recyclerwishlist.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        adapterWish=new Wishlist_adapter();

        recyclerwishlist.setAdapter(adapterWish);
        vehicleDetailswish=new ArrayList<VehicleDetails>();
        new wishlisttask().execute();
        return root;
    }

    private class wishlisttask extends AsyncTask<Void,Void,Void> {
        String respond;
        JSONArray jsonArray = null;
        @Override
        protected Void doInBackground(Void... voids) {
            RequestBody requestBody = RequestBody.create(null, new byte[0]);
            respond = request.PostHeader(requestBody,getString(R.string.wishlist),token);
            try {
                jsonArray=new JSONArray(respond);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    JSONObject vehicle = jsonObject.getJSONObject("vehicle");
                    //TODO
                    // APE WTH TU
                    vehicleDetailswish.add(new VehicleDetails(vehicle.getJSONObject("owner").getString("name"),
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
            adapterWish.setWishlistDetails(vehicleDetailswish);
        }
    }

    private class suggesttask extends AsyncTask<Void,Void,Void> {
        String respond;
        JSONObject jsonObject;
        RequestBody requestBody;
        @Override
        protected Void doInBackground(Void... voids) {
            requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("type", "all")
                    .build();
            respond = request.RequestPost(requestBody,getString(R.string.vehiclelist)+"?page="+currentPage);
            try {
                jsonObject = new JSONObject(respond);
                lastPage = Integer.parseInt(jsonObject.getString("last_page"));
                JSONArray jsonArray = new JSONArray(jsonObject.getString("data"));
                for (int i=0; i<jsonArray.length(); i++){
                    jsonObject = jsonArray.getJSONObject(i);
                    vehicleDetailssuggest.add(new VehicleDetails(jsonObject.getJSONObject("owner").getString("name"),jsonObject.getJSONObject("owner").getString("id"), jsonObject.getString("plat"), jsonObject.getString("brand"), jsonObject.getString("model"),jsonObject.getString("insurance"), jsonObject.getString("age"),jsonObject.getString("passanger"), jsonObject.getString("door"), jsonObject.getString("luggage"), jsonObject.getString("gallon"), jsonObject.getString("rent")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapterExplore.setVehicleDetails(vehicleDetailssuggest);
        }
    }

}