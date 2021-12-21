package com.example.vroom.ui.chat.modal;

public class MessageCard {
    String id, message, sender, time;

    public MessageCard(String id, String message, String sender, String time) {
        this.id = id;
        this.message = message;
        this.sender = sender;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
