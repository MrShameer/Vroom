package com.example.vroom.ui.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.vroom.R;
import com.example.vroom.database.User.User;
import com.example.vroom.database.User.UserViewModel;


public class EditMyDetails extends AppCompatActivity {
    Intent intent;
    private User user;
    private UserViewModel userViewModel;
    EditText et_newdetails;
    TextView tv_details,tv_current,tv_titles,tv_new;
    ConstraintLayout cl_hide;
    Button btn_done,btn_cancel;
    ImageView iv_camera;
    private String userID,name, email,address,phone,icstatus,dlstatus;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editdetails);
        userViewModel=new ViewModelProvider(this).get(UserViewModel.class);
        et_newdetails=findViewById(R.id.et_newdetails);
        tv_titles=findViewById(R.id.tv_titles);
        tv_details=findViewById(R.id.tv_details);
        tv_current=findViewById(R.id.tv_current);
        tv_new=findViewById(R.id.tv_new);
        btn_done=findViewById(R.id.btn_done);
        btn_cancel=findViewById(R.id.btn_cancel);
        cl_hide=findViewById(R.id.cl_hide);
        iv_camera=findViewById(R.id.iv_camera);
        intent=getIntent();

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

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(et_newdetails.getText().toString())){
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    }
                else{
                    eventsetup();
                    //TODO
                    user=new User(userID,name,email,"lessee",address,phone,icstatus,dlstatus);
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
        tv_titles.setText("Change "+intent.getStringExtra("TITLE"));
        tv_current.setText("Current "+intent.getStringExtra("TITLE"));
        tv_new.setText("New "+intent.getStringExtra("TITLE"));
        if (intent.hasExtra("CURRENT")){
            iv_camera.setVisibility(View.GONE);
            tv_details.setText(intent.getStringExtra("CURRENT"));
        }
        else if (intent.hasExtra("PASSWORD")){
            iv_camera.setVisibility(View.GONE);
        }
        else if (intent.hasExtra("IC")){
            cl_hide.setVisibility(View.GONE);
        }
        else if (intent.hasExtra("DRIVING")){
            cl_hide.setVisibility(View.GONE);
        }
    }
}
