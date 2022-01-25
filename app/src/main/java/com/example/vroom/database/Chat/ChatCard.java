package com.example.vroom.database.Chat;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Chat_table")

public class ChatCard implements Serializable {
    @PrimaryKey
    @NonNull
    String chatid;
    String name;
    String message;
    String id;

    public ChatCard(@NonNull String chatid, String name, String message, String id) {
        this.chatid = chatid;
        this.name = name;
        this.message = message;
        this.id = id;
    }

    @NonNull
    public String getChatid() {
        return chatid;
    }

    public void setChatid(@NonNull String chatid) {
        this.chatid = chatid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
