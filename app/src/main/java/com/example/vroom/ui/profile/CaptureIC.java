package com.example.vroom.ui.profile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.vroom.R;
import com.example.vroom.database.User.User;
import com.example.vroom.database.User.UserViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CaptureIC extends AppCompatActivity {
    EditText et_icdetails;
    ImageView click_image;
    FloatingActionButton btn_camera;
    Button btn_done;
    Intent intent;
    private String userID, username, name, email, address, phone, password, icstatus, dlstatus;
    private User user;
    private UserViewModel userViewModel;
    private static final int pic_id = 123;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_ic);
        userViewModel=new ViewModelProvider(this).get(UserViewModel.class);
        et_icdetails = findViewById(R.id.et_icdetails);
        click_image = findViewById(R.id.click_image);
        btn_camera = findViewById(R.id.btn_camera);
        btn_done = findViewById(R.id.btn_done);
        intent = getIntent();
        ArrayList<String> userdetails = (ArrayList<String>) intent.getSerializableExtra("userdetails");
        userID = userdetails.get(0);
        username = userdetails.get(1);
        name = userdetails.get(2);
        email = userdetails.get(3);
        address = userdetails.get(4);
        phone = userdetails.get(5);
        password = userdetails.get(6);
        icstatus = userdetails.get(7);
        dlstatus = userdetails.get(8);
        AlertDialog.Builder builder = new AlertDialog.Builder(CaptureIC.this);
        builder.setMessage("Empty");
        builder.setTitle("Please Fill in Details !");
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraActivityResultLauncher.launch(camera_intent);
            }
        });
        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (click_image.getDrawable() == null) {
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    icstatus = "IC is Under Review";
//                    user=new User(userID,username,name,email,address,phone,password);
                    user = new User(userID, username, name, email, address, phone, password, icstatus, dlstatus);
                    user.setUserID(userID);
                    userViewModel.update(user);
                    Intent intent = new Intent(CaptureIC.this, MyDetails.class);
                    startActivity(intent);
                    finishAndRemoveTask();
                }

            }
        });
    }

    private final ActivityResultLauncher<Intent> cameraActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Bitmap photo = (Bitmap) data.getExtras().get("data");

                        // Set the image in imageview for display
                        click_image.setImageBitmap(photo);
                    }
                }
            });

}
