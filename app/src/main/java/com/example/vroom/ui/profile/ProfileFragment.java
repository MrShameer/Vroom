package com.example.vroom.ui.profile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.vroom.R;
import com.example.vroom.database.TokenHandler;
import com.example.vroom.database.User.User;
import com.example.vroom.database.User.UserViewModel;
import com.example.vroom.ui.lessor.Lessorhome;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {
    private UserViewModel userViewModel;
    TextView tv_myname,tv_email;
    ImageButton btn_edetails;
    ImageView iv_verified;
    CircleImageView user_image;
    Button btn_lessor;
    File file,dir;
    String mImageName;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        user_image= root.findViewById(R.id.user_image);
        dir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + getActivity().getApplicationContext().getPackageName()
                + "/Picture/");
         mImageName= TokenHandler.read(TokenHandler.USER_ID, null)+".jpg";

        file = new File(dir, mImageName);
        if(file.exists()){
            Picasso.get().load(file).into(user_image);
        }else{
            Picasso.get().load(R.drawable.profile_image).into(user_image);
        }

        iv_verified= root.findViewById(R.id.iv_verified);
        tv_myname= root.findViewById(R.id.tv_myname);
        tv_email= root.findViewById(R.id.tv_email);
        userViewModel=new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getGetAllUser().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                User currentUser=users.get(0);
                tv_myname.setText(currentUser.getName());
                tv_email.setText(currentUser.getEmail());
                if (currentUser.getIcstatus().equals("done") && currentUser.getDlstatus().equals("done")){
                    Picasso.get().load(R.drawable.ic_baseline_check_circle_24).into(iv_verified);
                }
            }
        });
        btn_edetails= root.findViewById(R.id.btn_edetails);
        btn_edetails.setOnClickListener(view -> {
            Intent intent=new Intent(getContext(),MyDetails.class);
            startActivity(intent);
        });

        btn_lessor=root.findViewById(R.id.btn_lessor);
        btn_lessor.setOnClickListener(v -> {
            Intent intent=new Intent(getContext(), Lessorhome.class);
            startActivity(intent);
        });
        return root;

    }

    @Override
    public void onResume() {
        super.onResume();
        if(file.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            user_image.setImageBitmap(myBitmap);

        }else{
            user_image.setImageResource(R.drawable.profile_image);
        }        //TODO
        // GMBR TKNK UBAH BILA BACK, HANYA UBAH BILA REFRESH
    }
}