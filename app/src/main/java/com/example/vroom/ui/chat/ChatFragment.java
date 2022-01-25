package com.example.vroom.ui.chat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroom.Login;
import com.example.vroom.MainActivity;
import com.example.vroom.R;

import com.example.vroom.api.Request;
import com.example.vroom.database.Chat.ChatCard;
import com.example.vroom.database.Chat.ChatCardDAO;
import com.example.vroom.database.Chat.ChatCardDatabase;
import com.example.vroom.database.Chat.ChatViewModel;
import com.example.vroom.database.TokenHandler;
import com.example.vroom.database.User.User;
import com.example.vroom.ui.chat.adapter.ChatAdapter;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ChatFragment extends Fragment implements LifecycleOwner {
    RecyclerView recyclerView;
    ChatViewModel chatViewModel;
    ChatAdapter chatAdapter;
    ChatCard chatCard;
    Request request = new Request();
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_chat, container, false);
        new mytask().execute();
        recyclerView = root.findViewById(R.id.chat_recv);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        chatAdapter = new ChatAdapter();
        recyclerView.setAdapter(chatAdapter);

        chatViewModel = new ViewModelProvider(requireActivity()).get(ChatViewModel.class);

        chatViewModel.getGetAllChatCard().observe(getViewLifecycleOwner(), chatCards -> {
//            chatCard=arrayList.get(0);
            chatAdapter.setChatCards(chatCards);
        });

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                chatViewModel.deleteAll();
                jsonArray=new JSONArray(respond);
                System.out.println(respond);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    chatViewModel.insert(new ChatCard(jsonObject.getString("chatid")
                            ,jsonObject.getString("name")
                            ,jsonObject.getString("message")
                            ,jsonObject.getString("id")));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
//            chatLiveData.setValue(chatArrayList);
        }
    }
//    private class mytask extends AsyncTask<Void,Void,Void> {
//
//        private ChatCard chatCard;
//        @Override
//        protected Void doInBackground(Void... voids) {
//            Toast.makeText(getActivity(),"Message",Toast.LENGTH_SHORT).show();
//
//            try{
//                chatCard=new ChatCard("Anwar","Hello2","10","2");
//                chatCard.setId("10");
//                chatViewModel.insert(chatCard);
//            }
//            catch (Exception e){
//                Toast.makeText(getContext(),"Taknak Masuk",Toast.LENGTH_SHORT).show();
//            }
//            return null;
//        }
//
//    }

}