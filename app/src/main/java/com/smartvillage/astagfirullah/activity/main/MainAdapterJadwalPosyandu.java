package com.smartvillage.astagfirullah.activity.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.smartvillage.astagfirullah.R;
import com.smartvillage.astagfirullah.model.JadwalPosyandu;

import java.util.ArrayList;
import java.util.List;

public class MainAdapterJadwalPosyandu extends RecyclerView.Adapter<MainAdapterJadwalPosyandu.RecyclerViewAdapter> {

    private ItemClickListener itemClickListener;
    private List<JadwalPosyandu> jadwalPosyanduList;

    public MainAdapterJadwalPosyandu(MainAdapterJadwalPosyandu.ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
        this.jadwalPosyanduList = new ArrayList<>();
    }

    public void setJadwalPosyanduList(List<JadwalPosyandu> jadwalPosyanduList) {
        this.jadwalPosyanduList = jadwalPosyanduList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jadwalposyandu, parent, false);
        return new RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter holder, int position) {
        JadwalPosyandu jadwalPosyandu = jadwalPosyanduList.get(position);
        holder.jadwalPosyandu = jadwalPosyandu;
        holder.tv_jadwalbidan.setText(jadwalPosyandu.getLokasi());
        holder.tv_waktuyandu.setText(jadwalPosyandu.getWaktuyandu());
        holder.tanggalPosyandu.setText(jadwalPosyandu.getTglYandu());
    }

    @Override
    public int getItemCount() {
        return jadwalPosyanduList.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tanggalPosyandu, tv_jadwalbidan, tv_waktuyandu;
        CardView itemjadwalposyandu;
        ItemClickListener itemClickListener;
        JadwalPosyandu jadwalPosyandu;

        RecyclerViewAdapter(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            tanggalPosyandu = itemView.findViewById(R.id.tanggal_posyandu);
            tv_jadwalbidan = itemView.findViewById(R.id.tempatposyandu);
            tv_waktuyandu = itemView.findViewById(R.id.waktu_posyandu);
            itemjadwalposyandu = itemView.findViewById(R.id.itemjadwalposyandu);

            this.itemClickListener = itemClickListener;
            itemjadwalposyandu.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(jadwalPosyandu, getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onItemClick(JadwalPosyandu jadwalPosyandu, int position);
    }
}