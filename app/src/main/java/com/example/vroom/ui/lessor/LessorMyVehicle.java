package com.example.vroom.ui.lessor;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.vroom.R;
import com.example.vroom.ui.lessor.tab.LessorTabList;
import com.google.android.material.tabs.TabLayout;

public class LessorMyVehicle extends AppCompatActivity {

    ViewPager2 view_pager;
    ImageButton btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_lessor_myvehicle);

    view_pager=findViewById(R.id.view_pager);
    TabLayout tabs = findViewById(R.id.tabs);
    btn_back=findViewById(R.id.btn_back);

    FragmentManager fm = getSupportFragmentManager();
    SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(fm, getLifecycle());
    view_pager.setAdapter(sectionsPagerAdapter);
    tabs.addTab(tabs.newTab().setText("Listed"));
    tabs.addTab(tabs.newTab().setText("Unlisted"));

    tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        view_pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) { }

    @Override
    public void onTabReselected(TabLayout.Tab tab) { }
});
        view_pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageSelected(int position) {
            tabs.selectTab(tabs.getTabAt(position));
        }
    });
}
    public class SectionsPagerAdapter extends FragmentStateAdapter {

        public SectionsPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if (position==0) {
                return new LessorTabList(true);
            }
            return new LessorTabList(false);
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
}