package com.example.vroom.ui.lessor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.vroom.R;

import java.util.ArrayList;
import java.util.List;

public class LessorAddVehicle extends AppCompatActivity {
    TextView tv_type,tv_brand,tv_color,tv_age,tv_platno,tv_condition,tv_insurance,tv_rate,tv_available,tv_location;
    Button btn_type,btn_brand,btn_color,btn_age,btn_platno,btn_condition,btn_insurance,btn_rate,btn_available,btn_location;
    ImageButton btn_back;
    List<String> type = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessor_add_vehicle);

        //Declaration
        tv_type=findViewById(R.id.tv_type);
        tv_brand=findViewById(R.id.tv_brand);
        tv_color=findViewById(R.id.tv_color);
        tv_age=findViewById(R.id.tv_age);
        tv_platno=findViewById(R.id.tv_platno);
        tv_condition=findViewById(R.id.tv_condition);
        tv_insurance=findViewById(R.id.tv_insurance);
        tv_rate=findViewById(R.id.tv_rate);
        tv_available=findViewById(R.id.tv_available);
        tv_location=findViewById(R.id.tv_location);
        btn_type=findViewById(R.id.btn_type);
        btn_brand=findViewById(R.id.btn_brand);
        btn_color=findViewById(R.id.btn_color);
        btn_age=findViewById(R.id.btn_age);
        btn_platno=findViewById(R.id.btn_platno);
        btn_condition=findViewById(R.id.btn_condition);
        btn_insurance=findViewById(R.id.btn_insurance);
        btn_rate=findViewById(R.id.btn_rate);
        btn_available=findViewById(R.id.btn_available);
        btn_location=findViewById(R.id.btn_location);
        btn_back=findViewById(R.id.btn_back);

        type.add("Car");
        type.add("Bike");
        type.add("Van");

        btn_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialogButtonClicked(v,"type");
            }
        });
        btn_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialogButtonClicked(v,"color");
            }
        });
    }

    public void showAlertDialogButtonClicked(View view,String current)
    {
        // Create an alert builder
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        View customLayout;
        switch(current){
            case "type":
                customLayout=getLayoutInflater().inflate(R.layout.layout_dialog_type,null);
                builder.setTitle("Vehicle Type");
                builder.setView(customLayout);
                //create an ArrayAdapter from the String Array
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(LessorAddVehicle.this,R.layout.spinner_text, type);
                //set the view for the Drop down list
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //set the ArrayAdapter to the spinner
                Spinner spinner=(Spinner) customLayout.findViewById(R.id.spinner);
                spinner.setAdapter(dataAdapter);
                spinner.setPrompt("Type");
                spinner.setOnItemSelectedListener(new LessorAddVehicle.MyOnItemSelectedListener());

                builder.setPositiveButton("OK", (dialog, which) -> {
                    tv_type.setText("Done");
                    tv_type.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.accepted));
//                        EditText editText= customLayout.findViewById(R.id.editText);
//                        sendDialogDataToActivity(editText.getText().toString());
                });
                break;
            case "brand":
                customLayout=getLayoutInflater().inflate(R.layout.layout_dialog_brand,null);
                builder.setTitle("Vehicle Brand and Model");
                builder.setView(customLayout);
                builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which)
                    {


                    }
                });
                break;
            case "color":
                customLayout=getLayoutInflater().inflate(R.layout.layout_dialog_color,null);
                builder.setTitle("Vehicle Colour");
                builder.setView(customLayout);
                builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which)
                    {
                        tv_color.setText("Done");
                        tv_color.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.accepted));

                    }
                });
                break;
            case "age":
                customLayout=getLayoutInflater().inflate(R.layout.layout_dialog_age,null);
                builder.setTitle("Vehicle Age");
                builder.setView(customLayout);
                builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which)
                    {


                    }
                });
                break;
            case "plat no":
                customLayout=getLayoutInflater().inflate(R.layout.layout_dialog_platno,null);
                builder.setTitle("Vehicle Plat No");
                builder.setView(customLayout);
                builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which)
                    {


                    }
                });
                break;
            case "insurance":
                customLayout=getLayoutInflater().inflate(R.layout.layout_dialog_insurance,null);
                builder.setTitle("Vehicle Insurance");
                builder.setView(customLayout);
                builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which)
                    {


                    }
                });
                break;
            case "rate":
                customLayout=getLayoutInflater().inflate(R.layout.layout_dialog_rate,null);
                builder.setTitle("Rate Per Day");
                builder.setView(customLayout);
                builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which)
                    {


                    }
                });
                break;
            case "days":
                customLayout=getLayoutInflater().inflate(R.layout.layout_dialog_days,null);
                builder.setTitle("Available Days");
                builder.setView(customLayout);
                builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which)
                    {


                    }
                });
                break;
            case "location":
                customLayout=getLayoutInflater().inflate(R.layout.layout_dialog_location,null);
                builder.setTitle("Vehicle Location  ");
                builder.setView(customLayout);
                builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which)
                    {


                    }
                });
                break;
        }
        AlertDialog dialog= builder.create();
        dialog.show();
    }

    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(@NonNull AdapterView<?> parent, View view, int pos, long id) {

            String selectedItem = parent.getItemAtPosition(pos).toString();
            Toast.makeText(LessorAddVehicle.this, selectedItem, Toast.LENGTH_SHORT).show();

        }

        public void onNothingSelected(AdapterView<?> parent) {

        }

    }
    private void sendDialogDataToActivity(String data)
    {
        Toast.makeText(this,data,Toast.LENGTH_SHORT).show();
    }
}
