package com.smartvillage.astagfirullah.activity.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.smartvillage.astagfirullah.R;
import com.smartvillage.astagfirullah.model.JadwalPosyandu;

import java.util.List;

public class MainAdapterJadwalPosyandu extends RecyclerView.Adapter<MainAdapterJadwalPosyandu.RecyclerViewAdapter> {

    private Context context;
    private ItemClickListener itemClickListener;
    private List<JadwalPosyandu> jadwalPosyanduList;

    public MainAdapterJadwalPosyandu(Context context, List<JadwalPosyandu> jadwalPosyanduList, MainAdapterJadwalPosyandu.ItemClickListener itemClickListener) {
        this.context = context;
        this.itemClickListener = itemClickListener;
        this.jadwalPosyanduList = jadwalPosyanduList;
    }

    @NonNull
    @Override
    public MainAdapterJadwalPosyandu.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_jadwalposyandu, parent, false);
        return new MainAdapterJadwalPosyandu.RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapterJadwalPosyandu.RecyclerViewAdapter holder, int position) {
        JadwalPosyandu jadwalPosyandu = jadwalPosyanduList.get(position);
        holder.tv_namabidan.setText(jadwalPosyandu.getNamabidan());
        holder.tv_jadwalbidan.setText(jadwalPosyandu.getJadwalbidan());
        holder.tv_waktuyandu.setText(jadwalPosyandu.getWaktuyandu());
    }

    @Override
    public int getItemCount() {
        return jadwalPosyanduList.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_namabidan, tv_jadwalbidan, tv_waktuyandu;
        CardView itemjadwalposyandu;
        MainAdapterJadwalPosyandu.ItemClickListener itemClickListener;

        RecyclerViewAdapter(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            tv_namabidan = itemView.findViewById(R.id.namabidan);
            tv_jadwalbidan = itemView.findViewById(R.id.jadwalbidan);
            tv_waktuyandu = itemView.findViewById(R.id.waktuyandu);
            itemjadwalposyandu = itemView.findViewById(R.id.itemjadwalposyandu);

            this.itemClickListener = itemClickListener;
            itemjadwalposyandu.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}