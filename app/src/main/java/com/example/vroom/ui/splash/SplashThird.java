package com.example.vroom.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vroom.Login;
import com.example.vroom.MainActivity;
import com.example.vroom.R;
import com.example.vroom.SplashScreen;

public class SplashThird extends Fragment {
    TextView tv_last;
    public SplashThird() {
               // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash_third, container, false);
        // Inflate the layout for this fragment
        tv_last=(TextView) view.findViewById(R.id.tv_last);
        tv_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Login.class);
                startActivity(intent);
            }
        });
        return view;
    }

}