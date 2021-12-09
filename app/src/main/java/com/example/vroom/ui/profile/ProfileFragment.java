package com.example.vroom.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.vroom.R;
import com.example.vroom.database.User.User;
import com.example.vroom.database.User.UserViewModel;

import java.io.File;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {
    private UserViewModel userViewModel;
    TextView tv_myname,tv_email;
    ImageButton btn_edetails;
    CircleImageView user_image;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        tv_myname=(TextView) root.findViewById(R.id.tv_myname);
        tv_email=(TextView) root.findViewById(R.id.tv_email);
        userViewModel=new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getGetAllUser().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                User currentUser=users.get(0);
                tv_myname.setText(currentUser.getUsername());
                tv_email.setText(currentUser.getEmail());
            }
        });
        btn_edetails=(ImageButton) root.findViewById(R.id.btn_edetails);
        btn_edetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),MyDetails.class);
                startActivity(intent);
            }
        });

        //Setting Up Image
        user_image=(CircleImageView)root.findViewById(R.id.user_image);
        File dir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + getActivity().getApplicationContext().getPackageName()
                + "/Files");
        String mImageName="profile_image.jpg";

        File file = new File(dir, mImageName);
        if(file.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            user_image.setImageBitmap(myBitmap);

        }else{
            user_image.setImageResource(R.drawable.profile_image);
        }
        return root;
    }
    @Override
    public void onResume() {
        super.onResume();
        //Setting Up Image
        File dir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + getActivity().getApplicationContext().getPackageName()
                + "/Files");
        String mImageName="profile_image.jpg";

        File file = new File(dir, mImageName);
        if(file.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            user_image.setImageBitmap(myBitmap);

        }else{
            user_image.setImageResource(R.drawable.profile_image);
        }
    }
}