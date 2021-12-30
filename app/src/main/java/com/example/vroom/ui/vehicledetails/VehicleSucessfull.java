package com.example.vroom.ui.vehicledetails;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.vroom.MainActivity;
import com.example.vroom.R;
import com.example.vroom.ui.status.StatusFragment;
import com.example.vroom.ui.vehicle.vehicle_tab.VehicleExplore;
import com.sanojpunchihewa.glowbutton.GlowButton;

public class VehicleSucessfull extends AppCompatActivity {
    GlowButton btn_ok;
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_request_succesfull);
    btn_ok=findViewById(R.id.btn_ok);
    btn_ok.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Intent intent=new Intent(VehicleSucessfull.this, MainActivity.class);
//            startActivity(intent);
            finish();
        }
    });
    }

}