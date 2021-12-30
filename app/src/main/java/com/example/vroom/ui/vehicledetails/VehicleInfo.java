package com.example.vroom.ui.vehicledetails;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.vroom.R;
import com.example.vroom.database.VehicleDetails.VehicleDetails;
import com.example.vroom.ui.wishlist.Wishlist;
import com.google.android.material.tabs.TabLayout;

public class VehicleInfo extends AppCompatActivity {
    ImageButton btn_back;
    Button btn_book,btn_wishlist, btn_passanger, btn_door, btn_luggage, btn_gas;
    TextView tv_price;
    VehicleDetails vehicleDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_details);
        ViewPager viewPager = findViewById(R.id.view_pager);
        SectionsPagerAdapter reqPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(reqPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        vehicleDetails = (VehicleDetails) getIntent().getSerializableExtra("VEHICLE_INFO");

        tv_price=findViewById(R.id.tv_price4);
        tv_price.setText(vehicleDetails.getVehicleprice());
        btn_passanger=findViewById(R.id.btn_passenger4);
        btn_passanger.setText(vehicleDetails.getVehiclepassanger());
        btn_door=findViewById(R.id.btn_door4);
        btn_door.setText(vehicleDetails.getVehicledoor());
        btn_luggage=findViewById(R.id.btn_luggage4);
        btn_luggage.setText(vehicleDetails.getVehicleluggage());
        btn_gas=findViewById(R.id.btn_gas4);
        btn_gas.setText(vehicleDetails.getVehicletank());

        //setup Back Button
        btn_back=findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAndRemoveTask();
            }
        });

        //setup Book Button
        btn_book=findViewById(R.id.btn_book);
        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(VehicleInfo.this,SetReqDetails.class);
                startActivity(intent);
            }
        });

        //setup Wishlist Button
        btn_wishlist=findViewById(R.id.btn_reject);
        btn_wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(VehicleInfo.this, Wishlist.class);
                startActivity(intent);
            }
        });
    }

    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
        private final String[] tabTitles = new String[]{"Details","Rating"};
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new TabVehicleDetails(vehicleDetails);
                    break;
                case 1:
                    fragment = new TabRating();
                    break;
            }
            return fragment;
        }
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
        @Override
        public int getCount() {
            return 2;
        }

   }
}
