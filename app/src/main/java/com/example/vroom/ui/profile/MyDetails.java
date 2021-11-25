package com.example.vroom.ui.profile;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.vroom.R;
import com.example.vroom.database.User.User;
import com.example.vroom.database.User.UserViewModel;

import java.util.List;

public class MyDetails extends AppCompatActivity {
    private UserViewModel userViewModel;
    TextView tv_username,tv_fullname,tv_email,tv_address,tv_phone,tv_password,tv_addic,tv_adddriving;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydetails);

        tv_username=findViewById(R.id.tv_username);
        tv_fullname=findViewById(R.id.tv_fullname);
        tv_email=findViewById(R.id.tv_email);
        tv_address=findViewById(R.id.tv_address);
        tv_phone=findViewById(R.id.tv_phone);
        tv_password=findViewById(R.id.tv_password);
        tv_addic=findViewById(R.id.tv_addic);
        tv_adddriving=findViewById(R.id.tv_adddriving);

        userViewModel=new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getGetAllUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                User currentUser=users.get(0);
                tv_username.setText(currentUser.getUsername());
                tv_fullname.setText(currentUser.getName());
                tv_email.setText(currentUser.getEmail());
                tv_address.setText(currentUser.getAddress());
                tv_phone.setText(currentUser.getPhone());
                tv_password.setText(currentUser.getPassword());
                tv_addic.setText("No Documents Added");
                tv_adddriving.setText("No Documents Added");
//                tv_addic.setText(currentUser.getEmail());
//                tv_adddriving.setText(currentUser.getAddress());
            }
        });
    }

}