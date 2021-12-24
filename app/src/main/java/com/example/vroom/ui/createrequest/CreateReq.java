package com.example.vroom.ui.createrequest;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.vroom.R;
import com.example.vroom.ui.vehicle.vehicle_tab.SectionsPagerAdapter;
import com.example.vroom.ui.wishlist.Wishlist;
import com.google.android.material.tabs.TabLayout;

public class CreateReq extends AppCompatActivity {
    ImageButton btn_back;
    Button btn_book,btn_wishlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_request);
        ViewPager viewPager = findViewById(R.id.view_pager);
        SectionsPagerAdapter reqPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(reqPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

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
                Intent intent=new Intent(CreateReq.this,SetReqDetails.class);
                startActivity(intent);
            }
        });


        //setup Wishlist Button
        btn_wishlist=findViewById(R.id.btn_wishlist);
        btn_wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CreateReq.this, Wishlist.class);
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
                    fragment = new VehicleDetails();
                    break;
                case 1:
                    fragment = new Rating();
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
