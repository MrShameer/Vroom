package com.example.vroom.ui.vehicledetails;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.vroom.R;
import com.example.vroom.api.Request;
import com.example.vroom.database.TokenHandler;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SetReqDetails extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    int day, month, year, hour, minute;
    int myday, myMonth, myYear, myHour, myMinute;
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

        tv_pickupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(SetReqDetails.this, SetReqDetails.this,year, month,day);
                datePickerDialog.show();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        myYear = year;
        myday = day;
        myMonth = month;
        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(SetReqDetails.this, SetReqDetails.this, hour, minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        myHour = hourOfDay;
        myMinute = minute;
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,myYear);
        calendar.set(Calendar.MONTH,myMonth);
        calendar.set(Calendar.DAY_OF_MONTH,myday);
        calendar.set(Calendar.HOUR_OF_DAY,myHour);
        calendar.set(Calendar.MINUTE,myMinute);
        Date date1=calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        String pickupdate=formatter.format(date1);
        tv_pickupdate.setText(pickupdate);
//        tv_pickupdate.setText("Year: " + myYear + "\n" +
//                "Month: " + myMonth + "\n" +
//                "Day: " + myday + "\n" +
//                "Hour: " + myHour + "\n" +
//                "Minute: " + myMinute);
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
            SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy");
            Date date1=mCalendar.getTime();
            String selectedDate = formatter.format(date1);
            // Set the textview to the selectedDate String
            tv1.setText(selectedDate);
        }

    }

}