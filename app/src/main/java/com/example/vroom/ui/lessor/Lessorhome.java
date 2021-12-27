package com.example.vroom.ui.lessor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroom.R;
import com.example.vroom.ui.lessor.adapter.VehicleListAdapter;
import com.example.vroom.ui.lessor.model.VehicleListData;
import com.example.vroom.ui.wishlist.adapter.WishlistAdapter;
import com.example.vroom.ui.wishlist.model.WishlistData;

import java.util.ArrayList;

public class Lessorhome extends AppCompatActivity {
    RecyclerView rc_vehicle;
    RecyclerView.Adapter adapter;
    ImageButton btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessorhome);

        rc_vehicle=(RecyclerView) findViewById(R.id.rc_vehicle);
        rc_vehicle.setHasFixedSize(true);
        rc_vehicle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));


        ArrayList<VehicleListData> vehicleListData=new ArrayList<VehicleListData>();
        vehicleListData.add(new VehicleListData("Myvi","10","130","15000","BND6731"));
        vehicleListData.add(new VehicleListData("Myvi","10","130","15000","BND6731"));
        vehicleListData.add(new VehicleListData("Myvi","10","130","15000","BND6731"));
        vehicleListData.add(new VehicleListData("Myvi","10","130","15000","BND6731"));
        adapter=new VehicleListAdapter(vehicleListData);
        rc_vehicle.setAdapter(adapter);

        btn_back=findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAndRemoveTask();
            }
        });
    }
}
