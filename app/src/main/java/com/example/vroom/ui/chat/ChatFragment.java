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
        chatAdapter = new ChatAdapter();
        recyclerView.setAdapter(chatAdapter);

        viewModel = ViewModelProviders.of(requireActivity()).get(ChatViewModel.class);
        viewModel.getUserMutableLiveData().observe(getViewLifecycleOwner(), chatListUpdateObserver);
        return root;
    }

    Observer<ArrayList<ChatCard>> chatListUpdateObserver = new Observer<ArrayList<ChatCard>>() {
        @Override
        public void onChanged(ArrayList<ChatCard> userArrayList) {
            chatAdapter.updateChatList(userArrayList);
        }
    };


}