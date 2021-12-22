package com.example.vroom.ui.createrequest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.vroom.R;
import com.example.vroom.database.VehicleDetails.VehicleViewModel;

public class VehicleDetails extends Fragment  {

    private VehicleViewModel vehicleViewModel;
    public VehicleDetails() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root =inflater.inflate(R.layout.fragment_request_details, container, false);

        return root;
    }
}