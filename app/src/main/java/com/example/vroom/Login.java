package com.example.vroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vroom.api.Request;
import com.example.vroom.database.TokenHandler;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class Login extends AppCompatActivity {
    Request request = new Request();
    Button btn_login;
    Button btn_signup;
    ExtendedEditText ETemail;
    ExtendedEditText ETpasword;
    String email;
    String password;
    String fcmtoken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = findViewById(R.id.btn_login);
        btn_signup = findViewById(R.id.btn_signup);
        ETemail=findViewById(R.id.email);
        ETpasword=findViewById(R.id.password);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });
    }

    private class mytask extends AsyncTask<Void,Void,Void> {
        String respond,id,pic;
        JSONObject jsonObject = null;
        Boolean RunIntent = false;
        Intent intent;
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
                    id=jsonObject.getString("id");
                    pic=jsonObject.getString("picture");
                    TokenHandler.write("USER_ID",id);
                    TokenHandler.write("USER_TOKEN",jsonObject.getString("access_token"));
                    TokenHandler.write("USER_ROLE",jsonObject.getString("role"));
                    TokenHandler.write("USER_PIC",pic);
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
                Picasso.get().load(getString(R.string.profilepic)+id+"."+pic).into(request.SaveImage(getApplicationContext().getPackageName()+"/Picture/",id+"."+pic));
                startActivity(intent);
                finish();
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