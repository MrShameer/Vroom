package com.example.vroom.ui.lessor.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroom.R;
import com.example.vroom.ui.lessor.adapter.VehicleMyVehicleAdapter;
import com.example.vroom.ui.lessor.model.MyVehicleListData;

import java.util.ArrayList;

public class LessorTabList extends Fragment {
    RecyclerView rc_unlisted;
    ArrayList<MyVehicleListData> myVehicleListData;
    VehicleMyVehicleAdapter vehicleMyVehicleAdapter;
    Boolean list;
    public LessorTabList(Boolean list) {
        this.list=list;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root =inflater.inflate(R.layout.fragment_lessor_myvehicle_list, container, false);

        rc_unlisted=(RecyclerView) root.findViewById(R.id.rc_list);
        rc_unlisted.setHasFixedSize(true);
        rc_unlisted.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));

        if (list){
            myVehicleListData=new ArrayList<MyVehicleListData>();
            myVehicleListData.add(new MyVehicleListData("Myvi","4.8","5","4","5","15","150","list"));
            myVehicleListData.add(new MyVehicleListData("Myvi","4.8","5","4","5","15","150","list"));
            myVehicleListData.add(new MyVehicleListData("Myvi","4.8","5","4","5","15","150","list"));
            myVehicleListData.add(new MyVehicleListData("Myvi","4.8","5","4","5","15","150","list"));
            myVehicleListData.add(new MyVehicleListData("Myvi","4.8","5","4","5","15","150","list"));
        }
        else {
            myVehicleListData=new ArrayList<MyVehicleListData>();
            myVehicleListData.add(new MyVehicleListData("Myvi","4.8","5","4","5","15","150","unlisted"));
            myVehicleListData.add(new MyVehicleListData("Myvi","4.8","5","4","5","15","150","unlisted"));
            myVehicleListData.add(new MyVehicleListData("Myvi","4.8","5","4","5","15","150","unlisted"));
            myVehicleListData.add(new MyVehicleListData("Myvi","4.8","5","4","5","15","150","unlisted"));
            myVehicleListData.add(new MyVehicleListData("Myvi","4.8","5","4","5","15","150","unlisted"));
        }

        vehicleMyVehicleAdapter=new VehicleMyVehicleAdapter(myVehicleListData);
        rc_unlisted.setAdapter(vehicleMyVehicleAdapter);
        return root;
    }

}