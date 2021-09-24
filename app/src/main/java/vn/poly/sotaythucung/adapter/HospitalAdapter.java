package vn.poly.sotaythucung.adapter;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.poly.sotaythucung.petservice.HospitalMapActivity;
import vn.poly.sotaythucung.model.Hospital;
import vn.poly.sotaythucung.R;
import vn.poly.sotaythucung.sqlite.HospitalDAO;
import vn.poly.sotaythucung.sqlite.SQLiteDB;

import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder> {
    private Context context;
    private List<Hospital> hospitalList;
    Dialog dialog;

    public HospitalAdapter(Context context, List<Hospital> hospitalList) {
        this.context = context;
        this.hospitalList = hospitalList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hospital, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final HospitalDAO hospitalDAO = new HospitalDAO(new SQLiteDB(context));
        Hospital hospital = hospitalList.get(position);
        if (hospital == null) {
            return;
        }
        dialog = new Dialog(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.layout_hospital_infomation, null);

        final TextView tvTTTenBV = view.findViewById(R.id.tvTTTenBV);
        final TextView tvTTDiaDiemBV = view.findViewById(R.id.tvTTDiaDiemBV);
        Button btnThoatTT = view.findViewById(R.id.btnThoatTT);
        TextView tvTTDichVuBV = view.findViewById(R.id.tvTTDichVuBV);

        holder.imgXemThonTinBV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Hospital> hospitalList2 = hospitalDAO.getAllBenhVien();
                tvTTTenBV.setText(hospitalList2.get(position).getTenBenhVien());
                tvTTDiaDiemBV.setText(hospitalList2.get(position).getDiaChiBenhVien());

                dialog.setContentView(view);
                dialog.show();
            }
        });
        btnThoatTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        holder.imgBenhVien.setImageResource(hospital.getResouceImages());
        holder.tvTenBenhVien.setText(hospital.getTenBenhVien());
        holder.tvDiaDiemBV.setText(hospital.getDiaChiBenhVien());
        holder.tvDiaDiemBV.setTextColor(Color.RED);


        holder.imgMapBenhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hospitalList = hospitalDAO.getMap();
                Intent intent = new Intent(context, HospitalMapActivity.class);
                intent.putExtra("KINHDO", hospitalList.get(position).getKinhDo());
                intent.putExtra("VIDO", hospitalList.get(position).getViDo());
                context.startActivity(intent);

            }
        });
        final List<Hospital> hospitalList1 = hospitalDAO.getMap();
        if (hospitalList1.get(position).getDanhGiaBenhVien() == 0) {
            holder.imgRateStar.setImageResource(R.drawable.star_rate5);

        } else if (hospitalList1.get(position).getDanhGiaBenhVien() == 1) {
            holder.imgRateStar.setImageResource(R.drawable.star_rate4);
        } else if (hospitalList1.get(position).getDanhGiaBenhVien() == 2) {
            holder.imgRateStar.setImageResource(R.drawable.star_rate3);
        } else if (hospitalList1.get(position).getDanhGiaBenhVien() == 3) {
            holder.imgRateStar.setImageResource(R.drawable.star_rate2);
        } else if (hospitalList1.get(position).getDanhGiaBenhVien() == 4) {
            holder.imgRateStar.setImageResource(R.drawable.star_rate1);
        } else if (hospitalList1.get(position).getDanhGiaBenhVien() == 5) {
            holder.imgRateStar.setImageResource(R.drawable.star_rate6);
        } else {
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
        ImageView imgBenhVien, imgXemThonTinBV, imgMapBenhVien, imgRateStar;
        TextView tvTenBenhVien, tvDiaDiemBV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBenhVien = itemView.findViewById(R.id.imgBenhVien);
            tvTenBenhVien = itemView.findViewById(R.id.tvTenBenhVien);
            tvDiaDiemBV = itemView.findViewById(R.id.tvDiaDiemBV);
            imgXemThonTinBV = itemView.findViewById(R.id.imgXemThonTinBV);
            imgMapBenhVien = itemView.findViewById(R.id.imgChiDuongMapp);
            imgRateStar = itemView.findViewById(R.id.imgRateStar);
        }

    }
}
