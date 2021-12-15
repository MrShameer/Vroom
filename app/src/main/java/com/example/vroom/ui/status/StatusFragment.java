package com.example.vroom.ui.status;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroom.Login;
import com.example.vroom.MainActivity;
import com.example.vroom.R;
import com.example.vroom.api.Request;
import com.example.vroom.ui.status.adapter.StatusAdapter;
import com.example.vroom.ui.status.model.StatusCard;
import com.example.vroom.ui.status.model.StatusName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class StatusFragment extends Fragment {

    Request request = new Request();
    ArrayList<Object> items = new ArrayList<>();
    RecyclerView recyclerView;
    StatusAdapter statusAdapter;

    private List<StatusName> nameList=new ArrayList<>();;
    private List<StatusCard> cardList=new ArrayList<>();;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_status, container, false);
        recyclerView = root.findViewById(R.id.status_recv);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        statusAdapter = new StatusAdapter(items);
        recyclerView.setAdapter(statusAdapter);
        new mytask().execute();
        return root;
    }


    private class mytask extends AsyncTask<Void,Void,Void> {
        String respond;
        JSONObject jsonObject = null;
        @Override
        protected Void doInBackground(Void... voids) {
            String id = "3"; //AMIK TOKEN KT SINI
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("id", id)
                    .build();

            respond = request.RequestPost(requestBody,getString(R.string.status));

            try {
                jsonObject=new JSONObject(respond);
                Iterator<String> keys = jsonObject.keys();
                while(keys.hasNext()) {
                    String key = keys.next();
                    JSONArray arrlist = (JSONArray) jsonObject.get(key);
                    items.add(new StatusName(key));
                    for (int i = 0; i< arrlist.length(); i++){
                        JSONObject list = (JSONObject) arrlist.get(i);
                        JSONObject vehicle = list.getJSONObject("vehicle");
                        items.add(new StatusCard(vehicle.getJSONObject("owner").getString("name"),vehicle.getString("model")+", "+vehicle.getString("brand")));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            statusAdapter.notifyDataSetChanged();
        }
    }
}