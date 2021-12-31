package com.example.vroom.ui.vehicledetails;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vroom.R;
import com.example.vroom.api.Request;
import com.example.vroom.database.TokenHandler;

import org.json.JSONException;
import org.json.JSONObject;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SetReqDetails extends AppCompatActivity {
    ImageButton btn_back;
    TextView btn_req;
    Intent intent;
    Request request = new Request();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_details);

        intent=getIntent();
        //setup Back Button
        btn_back=findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAndRemoveTask();
            }
        });

        btn_req=findViewById(R.id.btn_req);
        btn_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new mytask().execute();
            }
        });
    }

    private class mytask extends AsyncTask<Void,Void,Void> {
        String respond;
        JSONObject jsonObject = null;
        @Override
        protected Void doInBackground(Void... voids) {
            String token = TokenHandler.read(TokenHandler.USER_TOKEN, null);
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("plat", intent.getStringExtra("PLAT"))
                    .build();
            respond = request.PostHeader(requestBody,getString(R.string.requestvehicle),token);
            try {
                jsonObject=new JSONObject(respond);
                respond=jsonObject.getString("message");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(),respond,Toast.LENGTH_SHORT).show();
                if (respond.contains("Sucessfully")){
                    Intent intent=new Intent(SetReqDetails.this, VehicleSucessfull.class);
                    startActivity(intent);
                }
                finish();
        }
    }

}