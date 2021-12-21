package com.example.vroom.ui.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.example.vroom.R;
import com.example.vroom.api.Request;
import com.example.vroom.database.TokenHandler;
import com.example.vroom.ui.chat.adapter.MessageAdapter;
import com.example.vroom.ui.chat.modal.ChatCard;
import com.example.vroom.ui.chat.modal.MessageCard;
import com.example.vroom.ui.status.StatusFragment;
import com.example.vroom.ui.status.adapter.StatusAdapter;
import com.example.vroom.ui.status.model.StatusCard;
import com.example.vroom.ui.status.model.StatusName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.RequestBody;

public class MessageActivity extends AppCompatActivity {
    String id;
    TextView name;

    Request request = new Request();
    ArrayList<MessageCard> messageCards = new ArrayList<>();
    RecyclerView recyclerView;
    MessageAdapter messageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        id = getIntent().getStringExtra("CHAT_ID");
        name = findViewById(R.id.chatName);
        name.setText(getIntent().getStringExtra("CHAT_NAME"));

        recyclerView = findViewById(R.id.status_recv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        messageAdapter = new MessageAdapter(messageCards);
        recyclerView.setAdapter(messageAdapter);
        //new mytask().execute();
    }

    private class mytask extends AsyncTask<Void,Void,Void> {
        String respond;
        JSONObject jsonObject = null;
        @Override
        protected Void doInBackground(Void... voids) {
            String token = TokenHandler.read(TokenHandler.USER_TOKEN, null);
            RequestBody requestBody = RequestBody.create(null, new byte[0]);
            respond = request.PostHeader(requestBody,getString(R.string.status),token);
            try {
                jsonObject=new JSONObject(respond);
//                Iterator<String> keys = jsonObject.keys();
//                while(keys.hasNext()) {
//                    String key = keys.next();
//                    JSONArray arrlist = (JSONArray) jsonObject.get(key);
//                    items.add(new StatusName(key));
//                    for (int i = 0; i< arrlist.length(); i++){
//                        JSONObject list = (JSONObject) arrlist.get(i);
//                        JSONObject vehicle = list.getJSONObject("vehicle");
//                        items.add(new StatusCard(vehicle.getJSONObject("owner").getString("name"),vehicle.getString("model")+", "+vehicle.getString("brand")));
//                    }
//                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            messageAdapter.notifyDataSetChanged();
        }
    }
}