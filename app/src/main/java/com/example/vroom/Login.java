package com.example.vroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vroom.api.Request;
import com.example.vroom.database.TokenHandler;

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
                    new mytask().execute();
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
        String respond;
        JSONObject jsonObject = null;
        @Override
        protected Void doInBackground(Void... voids) {
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("email", email)
                    .addFormDataPart("password", password)
                    .build();

            respond = request.RequestPost(requestBody,getString(R.string.login));

            try {
                jsonObject = new JSONObject(respond);
                if (jsonObject.has("access_token")){
                    TokenHandler.write("USER_ID",jsonObject.getString("id"));
                    TokenHandler.write("USER_TOKEN",jsonObject.getString("access_token"));
                    TokenHandler.write("USER_ROLE",jsonObject.getString("role"));
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (jsonObject.has("message")){
                try {
                    Toast.makeText(getBaseContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}