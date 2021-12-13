package com.example.vroom.ui.status;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroom.R;
import com.example.vroom.ui.status.adapter.StatusAdapter;
import com.example.vroom.ui.status.model.StatusCard;
import com.example.vroom.ui.status.model.StatusName;

import java.util.ArrayList;
import java.util.List;

public class StatusFragment extends Fragment {

    private List<StatusName> nameList=new ArrayList<>();;
    private List<StatusCard> cardList=new ArrayList<>();;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_status, container, false);
        final RecyclerView recyclerView = root.findViewById(R.id.status_recv);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

            ArrayList<Object> items = new ArrayList<>();
            items.add(new StatusName("Accepted"));
            items.add(new StatusCard("Rob Stark"));
            items.add(new StatusCard("Rob Stark"));
            //items.add(new StatusCard("Rob Stark"));
            items.add(new StatusName("Rejected"));
            items.add(new StatusCard("Tyrion Lanister"));

        recyclerView.setAdapter(new StatusAdapter(items));
        return root;
    }
}