package com.example.vroom.ui.createrequest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroom.R;
import com.example.vroom.database.VehicleDetails.VehicleViewModel;
import com.example.vroom.ui.createrequest.adapter.ReviewAdapter;
import com.example.vroom.ui.createrequest.model.ReviewCard;

import java.util.ArrayList;

public class Rating extends Fragment  {
    RecyclerView rc_rating;
    ReviewAdapter reviewAdapter;

    private VehicleViewModel vehicleViewModel;
    public Rating() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root =inflater.inflate(R.layout.fragment_request_rating, container, false);

        rc_rating=(RecyclerView) root.findViewById(R.id.rc_rating);
        rc_rating.setHasFixedSize(true);
        rc_rating.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));


        ArrayList<ReviewCard> reviewCards=new ArrayList<ReviewCard>();
        reviewCards.add(new ReviewCard("Mohamad Anwar","Its Very Good","7/20/2021"));
        reviewCards.add(new ReviewCard("Mohamad Aiman","Its Very Good","7/20/2021"));
        reviewCards.add(new ReviewCard("Mohamad Abu","Its Very Good","7/20/2021"));
        reviewAdapter = new ReviewAdapter(reviewCards);
        rc_rating.setAdapter(reviewAdapter);

        return root;
    }

}