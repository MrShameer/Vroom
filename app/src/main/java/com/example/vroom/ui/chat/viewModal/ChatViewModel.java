package com.example.vroom.ui.chat.viewModal;

import android.content.Context;
import android.content.res.loader.ResourcesProvider;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vroom.R;
import com.example.vroom.api.Request;
import com.example.vroom.database.TokenHandler;
import com.example.vroom.ui.chat.modal.ChatCard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.RequestBody;

public class ChatViewModel extends ViewModel {
    MutableLiveData<ArrayList<ChatCard>> chatLiveData;
    ArrayList<ChatCard> chatArrayList;
    Request request = new Request();

    public ChatViewModel() {
        chatLiveData = new MutableLiveData<>();
        // call your Rest API in init method
        init();
        //chatLiveData.p = chatArrayList
    }

    public MutableLiveData<ArrayList<ChatCard>> getUserMutableLiveData(){
        return chatLiveData;
    }

    public void init(){
//        populateList();
        new mytask().execute();
        chatArrayList = new ArrayList<>();

    }

    private class mytask extends AsyncTask<Void,Void,Void> {
        String respond;
        JSONArray jsonArray = null;

        @Override
        protected Void doInBackground(Void... voids) {

            String token = TokenHandler.read(TokenHandler.USER_TOKEN, null);
            RequestBody requestBody = RequestBody.create(null, new byte[0]);
            respond = request.PostHeader(requestBody, "https://vroom.lepak.xyz/api/chatroom",token);
            try {
                jsonArray=new JSONArray(respond);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    chatArrayList.add(new ChatCard(jsonObject.getString("name"),jsonObject.getString("message"),jsonObject.getString("chatid")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            chatLiveData.setValue(chatArrayList);
        }
    }
}
