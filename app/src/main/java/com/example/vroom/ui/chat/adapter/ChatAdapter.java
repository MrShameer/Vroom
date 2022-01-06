package com.example.vroom.ui.chat.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroom.R;
import com.example.vroom.database.VehicleDetails.VehicleDetails;
import com.example.vroom.ui.chat.MessageActivity;
import com.example.vroom.ui.chat.modal.ChatCard;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    public ArrayList<ChatCard> chatCards= new ArrayList<ChatCard>();

    @SuppressLint("NotifyDataSetChanged")
    public void setChatCards(ArrayList<ChatCard> chatArrayList){
        this.chatCards=chatArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_chat,parent,false);
        return new ChatViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        ChatCard chatCard= chatCards.get(position);
        holder.name.setText(chatCard.getName());
        holder.message.setText(chatCard.getMessage());

        Picasso.get().load("https://vroom.lepak.xyz/storage/picture/profile/"+chatCard.getId()+".jpg").into(holder.lessorPic, new Callback() {
            @Override
            public void onSuccess() {
            }
            @Override
            public void onError(Exception e) {
                Picasso.get().load(R.drawable.profile_image).into(holder.lessorPic);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chatCards.size();
    }

    public void updateChatList(ArrayList<ChatCard> chatArrayList) {
        this.chatCards.clear();
        this.chatCards = chatArrayList;
        notifyDataSetChanged();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder{
        TextView name, message;
        ImageView lessorPic;
        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.namechat);
            message=itemView.findViewById(R.id.message);
            lessorPic=itemView.findViewById(R.id.lessorPic);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    ChatCard chatCard= chatCards.get(getAdapterPosition());
                    Intent intent = new Intent(context, MessageActivity.class);
                    intent.putExtra("CHAT_ID",chatCard.getChatid());
                    intent.putExtra("ID",chatCard.getId());
                    intent.putExtra("CHAT_NAME", chatCard.getName());
                    context.startActivity(intent);
                }
            });
        }
    }
}
