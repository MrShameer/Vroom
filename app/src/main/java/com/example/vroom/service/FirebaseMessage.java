package com.example.vroom.service;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.vroom.ui.chat.ChatFragment;
import com.example.vroom.ui.chat.adapter.ChatAdapter;
import com.example.vroom.ui.chat.modal.ChatCard;
import com.example.vroom.ui.chat.viewModal.ChatViewModel;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class FirebaseMessage extends FirebaseMessagingService {
    private static final String TAG = "";
    ChatAdapter chatAdapter;
    ArrayList<ChatCard> chatArrayList;
//    public ChatViewModel chatViewModel;
    public ChatViewModel chatViewModel = ChatViewModel.getInstance();

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
//        chatArrayList.add(new ChatCard("anwar","ollaa","88"));
//        chatAdapter.updateChatList(chatArrayList);
        JSONObject message = new JSONObject(remoteMessage.getData());

        String body = remoteMessage.getData().get("body");
        String title = remoteMessage.getData().get("title");
        System.out.println(body+title);
        chatArrayList.add(new ChatCard("anwar","body","title"));
        chatViewModel.updatedmessage("anwar",body,title);

//        try {
//            ChatCard chatCard=new ChatCard(message.getString("name"),message.getString("message"),message.getString("chatid"));
//            System.out.println(" NEW MESSAGENEW MESSAGENEW MESSAGENEW MESSAGENEW MESSAGENEW MESSAGENEW MESSAGENEW MESSAGENEW MESSAGENEW MESSAGENEW MESSAGENEW MESSAGENEW MESSAGENEW MESSAGENEW MESSAGENEW MESSAGENEW MESSAGENEW MESSAGE");
//        }
//        catch (JSONException e) {
//            e.printStackTrace();
//        }
//        chatViewModel.getUserMutableLiveData().postValue(chatArrayList);
//        ChatFragment chatFragment=new ChatFragment();
//        chatFragment.messageupdate((JSONObject) remoteMessage.getData());
//        System.out.println(chatViewModel.getUserMutableLiveData().getValue());


//        try {
//            chatArrayList.add(new ChatCard(message.getString("name"),message.getString("message"),message.getString("chatid")));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        //chatAdapter.chatmap.containsKey()


//        chatViewModel.getUserMutableLiveData().postValue(chatArrayList);
//        try {
//            if (chatAdapter.chatmap.containsKey(message.getString("title")));
                System.out.println(remoteMessage.getData());

            // Check if message contains a data payload.
            if (remoteMessage.getData().size() > 0) {
                Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
//            Map<String, String> data = remoteMessage.getData();
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
    }

}
