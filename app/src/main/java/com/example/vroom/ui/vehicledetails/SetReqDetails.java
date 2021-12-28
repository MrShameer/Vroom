package com.example.vroom.ui.vehicledetails;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vroom.R;

public class SetReqDetails extends AppCompatActivity {
    ImageButton btn_back;
    TextView btn_req;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_details);

        //setup Back Button
        btn_back=findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAndRemoveTask();
            }
        });

        btn_req=findViewById(R.id.btn_req);
        btn_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SetReqDetails.this, VehicleSucessfull.class);
                startActivity(intent);
            }
        });
    }

}