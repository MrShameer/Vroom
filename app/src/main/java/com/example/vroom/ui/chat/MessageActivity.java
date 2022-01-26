package com.example.vroom.ui.chat;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroom.R;
import com.example.vroom.api.Request;
import com.example.vroom.database.Message.MessageCard;
import com.example.vroom.database.Message.MessageViewModel;
import com.example.vroom.database.TokenHandler;
import com.example.vroom.ui.chat.adapter.MessageAdapter;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MessageActivity extends AppCompatActivity {
    String chatid,to,idother;
    TextView name, send_message;
    Button send_btn;
    ImageButton btn_back;
    ImageView lessorPic;
    Request request = new Request();
//    ArrayList<Message> Messages = new ArrayList<>();
    MessageViewModel messageViewModel;
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
        new fetch().execute();
        recyclerView = findViewById(R.id.message_recv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        btn_back.setOnClickListener(v -> finish());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        llm.setReverseLayout(false);// Scroll to bottom
        recyclerView.setLayoutManager(llm);
        llm.setStackFromEnd(true);
        llm.setReverseLayout(false);

        messageViewModel = new ViewModelProvider(this).get(MessageViewModel.class);

        messageViewModel.getAllMessagelist(chatid).observe(this,

//
//        messageViewModel.getGetallMessage().observe(this,
        new Observer<List<MessageCard>>() {

            @Override
            public void onChanged(List<MessageCard> messages) {

                messageAdapter.setMessageCards(messages);
            }
        });
//        messageAdapter = new MessageAdapter(Messages);
        recyclerView.setAdapter(messageAdapter);


        send_btn.setOnClickListener(view -> new send().execute());
    }

    private class fetch extends AsyncTask<Void,Void,Void> {
        String respond;
        JSONObject jsonObject = null;
        MessageCard messageCard;
        @Override
        protected Void doInBackground(Void... voids) {
            String token = TokenHandler.read(TokenHandler.USER_TOKEN, null);
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("id", chatid)
                    .build();
            respond = request.PostHeader(requestBody,getString(R.string.message),token);
            try {
                messageViewModel.deleteAll();
                JSONArray jsonArray = new JSONArray(respond);
                for (int i=0; i<jsonArray.length(); i++){
                    jsonObject = jsonArray.getJSONObject(i);
                    messageCard=new MessageCard(jsonObject.getString("id"),
                            jsonObject.getString("message")
                            ,jsonObject.getString("sender"),
                            jsonObject.getString("created_at"));

                     messageViewModel.insert(messageCard);
                    Log.d("message masuk",messageViewModel.getGetallMessage().toString());

//                    messageViewModel.insert(new Message(jsonObject.getString("id"),
//                            jsonObject.getString("message"),jsonObject.getString("sender"),
//                            jsonObject.getString("created_at")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
//            messageAdapter.notifyDataSetChanged();
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
            messageViewModel.insert(new MessageCard(null,send_message.getText().toString(),id,null));
//            Messages.add(new Message(null,send_message.getText().toString(),id,null));
            send_message.setText("");
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
//            messageAdapter.notifyDataSetChanged();
        }
    }
}