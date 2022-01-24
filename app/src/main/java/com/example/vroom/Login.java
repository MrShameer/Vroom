package com.example.vroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vroom.api.Request;
import com.example.vroom.database.TokenHandler;
import com.example.vroom.database.User.User;
import com.example.vroom.database.User.UserViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import androidx.lifecycle.ViewModelProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;

public class Login extends AppCompatActivity {
    Request request = new Request();
    Button btn_login, btn_signup;
    ExtendedEditText ETemail, ETpasword;
    String email, password, fcmtoken;
    Intent intent;
    private UserViewModel userViewModel;
    public static Boolean RunIntent = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = findViewById(R.id.btn_login);
        btn_signup = findViewById(R.id.btn_signup);
        ETemail=findViewById(R.id.email);
        ETpasword=findViewById(R.id.password);

        userViewModel=new ViewModelProvider(this).get(UserViewModel.class);

        btn_login.setOnClickListener(view -> {
            email = ETemail.getEditableText().toString();
            password = ETpasword.getEditableText().toString();
            if (email.isEmpty()) {
                Toast.makeText(getBaseContext(), "Please put in your email", Toast.LENGTH_SHORT).show();
            } else if (password.isEmpty()) {
                Toast.makeText(getBaseContext(), "Please put in your password", Toast.LENGTH_LONG).show();
            }
            else {
                FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }
                        TokenHandler.init(getApplicationContext());
                        fcmtoken=task.getResult();
                        new mytask().execute();
                    }
                });
            }
        });

        btn_signup.setOnClickListener(view -> {
            intent = new Intent(Login.this, SignUp.class);
            startActivity(intent);
        });
    }

    private class mytask extends AsyncTask<Void,Void,Void> {
        String respond,id,name,role,address,phone,icstatus,dlstatus;
        JSONObject jsonObject = null;
        private User user;

        @Override
        protected Void doInBackground(Void... voids) {
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("email", email)
                    .addFormDataPart("password", password)
                    .addFormDataPart("fcm",fcmtoken)
                    .build();
            respond = request.RequestPost(requestBody,getString(R.string.login));

            try {
                jsonObject = new JSONObject(respond);
                if (jsonObject.has("access_token")){
                    JSONObject info = jsonObject.getJSONObject("info");
                    //TODO
                    id=info.getString("id");
                    name=info.getString("name");
                    role=info.getString("role");
                    phone=(info.getString("phone").equals("null")) ? "" : info.getString("phone");
                    icstatus=info.getString("icverified");
                    dlstatus=info.getString("dlverified");
                    TokenHandler.write("USER_ID",id);
                    TokenHandler.write("USER_TOKEN",jsonObject.getString("access_token"));
                    intent = new Intent(Login.this, MainActivity.class);
                    RunIntent = true;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (RunIntent){
                Picasso.get().load(getString(R.string.profilepic)+id+".jpg").into(new Target() {
                    File file;
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        getExternalCacheDir().getParent();
                        file = new File(Environment.getExternalStorageDirectory() + "/Android/data/" + getApplicationContext().getPackageName()+"/Picture/");
                        if (!file.exists()){
                            file.mkdirs();
                        }
                        file = new File(file+"/"+id+".jpg");
                        try {
                            file.createNewFile();
                            FileOutputStream ostream = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
                            ostream.flush();
                            ostream.close();
                            startActivity(intent);finish();
                        }catch (IOException e){
                            Log.e("IOException", e.getLocalizedMessage());
                        }
                    }
                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                        startActivity(intent);finish();
                    }
                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) { }
                });

                user=new User(id,name,email,role,address,phone,icstatus,dlstatus);
                user.setUserID(id);
                userViewModel.deleteAll(user);
                userViewModel.insert(user);
            }
            else if (jsonObject.has("message")){
                try {
                    Toast.makeText(getBaseContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}