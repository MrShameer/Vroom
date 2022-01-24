package com.example.vroom.database.Chat;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ChatCardDAO {
    @Insert
    void insert(ChatCard chatCard);

    @Update
    void update(ChatCard chatCard);

    @Delete
    void delete(ChatCard chatCard);

    @Query("DELETE FROM Chat_table")
    void deleteAllChat();

    @Query("SELECT * FROM Chat_table")
    LiveData<List<ChatCard>> getAllChat();
}
