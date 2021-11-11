package com.example.vroom.ui.home.recyclervire.Topvehicle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroom.R;

import java.util.ArrayList;

public class topvehicle_adapter extends RecyclerView.Adapter<topvehicle_adapter.DesignViewHolder> {
    ArrayList<topvehicle_data> CardviewDatas;

    //this will hold the Data
    public topvehicle_adapter(ArrayList<topvehicle_data> CardviewDatas) {
        this.CardviewDatas = CardviewDatas;
    }
    @NonNull
    @Override
    public DesignViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_topvehicle,parent,false);
        DesignViewHolder DesignViewHolder=new DesignViewHolder(view);

        return DesignViewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull DesignViewHolder holder, int position) {
        //main function to bind the design
        //pass down the position
        topvehicle_data topvehicle_data= CardviewDatas.get(position);
        //set the image
        holder.iv_vehicle.setImageResource(topvehicle_data.getPhoto());
        holder.tv_brand.setText(topvehicle_data.getBrand());
        holder.tv_passanger.setText(topvehicle_data.getPassanger());
        holder.tv_door.setText(topvehicle_data.getDoor());
        holder.tv_luggage.setText(topvehicle_data.getLuggage());
        holder.tv_gas.setText(topvehicle_data.getGas());
        holder.tv_title.setText(topvehicle_data.getTitle());
    }
    @Override
    public int getItemCount() {
        return CardviewDatas.size();
    }

    //this will hold the View Design
    public static class DesignViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_vehicle;
        TextView tv_title,tv_brand,tv_passanger,tv_door,tv_luggage,tv_gas;

        public DesignViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            iv_vehicle=itemView.findViewById(R.id.iv_vehicle);
            tv_passanger=itemView.findViewById(R.id.tv_passanger);
            tv_door=itemView.findViewById(R.id.tv_door);
            tv_luggage=itemView.findViewById(R.id.tv_luggage);
            tv_gas=itemView.findViewById(R.id.tv_gas);
            tv_brand=itemView.findViewById(R.id.tv_brand);
            tv_title=itemView.findViewById(R.id.tv_title);


        }
    }
}