package com.example.vroom.service;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.example.vroom.database.Chat.ChatCard;
import com.example.vroom.database.Chat.ChatCardDAO;
import com.example.vroom.database.Chat.ChatViewModel;
import com.example.vroom.database.User.UserViewModel;
import com.example.vroom.ui.chat.ChatFragment;
import com.example.vroom.ui.chat.adapter.ChatAdapter;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONObject;

import java.util.ArrayList;

public class FirebaseMessage extends FirebaseMessagingService {
    private static final String TAG = "";
    ChatFragment chatFragment=new ChatFragment();
    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {


        ChatCard chatCard;
        super.onMessageReceived(remoteMessage);

        JSONObject message = new JSONObject(remoteMessage.getData());

            if (remoteMessage.getData().size() > 0) {
                Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            }


        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            chatFragment.updatemessage();
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody()+" "+remoteMessage.getNotification().getTitle());

        }
    }

}
