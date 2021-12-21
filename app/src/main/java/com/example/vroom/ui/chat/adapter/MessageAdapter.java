package com.example.vroom.ui.chat.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroom.ui.chat.modal.MessageCard;
import com.example.vroom.ui.status.adapter.StatusAdapter;
import com.example.vroom.ui.status.model.StatusCard;
import com.example.vroom.ui.status.model.StatusName;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    private final List<MessageCard> list;
    public MessageAdapter(List<MessageCard> list) {
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getSender()==) {
            return StatusName;
        } else if (list.get(position) instanceof StatusCard) {
            return StatusCard;
        }
        return -1;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder  {
        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
