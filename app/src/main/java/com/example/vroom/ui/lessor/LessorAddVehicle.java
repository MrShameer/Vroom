package com.example.vroom.ui.lessor;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;

import com.example.vroom.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class LessorAddVehicle extends AppCompatActivity {
    TextView tv_type,tv_brand,tv_age,tv_platno,tv_condition,tv_insurance,tv_rate,tv_available,tv_location;
    Button btn_type,btn_brand,btn_color,btn_age,btn_platno,btn_condition,btn_insurance,btn_rate,btn_available,btn_location,btn_submit;
    ImageButton btn_back;
    List<String> typelist = new ArrayList<>();
    List<String> brandlist = new ArrayList<>();
    List<String> modellist = new ArrayList<>();
    List<String> dayslist = new ArrayList<>();
    List<String> insurancelist = new ArrayList<>();
    List<String> locationlist = new ArrayList<>();
    String selectedItem,selectedItem2,brand,model,color,age,platno,startday,endday,insurance,rate,location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessor_add_vehicle);

        //Declaration
        tv_type=findViewById(R.id.tv_type);
        tv_brand=findViewById(R.id.tv_brand);
        tv_age=findViewById(R.id.tv_age);
        tv_platno=findViewById(R.id.tv_platno);
        tv_condition=findViewById(R.id.tv_condition);
        tv_insurance=findViewById(R.id.tv_insurance);
        tv_rate=findViewById(R.id.tv_rate);
        tv_available=findViewById(R.id.tv_available);
        tv_location=findViewById(R.id.tv_location);
        btn_type=findViewById(R.id.btn_type);
        btn_brand=findViewById(R.id.btn_brand);
        btn_age=findViewById(R.id.btn_age);
        btn_platno=findViewById(R.id.btn_platno);
        btn_condition=findViewById(R.id.btn_condition);
        btn_insurance=findViewById(R.id.btn_insurance);
        btn_rate=findViewById(R.id.btn_rate);
        btn_available=findViewById(R.id.btn_available);
        btn_location=findViewById(R.id.btn_location);
        btn_back=findViewById(R.id.btn_back);
        btn_submit=findViewById(R.id.btn_submit);

        typelist.add("Car");
        typelist.add("Bike");
        typelist.add("Van");

        brandlist.add("Proton");
        brandlist.add("Perodua");
        brandlist.add("Mercedes");

        modellist.add("Myvi");
        modellist.add("Bukan");
        modellist.add("Betullahtu");
        modellist.add("Betullah2");
        modellist.add("Betullah3");

        dayslist.add("Monday");
        dayslist.add("Tuesday");
        dayslist.add("Wednesday");
        dayslist.add("Thursday");
        dayslist.add("Friday");
        dayslist.add("Saturday");
        dayslist.add("Sunday");

        insurancelist.add("Allyance");
        insurancelist.add("Etiqa");
        insurancelist.add("Zurich");
        insurancelist.add("Takaful");
        insurancelist.add("AIA");
        insurancelist.add("AIG");
        insurancelist.add("Tokyo Marine");
        insurancelist.add("Axa Arifin");

        locationlist.add("location 1");
        locationlist.add("location 2");

        btn_type.setOnClickListener(v -> showAlertDialogButtonClicked(v,"type"));
        btn_brand.setOnClickListener(v -> showAlertDialogButtonClicked(v,"brand"));
        btn_age.setOnClickListener(v -> showAlertDialogButtonClicked(v,"age"));
        btn_platno.setOnClickListener(v -> showAlertDialogButtonClicked(v,"platno"));
        btn_insurance.setOnClickListener(v -> showAlertDialogButtonClicked(v,"insurance"));
        btn_rate.setOnClickListener(v -> showAlertDialogButtonClicked(v,"rate"));
        btn_available.setOnClickListener(v -> showAlertDialogButtonClicked(v,"days"));
        btn_location.setOnClickListener(v -> showAlertDialogButtonClicked(v,"location"));

        btn_condition.setOnClickListener(v -> {
            Intent intent= new Intent(LessorAddVehicle.this,LessorVehicleImage.class);
            startActivity(intent);
        });
        String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("statusimage");
                tv_condition.setText("Evaluating");
                tv_condition.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.accepted));
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("statusimage");
        }

        btn_submit.setOnClickListener(view -> {

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
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(LessorAddVehicle.this,R.layout.spinner_text, typelist);
                //set the view for the Drop down list
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //set the ArrayAdapter to the spinner
                Spinner spinner= customLayout.findViewById(R.id.spinner);
                spinner.setAdapter(dataAdapter);
                spinner.setPrompt("Type");
                spinner.setOnItemSelectedListener(new LessorAddVehicle.MyOnItemSelectedListener());

                builder.setPositiveButton("OK", (dialog, which) -> {
                    tv_type.setText(selectedItem);
                    tv_type.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.accepted));
                    btn_brand.setEnabled(true);
                    btn_color.setEnabled(true);
                    btn_age.setEnabled(true);
                    btn_platno.setEnabled(true);
                    btn_condition.setEnabled(true);
                    btn_insurance.setEnabled(true);
                    btn_rate.setEnabled(true);
                    btn_available.setEnabled(true);
                    btn_location.setEnabled(true);
                });
                break;

            case "brand":
                customLayout=getLayoutInflater().inflate(R.layout.layout_dialog_brand,null);
                builder.setTitle("Select Your Vehicle Brand and Model");
                builder.setView(customLayout);


                //create an ArrayAdapter from the String Array
                ArrayAdapter<String> dataAdapterbrand = new ArrayAdapter<>(LessorAddVehicle.this, R.layout.spinner_text, brandlist);
                //set the view for the Drop down list
                dataAdapterbrand.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //set the ArrayAdapter to the spinner
                Spinner spinnerbrand= customLayout.findViewById(R.id.spinnerbrand);
                spinnerbrand.setAdapter(dataAdapterbrand);
                spinnerbrand.setPrompt("Brand");
                spinnerbrand.setOnItemSelectedListener(new LessorAddVehicle.MyOnItemSelectedListener());


                ArrayAdapter<String> dataAdaptermodel = new ArrayAdapter<String>(LessorAddVehicle.this,R.layout.spinner_text, modellist);
                Spinner spinnermodel=customLayout.findViewById(R.id.spinnermodel);
                spinnermodel.setAdapter(dataAdaptermodel);
                spinnermodel.setPrompt("Model");
                spinnermodel.setOnItemSelectedListener(new LessorAddVehicle.MyOnItemSelectedListener());


                builder.setPositiveButton("OK", (dialog, which) -> {
                    selectedItem2=brand+" "+model;
                    tv_brand.setText(selectedItem2);
                    tv_brand.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.accepted));

                });
                break;

            case "age":
                customLayout=getLayoutInflater().inflate(R.layout.layout_dialog_age,null);
                builder.setTitle("Vehicle Age");
                builder.setView(customLayout);
                RadioGroup radiogroup = (RadioGroup) customLayout.findViewById(R.id.radiogroup);
                radiogroup.setOnCheckedChangeListener((RadioGroup group, int checkedId) -> {
                    RadioButton radioButton = (RadioButton) customLayout.findViewById(checkedId);
                    age=radioButton.getText().toString();
                });

                builder.setPositiveButton("OK", (dialog, which) -> {
                    tv_age.setText(age);
                    tv_age.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.accepted));

                });
                break;
            case "platno":
                customLayout=getLayoutInflater().inflate(R.layout.layout_dialog_platno,null);
                builder.setTitle("Vehicle Plat No");
                builder.setView(customLayout);
                EditText et_platno=(EditText)customLayout.findViewById(R.id.et_platno);

                builder.setPositiveButton("OK", (dialog, which) -> {
                    platno=et_platno.getText().toString();
                    tv_platno.setText(platno);
                    tv_platno.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.accepted));

                });
                break;
            case "insurance":
                customLayout=getLayoutInflater().inflate(R.layout.layout_dialog_insurance,null);
                builder.setTitle("Vehicle Insurance");
                builder.setView(customLayout);
                RadioGroup radiogroupinsurance = (RadioGroup) customLayout.findViewById(R.id.radiogroup);
                ArrayAdapter<String> dataAdapterinsurance = new ArrayAdapter<String>(LessorAddVehicle.this,R.layout.spinner_text, insurancelist);
                Spinner spinnerinsurance=customLayout.findViewById(R.id.spinnerinsurance);
                spinnerinsurance.setAdapter(dataAdapterinsurance);
                spinnerinsurance.setOnItemSelectedListener(new LessorAddVehicle.MyOnItemSelectedListener());

                radiogroupinsurance.setOnCheckedChangeListener((RadioGroup group, int checkedId) -> {
                    RadioButton radioButton = (RadioButton) customLayout.findViewById(checkedId);
                    insurance=radioButton.getText().toString();
                    if(checkedId==R.id.notcovered){
                        spinnerinsurance.setVisibility(View.GONE);
                    }
                    else{
                        spinnerinsurance.setVisibility(View.VISIBLE);
                    }
                });

                builder.setPositiveButton("OK", (dialog, which) -> {
                    tv_insurance.setText(insurance);
                    tv_insurance.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.accepted));

                });
                break;
            case "rate":
                customLayout=getLayoutInflater().inflate(R.layout.layout_dialog_rate,null);
                builder.setTitle("Rate Per Day");
                builder.setView(customLayout);
                EditText et_rate=(EditText)customLayout.findViewById(R.id.et_rate);
                builder.setPositiveButton("OK", (dialog, which) -> {
                    rate=et_rate.getText().toString();
                    tv_rate.setText(rate);
                    tv_rate.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.accepted));

                });
                break;
            case "days":
                customLayout=getLayoutInflater().inflate(R.layout.layout_dialog_days,null);
                builder.setTitle("Available Days");
                builder.setView(customLayout);
                //create an ArrayAdapter from the String Array
                ArrayAdapter<String> dataAdapterfrom = new ArrayAdapter<>(LessorAddVehicle.this, R.layout.spinner_text, dayslist);
                //set the view for the Drop down list
                dataAdapterfrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //set the ArrayAdapter to the spinner
                Spinner spinnerfrom= customLayout.findViewById(R.id.spinnerfrom);
                spinnerfrom.setAdapter(dataAdapterfrom);
                spinnerfrom.setOnItemSelectedListener(new LessorAddVehicle.MyOnItemSelectedListener());


                ArrayAdapter<String> dataAdapterto = new ArrayAdapter<>(LessorAddVehicle.this, R.layout.spinner_text, dayslist);
                //set the view for the Drop down list
                dataAdapterto.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //set the ArrayAdapter to the spinner
                Spinner spinnerto= customLayout.findViewById(R.id.spinnerto);
                spinnerto.setAdapter(dataAdapterto);
                spinnerto.setOnItemSelectedListener(new LessorAddVehicle.MyOnItemSelectedListener());

                builder.setPositiveButton("OK", (dialog, which) -> {
                    String availabledays="From "+startday+ " Till "+endday;
                    tv_available.setText(availabledays);
                    tv_available.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.accepted));
                });
                break;
            case "location":
                customLayout=getLayoutInflater().inflate(R.layout.layout_dialog_location,null);
                builder.setTitle("Vehicle Location");
                builder.setView(customLayout);
                ArrayAdapter<String> dataAdapterlocation = new ArrayAdapter<>(LessorAddVehicle.this, R.layout.spinner_text, locationlist);
                //set the view for the Drop down list
                dataAdapterlocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //set the ArrayAdapter to the spinner
                Spinner spinnerlocation= customLayout.findViewById(R.id.spinnerlocation);
                spinnerlocation.setAdapter(dataAdapterlocation);
                spinnerlocation.setOnItemSelectedListener(new LessorAddVehicle.MyOnItemSelectedListener());

                builder.setPositiveButton("OK", (dialog, which) -> {
                    tv_location.setText(location);
                    tv_location.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.accepted));
                });
                break;
        }
        AlertDialog dialog= builder.create();
        dialog.show();

    }

    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        @SuppressLint("NonConstantResourceId")
        public void onItemSelected(@NonNull AdapterView<?> parent, View view, int pos, long id) {
            switch (parent.getId()) {
                //Language From spinner
                case R.id.spinner:
                    selectedItem = parent.getItemAtPosition(pos).toString();
                    break;
                case R.id.spinnerbrand:
                     brand= parent.getItemAtPosition(pos).toString();
                    break;
                case R.id.spinnermodel:
                     model= parent.getItemAtPosition(pos).toString();
                    break;
                case R.id.spinnerfrom:
                    startday=parent.getItemAtPosition(pos).toString();
                    break;
                case R.id.spinnerto:
                    endday=parent.getItemAtPosition(pos).toString();
                    break;
                case R.id.spinnerinsurance:
                    insurance=parent.getItemAtPosition(pos).toString();
                    break;
                case R.id.spinnerlocation:
                    location=parent.getItemAtPosition(pos).toString();
                    break;
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            Toast.makeText(parent.getContext(), "Nothing", Toast.LENGTH_SHORT).show();
        }

    }


}
