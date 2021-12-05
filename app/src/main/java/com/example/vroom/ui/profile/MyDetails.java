package com.example.vroom.ui.profile;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.vroom.R;
import com.example.vroom.database.User.User;
import com.example.vroom.database.User.UserViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyDetails extends AppCompatActivity implements View.OnClickListener  {
    private UserViewModel userViewModel;
    Button btn_eimage;
    ImageButton btn_back,btn_eusername,btn_efullname,btn_eemail,btn_eaddress,btn_ephone,btn_epassword,btn_eic,btn_eadddriving;
    TextView tv_username,tv_fullname,tv_email,tv_address,tv_phone,tv_password,tv_addic,tv_adddriving;
    ArrayList<String>userdetails=new ArrayList<>();
    private static final int GALLERY_CODE = 103;
    CircleImageView user_image;

    SharedPreferences sharedpreferences;
    public static String MYPREFERENCES="MyPrefs";
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
        user_image=findViewById(R.id.user_image);
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
                userdetails.add(currentUser.getUserID());
                userdetails.add(currentUser.getUsername());
                userdetails.add(currentUser.getName());
                userdetails.add(currentUser.getEmail());
                userdetails.add(currentUser.getAddress());
                userdetails.add(currentUser.getPhone());
                userdetails.add(currentUser.getPassword());

            }
        });

        btn_eusername=findViewById(R.id.btn_eusername);
        btn_eusername.setOnClickListener(this);

        btn_efullname=findViewById(R.id.btn_efullname);
        btn_efullname.setOnClickListener(this);

        btn_eemail=findViewById(R.id.btn_eemail);
        btn_eemail.setOnClickListener(this);

        btn_eaddress=findViewById(R.id.btn_eaddress);
        btn_eaddress.setOnClickListener(this);

        btn_ephone=findViewById(R.id.btn_ephone);
        btn_ephone.setOnClickListener(this);

        btn_epassword=findViewById(R.id.btn_epassword);
        btn_epassword.setOnClickListener(this);

        btn_eic=findViewById(R.id.btn_eic);
        btn_eic.setOnClickListener(this);

        btn_eadddriving=findViewById(R.id.btn_eadddriving);
        btn_eadddriving.setOnClickListener(this);

        btn_back=findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_eimage=findViewById(R.id.btn_eimage);
        btn_eimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            // There are no request codes
            Intent intent=new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            galleryActivityResultLauncher.launch(intent);

            }
        });
        sharedpreferences = getSharedPreferences(MYPREFERENCES, 0);
    }
    private ActivityResultLauncher<Intent>galleryActivityResultLauncher=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()==Activity.RESULT_OK){
                        Intent data=result.getData();
                        Uri imageUri=data.getData();
//                        user_image.setImageBitmap(BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.profile_image));
                        Toast.makeText(MyDetails.this,"Profile Changed",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MyDetails.this,"No Image Selected",Toast.LENGTH_SHORT).show();

                    }
                }
            }
    );

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(this,EditMyDetails.class);
        intent.putExtra("userdetails",userdetails);
        switch (view.getId()) {
            case R.id.btn_eusername:
                intent.putExtra("current","username");
                startActivity(intent);
                break;

            case R.id.btn_efullname:
                intent.putExtra("current","fullname");
                startActivity(intent);

                break;
            case R.id.btn_eemail:
                intent.putExtra("current","email");
                startActivity(intent);

                break;
            case R.id.btn_eaddress:
                intent.putExtra("current","address");
                startActivity(intent);

                break;
            case R.id.btn_ephone:
                intent.putExtra("current","phone");
                startActivity(intent);

                break;
            case R.id.btn_epassword:
                intent.putExtra("current","password");
                startActivity(intent);

                break;
            case R.id.btn_eic:


                break;
            case R.id.btn_eadddriving:


                break;

        }
    }
}