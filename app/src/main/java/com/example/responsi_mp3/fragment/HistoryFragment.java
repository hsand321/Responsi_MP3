package com.example.responsi_mp3.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.responsi_mp3.R;
import com.example.responsi_mp3.history.HistoryAdapter;
import com.example.responsi_mp3.history.HistoryViewModel;


public class HistoryFragment extends Fragment implements  {

    private RecyclerView recyclerView;
    private HistoryAdapter historyAdapter;
    private HistoryViewModel historyViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_history, container, false);

        recyclerView = view.findViewById(R.id.rvHistory);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        historyViewModel = new ViewModelProviders.of(HistoryFragment.this).get(HistoryViewModel.class);
        historyViewModel.init();

        historyAdapter = new HistoryAdapter(historyViewModel.getHistorys().getValue());
        recyclerView.setAdapter(historyAdapter);
        return view;
    }
}