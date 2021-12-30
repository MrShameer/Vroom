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
import com.example.vroom.ui.lessor.adapter.VehicleMyVehicleAdapter;
import com.example.vroom.ui.lessor.model.MyRentalStatusData;
import com.example.vroom.ui.lessor.model.MyVehicleListData;

import java.util.ArrayList;

public class LessorTabRequest extends Fragment {
    RecyclerView rc_request;
    String type;
    LessorRentalStatusAdapter rentalStatusAdapter;
    public LessorTabRequest(String type) {
        this.type=type;
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root =inflater.inflate(R.layout.fragment_lessor_rental_accepted, container, false);

        rc_request=(RecyclerView) root.findViewById(R.id.rc_request);
        rc_request.setHasFixedSize(true);
        rc_request.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));

        ArrayList<MyRentalStatusData> myRentalStatusData=new ArrayList<MyRentalStatusData>();
        switch (type) {
            case "accepted":
                myRentalStatusData.add(new MyRentalStatusData("BND 8478", "Myvi", "4.9", "4", "4", "5", "15", "1500", "accepted", "Mohamad Apan",
                "1410", "online banking", "1/1/2021", "2/2/2021", "Kajang", "done", "done", "done", "2/1/2021",
                "1/2/2021", "2/2/2021"));
                break;

            case "rejected":
                myRentalStatusData.add(new MyRentalStatusData("BND 8478","Myvi","4.9","4","4","5","15","1500","rejected","Mohamad Apan",
                        "1410","online banking","1/1/2021","2/2/2021","Kajang","done","done","done","2/1/2021",
                        "1/2/2021","2/2/2021"));
                break;

            case "pending":
                myRentalStatusData.add(new MyRentalStatusData("BND 8478","Myvi","4.9","4","4","5","15","1500","pending","Mohamad Apan",
                        "1410","online banking","1/1/2021","2/2/2021","Kajang","done","done","done","2/1/2021",
                        "1/2/2021","2/2/2021"));
                break;

        }

        rentalStatusAdapter=new LessorRentalStatusAdapter(myRentalStatusData);
        rc_request.setAdapter(rentalStatusAdapter);

        return root;
    }

}