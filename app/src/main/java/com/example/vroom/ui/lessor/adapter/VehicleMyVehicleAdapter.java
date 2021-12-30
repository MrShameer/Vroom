package com.example.vroom.ui.lessor.adapter;

import static com.example.vroom.R.drawable.*;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.example.vroom.R;
import com.example.vroom.database.VehicleDetails.VehicleDetails;
import com.example.vroom.ui.lessor.model.MyVehicleListData;
import com.example.vroom.ui.lessor.model.VehicleListData;
import com.example.vroom.ui.vehicle.adapter.Explore_adapter;
import com.example.vroom.ui.vehicledetails.VehicleInfo;
import com.example.vroom.ui.wishlist.Wishlist;
import java.util.ArrayList;
import java.util.List;

public class VehicleMyVehicleAdapter extends RecyclerView.Adapter<VehicleMyVehicleAdapter.DesignViewHolder> {
    ArrayList<MyVehicleListData> myVehicleListData=new ArrayList<>();

    public VehicleMyVehicleAdapter(ArrayList<MyVehicleListData> myVehicleListData) {
        this.myVehicleListData=myVehicleListData;
    }

    @NonNull
    @Override
    public VehicleMyVehicleAdapter.DesignViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_lessor_myvehicle,parent,false);
        return new VehicleMyVehicleAdapter.DesignViewHolder(view);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull VehicleMyVehicleAdapter.DesignViewHolder holder, int position) {
        //main function to bind the design
        //pass down the position
        MyVehicleListData currentVehicle= myVehicleListData.get(position);
        //set the image
        holder.iv_vehicle.setImageResource(perodua_bezza);
        holder.btn_passenger.setText(currentVehicle.getPassanger());
        holder.btn_door.setText(currentVehicle.getDoor());
        holder.btn_luggage.setText(currentVehicle.getLuggage());
        holder.btn_gas.setText(currentVehicle.getGas());
        holder.tv_price.setText(currentVehicle.getPrice());
        holder.tv_brand.setText(currentVehicle.getBrand());
        holder.tv_rating.setText(currentVehicle.getRating());
        String list=currentVehicle.getList();
        if(list=="list"){holder.ib_list.setForeground(null);}



//        holder.tv_title.setText(VehicleDetails.getVehiclebrand());
    }
    @Override
    public int getItemCount() {
        return myVehicleListData.size();
    }

//    @SuppressLint("NotifyDataSetChanged")
//    public void setVehicleDetails(List<MyVehicleListData>myVehicleListData){
//        this.myVehicleListData=myVehicleListData;
//        notifyDataSetChanged();
//
//    }

    //this will hold the View Design
    public static class DesignViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_vehicle;
        TextView tv_brand,tv_price,tv_rating;
        ImageButton ib_list;
        Button btn_passenger,btn_door,btn_luggage,btn_gas,btn_viewdetails;

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
            tv_brand=itemView.findViewById(R.id.tv_brand);
            btn_viewdetails=itemView.findViewById(R.id.btn_viewdetails);
            ib_list=itemView.findViewById(R.id.ib_list);


        }
    }
}