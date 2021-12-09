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
    @ColumnInfo
    private String userID;
    @ColumnInfo
    private String username;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String email;
    @ColumnInfo
    private String address;
    @ColumnInfo
    private String phone;
//    @ColumnInfo(name="userpassword")
    @ColumnInfo
    private String password;
    @ColumnInfo
    private String icstatus;
    @ColumnInfo
    private String dlstatus;
//
//    public User(String userID, String username, String name, String email, String address, String phone, String password) {
  public User(String userID, String username, String name, String email, String address, String phone, String password,String icstatus,String dlstatus) {

        this.userID = userID;
        this.username = username;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.password = password;
        this.icstatus = icstatus;
        this.dlstatus = dlstatus;
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
//

    public void setIcstatus(String icstatus) {
        this.icstatus = icstatus;
    }

    public void setDlstatus(String dlstatus) {
        this.dlstatus = dlstatus;
    }

    public String getIcstatus() {return icstatus;}

    public String getDlstatus() {return dlstatus;}
}
