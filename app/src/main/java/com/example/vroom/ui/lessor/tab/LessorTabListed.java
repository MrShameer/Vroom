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
import com.example.vroom.ui.lessor.adapter.VehicleListAdapter;
import com.example.vroom.ui.lessor.adapter.VehicleMyVehicleAdapter;
import com.example.vroom.ui.lessor.model.MyVehicleListData;
import com.example.vroom.ui.lessor.model.VehicleListData;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LessorTabListed extends Fragment {
    RecyclerView rc_listed;
    VehicleMyVehicleAdapter vehicleMyVehicleAdapter;
    public LessorTabListed() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root =inflater.inflate(R.layout.fragment_lessor_myvehicle_listed, container, false);

        rc_listed=(RecyclerView) root.findViewById(R.id.rc_listed);
        rc_listed.setHasFixedSize(true);
        rc_listed.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));

        ArrayList<MyVehicleListData> myVehicleListData=new ArrayList<MyVehicleListData>();
        myVehicleListData.add(new MyVehicleListData("Myvi","4.8","5","4","5","15","150","list"));
        myVehicleListData.add(new MyVehicleListData("Myvi","4.8","5","4","5","15","150","list"));
        myVehicleListData.add(new MyVehicleListData("Myvi","4.8","5","4","5","15","150","list"));
        myVehicleListData.add(new MyVehicleListData("Myvi","4.8","5","4","5","15","150","list"));
        myVehicleListData.add(new MyVehicleListData("Myvi","4.8","5","4","5","15","150","list"));


        vehicleMyVehicleAdapter=new VehicleMyVehicleAdapter(myVehicleListData);
        rc_listed.setAdapter(vehicleMyVehicleAdapter);

        return root;
    }

}