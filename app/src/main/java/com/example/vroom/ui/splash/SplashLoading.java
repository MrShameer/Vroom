package com.example.vroom.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.vroom.Login;
import com.example.vroom.MainActivity;
import com.example.vroom.R;
import com.example.vroom.api.Request;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.RequestBody;

public class SplashLoading extends AppCompatActivity {
    Request request = new Request();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_loading);
        new mytask().execute();
    }

    private class mytask extends AsyncTask<Void,Void,Void> {
        String respond;
        JSONObject jsonObject = null;
        Intent intent;

        @Override
        protected Void doInBackground(Void... voids) {
            String token = getString(R.string.tokentemporary);
            RequestBody requestBody = RequestBody.create(null, new byte[0]);
            respond = request.PostHeader(requestBody,getString(R.string.validate),token);
            try {
                jsonObject = new JSONObject(respond);
                if (jsonObject.has("id")){
                    System.out.println(jsonObject.getString("id"));//NI ID EHH SO STORE MANE2
                    jsonObject.getString("name");
                    jsonObject.getString("email");
                    jsonObject.getString("role");
                    jsonObject.getString("phone");
                    //SEMUA NI STORE SEKALI EH

                    Thread.sleep(2000); //saja nk bgi org tgok dulu
                    intent = new Intent(SplashLoading.this, MainActivity.class);
                }
                else {
                    Thread.sleep(2000);
                    intent = new Intent(SplashLoading.this, Login.class);
                }
                startActivity(intent);
                finish();
            } catch (JSONException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}