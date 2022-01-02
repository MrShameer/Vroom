package com.example.vroom.ui.profile;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.vroom.R;
import com.example.vroom.api.Request;
import com.example.vroom.database.TokenHandler;
import com.example.vroom.database.User.User;
import com.example.vroom.database.User.UserViewModel;

import org.json.JSONObject;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class EditMyDetails extends AppCompatActivity {
    private static final int PICK_IMAGE = 1;
    Intent intent;
    Request request = new Request();
    private UserViewModel userViewModel;
    EditText et_newdetails;
    TextView tv_details,tv_current,tv_titles,tv_new;
    ConstraintLayout cl_hide;
    Button btn_done,btn_cancel;
    ImageView iv_camera;
    User currentuser;
    File file;
    String data;
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
                    new mytask().execute();
                }
            }
        });
        btn_cancel.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        iv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int permissionstorage = ContextCompat.checkSelfPermission(EditMyDetails.this, Manifest.permission.READ_EXTERNAL_STORAGE);
                if (permissionstorage != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(EditMyDetails.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                }else{
                    Intent intent=new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
                }
            }
        });
    }

    public void eventsetup(){
        currentuser = (User) intent.getSerializableExtra("DATA");
        data = intent.getStringExtra("TITLE");
        tv_titles.setText("Change "+data);
        tv_current.setText("Current "+data);
        tv_new.setText("New "+data);
        if (intent.hasExtra("CURRENT")){
            iv_camera.setVisibility(View.GONE);
            tv_details.setText(intent.getStringExtra("CURRENT"));
        }
        else if (intent.hasExtra("PASSWORD")){
            iv_camera.setVisibility(View.GONE);
        }
        else if (intent.hasExtra("I/C")){
            cl_hide.setVisibility(View.GONE);
        }
        else if (intent.hasExtra("DRIVING")){
            cl_hide.setVisibility(View.GONE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
            Intent intent=new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            file=new File(request.getPath(getApplicationContext(),data.getData()));
        }
    }

    private class mytask extends AsyncTask<Void,Void,Void> {
        String respond;
        @Override
        protected Void doInBackground(Void... voids) {
            String token = TokenHandler.read(TokenHandler.USER_TOKEN, null);
            if (data.equals("I/C")){
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("image",currentuser.getUserID()+".jpg", RequestBody.create(MediaType.parse("image/*"),file))
                        .addFormDataPart("path", "identification")
                        .build();
                respond = request.PostHeader(requestBody,getString(R.string.uploadimage),token);
            }
            else if (data.equals("Driving License")){
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("image",currentuser.getUserID()+".jpg", RequestBody.create(MediaType.parse("image/*"),file))
                        .addFormDataPart("path", "license")
                        .build();
                respond = request.PostHeader(requestBody,getString(R.string.uploadimage),token);
            }
            else{
                //TODO : KT DATABASE TABLE USER NK STORE IC AND DRIVING LICENSE KE?
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("column", data.toLowerCase())
                        .addFormDataPart("data", et_newdetails.getText().toString())
                        .build();
                respond = request.PostHeader(requestBody,getString(R.string.updateinfo),token);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            switch (data){
                case "Name":
                    currentuser.setName(et_newdetails.getText().toString());
                    break;
                case "Address":
                    currentuser.setAddress(et_newdetails.getText().toString());
                    break;
                case "Phone":
                    currentuser.setPhone(et_newdetails.getText().toString());
                    break;
            }
            userViewModel.update(currentuser);
            finish();
        }
    }
}
