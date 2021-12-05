package com.example.vroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vroom.api.Request;

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

    TextFieldBoxes TFemail;
    TextFieldBoxes TFpassword;

    String email;
    String password;

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
                    //new SignUp.mytask().execute();
                    new mytask().execute();
//                    Toast.makeText(getBaseContext(), "Check your email and verify your account", Toast.LENGTH_LONG).show();
//                    finish();
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
        String ok;
        @Override
        protected Void doInBackground(Void... voids) {
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("email", email)
                    .addFormDataPart("password", password)
                    .build();

            //new Request(requestBody,"https://vroom.lepak.xyz/login.php");
            ok = request.RequestPost(requestBody,"https://vroom.lepak.xyz/login.php");
            if (ok.equals("200")){
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
//            else
//            {
//                ETemail.set("Account Doesn't Exist");
//            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
            if (!ok.equals("200")){
                Toast.makeText(getBaseContext(), ok, Toast.LENGTH_LONG).show();
            }
        }
    }

}