package com.example.vroom;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity{
//public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    MapView mapview;
    MarkerOptions now,destination;
    Double distance;
    TextView tv_gonow;
    LinearLayout ll_map;
    LatLng now1;
    RecyclerView recycler;
    RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_vehicle, R.id.navigation_status, R.id.navigation_chat, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
//
//        mapview=findViewById(R.id.Mapview);
//        mapview.getMapAsync(this);
//        mapview.onCreate(savedInstanceState);
//        now1=new LatLng(2.9911, 101.788);
//        now=new MarkerOptions().position(now1).title("Marker in Now");
//        destination=new MarkerOptions().position(new LatLng(2.9303, 101.7774)).title("Marker in Destination");
//        tv_gonow=findViewById(R.id.tv_gonow);
//        tv_gonow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Uri gmmIntentUri = Uri.parse("google.navigation:q=2.976329428,+101.78749685&mode=d");
//                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//                mapIntent.setPackage("com.google.android.apps.maps");
//                startActivity(mapIntent);
//
//            }
//        });

//        recycler=(RecyclerView) findViewById(R.id.rc_top);
//        top();
    }
//    public void top() {
//        recycler.setHasFixedSize(true);
//        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
//        ArrayList<topvehicle_data> card=new ArrayList<topvehicle_data>();
//        card.add(new topvehicle_data("Top Week","Perodua Bezza","4","4","3","36",R.drawable.perodua_bezza));
//        card.add(new topvehicle_data("Top Month","Perodua Bezza","4","4","3","36",R.drawable.perodua_bezza));
//        card.add(new topvehicle_data("Top Year","Perodua Bezza","4","4","3","36",R.drawable.perodua_bezza));
//
//        adapter=new topvehicle_adapter(card);
//        recycler.setAdapter(adapter);
//    }
//
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//        // Add a marker in Sydney and move the camera
//        mMap.addMarker(now);
//        mMap.addMarker(destination);
//        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(Marker marker) {
//                return true;
//            }
//        });
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(now1));
//
//
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        mapview.onStart();
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        mapview.onResume();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        mapview.onPause();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        mapview.onStop();
//    }
//
//    @Override
//    public void onLowMemory() {
//        super.onLowMemory();
//        mapview.onLowMemory();
//    }

}