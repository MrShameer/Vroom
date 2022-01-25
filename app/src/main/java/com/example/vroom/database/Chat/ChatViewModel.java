package com.example.vroom.database.Chat;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.vroom.database.VehicleDetails.VehicleDetails;
import com.example.vroom.database.VehicleDetails.VehicleDetailsRepository;

import java.util.List;

public class ChatViewModel extends AndroidViewModel {

    private ChatRepository chatRepository;
    private LiveData<List<ChatCard>> getAllChatCard;

    public ChatViewModel(@NonNull Application application) {

        super(application);
        chatRepository=new ChatRepository(application);
        getAllChatCard= chatRepository.getAllChatCard();
    }
    public void insert (ChatCard chatCard){
        chatRepository.insert(chatCard);
    }
    public void update (ChatCard chatCard){
        chatRepository.update(chatCard);
    }
    public void delete (ChatCard chatCard){
        chatRepository.delete(chatCard);
    }
    public void deleteAll (){
        chatRepository.deleteAllChatCard();
    }
    public LiveData<List<ChatCard>> getGetAllChatCard() {
        return getAllChatCard;
    }
}
