package com.example.vroom.ui.vehicledetails;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.vroom.R;
import com.example.vroom.api.Request;
import com.example.vroom.database.TokenHandler;

import org.json.JSONException;
import org.json.JSONObject;


import java.text.DateFormat;
import java.util.Calendar;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SetReqDetails extends AppCompatActivity {
    ImageButton btn_back;
    public TextView btn_req;
    public static TextView tv_startdate,tv_enddate;
    public TextView tv_pickuplocation;
    public TextView tv_pickupdate;
    public TextView tv_cost;
    String selectedDate;
    Intent intent;
    Request request = new Request();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_details);

        tv_startdate=findViewById(R.id.tv_startdate);
        tv_enddate=findViewById(R.id.tv_enddate);
        tv_pickuplocation=findViewById(R.id.tv_pickuplocation);
        tv_pickupdate=findViewById(R.id.tv_pickupdate);
        tv_cost=findViewById(R.id.tv_cost);


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

        tv_startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment("start");
                newFragment.show(getSupportFragmentManager(), "datePicker");

            }
        });
        tv_enddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment("end");
                newFragment.show(getSupportFragmentManager(), "datePicker");
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

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        TextView tv1;
        public DatePickerFragment(String selectedDate) {
            if(selectedDate.equals("start")){
                tv1=SetReqDetails.tv_startdate;

            }else
                tv1=SetReqDetails.tv_enddate;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Create a Calendar instance
            Calendar mCalendar = Calendar.getInstance();
            // Set static variables of Calendar instance
            mCalendar.set(Calendar.YEAR,year);
            mCalendar.set(Calendar.MONTH,month);
            mCalendar.set(Calendar.DAY_OF_MONTH,day);
            // Get the date in form of string
            String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(mCalendar.getTime());
            // Set the textview to the selectedDate String
            tv1.setText(selectedDate);
        }

    }

}