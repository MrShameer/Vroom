package com.example.vroom.ui.vehicledetails;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vroom.R;
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
            Intent intent=new Intent(VehicleSucessfull.this, VehicleExplore.class);
            startActivity(intent);
        }
    });
    }

}