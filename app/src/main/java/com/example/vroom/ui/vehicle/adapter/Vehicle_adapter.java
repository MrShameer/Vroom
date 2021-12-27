package com.example.vroom.ui.vehicle.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroom.R;
import com.example.vroom.database.VehicleDetails.VehicleDetails;
import com.example.vroom.ui.createrequest.CreateReq;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Vehicle_adapter extends RecyclerView.Adapter<Vehicle_adapter.DesignViewHolder> {
    private List<VehicleDetails> vehicleDetails=new ArrayList<>();

    @NonNull
    @Override
    public Vehicle_adapter.DesignViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_explore,parent,false);
        return new Vehicle_adapter.DesignViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull Vehicle_adapter.DesignViewHolder holder, int position) {
        //main function to bind the design
        //pass down the position
        VehicleDetails currentVehicle= vehicleDetails.get(position);
        //set the image
        holder.iv_vehicle.setImageResource(R.drawable.perodua_bezza);
        holder.tv_price.setText(currentVehicle.getVehicleprice());
        holder.tv_brand.setText(currentVehicle.getVehiclebrand()+" "+currentVehicle.getVehiclemodel());
        holder.tv_rating.setText(currentVehicle.getVehiclerating());

        holder.btn_wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), CreateReq.class);
                v.getContext().startActivity(intent);
            }
        });

        holder.btn_booknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), CreateReq.class);
                v.getContext().startActivity(intent);
            }
        });


//        holder.tv_title.setText(VehicleDetails.getVehiclebrand());
    }
    @Override
    public int getItemCount() {
        return vehicleDetails.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setVehicleDetails(List<VehicleDetails>vehicleDetails){
        this.vehicleDetails=vehicleDetails;
        notifyDataSetChanged();

    }

    //this will hold the View Design
    public static class DesignViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_vehicle;
        CircleImageView iv_lessor;
        TextView tv_brand,tv_price,tv_rating;
        Button btn_booknow,btn_wishlist;

        public DesignViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            tv_rating=itemView.findViewById(R.id.tv_rating);
            tv_price=itemView.findViewById(R.id.tv_price);
            iv_vehicle=itemView.findViewById(R.id.iv_vehicle);
            tv_brand=itemView.findViewById(R.id.tv_brand);
            iv_lessor=itemView.findViewById(R.id.iv_lessor);
            btn_booknow=itemView.findViewById(R.id.btn_booknow);
            btn_wishlist=itemView.findViewById(R.id.btn_wishlist);



        }
    }
}