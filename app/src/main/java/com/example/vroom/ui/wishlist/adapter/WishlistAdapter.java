package com.example.vroom.ui.wishlist.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroom.R;
import com.example.vroom.ui.wishlist.model.WishlistData;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class WishlistAdapter  extends RecyclerView.Adapter<WishlistAdapter.DesignViewHolder> {
    public List<WishlistData> wishlistData;

    public WishlistAdapter(ArrayList<WishlistData> wishlistData) {this.wishlistData=wishlistData;}

    @NonNull
    @Override
    public WishlistAdapter.DesignViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_activity_wishlist,parent,false);
        return new WishlistAdapter.DesignViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull WishlistAdapter.DesignViewHolder holder, int position) {
        //main function to bind the design
        //pass down the position
        WishlistData wishlistDatas=wishlistData.get(position);
        //set the image
        holder.tv_lessorname.setText(wishlistDatas.getLessorname());
        holder.tv_car.setText(wishlistDatas.getCar());
        holder.tv_rating.setText(wishlistDatas.getRating());
        holder.vehiclePic.setImageResource(R.drawable.perodua_bezza);



//        holder.tv_title.setText(VehicleDetails.getVehiclebrand());
    }
    @Override
    public int getItemCount() {
        return wishlistData.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setWishlistDetails(List<WishlistData>wishlistData){
        this.wishlistData=wishlistData;
        notifyDataSetChanged();

    }

    //this will hold the View Design
    public static class DesignViewHolder extends RecyclerView.ViewHolder{
        ImageView vehiclePic;
        CircleImageView lessorPic3;
        TextView tv_car, tv_lessorname,tv_rating;

        public DesignViewHolder(@NonNull View itemView) {
            super(itemView);
            vehiclePic=itemView.findViewById(R.id.vehiclePic);
            tv_car=itemView.findViewById(R.id.tv_car);
            lessorPic3=itemView.findViewById(R.id.lessorPic3);
            tv_lessorname=itemView.findViewById(R.id.tv_totaldays);
            tv_rating=itemView.findViewById(R.id.tv_rating);
        }
    }
}