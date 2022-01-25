package com.example.vroom.ui.lessor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vroom.R;
import com.example.vroom.api.Request;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LessorVehicleImage extends AppCompatActivity {
    ImageView ib_rear,ib_front,ib_left,ib_right;
    String selected;
    ImageButton btn_back;
    Button btn_submit;
    Request request = new Request();
    ActivityResultLauncher<Intent> activityResultLauncher;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_vehicle);
        ib_rear=findViewById(R.id.ib_rear);
        ib_front=findViewById(R.id.ib_front);
        ib_left=findViewById(R.id.ib_left);
        ib_right=findViewById(R.id.ib_right);
        btn_back=findViewById(R.id.btn_back);
        btn_submit=findViewById(R.id.btn_submit);

        ib_rear.setOnClickListener(v -> {
            selectImage(LessorVehicleImage.this);
            selected="rear";
        });
        ib_front.setOnClickListener(v -> {
            selectImage(LessorVehicleImage.this);
            selected="front";
        });
        ib_left.setOnClickListener(v -> {
            selectImage(LessorVehicleImage.this);
            selected="left";
        });
        ib_right.setOnClickListener(v -> {
            selectImage(LessorVehicleImage.this);
            selected="right";
        });
        btn_back.setOnClickListener(v -> {
            finishAndRemoveTask();
        });
        btn_submit.setOnClickListener(v -> {
//           Intent intent=new Intent(LessorVehicleImage.this,LessorAddVehicle.class);
//           intent.putExtra("statusimage","done");
//           startActivity(intent);

            //Todo save gambar dalam serve, ada 4 gambar
            finishAndRemoveTask();

        });

    }
    private void selectImage(Context context) {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, (dialog, item) -> {

            if (options[item].equals("Take Photo")) {
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 0);

            } else if (options[item].equals("Choose from Gallery")) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);

            } else if (options[item].equals("Cancel")) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String select=data.getStringExtra("select");
        Toast.makeText(this, select,Toast.LENGTH_SHORT).show();
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        switch (selected){

                            case "rear":
                                ib_rear.setImageBitmap(selectedImage);

                                break;
                            case "front":
                                ib_front.setImageBitmap(selectedImage);

                                break;
                            case "right":
                                ib_right.setImageBitmap(selectedImage);

                                break;
                            case "left":
                                ib_left.setImageBitmap(selectedImage);

                                break;
                        }
                    }
                    break;
                case 1:
                    switch (selected){

                        case "rear":
                            Picasso.get().load(data.getData()).into(ib_rear);

                            break;
                        case "front":
                            Picasso.get().load(data.getData()).into(ib_front);

                            break;
                        case "right":
                            Picasso.get().load(data.getData()).into(ib_right);

                            break;
                        case "left":
                            Picasso.get().load(data.getData()).into(ib_left);

                            break;
                    }
                    break;
                    }

            }
        }
}
