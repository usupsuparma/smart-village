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
import com.smartvillage.astagfirullah.model.Profil;

import java.util.ArrayList;
import java.util.List;

public class MainAdapterProfil extends RecyclerView.Adapter<MainAdapterProfil.RecyclerViewAdapter> {

    private Context context;
    private ItemClickListener itemClickListener;
    private List<Profil> profilList = new ArrayList<>();

    public MainAdapterProfil(Context context, MainAdapterProfil.ItemClickListener itemClickListener) {
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    public void setProfilList(List<Profil> profilList) {
        this.profilList = profilList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainAdapterProfil.RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_profil, parent, false);
        return new MainAdapterProfil.RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapterProfil.RecyclerViewAdapter holder, int position) {
        Profil profil = profilList.get(position);
        holder.profil = profil;
        holder.tv_nik.setText(profil.getNik());
        holder.tv_nama.setText(profil.getNama());
        holder.tv_tanggallahir.setText(profil.getTanggallahir());
        holder.tv_jeniskelamin.setText(profil.getJeniskelamin());
        holder.tv_alamat.setText(profil.getAlamat());
        holder.tv_agama.setText(profil.getAgama());
        holder.tv_status.setText(profil.getStatus());
        holder.tv_pekerjaan.setText(profil.getPekerjaan());
    }

    @Override
    public int getItemCount() {
        return profilList.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_nik, tv_nama, tv_tanggallahir, tv_jeniskelamin, tv_alamat, tv_agama, tv_status, tv_pekerjaan;
        CardView itemprofil;
        MainAdapterProfil.ItemClickListener itemClickListener;
        Profil profil;

        RecyclerViewAdapter(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            tv_nik = itemView.findViewById(R.id.nik);
            tv_nama = itemView.findViewById(R.id.nama);
            tv_tanggallahir = itemView.findViewById(R.id.tanggallahir);
            tv_jeniskelamin = itemView.findViewById(R.id.jeniskelamin);
            tv_alamat = itemView.findViewById(R.id.alamat);
            tv_agama = itemView.findViewById(R.id.agama);
            tv_status = itemView.findViewById(R.id.status);
            tv_pekerjaan = itemView.findViewById(R.id.pekerjaan);
            itemprofil = itemView.findViewById(R.id.itemprofil);

            this.itemClickListener = itemClickListener;
            itemprofil.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(profil);
        }
    }

    public interface ItemClickListener {
        void onItemClick(Profil profil);
    }
}
