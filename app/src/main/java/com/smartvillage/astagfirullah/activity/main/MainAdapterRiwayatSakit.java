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
import com.smartvillage.astagfirullah.model.RiwayatSakit;

import java.util.ArrayList;
import java.util.List;

public class MainAdapterRiwayatSakit extends RecyclerView.Adapter<MainAdapterRiwayatSakit.RecyclerViewAdapter> {

    private Context context;
    private ItemClickListener itemClickListener;
    private List<RiwayatSakit> riwayatSakitList;

    public MainAdapterRiwayatSakit(Context context, ItemClickListener itemClickListener) {
        this.context = context;
        this.itemClickListener = itemClickListener;
        riwayatSakitList = new ArrayList<>();
    }

    public void setRiwayatSakitList(List<RiwayatSakit> riwayatSakitList) {
        this.riwayatSakitList = riwayatSakitList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_riwayatsakit, parent, false);
        return new RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter holder, int position) {
        RiwayatSakit riwayatSakit = riwayatSakitList.get(position);
        holder.riwayatSakit = riwayatSakit;
        holder.tv_namapasien.setText(riwayatSakit.getNamapasien());
        holder.tv_penyakitpasien.setText(riwayatSakit.getPenyakitpasien());
        holder.tv_tanggalsakit.setText(riwayatSakit.getTanggalsakit());
    }

    @Override
    public int getItemCount() {
        return riwayatSakitList.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_namapasien, tv_penyakitpasien, tv_tanggalsakit;
        CardView itemriwayatsakit;
        ItemClickListener itemClickListener;
        RiwayatSakit riwayatSakit;

        RecyclerViewAdapter(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            tv_namapasien = itemView.findViewById(R.id.namapasien);
            tv_penyakitpasien = itemView.findViewById(R.id.penyakitpasien);
            tv_tanggalsakit = itemView.findViewById(R.id.tanggalsakit);
            itemriwayatsakit = itemView.findViewById(R.id.itemriwayatsakit);

            this.itemClickListener = itemClickListener;
            itemriwayatsakit.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(riwayatSakit, getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, RiwayatSakit riwayatSakit);
    }
}
