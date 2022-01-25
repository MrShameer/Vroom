package com.example.vroom.ui.vehicledetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.vroom.R;
import com.example.vroom.database.VehicleDetails.VehicleDetails;
import com.example.vroom.database.VehicleDetails.VehicleViewModel;

public class TabVehicleDetails extends Fragment  {

    private VehicleViewModel vehicleViewModel;
    VehicleDetails vehicleDetails;

    TextView tv_brand, tv_age, tv_insurance, tv_fuel;
    public TabVehicleDetails(VehicleDetails vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root =inflater.inflate(R.layout.fragment_vehicle_details, container, false);
        tv_brand=root.findViewById(R.id.tv_brand);
        tv_brand.setText(vehicleDetails.getVehiclebrand()+" "+vehicleDetails.getVehiclemodel());
        tv_age=root.findViewById(R.id.tv_age);
        tv_age.setText(vehicleDetails.getVehicleage());
        tv_insurance=root.findViewById(R.id.tv_insurance);
        tv_insurance.setText(vehicleDetails.getVehicleinsurance());
        tv_fuel=root.findViewById(R.id.tv_fuel);
        tv_fuel.setText(vehicleDetails.getVehicletank());
        return root;
    }
}