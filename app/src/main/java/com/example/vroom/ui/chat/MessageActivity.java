package com.example.vroom.ui.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vroom.R;
import com.example.vroom.api.Request;
import com.example.vroom.database.TokenHandler;
import com.example.vroom.ui.chat.adapter.MessageAdapter;
import com.example.vroom.ui.chat.modal.MessageCard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MessageActivity extends AppCompatActivity {
    String chatid,to,idother;
    TextView name, send_message;
    Button send_btn;

    Request request = new Request();
    ArrayList<MessageCard> messageCards = new ArrayList<>();
    RecyclerView recyclerView;
    MessageAdapter messageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        chatid = getIntent().getStringExtra("CHAT_ID");
        idother=getIntent().getStringExtra("ID");
        name = findViewById(R.id.chatName);
        name.setText(getIntent().getStringExtra("CHAT_NAME"));
        send_message = findViewById(R.id.send_message);
        send_btn = findViewById(R.id.send_btn);

        recyclerView = findViewById(R.id.message_recv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        messageAdapter = new MessageAdapter(messageCards);
        recyclerView.setAdapter(messageAdapter);
        new fetch().execute();

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new send().execute();
            }
        });
    }

    private class fetch extends AsyncTask<Void,Void,Void> {
        String respond;
        JSONObject jsonObject = null;
        @Override
        protected Void doInBackground(Void... voids) {
            String token = TokenHandler.read(TokenHandler.USER_TOKEN, null);
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("id", chatid)
                    .build();
            respond = request.PostHeader(requestBody,getString(R.string.message),token);
            try {
                JSONArray jsonArray = new JSONArray(respond);
                for (int i=0; i<jsonArray.length(); i++){
                    jsonObject = jsonArray.getJSONObject(i);
                   // to=jsonObject.getString("sender");
                    messageCards.add(new MessageCard(jsonObject.getString("id"),jsonObject.getString("message"),jsonObject.getString("sender"),jsonObject.getString("created_at")));
                }
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


    private class send extends AsyncTask<Void,Void,Void> {
        String respond;
        @Override
        protected Void doInBackground(Void... voids) {
            String token = TokenHandler.read(TokenHandler.USER_TOKEN, null);
            String id = TokenHandler.read(TokenHandler.USER_ID, null);
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("to", idother)
                    .addFormDataPart("message",send_message.getText().toString())
                    .addFormDataPart("chatid",chatid)
                    .build();

            respond = request.PostHeader(requestBody,getString(R.string.send),token);
            messageCards.add(new MessageCard(null,send_message.getText().toString(),id,null));
            send_message.setText("");
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            messageAdapter.notifyDataSetChanged();
        }
    }
}