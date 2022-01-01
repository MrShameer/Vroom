package com.example.vroom.ui.chat.viewModal;

import android.app.Application;
import android.os.AsyncTask;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vroom.api.Request;
import com.example.vroom.database.TokenHandler;
import com.example.vroom.ui.chat.ChatFragment;
import com.example.vroom.ui.chat.adapter.ChatAdapter;
import com.example.vroom.ui.chat.modal.ChatCard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;

    public class ChatViewModel extends ViewModel {
    MutableLiveData<ArrayList<ChatCard>> chatLiveData;
    ArrayList<ChatCard> chatArrayList;
    private Application application;
    Request request = new Request();

    private static ChatViewModel _instance;
    public static ChatViewModel getInstance() {
        {
            if (_instance == null)
            {
                _instance = new ChatViewModel();
            }
            return _instance;
        }
    }
    public ChatViewModel() {
        chatLiveData = new MutableLiveData<>();
        init();
    }

    public ArrayList<ChatCard> getUserArraylist(){
        return chatArrayList;
    }

    public void updatedmessage(String name,String body,String header, String id){
        chatArrayList.add(new ChatCard(name,body,header,id));
        chatLiveData.postValue(chatArrayList);
        ChatAdapter chatAdapter=new ChatAdapter();
        chatAdapter.updateChatList(chatArrayList);
        System.out.println(chatArrayList.size()+"RECYCLERVIEW");
    }

    public MutableLiveData<ArrayList<ChatCard>> getUserMutableLiveData(){
        return chatLiveData;
    }

    public void init(){
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
                System.out.println(respond);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    chatArrayList.add(new ChatCard(jsonObject.getString("name"),jsonObject.getString("message"),jsonObject.getString("chatid"),jsonObject.getString("id")));
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
