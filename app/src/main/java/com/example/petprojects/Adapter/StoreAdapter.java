package com.example.petprojects.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petprojects.ModelThuCung.Store;

import java.util.List;

import vn.poly.sotaythucung.R;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {
    List<Store> storeList;
    Context context;

    public StoreAdapter(List<Store> storeList, Context context) {
        this.storeList = storeList;
        this.context = context;
    }

    @NonNull
    @Override
    public StoreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StoreAdapter.ViewHolder holder, int position) {
        Store store = storeList.get(position);
        holder.tvTenCuaHang.setText(store.getTenCuaHang());
        holder.tvDiaChiCuaHang.setText(store.getDiaChiCuaHang());
        holder.imgCuaHang.setImageResource(store.getAnhCuaHang());
    }

    @Override
    public int getItemCount() {
        return storeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenCuaHang, tvDiaChiCuaHang;
        ImageView imgCuaHang;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenCuaHang = itemView.findViewById(R.id.tvTenCuahang);
            tvDiaChiCuaHang = itemView.findViewById(R.id.tvDiaChiCuaHang);
            imgCuaHang = itemView.findViewById(R.id.imgCuaHang);
        }
    }
}
