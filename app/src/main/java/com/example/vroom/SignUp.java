package com.example.vroom;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vroom.api.Request;

import java.util.Random;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;

public class SignUp extends AppCompatActivity {
    Random rand = new Random();
    Request request = new Request();
    Button btn_signup;
    Button btn_login;

    ExtendedEditText ETname;
    ExtendedEditText ETemail;
    ExtendedEditText ETpasword;

    String name;
    String email;
    String password;
    String code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

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
        //name.getEditableText().toString()
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              Intent intent = new Intent(Login.this, MainActivity.class);
//              startActivity(intent);

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
                    new mytask().execute();
                    Toast.makeText(getBaseContext(), "Check your email and verify your account", Toast.LENGTH_LONG).show();
                    finish();
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

        @Override
        protected Void doInBackground(Void... voids) {
            code = String.valueOf(rand.nextLong());
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("code",code)
                    .addFormDataPart("email", email)
                    .addFormDataPart("name", name)
                    .addFormDataPart("password", password)
                    .build();

           // new Request(requestBody,"https://vroom.lepak.xyz/insert.php");
            request.Request(requestBody,"https://vroom.lepak.xyz/insert.php");
            return null;
        }
    }

}