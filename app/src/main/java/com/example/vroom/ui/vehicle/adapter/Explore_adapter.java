package com.example.vroom.ui.vehicle.adapter;

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
import com.example.vroom.database.VehicleDetails.VehicleDetails;
import com.example.vroom.ui.home.recyclervire.Topvehicle.topvehicle_adapter;

import java.util.ArrayList;
import java.util.List;

public class Explore_adapter extends RecyclerView.Adapter<Explore_adapter.DesignViewHolder> {
    private List<VehicleDetails> vehicleDetails=new ArrayList<>();

    @NonNull
    @Override
    public Explore_adapter.DesignViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_youmayalsolike,parent,false);
        return new Explore_adapter.DesignViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull Explore_adapter.DesignViewHolder holder, int position) {
        //main function to bind the design
        //pass down the position
        VehicleDetails currentVehicle= vehicleDetails.get(position);
        //set the image
        holder.iv_vehicle.setImageResource(R.drawable.perodua_bezza);
        holder.tv_names.setText(currentVehicle.getLessorname());
        holder.btn_passenger.setText(currentVehicle.getVehiclepassanger());
        holder.btn_door.setText(currentVehicle.getVehicledoor());
        holder.btn_luggage.setText(currentVehicle.getVehicleluggage());
        holder.btn_gas.setText(currentVehicle.getVehicletank());
        holder.tv_price.setText(currentVehicle.getVehicleprice());
        holder.tv_brand.setText(currentVehicle.getVehiclebrand()+" "+currentVehicle.getVehiclemodel());
        holder.tv_rating.setText(currentVehicle.getVehiclerating());


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
        TextView tv_title, tv_brand,tv_names,tv_price,tv_rating;
        Button btn_passenger,btn_door,btn_luggage,btn_gas;

        public DesignViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            tv_rating=itemView.findViewById(R.id.tv_rating);
            tv_price=itemView.findViewById(R.id.tv_price);
            iv_vehicle=itemView.findViewById(R.id.iv_vehicle);
            btn_passenger=itemView.findViewById(R.id.btn_passenger);
            btn_door=itemView.findViewById(R.id.btn_door);
            btn_luggage=itemView.findViewById(R.id.btn_luggage);
            btn_gas=itemView.findViewById(R.id.btn_gas);
            tv_names=itemView.findViewById(R.id.tv_lessorname);
            tv_brand=itemView.findViewById(R.id.tv_brand);
            tv_title=itemView.findViewById(R.id.tv_title);


        }
    }
}