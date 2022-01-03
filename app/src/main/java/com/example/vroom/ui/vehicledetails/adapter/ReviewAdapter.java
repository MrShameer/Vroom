package com.example.vroom.ui.vehicledetails.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroom.R;
import com.example.vroom.ui.vehicledetails.model.ReviewCard;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.DesignViewHolder> {
    public List<ReviewCard> reviewCards;

    public ReviewAdapter(ArrayList<ReviewCard> reviewCards) {this.reviewCards=reviewCards;}

    @NonNull
    @Override
    public ReviewAdapter.DesignViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_rating,parent,false);
        return new ReviewAdapter.DesignViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.DesignViewHolder holder, int position) {
        ReviewCard reviewCard= reviewCards.get(position);

        Picasso.get().load("https://vroom.lepak.xyz/storage/picture/profile/"+reviewCard.getLessorid()+".jpg").into(holder.lessorPic2, new Callback() {
            @Override
            public void onSuccess() {
            }
            @Override
            public void onError(Exception e) {
                Picasso.get().load(R.drawable.profile_image).into(holder.lessorPic2);
            }
        });

        holder.tv_lessorname.setText(reviewCard.getLessorname());
        holder.tv_review.setText(reviewCard.getReview());
        holder.tv_date.setText(reviewCard.getDate());

    }
    @Override
    public int getItemCount() {
        return reviewCards.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setVehicleDetails(List<ReviewCard>reviewCards){
        this.reviewCards=reviewCards;
        notifyDataSetChanged();
    }

    public class DesignViewHolder extends RecyclerView.ViewHolder{
        ImageView lessorPic2;
        TextView tv_lessorname, tv_review,tv_date;

        public DesignViewHolder(@NonNull View itemView) {
            super(itemView);
            lessorPic2=itemView.findViewById(R.id.lessorPic2);
            tv_lessorname=itemView.findViewById(R.id.tv_totaldays);
            tv_review=itemView.findViewById(R.id.tv_review);
            tv_date=itemView.findViewById(R.id.tv_date);
        }
    }
}