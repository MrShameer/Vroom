package com.example.vroom.ui.lessor;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroom.R;
import com.example.vroom.database.TokenHandler;
import com.example.vroom.database.User.User;
import com.example.vroom.database.User.UserViewModel;
import com.example.vroom.ui.lessor.adapter.VehicleListAdapter;
import com.example.vroom.ui.lessor.model.VehicleListData;
import com.example.vroom.ui.wishlist.adapter.WishlistAdapter;
import com.example.vroom.ui.wishlist.model.WishlistData;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Lessorhome extends AppCompatActivity {
    private UserViewModel userViewModel;
    RecyclerView rc_vehicle;
    RecyclerView.Adapter adapter;
    ImageView iv_verified;
    CircleImageView user_image;
    TextView tv_myname,tv_email;
    ImageButton btn_back;
    String mImageName = TokenHandler.read(TokenHandler.USER_ID, null)+".jpg";
    Button btn_vehicle,btn_request,btn_addvehicle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessorhome);

        iv_verified= findViewById(R.id.iv_verified);
        tv_myname= findViewById(R.id.tv_myname);
        tv_email= findViewById(R.id.tv_email);
        userViewModel=new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getGetAllUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                User currentUser=users.get(0);
                tv_myname.setText(currentUser.getName());
                tv_email.setText(currentUser.getEmail());
                if (currentUser.getIcstatus().equals("done") && currentUser.getDlstatus().equals("done")){
                    Picasso.get().load(R.drawable.ic_baseline_check_circle_24).into(iv_verified);
                }
            }
        });
        user_image=(CircleImageView) findViewById(R.id.user_image);
        File dir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + this.getApplicationContext().getPackageName()
                + "/Picture/");
        String mImageName = TokenHandler.read(TokenHandler.USER_ID, null)+".jpg";

        File file = new File(dir, mImageName);
        if(file.exists()){
            Picasso.get().invalidate(file);
            Picasso.get().load(file).into(user_image);
        }else{
            Picasso.get().load(R.drawable.profile_image).into(user_image);
        }

        rc_vehicle=(RecyclerView) findViewById(R.id.rc_vehicle);
        rc_vehicle.setHasFixedSize(true);
        rc_vehicle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));


        ArrayList<VehicleListData> vehicleListData=new ArrayList<VehicleListData>();
        vehicleListData.add(new VehicleListData("Myvi","10","130","15000","BND6731"));
        vehicleListData.add(new VehicleListData("Myvi","10","130","15000","BND6731"));
        vehicleListData.add(new VehicleListData("Myvi","10","130","15000","BND6731"));
        vehicleListData.add(new VehicleListData("Myvi","10","130","15000","BND6731"));
        adapter=new VehicleListAdapter(vehicleListData);
        rc_vehicle.setAdapter(adapter);

        btn_back=findViewById(R.id.btn_back);
        btn_back.setOnClickListener(view -> finishAndRemoveTask());

        btn_vehicle=findViewById(R.id.btn_vehicle);
        btn_vehicle.setOnClickListener(v -> {
            Intent intent = new Intent(Lessorhome.this, LessorMyVehicle.class);
            startActivity(intent);
        });

        btn_addvehicle=findViewById(R.id.btn_addvehicle);
        btn_addvehicle.setOnClickListener(v -> {
            Intent intent = new Intent(Lessorhome.this, LessorAddVehicle.class);
            startActivity(intent);
        });
        btn_request=findViewById(R.id.btn_request);
        btn_request.setOnClickListener(v -> {
            Intent intent = new Intent(Lessorhome.this, LessorMyRequest.class);
            startActivity(intent);
        });

    }
}
