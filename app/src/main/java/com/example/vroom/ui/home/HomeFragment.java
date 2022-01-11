package com.example.vroom.ui.home;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroom.R;
import com.example.vroom.api.Request;
import com.example.vroom.database.TokenHandler;
import com.example.vroom.database.User.User;
import com.example.vroom.database.User.UserViewModel;
import com.example.vroom.database.VehicleDetails.VehicleDetails;
import com.example.vroom.database.VehicleDetails.VehicleViewModel;
import com.example.vroom.ui.home.recyclervire.Topvehicle.topvehicle_adapter;
import com.example.vroom.ui.profile.LocationPicker;
import com.example.vroom.ui.vehicle.vehicle_tab.VehicleExplore;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class HomeFragment extends Fragment implements OnMapReadyCallback {
    Request request = new Request();
    int pastVisiblesItems, visibleItemCount, totalItemCount, currentPage=0, lastPage=1;
    private GoogleMap mMap;
    MapView mapview;
    MarkerOptions now,destination;
    Double distance;
    TextView tv_gonow,tv_name;
    LinearLayout ll_map;
    private boolean loading = true;
    LatLng now1;
    RecyclerView recycler;
    topvehicle_adapter adapter;
    ScrollView scrollview;
    ArrayList<VehicleDetails> vehicleDetailsTop;
    private UserViewModel userViewModel;
    private VehicleViewModel vehicleViewModel;
    NotificationManagerCompat notificationManagerCompat;
    Notification notification;
    CircleImageView user_image;
    String statusIC;
    SearchView sv_search;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        user_image=(CircleImageView) root.findViewById(R.id.user_image);
        File dir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + getActivity().getApplicationContext().getPackageName()
                + "/Picture/");
        String mImageName = TokenHandler.read(TokenHandler.USER_ID, null)+".jpg";

        File file = new File(dir, mImageName);
        if(file.exists()){
            Picasso.get().invalidate(file);
            Picasso.get().load(file).into(user_image);
        }else{
            Picasso.get().load(R.drawable.profile_image).into(user_image);
        }

        //Room & userViewModel
        tv_name=(TextView) root.findViewById(R.id.tv_name);
        userViewModel=new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getGetAllUser().observe(getViewLifecycleOwner(), users -> {
//                if (!users.isEmpty()){
                User currentUser=users.get(0);
                tv_name.setText(currentUser.getName());
                statusIC=currentUser.getIcstatus();
//                }
        });

        recycler=(RecyclerView) root.findViewById(R.id.rc_top);
        recycler.setHasFixedSize(true);
//        recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler.setLayoutManager(llm);

        adapter=new topvehicle_adapter();
        recycler.setAdapter(adapter);

        vehicleDetailsTop=new ArrayList<VehicleDetails>();
        new task().execute();
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                                new task().execute();
                            }
                            loading = true;
                        }
                    }
                }
            }
        });
//        vehicleViewModel=new ViewModelProvider(this).get(VehicleViewModel.class);
//            vehicleViewModel.getGetAllVehicleDetails().observe(getViewLifecycleOwner(), new Observer<List<VehicleDetails>>() {
//            @Override
//            public void onChanged(@Nullable List<VehicleDetails>vehicleDetails) {
//                adapter.setVehicleDetails(vehicleDetails);
//            }
//        });

        mapview=(MapView)root.findViewById(R.id.Mapview);
        mapview.getMapAsync(this);
        mapview.onCreate(savedInstanceState);
        now1=new LatLng(2.9911, 101.788);
        now=new MarkerOptions().position(now1).title("Marker in Now");
        destination=new MarkerOptions().position(new LatLng(2.9303, 101.7774)).title("Marker in Destination");
        tv_gonow=(TextView)root.findViewById(R.id.tv_gonow);
        tv_gonow.setOnClickListener(view -> {
            Uri gmmIntentUri = Uri.parse("google.navigation:q=2.976329428,+101.78749685&mode=d");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);

        });

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel("myCh", "My Channel", NotificationManager.IMPORTANCE_DEFAULT);
//            channel.setDescription("Your IC Has been Reviewed");
//
//            NotificationManager manager = getActivity().getSystemService(NotificationManager.class);
//            manager.createNotificationChannel(channel);
//        }
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(root.getContext(),"myCh")
//                .setSmallIcon(R.drawable.icon)
//                .setContentTitle("Your IC Is Done")
//                .setContentText("Your IC Has been Reviewed")
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
//
//        notification=builder.build();
//        notificationManagerCompat= NotificationManagerCompat.from(root.getContext());
//        if(statusIC=="IC is Under Review"){
//            //Toast.makeText(root.getContext(), "Notification On", Toast.LENGTH_SHORT).show();
//            notificationManagerCompat.notify(1,notification);}
//        else {
//            Toast.makeText(root.getContext(), "Notification On", Toast.LENGTH_SHORT).show();
//            notificationManagerCompat.notify(1,notification);}


        sv_search=(SearchView) root.findViewById(R.id.sv_search);
        sv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), LocationPicker.class);
                startActivity(intent);
            }
        });

        return root;
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        mMap.addMarker(now);
        mMap.addMarker(destination);
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                return true;
            }
        });
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(now1));
    }

    private class task extends AsyncTask<Void,Void,Void> {
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
                    vehicleDetailsTop.add(new VehicleDetails(jsonObject.getJSONObject("owner").getString("name"),jsonObject.getJSONObject("owner").getString("id"), jsonObject.getString("plat"), jsonObject.getString("brand"), jsonObject.getString("model"),jsonObject.getString("insurance"), jsonObject.getString("age"),jsonObject.getString("passanger"), jsonObject.getString("door"), jsonObject.getString("luggage"), jsonObject.getString("gallon"), jsonObject.getString("rent")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.setVehicleDetails(vehicleDetailsTop);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mapview.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapview.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapview.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapview.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapview.onLowMemory();
    }

}