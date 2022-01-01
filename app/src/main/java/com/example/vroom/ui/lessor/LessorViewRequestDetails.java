package com.example.vroom.ui.lessor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vroom.R;
import com.example.vroom.ui.vehicledetails.SetReqDetails;
import com.example.vroom.ui.vehicledetails.VehicleSucessfull;

public class LessorViewRequestDetails extends AppCompatActivity {

    ImageButton btn_back;
    ImageView iv_vehicle;
    TextView tv_carbrand,tv_startdate,tv_enddate,tv_pickuplocation,tv_pickupdate,tv_cost;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessor_request_details);

        tv_carbrand=findViewById(R.id.tv_carbrand);
        tv_startdate=findViewById(R.id.tv_startdate);
        tv_enddate=findViewById(R.id.tv_enddate);
        tv_pickuplocation=findViewById(R.id.tv_pickuplocation);
        tv_pickupdate=findViewById(R.id.tv_pickupdate);
        tv_cost=findViewById(R.id.tv_cost);
        iv_vehicle=findViewById(R.id.iv_vehicle);

        //setup Back Button
        btn_back=findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAndRemoveTask();
            }
        });
    }

}