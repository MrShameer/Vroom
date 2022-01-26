package com.example.vroom.ui.vehicledetails;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.vroom.R;
import com.example.vroom.api.Request;
import com.example.vroom.database.TokenHandler;
import com.example.vroom.database.VehicleDetails.VehicleDetails;
import com.example.vroom.ui.chat.MessageActivity;
import com.example.vroom.ui.chat.modal.MessageCard;
import com.example.vroom.ui.wishlist.Wishlist;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class VehicleInfo extends AppCompatActivity {
    Request request = new Request();
    ImageButton btn_back, chat_btn;
    Button btn_book,btn_wishlist, btn_passanger, btn_door, btn_luggage, btn_gas;
    TextView tv_price, tv_lessorname,tv_rating,tv_carbrand;
    VehicleDetails vehicleDetails;
    CircleImageView iv_lessor;
    ImageView iv_vehicle;
    String chatid="0",id,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_details);
        ViewPager viewPager = findViewById(R.id.view_pager);
        SectionsPagerAdapter reqPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(reqPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        vehicleDetails = (VehicleDetails) getIntent().getSerializableExtra("VEHICLE_INFO");
        id=vehicleDetails.getLessorid();
        name=vehicleDetails.getLessorname();
        iv_lessor=findViewById(R.id.lessorPic2);

        Picasso.get().load(getString(R.string.profilepic)+vehicleDetails.getLessorid()+".jpg").into(iv_lessor, new Callback() {
            @Override
            public void onSuccess() {
            }
            @Override
            public void onError(Exception e) {
                Picasso.get().load(R.drawable.profile_image).into(iv_lessor);
            }
        });

        iv_vehicle=findViewById(R.id.iv_vehicle);
        Picasso.get().load(getString(R.string.vehiclepic)+vehicleDetails.getVehicleplat()+".png").into(iv_vehicle, new Callback() {
            @Override
            public void onSuccess() {
            }
            @Override
            public void onError(Exception e) {
                Picasso.get().load(R.drawable.perodua_bezza).into(iv_vehicle);
            }
        });

        tv_carbrand=findViewById(R.id.tv_carbrand);
        tv_carbrand.setText(vehicleDetails.getVehiclebrand()+" "+vehicleDetails.getVehiclemodel());
        tv_rating=findViewById(R.id.tv_rating);
        tv_rating.setText(vehicleDetails.getVehiclerating());
        tv_lessorname=findViewById(R.id.tv_lessorname2);
        tv_lessorname.setText(vehicleDetails.getLessorname());
        tv_price=findViewById(R.id.tv_price4);
        tv_price.setText(vehicleDetails.getVehicleprice());
        btn_passanger=findViewById(R.id.btn_passenger4);
        btn_passanger.setText(vehicleDetails.getVehiclepassanger());
        btn_door=findViewById(R.id.btn_door4);
        btn_door.setText(vehicleDetails.getVehicledoor());
        btn_luggage=findViewById(R.id.btn_luggage4);
        btn_luggage.setText(vehicleDetails.getVehicleluggage());
        btn_gas=findViewById(R.id.btn_gas4);
        btn_gas.setText(vehicleDetails.getVehicletank());

        chat_btn=findViewById(R.id.chatbutton);
        chat_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new chattask().execute();
            }
        });

        btn_back=findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAndRemoveTask();
            }
        });

        btn_book=findViewById(R.id.btn_book);
        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(VehicleInfo.this,SetReqDetails.class);
                intent.putExtra("PLAT",vehicleDetails.getVehicleplat());
                intent.putExtra("NAME",vehicleDetails.getLessorname());
                intent.putExtra("PRICE",vehicleDetails.getVehicleprice());
                startActivity(intent);
            }
        });

        btn_wishlist=findViewById(R.id.btn_reject);
        btn_wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(VehicleInfo.this, Wishlist.class);
                intent.putExtra("ADD",vehicleDetails.getVehicleplat());
                startActivity(intent);
            }
        });
    }

    private class chattask extends AsyncTask<Void,Void,Void> {
        String respond;
        JSONObject jsonObject;
        @Override
        protected Void doInBackground(Void... voids) {
            String token = TokenHandler.read(TokenHandler.USER_TOKEN, null);
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("id", vehicleDetails.getLessorid())
                    .build();
            respond = request.PostHeader(requestBody,getString(R.string.chatexists),token);
            try {
                if (respond.contains("id")){
                    jsonObject = new JSONObject(respond);
                    chatid=jsonObject.getString("id");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
                Intent intent = new Intent(VehicleInfo.this, MessageActivity.class);
                intent.putExtra("CHAT_ID",chatid);
                intent.putExtra("ID",id);
                intent.putExtra("CHAT_NAME", name);
                startActivity(intent);
        }
    }

    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
        private final String[] tabTitles = new String[]{"Details","Rating"};
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new TabVehicleDetails(vehicleDetails);
            }
            return new TabRating(vehicleDetails.getVehicleplat());
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
        @Override
        public int getCount() {
            return 2;
        }
   }
}
