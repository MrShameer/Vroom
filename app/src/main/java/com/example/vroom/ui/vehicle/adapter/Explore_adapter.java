package com.example.vroom.ui.vehicle.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroom.R;
import com.example.vroom.database.User.User;
import com.example.vroom.database.User.UserViewModel;
import com.example.vroom.database.VehicleDetails.VehicleDetails;
import com.example.vroom.ui.profile.EditMyDetails;
import com.example.vroom.ui.profile.MyDetails;
import com.example.vroom.ui.vehicledetails.SetReqDetails;
import com.example.vroom.ui.vehicledetails.VehicleInfo;
import com.example.vroom.ui.wishlist.Wishlist;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Explore_adapter extends RecyclerView.Adapter<Explore_adapter.DesignViewHolder> {
    UserViewModel userViewModel;
    private List<VehicleDetails> vehicleDetails=new ArrayList<>();
    private Boolean proceed;
    AlertDialog.Builder builder1;
    @NonNull
    @Override
    public Explore_adapter.DesignViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_youmayalsolike,parent,false);
        builder1 = new AlertDialog.Builder(parent.getContext());

        builder1.setTitle("Incomplete Details")
                .setMessage("Please Complete Your User Details Before Requesting Any Rental")
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    Intent intent=new Intent(parent.getContext(), MyDetails.class);
                    parent.getContext().startActivity(intent);
                })
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null);
        return new Explore_adapter.DesignViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull Explore_adapter.DesignViewHolder holder, int position) {

        VehicleDetails currentVehicle= vehicleDetails.get(position);
        Picasso.get().load("https://vroom.lepak.xyz/storage/picture/profile/"+currentVehicle.getLessorid()+".jpg").into(holder.iv_lessor, new Callback() {
            @Override
            public void onSuccess() {
            }
            @Override
            public void onError(Exception e) {
                Picasso.get().load(R.drawable.profile_image).into(holder.iv_lessor);
            }
        });

        Picasso.get().load("https://vroom.lepak.xyz/storage/picture/vehicle/"+currentVehicle.getVehicleplat()+".png").into(holder.iv_vehicle, new Callback() {
            @Override
            public void onSuccess() {
            }
            @Override
            public void onError(Exception e) {
                Picasso.get().load(R.drawable.perodua_bezza).into(holder.iv_vehicle);
            }
        });
        holder.tv_names.setText(currentVehicle.getLessorname());
        holder.btn_passenger.setText(currentVehicle.getVehiclepassanger());
        holder.btn_door.setText(currentVehicle.getVehicledoor());
        holder.btn_luggage.setText(currentVehicle.getVehicleluggage());
        holder.btn_gas.setText(currentVehicle.getVehicletank());
        holder.tv_price.setText(currentVehicle.getVehicleprice());
        holder.tv_brand.setText(currentVehicle.getVehiclebrand()+" "+currentVehicle.getVehiclemodel());
        holder.tv_rating.setText(currentVehicle.getVehiclerating());
        holder.btn_booknow.setOnClickListener(v -> {
            if(proceed){
            Intent intent=new Intent(v.getContext(), SetReqDetails.class);
            intent.putExtra("PLAT",currentVehicle.getVehicleplat());
            v.getContext().startActivity(intent);
            }
            else{
                builder1.show();
            }
        });
        holder.btn_wishlist.setOnClickListener(v -> {
            Intent intent=new Intent(v.getContext(), Wishlist.class);
            intent.putExtra("ADD",currentVehicle.getVehicleplat());
            v.getContext().startActivity(intent);
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
    @SuppressLint("NotifyDataSetChanged")
    public void setUserDetails(Boolean proceed){
        this.proceed=proceed;
        notifyDataSetChanged();

    }

    public class DesignViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_vehicle;
        CircleImageView iv_lessor;
        TextView tv_title, tv_brand,tv_names,tv_price,tv_rating;
        Button btn_passenger,btn_door,btn_luggage,btn_gas,btn_booknow,btn_wishlist;

        public DesignViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_lessor=itemView.findViewById(R.id.lessorPic);
            tv_rating=itemView.findViewById(R.id.tv_rating);
            btn_booknow=itemView.findViewById(R.id.btn_viewdetails);
            btn_wishlist=itemView.findViewById(R.id.btn_reject);
            tv_price=itemView.findViewById(R.id.tv_price);
            iv_vehicle=itemView.findViewById(R.id.iv_vehicle);
            btn_passenger=itemView.findViewById(R.id.btn_passenger);
            btn_door=itemView.findViewById(R.id.btn_door);
            btn_luggage=itemView.findViewById(R.id.btn_luggage);
            btn_gas=itemView.findViewById(R.id.btn_gas);
            tv_names=itemView.findViewById(R.id.tv_totaldays);
            tv_brand=itemView.findViewById(R.id.tv_brand);
            tv_title=itemView.findViewById(R.id.tv_title);

            itemView.setOnClickListener(view -> {
                VehicleDetails currentVehicle= vehicleDetails.get(getAdapterPosition());
                Intent intent=new Intent(view.getContext(), VehicleInfo.class);
                intent.putExtra("VEHICLE_INFO",  currentVehicle);
                view.getContext().startActivity(intent);
            });
        }
    }
}