package com.example.vroom.ui.lessor.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroom.R;
import com.example.vroom.database.VehicleDetails.VehicleDetails;
import com.example.vroom.database.VehicleDetails.VehicleViewModel;
import com.example.vroom.ui.lessor.adapter.VehicleMyVehicleAdapter;
import com.example.vroom.ui.lessor.model.MyVehicleListData;
import com.example.vroom.ui.vehicledetails.adapter.ReviewAdapter;
import com.example.vroom.ui.vehicledetails.model.ReviewCard;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LessorTabUnlisted extends Fragment {
    RecyclerView rc_unlisted;
    VehicleMyVehicleAdapter vehicleMyVehicleAdapter;
    private VehicleViewModel vehicleViewModel;
    public LessorTabUnlisted() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root =inflater.inflate(R.layout.fragment_lessor_myvehicle_unlisted, container, false);

        rc_unlisted=(RecyclerView) root.findViewById(R.id.rc_unlisted);
        rc_unlisted.setHasFixedSize(true);
        rc_unlisted.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));

        ArrayList<MyVehicleListData> myVehicleListData=new ArrayList<MyVehicleListData>();
        myVehicleListData.add(new MyVehicleListData("Myvi","4.8","5","4","5","15","150","unlisted"));
        myVehicleListData.add(new MyVehicleListData("Myvi","4.8","5","4","5","15","150","unlisted"));
        myVehicleListData.add(new MyVehicleListData("Myvi","4.8","5","4","5","15","150","unlisted"));
        myVehicleListData.add(new MyVehicleListData("Myvi","4.8","5","4","5","15","150","unlisted"));
        myVehicleListData.add(new MyVehicleListData("Myvi","4.8","5","4","5","15","150","unlisted"));


        vehicleMyVehicleAdapter=new VehicleMyVehicleAdapter(myVehicleListData);
        rc_unlisted.setAdapter(vehicleMyVehicleAdapter);
        return root;
    }

}