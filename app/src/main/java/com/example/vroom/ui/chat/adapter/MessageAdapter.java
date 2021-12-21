package com.example.vroom.ui.chat.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroom.R;
import com.example.vroom.database.TokenHandler;
import com.example.vroom.ui.chat.modal.MessageCard;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    private List<MessageCard> messageCards;
    private final int LEFT = 0, RIGHT = 1;
    public MessageAdapter(List<MessageCard> messageCards) {
        this.messageCards = messageCards;
    }

    @Override
    public int getItemViewType(int position) {
        if (messageCards.get(position).getSender().equals(TokenHandler.read(TokenHandler.USER_TOKEN, null))) {
            return RIGHT;
        } else{
            return LEFT;
        }
        //return -1;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case RIGHT:
                View v1 = inflater.inflate(R.layout.layout_chat_right, parent, false);
                return new MessageViewHolder(v1);
            default:
                View v2 = inflater.inflate(R.layout.layout_chat_left, parent, false);
                return new MessageViewHolder(v2);
        }
        //return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        MessageCard messageCard= messageCards.get(position);
        holder.message.setText(messageCard.getMessage());
    }

    @Override
    public int getItemCount() {
        return messageCards.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder  {
        TextView message;
        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            message=itemView.findViewById(R.id.message_text);
        }
    }
}
