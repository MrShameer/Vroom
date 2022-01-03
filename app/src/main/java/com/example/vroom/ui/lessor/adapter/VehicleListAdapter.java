package com.example.vroom.ui.lessor.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroom.R;
import com.example.vroom.ui.lessor.model.VehicleListData;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class VehicleListAdapter extends RecyclerView.Adapter<VehicleListAdapter.DesignViewHolder> {
    private List<VehicleListData> vehicleListData=new ArrayList<>();

    public VehicleListAdapter(ArrayList<VehicleListData> vehicleListData) {
        this.vehicleListData=vehicleListData;
    }
    @NonNull
    @Override
    public VehicleListAdapter.DesignViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_lessor_vehicle,parent,false);
        return new VehicleListAdapter.DesignViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull VehicleListAdapter.DesignViewHolder holder, int position) {
        VehicleListData currentVehicle= vehicleListData.get(position);

        Picasso.get().load("https://vroom.lepak.xyz/storage/picture/vehicle/"+currentVehicle.getPlatno()+".png").into(holder.vehiclePic, new Callback() {
            @Override
            public void onSuccess() {
            }
            @Override
            public void onError(Exception e) {
                Picasso.get().load(R.drawable.perodua_bezza).into(holder.vehiclePic);
            }
        });

        holder.tv_car.setText(currentVehicle.getBrand());
        holder.tv_totaldays.setText("Total Days Rented (Days): "+currentVehicle.getTotaldays());
        holder.tv_rate.setText("Rate per Days (RM): "+currentVehicle.getRate());
        holder.tv_income.setText("Income Earned (RM): "+currentVehicle.getIncome());
        holder.tv_platno.setText("Plat No: "+currentVehicle.getPlatno());
    }
    @Override
    public int getItemCount() {
        return vehicleListData.size();
    }

    //this will hold the View Design
    public static class DesignViewHolder extends RecyclerView.ViewHolder{
        ImageView vehiclePic;
        TextView tv_car, tv_totaldays,tv_rate,tv_income,tv_platno;
        Button btn_details;

        public DesignViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            vehiclePic=itemView.findViewById(R.id.vehiclePic);
            tv_car=itemView.findViewById(R.id.tv_car);
            tv_totaldays=itemView.findViewById(R.id.tv_totaldays);
            tv_rate=itemView.findViewById(R.id.tv_rate);
            tv_income=itemView.findViewById(R.id.tv_income);
            tv_platno=itemView.findViewById(R.id.tv_platno);
            btn_details=itemView.findViewById(R.id.btn_details);



        }
    }
}