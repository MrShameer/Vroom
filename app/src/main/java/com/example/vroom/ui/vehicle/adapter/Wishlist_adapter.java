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
import com.example.vroom.ui.vehicledetails.SetReqDetails;
import com.example.vroom.ui.vehicledetails.VehicleInfo;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Wishlist_adapter extends RecyclerView.Adapter<Wishlist_adapter.DesignViewHolder> {
    private List<VehicleDetails> vehicleDetails=new ArrayList<>();

    @NonNull
    @Override
    public Wishlist_adapter.DesignViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_wishlist,parent,false);
        return new Wishlist_adapter.DesignViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull Wishlist_adapter.DesignViewHolder holder, int position) {
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

        holder.iv_vehicle.setImageResource(R.drawable.perodua_bezza);
        holder.tv_lessorname.setText(currentVehicle.getLessorname());
        holder.tv_rating.setText(currentVehicle.getVehiclerating());
        holder.tv_price.setText(currentVehicle.getVehicleprice());
        holder.tv_brand.setText(currentVehicle.getVehiclebrand()+" "+currentVehicle.getVehiclemodel());
        holder.btn_booknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), SetReqDetails.class);

                v.getContext().startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return vehicleDetails.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setWishlistDetails(List<VehicleDetails>vehicleDetails){
        this.vehicleDetails=vehicleDetails;
        notifyDataSetChanged();

    }

    //this will hold the View Design
    public class DesignViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_vehicle, iv_lessor;
        TextView tv_title, tv_brand,tv_lessorname,tv_rating,tv_price;
        Button btn_booknow;

        public DesignViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_lessor=itemView.findViewById(R.id.lessorPic);
            tv_rating=itemView.findViewById(R.id.tv_rating);
            btn_booknow=itemView.findViewById(R.id.btn_booknow);
            tv_price=itemView.findViewById(R.id.tv_price);
            iv_vehicle=itemView.findViewById(R.id.iv_vehicle);
            tv_lessorname=itemView.findViewById(R.id.tv_totaldays);
            tv_brand=itemView.findViewById(R.id.tv_brand);
            tv_title=itemView.findViewById(R.id.tv_title);

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