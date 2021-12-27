package com.example.vroom.ui.chat;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.vroom.R;
import com.example.vroom.api.Request;

import com.example.vroom.database.VehicleDetails.VehicleViewModel;
import com.example.vroom.ui.chat.adapter.ChatAdapter;
import com.example.vroom.ui.chat.modal.ChatCard;
import com.example.vroom.ui.chat.viewModal.ChatViewModel;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ChatFragment extends Fragment implements LifecycleOwner {
    Request request = new Request();
    RecyclerView recyclerView;
    ChatViewModel viewModel;
    ChatAdapter chatAdapter;
    ArrayList<ChatCard> chatCards = new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_chat, container, false);
        recyclerView = root.findViewById(R.id.chat_recv);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
       // chatAdapter = new ChatAdapter(chatCards);
        chatAdapter = new ChatAdapter();
        recyclerView.setAdapter(chatAdapter);

        viewModel = ViewModelProviders.of(requireActivity()).get(ChatViewModel.class);
//        viewModel =new ViewModelProvider(this).get(ChatViewModel.class);

        //viewModel = new ViewModelProvider(this).get(ChatViewModel.class);
        viewModel.getUserMutableLiveData().observe(getViewLifecycleOwner(), chatListUpdateObserver);

        //new mytask().execute();
        //token untuk fcm ade kt login
        return root;
    }

    Observer<ArrayList<ChatCard>> chatListUpdateObserver = new Observer<ArrayList<ChatCard>>() {
        @Override
        public void onChanged(ArrayList<ChatCard> userArrayList) {
            chatAdapter.updateChatList(userArrayList);
        }
    };

/*    private class mytask extends AsyncTask<Void,Void,Void> {
        String respond;
        JSONArray jsonArray = null;

        @Override
        protected Void doInBackground(Void... voids) {
            String token = TokenHandler.read(TokenHandler.USER_TOKEN, null);
            RequestBody requestBody = RequestBody.create(null, new byte[0]);
            respond = request.PostHeader(requestBody,getString(R.string.chatroom),token);
            try {
                jsonArray=new JSONArray(respond);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    chatCards.add(new ChatCard(jsonObject.getString("name"),jsonObject.getString("message"),jsonObject.getString("chatid")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            chatAdapter.notifyDataSetChanged();
        }
    }*/

}