package com.example.vroom.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroom.R;
import com.example.vroom.ui.home.recyclervire.Topvehicle.topvehicle_adapter;
import com.example.vroom.ui.home.recyclervire.Topvehicle.topvehicle_data;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Map;

public class HomeFragment extends Fragment implements OnMapReadyCallback {

    private HomeViewModel homeViewModel;
    private GoogleMap mMap;
    MapView mapview;
    MarkerOptions now,destination;
    Double distance;
    TextView tv_gonow;
    LinearLayout ll_map;
    LatLng now1;
    RecyclerView recycler;
    RecyclerView.Adapter adapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

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

        recycler=(RecyclerView) root.findViewById(R.id.rc_top);
        top();
        return root;
    }
    public void top() {
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        ArrayList<topvehicle_data> card=new ArrayList<topvehicle_data>();
        card.add(new topvehicle_data("Top Week","Perodua Bezza","4","4","3","36",R.drawable.perodua_bezza));
        card.add(new topvehicle_data("Top Month","Perodua Bezza","4","4","3","36",R.drawable.perodua_bezza));
        card.add(new topvehicle_data("Top Year","Perodua Bezza","4","4","3","36",R.drawable.perodua_bezza));

        adapter=new topvehicle_adapter(card);
        recycler.setAdapter(adapter);
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