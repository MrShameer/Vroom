package com.example.vroom.database.User;

import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDAO {
    
    //CRUD
    @Insert
    void insert(User user);

    @Update
    void update(User user);

//    @Query("UPDATE user_table SET order_price=:price WHERE order_id = :id")
//    void update(Float price, int id);

    @Delete
    void delete(User user);

    @Query("DELETE FROM user_table")
    void deleteAllUser();

    @Query("SELECT * FROM user_table")
    LiveData<List<User>> getAllUser();

}
