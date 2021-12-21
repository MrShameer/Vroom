package com.example.vroom;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vroom.api.Request;
import com.example.vroom.ui.chat.ChatFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.example.vroom.database.TokenHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;

public class SignUp extends AppCompatActivity {
    Request request = new Request();
    Button btn_signup;
    Button btn_login;

    ExtendedEditText ETname;
    ExtendedEditText ETemail;
    ExtendedEditText ETpasword;

    String name;
    String email;
    String password;
    String fcmtoken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        TokenHandler.init(getApplicationContext());
        btn_signup= findViewById(R.id.btn_signup);
        btn_login=findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUp.this,Login.class);
                startActivity(intent);
            }
        });
        ETname=findViewById(R.id.name);
        ETemail=findViewById(R.id.email);
        ETpasword=findViewById(R.id.password);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 name = ETname.getEditableText().toString();
                 email = ETemail.getEditableText().toString();
                 password = ETpasword.getEditableText().toString();

                if (name.isEmpty()) {
                    Toast.makeText(getBaseContext(), "Please put in your name", Toast.LENGTH_SHORT).show();
                } else if (email.isEmpty()) {
                    Toast.makeText(getBaseContext(), "Please put in your email", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty() || password.length() <5) {
                    Toast.makeText(getBaseContext(), "Please put in your password and make sure it's more than 5 character", Toast.LENGTH_LONG).show();
                }
                else {
                    FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
                                @Override
                                public void onComplete(@NonNull Task<String> task) {
                                    if (!task.isSuccessful()) {
                                        return;
                                    }
                                    fcmtoken=task.getResult();
                                    new mytask().execute();
                                }
                            });
                }
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private class mytask extends AsyncTask<Void,Void,Void>{
        String respond;
        JSONObject jsonObject = null;
        @Override
        protected Void doInBackground(Void... voids) {
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("email", email)
                    .addFormDataPart("name", name)
                    .addFormDataPart("password", password)
                    .addFormDataPart("fcm",fcmtoken)
                    .build();

            respond = request.RequestPost(requestBody,getString(R.string.register));
            try {
                jsonObject = new JSONObject(respond);
                if (jsonObject.has("access_token")){
                    Intent intent = new Intent(SignUp.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getBaseContext(), "Check your email and verify your account", Toast.LENGTH_LONG).show();
        }
    }

}