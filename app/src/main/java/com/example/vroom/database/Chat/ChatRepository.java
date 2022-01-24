package com.example.vroom.database.Chat;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ChatRepository
    {
        private ChatCardDAO ChatCardDAO;
        private LiveData<List<ChatCard>> getAllChatCard;

    public ChatRepository(Application application){

        ChatCardDatabase database=ChatCardDatabase.getInstance(application);
        ChatCardDAO= database.chatCardDAO();
        getAllChatCard= ChatCardDAO.getAllChat();
    }

        public void insert(ChatCard ChatCard){
        new ChatRepository.InsertChatCardAsyncTask(ChatCardDAO).execute(ChatCard);
    }
        private static class InsertChatCardAsyncTask extends AsyncTask<ChatCard,Void,Void>
        {
            private ChatCardDAO ChatCardDAO;
            private  InsertChatCardAsyncTask(ChatCardDAO ChatCardDAO){
                this.ChatCardDAO=ChatCardDAO;
            }
            @Override
            protected Void doInBackground(ChatCard... ChatCard) {
                ChatCardDAO.insert(ChatCard[0]);
                return null;
            }
        }

        public  void update(ChatCard ChatCard){
        new ChatRepository.UpdateChatCardAsyncTask(ChatCardDAO).execute(ChatCard);

    }
        private static class UpdateChatCardAsyncTask extends AsyncTask<ChatCard,Void,Void>
        {
            private ChatCardDAO ChatCardDAO;
            private  UpdateChatCardAsyncTask(ChatCardDAO ChatCardDAO){
                this.ChatCardDAO=ChatCardDAO;
            }
            @Override
            protected Void doInBackground(ChatCard... ChatCard) {
                ChatCardDAO.update(ChatCard[0]);
                return null;
            }
        }


        public  void delete(ChatCard ChatCard){
        new ChatRepository.DeleteChatCardAsyncTask(ChatCardDAO).execute(ChatCard);
    }
        private static class DeleteChatCardAsyncTask extends AsyncTask<ChatCard,Void,Void>
        {
            private ChatCardDAO ChatCardDAO;
            private  DeleteChatCardAsyncTask(ChatCardDAO ChatCardDAO){
                this.ChatCardDAO=ChatCardDAO;
            }
            @Override
            protected Void doInBackground(ChatCard... ChatCard) {
                ChatCardDAO.delete(ChatCard[0]);
                return null;
            }
        }

        public  void deleteAllChatCard(){
        new ChatRepository.DeleteAllChatCardAsyncTask(ChatCardDAO).execute();
    }
        private static class DeleteAllChatCardAsyncTask extends AsyncTask<ChatCard,Void,Void>
        {
            private ChatCardDAO ChatCardDAO;
            private  DeleteAllChatCardAsyncTask(ChatCardDAO ChatCardDAO){
                this.ChatCardDAO=ChatCardDAO;
            }
            @Override
            protected Void doInBackground(ChatCard... ChatCard) {
                ChatCardDAO.deleteAllChat();
                return null;
            }
        }

        public LiveData<List<ChatCard>> getAllChatCard(){
        return getAllChatCard;
    }




    }


