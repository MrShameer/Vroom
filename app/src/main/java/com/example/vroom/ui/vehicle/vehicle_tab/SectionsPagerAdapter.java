package com.example.vroom.ui.vehicle.vehicle_tab;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.vroom.R;

public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_explore, R.string.tab_car, R.string.tab_motorcycle, R.string.tab_van};
//    private static final int[] TAB_TITLES = new int[]{R.string.tab_explore, R.string.tab_car, R.string.tab_motorcycle, R.string.tab_van, R.string.tab_bicycle};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        if(position == 0){
            return new VehicleExplore();
        }
        else if (position == 1){
            return new VehicleCar();
        }
        else if (position == 2){
            return new VehicleMotorcycle();
        }
//        else
//            return new VehicleVan();
        else if (position == 3){
            return new VehicleVan();
        }
        return new VehicleBicycle();
        //return VehicleExplore.newInstance(position + 1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 4;
//        return 5;
    }
}