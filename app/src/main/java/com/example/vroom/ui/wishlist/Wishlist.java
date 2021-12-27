package com.example.vroom.ui.wishlist;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroom.R;
import com.example.vroom.api.Request;
import com.example.vroom.database.TokenHandler;
import com.example.vroom.ui.wishlist.adapter.WishlistAdapter;
import com.example.vroom.ui.wishlist.model.WishlistData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import okhttp3.RequestBody;

public class Wishlist extends AppCompatActivity {
    Request request = new Request();
    RecyclerView rc_wishlist;
    ImageButton btn_back;
    ArrayList<WishlistData> wishlistCards;
    WishlistAdapter wishlistAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        rc_wishlist=findViewById(R.id.rc_wishlist);
        rc_wishlist.setHasFixedSize(true);
        rc_wishlist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        wishlistCards=new ArrayList<WishlistData>();
        wishlistAdapter = new WishlistAdapter(wishlistCards);
        rc_wishlist.setAdapter(wishlistAdapter);
        new mytask().execute();

        btn_back=findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAndRemoveTask();
            }
        });

    }

    private class mytask extends AsyncTask<Void,Void,Void> {
        String respond;
        JSONObject jsonObject;
        JSONArray jsonArray = null;
        @Override
        protected Void doInBackground(Void... voids) {
            String token = TokenHandler.read(TokenHandler.USER_TOKEN, null);
            RequestBody requestBody = RequestBody.create(null, new byte[0]);
            respond = request.PostHeader(requestBody,getString(R.string.wishlist),token);
            try {
                jsonArray=new JSONArray(respond);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    wishlistCards.add(new WishlistData(
                            jsonObject.getJSONObject("vehicle").getString("model")+" "+jsonObject.getJSONObject("vehicle").getString("brand"),
                                jsonObject.getJSONObject("vehicle").getJSONObject("owner").getString("name"),
                            "10.0",
                            "No Image"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            wishlistAdapter.setWishlistDetails(wishlistCards);
        }
    }
}
