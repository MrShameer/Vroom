package com.example.vroom.ui.profile;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vroom.database.User.User;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.libraries.places.api.Places;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.example.vroom.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.material.button.MaterialButton;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocationPicker extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.OnConnectionFailedListener{
    //widgets
    private AutoCompleteTextView mSearchText;
    private ImageView mGps;
    //vars
    private Boolean mLocationPermissionsGranted = false;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    MaterialButton saveLocation;
    private GoogleApiClient mGoogleApiClient;
    String current;
    String[] clicked = {"no"};
    final String[] longlat = {"",""};
    String Address="";
    User currentuser;
    Intent intent;
    String data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_location_picker);
        mSearchText = findViewById(R.id.input_search);
        saveLocation=findViewById(R.id.saveLocation);
        mGps = findViewById(R.id.ic_gps);
        getLocationPermission();
        current= getIntent().getStringExtra("TITLE");
        saveLocation.setOnClickListener(v -> {
            //TODO save address line, lat and long dari sini
//            Address
//            longlat[] dalam bentuk String
        });

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "Map is Ready", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onMapReady: map is ready");
        mMap = googleMap;
        eventsetup();

        if (mLocationPermissionsGranted) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
        }
    }

    private static final String TAG = "MapActivity";

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15f;
    private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(
            new LatLng(-40, -168), new LatLng(71, 136));


    private void init(){
        Log.d(TAG, "init: initializing");

        mSearchText.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if(actionId == EditorInfo.IME_ACTION_SEARCH
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                    || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER){
                String searchString = mSearchText.getText().toString();
                //execute our method for searching
                geoLocate(searchString);
            }
            return false;
        });

        mGps.setOnClickListener(view -> {
            Log.d(TAG, "onClick: clicked gps icon");
            getDeviceLocation();
        });

        hideSoftKeyboard();
    }

    public void eventsetup(){
        intent=getIntent();
        currentuser = (User) intent.getSerializableExtra("DATA");
        data = intent.getStringExtra("TITLE");
        if(data.equals("Address 1")) {
            Toast.makeText(this,"Event Setup",Toast.LENGTH_SHORT).show();
            if(currentuser.getAddress().equals(null)){

//                  init();
                Toast.makeText(this,"No Location",Toast.LENGTH_SHORT).show();
                getDeviceLocation();
                init();
            }
            else{
                geoLocate(currentuser.getAddress());
            }
        }
        else{
            if(currentuser.getAddress2().equals(null)){
                Toast.makeText(this,"No Location",Toast.LENGTH_SHORT).show();
                getDeviceLocation();
                init();
            }
            else{
                geoLocate(currentuser.getAddress2());
            }
        }
    }
    private void geoLocate(String searchString){
        Log.d(TAG, "geoLocate: geolocating");
        String result = null;
        Geocoder geocoder = new Geocoder(LocationPicker.this);
        List<Address> list = new ArrayList<>();
        try{
            list = geocoder.getFromLocationName(searchString, 1);
        }catch (IOException e){
            Log.e(TAG, "geoLocate: IOException: " + e.getMessage() );
        }
        if(list.size() > 0){
            Address address = list.get(0);
            Toast.makeText(this, "Location Found", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "geoLocate: found a location: " + address.toString());

            result=address.getAddressLine(0)+" "+
                    address.getLocality()+" "+
                    address.getAdminArea()+" "+
                    address.getPostalCode()+" "
                    ;
            mSearchText.getText().clear();
            mSearchText.setHint(result);
            moveCamera(new LatLng(address.getLatitude(), address.getLongitude()), DEFAULT_ZOOM,address.getAddressLine(0));
        }
        else if(list.size() == 0){
            Toast.makeText(this, "Location does not Exist\n"+"Please Input a Real Location", Toast.LENGTH_SHORT).show();
        }

    }

    private void getDeviceLocation(){
        Log.d(TAG, "getDeviceLocation: getting the devices current location");

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try{
            if(mLocationPermissionsGranted){

                @SuppressLint("MissingPermission") final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Log.d(TAG, "onComplete: found location!");
                        Location currentLocation = (Location) task.getResult();
                        moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),DEFAULT_ZOOM,"My Location");

                    }else{
                        Log.d(TAG, "onComplete: current location is null");
                        Toast.makeText(LocationPicker.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }catch (SecurityException e){
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage() );
        }
    }

    private void moveCamera(LatLng latLng, float zoom, String title){
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        if(!title.equals("My Location")){

            MarkerOptions options = new MarkerOptions()
                    .position(latLng)
                    .title(title)
                    .draggable(true);

            mMap.clear();
            mMap.addMarker(options);

            mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {

                @Override
                public void onMarkerDragStart(Marker marker) {

                }

                @Override
                public void onMarkerDragEnd(Marker marker) {
                LatLng pos = marker.getPosition();
                marker.getTitle();
//                marker.setTitle(pos);
                longlat[0] = String.valueOf(pos.latitude);
                longlat[1] = String.valueOf(pos.longitude);
                String result = null;
                Geocoder geocoder = new Geocoder(LocationPicker.this);
                List<Address> list = new ArrayList<>();
                try{
                    list = geocoder.getFromLocation(pos.latitude,pos.longitude, 1);
                }catch (IOException e){
                    Log.e(TAG, "geoLocate: IOException: " + e.getMessage() );
                }
                if(list.size() > 0){
                    Address address = list.get(0);
                    Log.d(TAG, "geoLocate: found a location: " + address.toString());

                    result=address.getAddressLine(0)+" "+
                            address.getLocality()+" "+
                            address.getAdminArea()+" "+
                            address.getPostalCode()+" "
                    ;
                    mSearchText.getText().clear();
                    mSearchText.setHint(result);
                    Address=address.getAddressLine(0);
                    marker.setTitle(address.getAddressLine(0));
            Toast.makeText(LocationPicker.this, address.getAddressLine(0), Toast.LENGTH_SHORT).show();

            }
                }

                @Override
                public void onMarkerDrag(Marker marker) {

                }
            });
            mMap.setOnMarkerClickListener(marker -> {
                if(clicked[0] =="no"){
                marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                marker.setDraggable(false);
                    saveLocation.setVisibility(View.VISIBLE);
                    clicked[0] ="click";}
                else{
                    marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                    saveLocation.setVisibility(View.INVISIBLE);
                    marker.setDraggable(true);
                    clicked[0] ="no";
                }
                return false;
            });

        }

        hideSoftKeyboard();
    }

    private void initMap(){
        Log.d(TAG, "initMap: initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(LocationPicker.this);
    }

    private void getLocationPermission(){
        Log.d(TAG, "getLocationPermission: getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionsGranted = true;
                initMap();
            }else{
                ActivityCompat.requestPermissions(this,permissions,LOCATION_PERMISSION_REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(this,permissions,LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "onRequestPermissionsResult: called.");
        mLocationPermissionsGranted = false;

        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionsGranted = false;
                            Log.d(TAG, "onRequestPermissionsResult: permission failed");
                            return;
                        }
                    }
                    Log.d(TAG, "onRequestPermissionsResult: permission granted");
                    mLocationPermissionsGranted = true;
                    //initialize our map
                    initMap();
                }
            }
        }
    }

    private void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


}