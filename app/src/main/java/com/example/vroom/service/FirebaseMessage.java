package com.example.vroom.service;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.vroom.ui.chat.adapter.ChatAdapter;
import com.example.vroom.ui.chat.modal.ChatCard;
import com.example.vroom.ui.chat.viewModal.ChatViewModel;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FirebaseMessage extends FirebaseMessagingService {
    private static final String TAG = "";
    ChatAdapter chatAdapter;
    ChatViewModel viewModel;
    ArrayList<ChatCard> chatArrayList;

    @Override
    public void onCreate() {
        super.onCreate();

        chatArrayList = new ArrayList<>();
//        viewModel =new ViewModelProvider(this).get(ChatViewModel.class);
//        viewModel.getUserMutableLiveData().observe(getViewLifecycleOwner(), chatListUpdateObserver);

    }


    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        JSONObject message = new JSONObject(remoteMessage.getData());
        //chatAdapter.chatmap.containsKey()
        chatArrayList.add(new ChatCard("anwar","ollaa","88"));
        //viewModel.getUserMutableLiveData().postValue(chatArrayList);
//        try {
//            if (chatAdapter.chatmap.containsKey(message.getString("title")));
//                System.out.println(message);

            // Check if message contains a data payload.
            if (remoteMessage.getData().size() > 0) {
                Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
    }

}
