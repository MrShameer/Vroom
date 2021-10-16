package com.example.vroom.ui.vehicle.vehicle_tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.vroom.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class VehicleExplore extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";


    public static VehicleExplore newInstance(int index) {
        VehicleExplore fragment = new VehicleExplore();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_vehicle_explore, container, false);
        return root;
    }
}