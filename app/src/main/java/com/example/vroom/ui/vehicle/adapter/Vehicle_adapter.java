package com.example.vroom.ui.vehicle.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Parcelable;
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
import com.example.vroom.ui.vehicledetails.SetReqDetails;
import com.example.vroom.ui.vehicledetails.VehicleInfo;
import com.example.vroom.ui.wishlist.Wishlist;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

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

        VehicleDetails currentVehicle= vehicleDetails.get(position);

        Picasso.get().load("https://vroom.lepak.xyz/storage/picture/profile/"+currentVehicle.getLessorid()+".jpg").into(holder.iv_lessor, new Callback() {
            @Override
            public void onSuccess() {
            }
            @Override
            public void onError(Exception e) {
                holder.iv_lessor.setImageResource(R.drawable.profile_image);
            }
        });

        Picasso.get().load("https://vroom.lepak.xyz/storage/picture/vehicle/"+currentVehicle.getVehicleplat()+".png").into(holder.iv_vehicle, new Callback() {
            @Override
            public void onSuccess() {
            }
            @Override
            public void onError(Exception e) {
                holder.iv_vehicle.setImageResource(R.drawable.perodua_bezza);
            }
        });

        holder.tv_price.setText(currentVehicle.getVehicleprice());
        holder.tv_brand.setText(currentVehicle.getVehiclebrand()+" "+currentVehicle.getVehiclemodel());
        holder.tv_rating.setText(currentVehicle.getVehiclerating());
        holder.btn_passanger.setText(currentVehicle.getVehiclepassanger());
        holder.btn_door.setText(currentVehicle.getVehicledoor());
        holder.btn_luggage.setText(currentVehicle.getVehicleluggage());
        holder.btn_tank.setText(currentVehicle.getVehicletank());
        holder.btn_wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), Wishlist.class);
                intent.putExtra("ADD",currentVehicle.getVehicleplat());
                v.getContext().startActivity(intent);
            }
        });

        holder.btn_booknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), SetReqDetails.class);
                intent.putExtra("PLAT",currentVehicle.getVehicleplat());
                v.getContext().startActivity(intent);
            }
        });

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
    public class DesignViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_vehicle;
        CircleImageView iv_lessor;
        TextView tv_brand,tv_price,tv_rating;
        Button btn_booknow,btn_wishlist, btn_passanger, btn_door, btn_luggage, btn_tank;

        public DesignViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            tv_rating=itemView.findViewById(R.id.tv_rating);
            tv_price=itemView.findViewById(R.id.tv_price);
            iv_vehicle=itemView.findViewById(R.id.iv_vehicle);
            tv_brand=itemView.findViewById(R.id.tv_brand);
            iv_lessor=itemView.findViewById(R.id.iv_lessor);
            btn_booknow=itemView.findViewById(R.id.btn_viewdetails);
            btn_wishlist=itemView.findViewById(R.id.btn_reject);
            btn_passanger=itemView.findViewById(R.id.btn_passenger);
            btn_door=itemView.findViewById(R.id.btn_door);
            btn_luggage=itemView.findViewById(R.id.btn_luggage);
            btn_tank=itemView.findViewById(R.id.btn_gas);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    VehicleDetails currentVehicle= vehicleDetails.get(getAdapterPosition());
                    Intent intent=new Intent(view.getContext(), VehicleInfo.class);
                    intent.putExtra("VEHICLE_INFO",  currentVehicle);
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}