package com.example.vroom.ui.status.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroom.R;
import com.example.vroom.ui.status.model.StatusCard;
import com.example.vroom.ui.status.model.StatusName;

import java.util.List;

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.StatusViewHolder>  {
    private final List<Object> list;

    private final int StatusName = 0, StatusCard = 1;
    String title="";
    public StatusAdapter(List<Object> list) {
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position) instanceof StatusName) {
            return StatusName;
        } else if (list.get(position) instanceof StatusCard) {
            return StatusCard;
        }
        return -1;
    }

    public static class StatusViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView lessorTextView;
        TextView modelTextView;
        TextView requestTextView;
        Button btnstatusgreen;
        Button btnstatusred;

        View glowbar;

        final int StatusName=0, StatusCard=1;

        public StatusViewHolder(@NonNull View itemView , int viewType) {
            super(itemView);
            switch (viewType) {
                case StatusName:
                    nameTextView=itemView.findViewById(R.id.status);
                    break;
                default:
                    lessorTextView=itemView.findViewById(R.id.LessorName);
                    modelTextView=itemView.findViewById(R.id.ModelBrand);
                    requestTextView=itemView.findViewById(R.id.RequestText);
                    glowbar=itemView.findViewById(R.id.glowbar);
                    btnstatusgreen=itemView.findViewById(R.id.BtnStatus1);
                    btnstatusred=itemView.findViewById(R.id.BtsnStatus2);
            }
        }
    }

    @NonNull
    @Override
    public StatusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case StatusName:
                View v1 = inflater.inflate(R.layout.layout_requeststatus, parent, false);
                return new StatusViewHolder(v1,viewType);
            default:
                View v2 = inflater.inflate(R.layout.cardview_status, parent, false);
                return new StatusViewHolder(v2,viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull StatusViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case StatusName:
                StatusName name = (StatusName) list.get(position);
                title=name.getName();
                holder.nameTextView.setText(title+" request");
                break;
            default:
                StatusCard card = (StatusCard) list.get(position);
                holder.lessorTextView.setText(card.getlessorName());
                holder.modelTextView.setText(card.getModel());

                if (title.equals("accepted")){
                    holder.requestTextView.setText(R.string.AcceptedText);
                    holder.requestTextView.setTextColor(Color.parseColor("#67E405"));
                    //holder.requestTextView.setHighlightColor(R.color.accepted);
                    holder.btnstatusgreen.setText(R.string.PaymentButton);
                    holder.btnstatusred.setText(R.string.CancleButton);
                    holder.glowbar.setBackgroundResource(R.color.accepted);
                }
                else if (title.equals("rejected")){
                    holder.requestTextView.setText(R.string.RejectedText);
                    holder.requestTextView.setTextColor(Color.parseColor("#E40505"));
                    // holder.requestTextView.getResources().getColor(R.color.rejected);
                    holder.btnstatusgreen.setText(R.string.ViewMessageButton);
                    holder.btnstatusred.setVisibility(View.GONE);
                    holder.glowbar.setBackgroundResource(R.color.rejected);
                }
                else {
                    holder.requestTextView.setText(R.string.PendingText);
                    holder.requestTextView.setTextColor(Color.parseColor("#FFDC5D"));

                    //holder.requestTextView.getResources().getColor(R.color.pending);
                    holder.btnstatusgreen.setVisibility(View.GONE);
                    holder.btnstatusred.setText(R.string.CancleButton);
                    holder.glowbar.setBackgroundResource(R.color.pending);
                }
        }
    }

}
