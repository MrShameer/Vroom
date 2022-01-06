package com.example.vroom.ui.vehicledetails;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroom.R;
import com.example.vroom.api.Request;
import com.example.vroom.database.TokenHandler;
import com.example.vroom.ui.vehicledetails.adapter.ReviewAdapter;
import com.example.vroom.ui.vehicledetails.model.ReviewCard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class TabRating extends Fragment  {
    RecyclerView rc_rating;
    ReviewAdapter reviewAdapter;
    ArrayList<ReviewCard> reviewCards;
    Request request = new Request();
    String plat;
    TextView totalrating;
    public TabRating(String plat) {
        this.plat=plat;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reviewCards=new ArrayList();
        reviewAdapter = new ReviewAdapter(reviewCards);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root =inflater.inflate(R.layout.fragment_vehicle_rating, container, false);
        totalrating=root.findViewById(R.id.tv_totalrating);
        rc_rating=(RecyclerView) root.findViewById(R.id.rc_rating);
        rc_rating.setHasFixedSize(true);
        rc_rating.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        rc_rating.setAdapter(reviewAdapter);
        new mytask().execute();
        return root;
    }

    private class mytask extends AsyncTask<Void,Void,Void> {
        String respond;
        JSONObject jsonObject = null;
        JSONArray jsonArray;
        @Override
        protected Void doInBackground(Void... voids) {
            String token = TokenHandler.read(TokenHandler.USER_TOKEN, null);
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("plat", plat)
                    .build();
            respond = request.PostHeader(requestBody,getString(R.string.ratinglist),token);
            try {
                jsonArray = new JSONArray(respond);
                for (int i=0; i<jsonArray.length(); i++){
                    jsonObject = jsonArray.getJSONObject(i);
                    //TODO
                    // TMBH BINTANG RATING
                    reviewCards.add(new ReviewCard(jsonObject.getJSONObject("owner").getString("id"),jsonObject.getJSONObject("owner").getString("name"),jsonObject.getString("message"),jsonObject.getString("created")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            totalrating.setText(String.valueOf(jsonArray.length()));
            reviewAdapter.setVehicleDetails(reviewCards);
        }
    }
}