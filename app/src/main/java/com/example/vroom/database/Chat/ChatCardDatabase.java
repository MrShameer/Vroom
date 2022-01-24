package com.example.vroom.database.Chat;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.vroom.api.Request;
import com.example.vroom.database.TokenHandler;
import com.example.vroom.database.VehicleDetails.VehicleDetailsDAO;
import com.example.vroom.database.VehicleDetails.VehicleDetailsDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.RequestBody;

@Database(entities = {ChatCard.class},version = 1)

public abstract class ChatCardDatabase extends RoomDatabase {

    //singleton class
    private static ChatCardDatabase instance;

    public abstract ChatCardDAO chatCardDAO();


    //to ensure the database doesnt have any duplicates
    static synchronized ChatCardDatabase getInstance(Context context){
        //instance.clearAllTables();
        if (instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    ChatCardDatabase.class,"chat_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();

        }
        return instance;

    }
    private static RoomDatabase.Callback roomCallback=new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new ChatCardDatabase.PopulateDbAsyncTask(instance).execute();
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ChatCardDAO chatCardDAO;
        Request request = new Request();
        String respond;
        JSONArray jsonArray = null;
        private PopulateDbAsyncTask(ChatCardDatabase db){
            chatCardDAO=db.chatCardDAO();
        }

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
                    chatCardDAO.insert(new ChatCard(jsonObject.getString("chatid"),
                            jsonObject.getString("name"),
                            jsonObject.getString("message"),
                            jsonObject.getString("id")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//            chatLiveData.setValue(chatArrayList);
//        }
    }
}
