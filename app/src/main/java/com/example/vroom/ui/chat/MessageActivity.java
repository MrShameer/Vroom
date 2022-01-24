package com.example.vroom.ui.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vroom.R;
import com.example.vroom.api.Request;
import com.example.vroom.database.TokenHandler;
import com.example.vroom.ui.chat.adapter.MessageAdapter;
import com.example.vroom.ui.chat.modal.MessageCard;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

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
    ImageButton btn_back;
    ImageView lessorPic;
    Request request = new Request();
    ArrayList<MessageCard> messageCards = new ArrayList<>();
    RecyclerView recyclerView;
    MessageAdapter messageAdapter;
    int last;
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
        lessorPic=findViewById(R.id.lessorPic);
        btn_back=findViewById(R.id.btn_back);
        Picasso.get().load("https://vroom.lepak.xyz/storage/picture/profile/"+idother+".jpg").into(lessorPic, new Callback() {
            @Override
            public void onSuccess() {
            }
            @Override
            public void onError(Exception e) {
                Picasso.get().load(R.drawable.profile_image).into(lessorPic);
            }
        });
        recyclerView = findViewById(R.id.message_recv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        btn_back.setOnClickListener(v -> finish());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        llm.setReverseLayout(false);// Scroll to bottom
        recyclerView.setLayoutManager(llm);
        llm.setStackFromEnd(true);
        llm.setReverseLayout(false);
        messageAdapter = new MessageAdapter(messageCards);
        recyclerView.setAdapter(messageAdapter);

        new fetch().execute();

        send_btn.setOnClickListener(view -> new send().execute());
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
                    messageCards.add(new MessageCard(jsonObject.getString("id"),
                            jsonObject.getString("message"),jsonObject.getString("sender"),
                            jsonObject.getString("created_at")));
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