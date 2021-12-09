package com.example.vroom.ui.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.vroom.MainActivity;
import com.example.vroom.R;
import com.example.vroom.database.User.User;
import com.example.vroom.database.User.UserViewModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditMyDetails extends AppCompatActivity {
    Intent intent;
    private User user;
    private UserViewModel userViewModel;
    EditText et_newdetails;
    TextView tv_details,tv_titles;
    Button btn_done,btn_cancel;
    private String userID,username,name, email,address,phone,password,icstatus,dlstatus;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editdetails);
        userViewModel=new ViewModelProvider(this).get(UserViewModel.class);
        et_newdetails=findViewById(R.id.et_newdetails);
        tv_titles=findViewById(R.id.tv_titles);
        tv_details=findViewById(R.id.tv_details);
        btn_done=findViewById(R.id.btn_done);
        btn_cancel=findViewById(R.id.btn_cancel);
        intent=getIntent();
        ArrayList<String> userdetails =(ArrayList<String>) intent.getSerializableExtra("userdetails");
        userID=userdetails.get(0);
        username=userdetails.get(1);
        name=userdetails.get(2);
        email=userdetails.get(3);
        address=userdetails.get(4);
        phone=userdetails.get(5);
        password=userdetails.get(6);
        icstatus=userdetails.get(7);
        dlstatus=userdetails.get(8);
        AlertDialog.Builder builder= new AlertDialog.Builder(EditMyDetails.this);
        builder.setMessage("Empty");
        builder.setTitle("Please Fill in Details !");
        builder.setPositiveButton("Okay",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog,int which)
            {
                dialog.cancel();
            }
        });
        eventsetup();
//        tv_titles.setText(intent.hasExtra("username"));


        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(et_newdetails.getText().toString())){
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    }
                else{
                    eventsetup();
//                    user=new User(userID,username,name,email,address,phone,password);
                    user=new User(userID,username,name,email,address,phone,password,icstatus,dlstatus);
                    user.setUserID(userID);
                    userViewModel.update(user);
                    Intent intent=new Intent(EditMyDetails.this,MyDetails.class);
                    startActivity(intent);
                    finishAndRemoveTask();

                }


            }
        });
        btn_cancel.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    public void eventsetup(){
        switch(intent.getStringExtra("current")){
            case "username":
                tv_titles.setText("Change Username");
                tv_details.setText(username);
                username=et_newdetails.getText().toString();
                break;

            case "fullname":
                tv_titles.setText("Change Name");
                tv_details.setText(name);
                name=et_newdetails.getText().toString();
                break;

            case "email":
                tv_titles.setText("Change Email");
                tv_details.setText(email);
                email=et_newdetails.getText().toString();
                break;

            case "phone":
                tv_titles.setText("Change Phone");
                tv_details.setText(phone);
                phone=et_newdetails.getText().toString();
                break;

            case "password":
                tv_titles.setText("Change Password");
                tv_details.setText(password);
                password=et_newdetails.getText().toString();
                break;
        }}



}
