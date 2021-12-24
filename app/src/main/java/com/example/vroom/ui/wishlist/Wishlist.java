package com.example.vroom.ui.wishlist;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroom.R;
import com.example.vroom.ui.createrequest.adapter.ReviewAdapter;
import com.example.vroom.ui.createrequest.model.ReviewCard;
import com.example.vroom.ui.wishlist.adapter.WishlistAdapter;
import com.example.vroom.ui.wishlist.model.WishlistData;

import java.util.ArrayList;

public class Wishlist extends AppCompatActivity {
    RecyclerView rc_wishlist;
    ImageButton btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        rc_wishlist=(RecyclerView) findViewById(R.id.rc_wishlist);
        rc_wishlist.setHasFixedSize(true);
        rc_wishlist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));


        ArrayList<WishlistData> wishlistCards=new ArrayList<WishlistData>();
        wishlistCards.add(new WishlistData("Myvi Baru","Mohamad Anwar","7.9","No Image"));
        wishlistCards.add(new WishlistData("Myvi Baru","Mohamad Anwar","7.9","No Image"));
        RecyclerView.Adapter WishlistAdapter = new WishlistAdapter(wishlistCards);
        rc_wishlist.setAdapter(WishlistAdapter);

        btn_back=findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAndRemoveTask();
            }
        });

    }
}
