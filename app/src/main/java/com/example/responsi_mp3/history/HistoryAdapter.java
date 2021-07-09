package com.example.responsi_mp3.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.responsi_mp3.R;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private ArrayList<History> histories;

    public HistoryAdapter(ArrayList<History> histories) {
        this.histories = histories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.itemView.setTag(histories.get(position));
        holder.metode.setText(histories.get(position).getMetode());
        holder.pembayaran.setText(histories.get(position).getPembayaran());
        holder.tanggal.setText(histories.get(position).getTanggal());
        holder.status.setText(histories.get(position).getStatus());

    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView metode;
        private TextView pembayaran;
        private TextView tanggal;
        private TextView status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            metode = itemView.findViewById(R.id.metode);
            pembayaran = itemView.findViewById(R.id.pembayaran);
            tanggal = itemView.findViewById(R.id.tanggal);
            status = itemView.findViewById(R.id.status);
        }
    }
}
