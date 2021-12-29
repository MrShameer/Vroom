package com.example.vroom.ui.vehicle.vehicle_tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroom.R;
import com.example.vroom.database.VehicleDetails.VehicleDetails;
import com.example.vroom.database.VehicleDetails.VehicleViewModel;
import com.example.vroom.ui.home.recyclervire.Topvehicle.topvehicle_adapter;
import com.example.vroom.ui.vehicle.adapter.Explore_adapter;
import com.example.vroom.ui.vehicle.adapter.Wishlist_adapter;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class VehicleExplore extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private VehicleViewModel vehicleViewModel;
    RecyclerView recycleryoumayalsolike,recyclerwishlist;
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

        recycleryoumayalsolike=(RecyclerView) root.findViewById(R.id.rc_youmayalsolike);
        recycleryoumayalsolike.setHasFixedSize(true);
        recycleryoumayalsolike.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));

        recyclerwishlist=(RecyclerView) root.findViewById(R.id.rc_wishlist);
        recyclerwishlist.setHasFixedSize(true);
        recyclerwishlist.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));

        final Explore_adapter adapterExplore=new Explore_adapter();
        final Wishlist_adapter adapterWish=new Wishlist_adapter();
        recycleryoumayalsolike.setAdapter(adapterExplore);
        recyclerwishlist.setAdapter(adapterWish);

        vehicleViewModel=new ViewModelProvider(this).get(VehicleViewModel.class);
        vehicleViewModel.getGetAllVehicleDetails().observe(getViewLifecycleOwner(), new Observer<List<VehicleDetails>>() {
            @Override
            public void onChanged(@Nullable List<VehicleDetails>vehicleDetails) {
                adapterExplore.setVehicleDetails(vehicleDetails);
                adapterWish.setWishlistDetails(vehicleDetails);
            }
        });
        return root;
    }
}