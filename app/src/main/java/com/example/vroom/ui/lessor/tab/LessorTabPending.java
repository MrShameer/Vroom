package com.example.vroom.ui.lessor.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroom.R;
import com.example.vroom.ui.lessor.adapter.LessorRentalStatusAdapter;
import com.example.vroom.ui.lessor.model.MyRentalStatusData;

import java.util.ArrayList;

public class LessorTabPending extends Fragment {
    RecyclerView rc_pending;
    LessorRentalStatusAdapter rentalStatusAdapter;
    public LessorTabPending() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root =inflater.inflate(R.layout.fragment_lessor_rental_pending, container, false);

        rc_pending=(RecyclerView) root.findViewById(R.id.rc_pending);
        rc_pending.setHasFixedSize(true);
        rc_pending.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));

        ArrayList<MyRentalStatusData> myRentalStatusData=new ArrayList<MyRentalStatusData>();
        myRentalStatusData.add(new MyRentalStatusData("BND 8478","Myvi","4.9","4","4","5","15","1500","pending","Mohamad Apan",
                "1410","online banking","1/1/2021","2/2/2021","Kajang","done","done","done","2/1/2021",
                "1/2/2021","2/2/2021"));



        rentalStatusAdapter=new LessorRentalStatusAdapter(myRentalStatusData);
        rc_pending.setAdapter(rentalStatusAdapter);

        return root;
    }

}