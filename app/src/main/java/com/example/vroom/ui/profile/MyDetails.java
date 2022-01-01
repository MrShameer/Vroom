package com.example.vroom.ui.profile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.vroom.R;
import com.example.vroom.database.TokenHandler;
import com.example.vroom.database.User.User;
import com.example.vroom.database.User.UserViewModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyDetails extends AppCompatActivity implements View.OnClickListener  {
    private static final int PICK_IMAGE = 1;
    private UserViewModel userViewModel;
    Button btn_eimage;
    ImageButton btn_back,btn_efullname,btn_eemail,btn_eaddress,btn_ephone,btn_epassword,btn_eic,btn_eadddriving;
    TextView tv_fullname,tv_email,tv_address,tv_phone,tv_addic,tv_adddriving;
    private static final int GALLERY_CODE = 103;
    CircleImageView user_image;
    User currentUser;
    String mImageName = TokenHandler.read(TokenHandler.USER_ID, null)+".jpg";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydetails);

        user_image=findViewById(R.id.user_image);
        File dir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + getApplicationContext().getPackageName()
                + "/Picture/");


        File file = new File(dir, mImageName);
        if(file.exists()){
            Picasso.get().load(file).into(user_image);
        }else{
            user_image.setImageResource(R.drawable.profile_image);
        }

        tv_fullname=findViewById(R.id.tv_fullname);
        tv_email=findViewById(R.id.tv_email);
        tv_address=findViewById(R.id.tv_address);
        tv_phone=findViewById(R.id.tv_phone);
        tv_addic=findViewById(R.id.tv_addic);
        tv_adddriving=findViewById(R.id.tv_adddriving);

        userViewModel=new ViewModelProvider(this).get(UserViewModel.class);
        getData();
        btn_efullname=findViewById(R.id.btn_efullname);
        btn_efullname.setOnClickListener(this);

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

        btn_eimage=findViewById(R.id.btn_eimage);
        btn_eimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

        btn_back=findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAndRemoveTask();
            }
        });
    }
    @Override
    public void onBackPressed() {
        finishAndRemoveTask();
    }

    public void getData(){
        userViewModel.getGetAllUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                currentUser=users.get(0);
                tv_fullname.setText(currentUser.getName());
                tv_email.setText(currentUser.getEmail());
                tv_address.setText(currentUser.getAddress());
                tv_phone.setText(currentUser.getPhone());
                tv_addic.setText(currentUser.getIcstatus());
                tv_adddriving.setText(currentUser.getDlstatus());
                //TODO
                // FIX/SIAPKAN IC AND DRIVING LICENSE
//                if(userdetails.get(7).equals("IC is Under Review")){
//                    tv_addic.setTextColor(Color.YELLOW);
//                    btn_eic.setVisibility(View.INVISIBLE);
//                }
//                if(userdetails.get(8).equals("Driver's License is Under Review")){
//                    tv_adddriving.setTextColor(Color.YELLOW);
//                    btn_eadddriving.setVisibility(View.INVISIBLE);
//                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(this,EditMyDetails.class);
//        System.out.println(view.getParent());
        switch (view.getId()) {
            //TODO
            case R.id.btn_efullname:
                intent.putExtra("TITLE","Name");
                intent.putExtra("CURRENT",currentUser.getName());
                break;
            case R.id.btn_eaddress:
                intent.putExtra("TITLE","Address");
                intent.putExtra("CURRENT",currentUser.getAddress());
                break;
            case R.id.btn_ephone:
                intent.putExtra("TITLE","Phone");
                intent.putExtra("CURRENT",currentUser.getPhone());
                break;
            case R.id.btn_epassword:
                intent.putExtra("TITLE","Password");
                intent.putExtra("password","");
                break;
            case R.id.btn_eic:
                intent.putExtra("TITLE","I/C");
                intent.putExtra("IC","");
                break;
            case R.id.btn_eadddriving:
                intent.putExtra("TITLE","Driving License");
                intent.putExtra("DRIVING","");
                break;
        }
        intent.putExtra("DATA",  currentUser);
        startActivity(intent);
        finishAndRemoveTask();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //TODO
        // HANTR GMBR KE SERVER
        if (resultCode== Activity.RESULT_OK){
            Picasso.get().load(data.getData()).into(new Target() {
                File file;
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    getExternalCacheDir().getParent();
                    file = new File(Environment.getExternalStorageDirectory() + "/Android/data/" + getApplicationContext().getPackageName()+"/Picture/");
                    if (!file.exists()){
                        file.mkdirs();
                    }
                    file = new File(file+"/"+mImageName);
                    try {
                        file.createNewFile();
                        FileOutputStream ostream = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
                        ostream.flush();
                        ostream.close();
                        Picasso.get().load(data.getData()).into(user_image);
                    }catch (IOException e){
                        Log.e("IOException", e.getLocalizedMessage());
                    }
                }
                @Override
                public void onBitmapFailed(Exception e, Drawable errorDrawable) { }
                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) { }
            });
        }
    }
}