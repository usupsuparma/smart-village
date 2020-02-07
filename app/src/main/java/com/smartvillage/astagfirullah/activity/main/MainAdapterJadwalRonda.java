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
import com.smartvillage.astagfirullah.model.JadwalRonda;
import com.smartvillage.astagfirullah.model.RiwayatSakit;

import java.util.List;

public class MainAdapterJadwalRonda extends RecyclerView.Adapter<MainAdapterJadwalRonda.RecyclerViewAdapter> {

    private Context context;
    private ItemClickListener itemClickListener;
    private List<JadwalRonda> jadwalRondaList;

    public MainAdapterJadwalRonda(Context context, List<JadwalRonda> jadwalRondaList, MainAdapterJadwalRonda.ItemClickListener itemClickListener) {
        this.context = context;
        this.itemClickListener = itemClickListener;
        this.jadwalRondaList = jadwalRondaList;
    }

    @NonNull
    @Override
    public MainAdapterJadwalRonda.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_jadwalronda, parent, false);
        return new MainAdapterJadwalRonda.RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapterJadwalRonda.RecyclerViewAdapter holder, int position) {
        JadwalRonda jadwalRonda = jadwalRondaList.get(position);
        holder.tv_namapetugas.setText(jadwalRonda.getNamapetugas());
        holder.tv_jadwalpetugas.setText(jadwalRonda.getJadwalpetugas());
    }

    @Override
    public int getItemCount() {
        return jadwalRondaList.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_namapetugas, tv_jadwalpetugas;
        CardView itemjadwalronda;
        MainAdapterJadwalRonda.ItemClickListener itemClickListener;

        RecyclerViewAdapter(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            tv_namapetugas = itemView.findViewById(R.id.namapetugas);
            tv_jadwalpetugas = itemView.findViewById(R.id.jadwalpetugas);
            itemjadwalronda = itemView.findViewById(R.id.itemjadwalronda);

            this.itemClickListener = itemClickListener;
            itemjadwalronda.setOnClickListener(this);
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