package com.example.vroom.ui.chat.modal;

public class ChatCard {
    String name;
    String message;
    String id;
    String chatid;

    public ChatCard(String name, String message, String chatid) {
        this.name = name;
        this.message = message;
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

    public String getChatid() {
        return chatid;
    }

    public void setChatid(String phone) {
        this.chatid = chatid;
    }
}
