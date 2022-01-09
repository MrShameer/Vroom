package com.example.vroom.ui.home;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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
import com.example.vroom.database.TokenHandler;
import com.example.vroom.database.User.User;
import com.example.vroom.database.User.UserViewModel;
import com.example.vroom.database.VehicleDetails.VehicleDetails;
import com.example.vroom.database.VehicleDetails.VehicleViewModel;
import com.example.vroom.ui.home.recyclervire.Topvehicle.topvehicle_adapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    MapView mapview;
    MarkerOptions now,destination;
    Double distance;
    TextView tv_gonow,tv_name;
    LinearLayout ll_map;
    LatLng now1;
    RecyclerView recycler;
    ScrollView scrollview;
    private UserViewModel userViewModel;
    private VehicleViewModel vehicleViewModel;
    NotificationManagerCompat notificationManagerCompat;
    Notification notification;
    CircleImageView user_image;
    String statusIC;
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
        userViewModel.getGetAllUser().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
//                if (!users.isEmpty()){
                    User currentUser=users.get(0);
                    tv_name.setText(currentUser.getName());
                    statusIC=currentUser.getIcstatus();
//                }
            }
        });

        recycler=(RecyclerView) root.findViewById(R.id.rc_top);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));

        final topvehicle_adapter adapter=new topvehicle_adapter();
        recycler.setAdapter(adapter);

        vehicleViewModel=new ViewModelProvider(this).get(VehicleViewModel.class);
            vehicleViewModel.getGetAllVehicleDetails().observe(getViewLifecycleOwner(), new Observer<List<VehicleDetails>>() {
            @Override
            public void onChanged(@Nullable List<VehicleDetails>vehicleDetails) {
                adapter.setVehicleDetails(vehicleDetails);
            }
        });

        mapview=(MapView)root.findViewById(R.id.Mapview);
        mapview.getMapAsync(this);
        mapview.onCreate(savedInstanceState);
        now1=new LatLng(2.9911, 101.788);
        now=new MarkerOptions().position(now1).title("Marker in Now");
        destination=new MarkerOptions().position(new LatLng(2.9303, 101.7774)).title("Marker in Destination");
        tv_gonow=(TextView)root.findViewById(R.id.tv_gonow);
        tv_gonow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("google.navigation:q=2.976329428,+101.78749685&mode=d");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            }
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