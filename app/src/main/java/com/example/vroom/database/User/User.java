package com.example.vroom.database.User;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.vroom.ui.chat.viewModal.ChatViewModel;

import java.io.Serializable;

@Entity(tableName = "user_table")
public class User implements Serializable {
    @PrimaryKey
    @NonNull
    @ColumnInfo
    private String userID;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String email;
    @ColumnInfo
    private String role;
    @ColumnInfo
    private String address;
    @ColumnInfo
    private String phone;
    @ColumnInfo
    private String icstatus;
    @ColumnInfo
    private String dlstatus;

    public User(String userID, String name, String email, String role, String address, String phone, String icstatus, String dlstatus) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.role = role;
        this.address = address;
        this.phone = phone;
        this.icstatus = icstatus;
        this.dlstatus = dlstatus;
    }

    public void setUserID(@NonNull String userID) {
        this.userID = userID;
    }
    @NonNull
    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getIcstatus() {return icstatus;}

    public String getDlstatus() {return dlstatus;}


    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setIcstatus(String icstatus) {
        this.icstatus = icstatus;
    }

    public void setDlstatus(String dlstatus) {
        this.dlstatus = dlstatus;
    }


}
