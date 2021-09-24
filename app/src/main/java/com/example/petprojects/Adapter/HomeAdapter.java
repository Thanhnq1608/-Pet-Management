package com.example.petprojects.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petprojects.ModelThuCung.Pet;


import java.util.List;

import vn.poly.sotaythucung.R;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private Context context;
    private List<Pet> petList;
    Dialog dialog;

    public HomeAdapter(Context context, List<Pet> petList) {
        this.context = context;
        this.petList = petList;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.layout_pet_infomation);
        viewHolder.imgThuCung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        Pet pet = petList.get(position);
        if (petList == null) {
            return;
        }
        holder.imgThuCung.setImageResource(pet.getImageThuCung());
        holder.tvTenThuCung.setText(pet.getTenThuCung());
    }

    @Override
    public int getItemCount() {
        if (petList != null) {
            return petList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgThuCung;
        TextView tvTenThuCung;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgThuCung = itemView.findViewById(R.id.imgThuCung);
            tvTenThuCung = itemView.findViewById(R.id.tvTenThuCung);
        }
    }
}
