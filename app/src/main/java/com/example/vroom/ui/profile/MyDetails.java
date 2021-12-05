package com.example.vroom.ui.profile;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.util.proto.ProtoOutputStream;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.vroom.R;
import com.example.vroom.database.User.User;
import com.example.vroom.database.User.UserViewModel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.internal.Util;

public class MyDetails extends AppCompatActivity implements View.OnClickListener  {
    private UserViewModel userViewModel;
    Button btn_eimage;
    ImageButton btn_back,btn_eusername,btn_efullname,btn_eemail,btn_eaddress,btn_ephone,btn_epassword,btn_eic,btn_eadddriving;
    TextView tv_username,tv_fullname,tv_email,tv_address,tv_phone,tv_password,tv_addic,tv_adddriving;
    ArrayList<String>userdetails=new ArrayList<>();
    private static final int GALLERY_CODE = 103;
    CircleImageView user_image;

    SharedPreferences sharedpreferences;
    public static String MYPREFERENCES="MyPrefs";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydetails);

        tv_username=findViewById(R.id.tv_username);
        tv_fullname=findViewById(R.id.tv_fullname);
        tv_email=findViewById(R.id.tv_email);
        tv_address=findViewById(R.id.tv_address);
        tv_phone=findViewById(R.id.tv_phone);
        tv_password=findViewById(R.id.tv_password);
        tv_addic=findViewById(R.id.tv_addic);
        tv_adddriving=findViewById(R.id.tv_adddriving);
        user_image=findViewById(R.id.user_image);
        userViewModel=new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getGetAllUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                User currentUser=users.get(0);
                tv_username.setText(currentUser.getUsername());
                tv_fullname.setText(currentUser.getName());
                tv_email.setText(currentUser.getEmail());
                tv_address.setText(currentUser.getAddress());
                tv_phone.setText(currentUser.getPhone());
                tv_password.setText(currentUser.getPassword());
                tv_addic.setText("No Documents Added");
                tv_adddriving.setText("No Documents Added");
//                tv_addic.setText(currentUser.getEmail());
//                tv_adddriving.setText(currentUser.getAddress());
                userdetails.add(currentUser.getUserID());
                userdetails.add(currentUser.getUsername());
                userdetails.add(currentUser.getName());
                userdetails.add(currentUser.getEmail());
                userdetails.add(currentUser.getAddress());
                userdetails.add(currentUser.getPhone());
                userdetails.add(currentUser.getPassword());

            }
        });

        btn_eusername=findViewById(R.id.btn_eusername);
        btn_eusername.setOnClickListener(this);

        btn_efullname=findViewById(R.id.btn_efullname);
        btn_efullname.setOnClickListener(this);

        btn_eemail=findViewById(R.id.btn_eemail);
        btn_eemail.setOnClickListener(this);

        btn_eaddress=findViewById(R.id.btn_eaddress);
        btn_eaddress.setOnClickListener(this);

        btn_ephone=findViewById(R.id.btn_ephone);
        btn_ephone.setOnClickListener(this);

        btn_epassword=findViewById(R.id.btn_epassword);
        btn_epassword.setOnClickListener(this);

        btn_eic=findViewById(R.id.btn_eic);
        btn_eic.setOnClickListener(this);

        btn_eadddriving=findViewById(R.id.btn_eadddriving);
        btn_eadddriving.setOnClickListener(this);



        btn_eimage=findViewById(R.id.btn_eimage);
        btn_eimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // There are no request codes
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                galleryActivityResultLauncher.launch(intent);
            }
        });
        //Setting Up Image
        user_image=findViewById(R.id.user_image);
        File dir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + getApplicationContext().getPackageName()
                + "/Files");
        String mImageName="profile_image.jpg";
        File file = new File(dir, mImageName);
        if(file.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            user_image.setImageBitmap(myBitmap);

        }else{
            user_image.setImageResource(R.drawable.profile_image);

        }
        btn_back=findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
    }
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(this,EditMyDetails.class);
        intent.putExtra("userdetails",userdetails);
        switch (view.getId()) {
            case R.id.btn_eusername:
                intent.putExtra("current","username");
                startActivity(intent);
                break;

            case R.id.btn_efullname:
                intent.putExtra("current","fullname");
                startActivity(intent);

                break;
            case R.id.btn_eemail:
                intent.putExtra("current","email");
                startActivity(intent);

                break;
            case R.id.btn_eaddress:
                intent.putExtra("current","address");
                startActivity(intent);

                break;
            case R.id.btn_ephone:
                intent.putExtra("current","phone");
                startActivity(intent);

                break;
            case R.id.btn_epassword:
                intent.putExtra("current","password");
                startActivity(intent);

                break;
            case R.id.btn_eic:


                break;
            case R.id.btn_eadddriving:


                break;

        }
    }
    private ActivityResultLauncher<Intent>galleryActivityResultLauncher=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Uri imageUri = data.getData();
                        //Change From URI to Bitmap
                        Bitmap photoBmp = null;
                        if (imageUri != null) {
                            try {
                                photoBmp = getBitmapFormUri(MyDetails.this, imageUri);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        user_image.setImageBitmap(photoBmp);
                        storeImage(photoBmp);
                        Toast.makeText(MyDetails.this, "Profile Changed", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MyDetails.this, "No Image Selected", Toast.LENGTH_SHORT).show();

                    }


                }      }
    );
    public static Bitmap getBitmapFormUri(Activity ac, Uri uri) throws FileNotFoundException, IOException {
        InputStream input = ac.getContentResolver().openInputStream(uri);
        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither = true;//optional
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();
        int originalWidth = onlyBoundsOptions.outWidth;
        int originalHeight = onlyBoundsOptions.outHeight;
        if ((originalWidth == -1) || (originalHeight == -1))
            return null;
        //Image resolution is based on 480x800
        float hh = 800f;//The height is set as 800f here
        float ww = 480f;//Set the width here to 480f
        //Zoom ratio. Because it is a fixed scale, only one data of height or width is used for calculation
        int be = 1;//be=1 means no scaling
        if (originalWidth > originalHeight && originalWidth > ww) {//If the width is large, scale according to the fixed size of the width
            be = (int) (originalWidth / ww);
        } else if (originalWidth < originalHeight && originalHeight > hh) {//If the height is high, scale according to the fixed size of the width
            be = (int) (originalHeight / hh);
        }
        if (be <= 0)
            be = 1;
        //Proportional compression
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = be;//Set scaling
        bitmapOptions.inDither = true;//optional
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
        input = ac.getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();

        return compressImage(bitmap);//Mass compression again
    }
    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//Quality compression method, here 100 means no compression, store the compressed data in the BIOS
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {  //Cycle to determine if the compressed image is greater than 100kb, greater than continue compression
            baos.reset();//Reset the BIOS to clear it
            //First parameter: picture format, second parameter: picture quality, 100 is the highest, 0 is the worst, third parameter: save the compressed data stream
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//Here, the compression options are used to store the compressed data in the BIOS
            options -= 10;//10 less each time
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//Store the compressed data in ByteArrayInputStream
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//Generate image from ByteArrayInputStream data
        return bitmap;
    }

    private void storeImage(Bitmap image) {
        String TAG="Image Error";
        File pictureFile = getOutputMediaFile();
        if (pictureFile == null) {
            Log.d(TAG,"Error creating media file, check storage permissions: ");// e.getMessage());
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d(TAG, "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, "Error accessing file: " + e.getMessage());
        }
    }
    /** Create a File for saving an image or video */
    private  File getOutputMediaFile(){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + getApplicationContext().getPackageName()
                + "/Files");

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }
        // Create a media file name
        File mediaFile;
        String mImageName="profile_image.jpg";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }
}