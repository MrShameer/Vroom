package com.example.vroom.ui.vehicle.vehicle_tab;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vroom.R;
import com.example.vroom.database.VehicleDetails.VehicleDetails;
import com.example.vroom.database.VehicleDetails.VehicleViewModel;
import com.example.vroom.ui.vehicle.adapter.Explore_adapter;
import com.example.vroom.ui.vehicle.adapter.Vehicle_adapter;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VehicleCar extends Fragment {
    RecyclerView recyclerviewexplore;
    private VehicleViewModel vehicleViewModel;
    public VehicleCar() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root =inflater.inflate(R.layout.fragment_vehicle_car, container, false);

        recyclerviewexplore=(RecyclerView) root.findViewById(R.id.rc_explorecar);
        recyclerviewexplore.setHasFixedSize(true);
        recyclerviewexplore.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));

        final Vehicle_adapter vehicle_adapter=new Vehicle_adapter();
        recyclerviewexplore.setAdapter(vehicle_adapter);

        vehicleViewModel=new ViewModelProvider(this).get(VehicleViewModel.class);
        vehicleViewModel.getGetAllVehicleDetails().observe(getViewLifecycleOwner(), new Observer<List<VehicleDetails>>() {
            @Override
            public void onChanged(@Nullable List<VehicleDetails>vehicleDetails) {
                vehicle_adapter.setVehicleDetails(vehicleDetails);
            }
        });
        return root;
    }
}