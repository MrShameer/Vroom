package com.example.vroom.database.User;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {
    //table user (username, full name, email, current address, phone number, password)
    @PrimaryKey
    @NonNull
    private String userID;
    private String username;
    private String name;
    private String email;
    private String address;
    private String phone;
//    @ColumnInfo(name="userpassword")
    private String password;
    private Bitmap images;
    public User(String userID, String username, String name, String email, String address, String phone, String password) {
        this.userID = userID;
        this.username = username;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.password = password;
//        this.images=images;
    }

    public void setUserID(@NonNull String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public Bitmap getImages() {
        return images;
    }
}
