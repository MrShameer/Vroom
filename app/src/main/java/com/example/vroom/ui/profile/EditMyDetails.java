package com.example.vroom.ui.profile;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.vroom.R;
import com.example.vroom.api.Request;
import com.example.vroom.database.TokenHandler;
import com.example.vroom.database.User.User;
import com.example.vroom.database.User.UserViewModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
    LinearLayoutCompat ll_details;
    Button btn_done,btn_cancel;
    ImageView iv_camera, iv_card,iv_profile;
    User currentuser;
    File file;
    String data,document;
    int current=0;
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
        iv_card=findViewById(R.id.iv_card);
        iv_profile=findViewById(R.id.iv_profile);
        ll_details=findViewById(R.id.ll_details);
        intent=getIntent();
        document=intent.getStringExtra("TITLE");

        AlertDialog.Builder builder= new AlertDialog.Builder(EditMyDetails.this);
        builder.setMessage("Empty");
        builder.setTitle("Please Fill in Details !");
        builder.setPositiveButton("Okay", (dialog, which) -> dialog.cancel());
        eventsetup();
        btn_done.setOnClickListener(view -> {
            if(TextUtils.isEmpty(et_newdetails.getText().toString())){
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                }
            else{
                new mytask().execute();
            }
        });
        btn_cancel.setOnClickListener (view -> finish());

        iv_camera.setOnClickListener(view -> {
            int permissionstorage = ContextCompat.checkSelfPermission(EditMyDetails.this, Manifest.permission.READ_EXTERNAL_STORAGE);
            if (permissionstorage != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(EditMyDetails.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }else{
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 0);

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
        else if (intent.hasExtra("IC")){
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
            for(int a=0;a<=1;a++){
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 0);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null) {
            Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
            String filepathh = Environment.getExternalStorageDirectory() + "/Android/data/" + getApplicationContext().getPackageName()+"/Picture/";

            if(current==0){
                file = new File(filepathh+"/"+document+".jpg");
                if (file.exists()) file.delete();
                try {
                    FileOutputStream out = new FileOutputStream(file);
                    selectedImage.compress(Bitmap.CompressFormat.JPEG, 90, out);
                    out.flush();
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                iv_card.setImageBitmap(selectedImage);
                current++;
            }
            else
            {
                file = new File(filepathh+"/"+document+"_profile.jpg");
                if (file.exists()) file.delete();
                try {
                    FileOutputStream out = new FileOutputStream(file);
                    selectedImage.compress(Bitmap.CompressFormat.JPEG, 90, out);
                    out.flush();
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            iv_profile.setImageBitmap(selectedImage);
            }

        }
    }

    private class mytask extends AsyncTask<Void,Void,Void> {
        String respond;
        Boolean sucess=false;
        @Override
        protected Void doInBackground(Void... voids) {
            String token = TokenHandler.read(TokenHandler.USER_TOKEN, null);
            if (data.equals("I/C")){
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("image",intent.getStringExtra("TITLE")+".jpg", RequestBody.create(MediaType.parse("image/*"),file))
                        .addFormDataPart("path", "identification")
                        .build();
                respond = request.PostHeader(requestBody,getString(R.string.uploadimage),token);
            }
            else if (data.equals("Driving License")){
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("image",intent.getStringExtra("TITLE")+".jpg", RequestBody.create(MediaType.parse("image/*"),file))
                        .addFormDataPart("path", "license")
                        .build();
                respond = request.PostHeader(requestBody,getString(R.string.uploadimage),token);
            }
            else{
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("column", data.toLowerCase())
                        .addFormDataPart("data", et_newdetails.getText().toString())
                        .build();
                respond = request.PostHeader(requestBody,getString(R.string.updateinfo),token);
            }

            if (respond.contains("success")){
                sucess=true;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (sucess){
                switch (data){
                    case "Name":
                        currentuser.setName(et_newdetails.getText().toString());
                        break;
//                    case "Address":
//                        currentuser.setAddress(et_newdetails.getText().toString());
//                        break;
                    case "Phone":
                        currentuser.setPhone(et_newdetails.getText().toString());
                        break;
                    case "I/C":
                        currentuser.setIcstatus("review");
                        break;
                    case "Driving License":
                        currentuser.setDlstatus("review");
                        break;
                }
                userViewModel.update(currentuser);
                Toast.makeText(getBaseContext(),"Your Information Has Been Updated", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getBaseContext(),"Sorry, A Problem Occur While Updating Your Information ", Toast.LENGTH_LONG).show();
            }
            finish();
        }
    }
}
