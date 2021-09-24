package com.example.petprojects.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petprojects.ModelThuCung.Hospital;

import java.util.List;

import vn.poly.sotaythucung.R;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder> {
    private Context context;
    private List<Hospital> hospitalList;
    Dialog dialog;

    public HospitalAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Hospital> hospitalList) {
        this.hospitalList = hospitalList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hospital, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        dialog = new Dialog(context);

        dialog.setContentView(R.layout.layout_hospital_infomation);
        dialog.setTitle("THÔNG TIN BỆNH VIỆN");
        TextView tvTTTenBV = dialog.findViewById(R.id.tvTTTenBV);
        TextView tvTTDiaDiemBV = dialog.findViewById(R.id.tvTTDiaDiemBV);
        TextView tvTTDichVuBV = dialog.findViewById(R.id.tvTTDichVuBV);

        tvTTTenBV.setText("Bệnh Viện ......");
        tvTTDiaDiemBV.setText("Địa Chỉ Tại");
        tvTTDichVuBV.setText("Cung Cấp Dịch vụ.....");
        viewHolder.imgXemThonTinBV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Hospital hospital = hospitalList.get(position);
        if (hospital == null) {
            return;
        }
        holder.imgBenhVien.setImageResource(hospital.getResouceImages());
        holder.tvTenBenhVien.setText(hospital.getTenBenhVien());
        holder.tvDiaDiemBV.setText(hospital.getDiaChiBenhVien());
        if (hospital.getDiaChiBenhVien().equalsIgnoreCase("Hà Nội")) {
            holder.tvDiaDiemBV.setTextColor(Color.RED);
        }
        if (hospital.getDiaChiBenhVien().equalsIgnoreCase("TP. Hồ Chí Minh")) {
            holder.tvDiaDiemBV.setTextColor(Color.MAGENTA);
        }
    }

    @Override
    public int getItemCount() {
        if (hospitalList != null) {
            return hospitalList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBenhVien, imgXemThonTinBV;
        TextView tvTenBenhVien, tvDiaDiemBV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBenhVien = itemView.findViewById(R.id.imgBenhVien);
            tvTenBenhVien = itemView.findViewById(R.id.tvTenBenhVien);
            tvDiaDiemBV = itemView.findViewById(R.id.tvDiaDiemBV);
            imgXemThonTinBV = itemView.findViewById(R.id.imgXemThonTinBV);
        }

    }
}
