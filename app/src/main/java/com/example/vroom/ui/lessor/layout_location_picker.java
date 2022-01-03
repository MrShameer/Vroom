package com.example.vroom.ui.lessor;

import android.location.LocationRequest;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroom.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

public class layout_location_picker extends AppCompatActivity implements OnMapReadyCallback {
    LocationCallback mLocationCallback;
    LocationRequest mLocationRequest;
    MapView mapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_location_picker);
        mapView=findViewById(R.id.mapView);

    }
        private FusedLocationProviderClient mFusedLocationProviderClient;
        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {

        }

        @Override
        public void onPointerCaptureChanged(boolean hasCapture) {

        }
    }
