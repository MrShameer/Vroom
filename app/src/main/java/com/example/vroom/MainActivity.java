package com.example.vroom;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroom.api.Request;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {
    //public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    Request request = new Request();
    private GoogleMap mMap;
    MapView mapview;
    MarkerOptions now, destination;
    Double distance;
    TextView tv_gonow;
    LinearLayout ll_map;
    LatLng now1;
    RecyclerView recycler;
    RecyclerView.Adapter adapter;
    private static final String TAG = "Permission Status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_vehicle, R.id.navigation_status, R.id.navigation_chat, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
        new mytask().execute();
    }

    private class mytask extends AsyncTask<Void,Void,Void> {
        String respond;
        JSONObject jsonObject = null;
        @Override
        protected Void doInBackground(Void... voids) {
            String token = "marhCW1SodbIZ5nAvigQo2BKM1Wymvpa5np2R0LH"; //BAGI TOKEN KT SINI
            RequestBody requestBody = RequestBody.create(null, new byte[0]);

            respond = request.PostHeader(requestBody,getString(R.string.validate),token);
            try {
                jsonObject = new JSONObject(respond);
                if (jsonObject.has("id")){
                    System.out.println(jsonObject.getString("id"));//NI ID EHH SO STORE MANE2
                    jsonObject.getString("name");
                    jsonObject.getString("email");
                    jsonObject.getString("role");
                    jsonObject.getString("phone");
                    //SEMUA NI STORE SEKALI EH
                }
                else {
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}


