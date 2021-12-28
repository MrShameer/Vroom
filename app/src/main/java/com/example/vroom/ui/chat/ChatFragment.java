package com.example.vroom.ui.chat;

import android.os.Bundle;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.example.vroom.R;
import com.example.vroom.api.Request;
import com.example.vroom.ui.chat.adapter.ChatAdapter;
import com.example.vroom.ui.chat.modal.ChatCard;
import com.example.vroom.ui.chat.viewModal.ChatViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ChatFragment extends Fragment implements LifecycleOwner {
    Request request = new Request();
    RecyclerView recyclerView;
//    ChatViewModel chatViewModel;
    ChatAdapter chatAdapter;
    public ChatViewModel chatViewModel;
//    public ChatViewModel chatViewModel = ChatViewModel.getInstance();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_chat, container, false);
        recyclerView = root.findViewById(R.id.chat_recv);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        // chatAdapter = new ChatAdapter(chatCards);
        chatAdapter = new ChatAdapter();
        recyclerView.setAdapter(chatAdapter);
        chatViewModel=new ViewModelProvider(this).get(ChatViewModel.class);
        chatViewModel.getUserMutableLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<ChatCard>>() {
            @Override
            public void onChanged(@Nullable ArrayList<ChatCard> chatArrayList) {
                    chatAdapter.updateChatList(chatArrayList);
            }
        });
//        viewModel =new ViewModelProvider(this).get(ChatViewModel.class);

        //viewModel = new ViewModelProvider(this).get(ChatViewModel.class);
//        viewModel.getUserMutableLiveData().observe(getViewLifecycleOwner(), chatListUpdateObserver);

        //new mytask().execute();
        //token untuk fcm ade kt login
        return root;
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
////        chatViewModel=new ViewModelProvider(this).get(ChatViewModel.class);
////        chatViewModel.getUserMutableLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<ChatCard>>() {
////            @Override
////            public void onChanged(ArrayList<ChatCard> chatArrayList) {
////                if (chatArrayList != null) {
////                    chatAdapter.updateChatList(chatArrayList);
////                    Toast.makeText(getContext(),"updated",Toast.LENGTH_SHORT).show();
////                }
////            }
////        });
//    }


//
//    Observer<ArrayList<ChatCard>> chatListUpdateObserver = new Observer<ArrayList<ChatCard>>() {
//        @Override
//        public void onChanged(ArrayList<ChatCard> userArrayList) {
//            chatAdapter.updateChatList(userArrayList);
//        }
//    };

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