package com.example.petprojects.DichVuThuCung;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.petprojects.Adapter.HospitalAdapter;
import com.example.petprojects.CaiDat.SettingActivity;
import com.example.petprojects.ModelThuCung.Hospital;

import com.example.petprojects.SQLite.HospitalDAO;
import com.example.petprojects.SQLite.SQLiteDB;
import com.example.petprojects.TinTucThuCung.PetNewsActivity;
import com.example.petprojects.TrangChu.HomeActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import vn.poly.sotaythucung.R;


public class HospitalActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recBenhVien;
    HospitalAdapter hospitalAdapter;
    private SQLiteDB sqLiteDB;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        sqLiteDB = new SQLiteDB(this);
        HospitalDAO hospitalDAO = new HospitalDAO(sqLiteDB);
        recBenhVien = findViewById(R.id.recBenhVien);
//        delete();
        Menu();
        ThemBenhVien();
        hospitalAdapter = new HospitalAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recBenhVien.setLayoutManager(gridLayoutManager);
        List<Hospital> hospitalList = hospitalDAO.getAllBenhVien();
        hospitalAdapter.setData(hospitalList);
        recBenhVien.setAdapter(hospitalAdapter);
    }

    private void Menu() {
        toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("Bệnh Viện Thú Cưng");
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerBenhVien);
        navigationView = findViewById(R.id.navigation_view);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    //    private List<BenhVien> benhVienList() {
//        List<BenhVien> benhVienList = new ArrayList<>();
//        BenhVien benhVien = new BenhVien("BV01", "Bệnh Viện Thú Cưng", "Số 3 Nguyễn Trải", R.drawable.hospital_item))
//        ;
////        benhVienList.add(new BenhVien("BV01", "Thú Cưng Hà Nội", "Số 3 Nguyễn Trải", R.drawable.hospital_item));
////        benhVienList.add(new BenhVien("BV01", "Bệnh Viện Lan Anh", "Số 3 Nguyễn Trải", R.drawable.hospital_item));
////        benhVienList.add(new BenhVien("BV01", "Pets Hospital", "Số 3 Nguyễn Trải", R.drawable.hospital_item));
////        benhVienList.add(new BenhVien("BV01", "Animal Viet Nam", "Số 3 Nguyễn Trải", R.drawable.hospital_item));
//        BenhVienDAO benhVienDAO = new BenhVienDAO(sqLiteDB);
//        benhVienDAO.themBenhVien(benhVien);
//        return benhVienList;
//
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home_pet) {
            startActivity(new Intent(HospitalActivity.this, HomeActivity.class));
        } else if (item.getItemId() == R.id.news_pet) {
            startActivity(new Intent(HospitalActivity.this, PetNewsActivity.class));
        } else if (item.getItemId() == R.id.hospital_pet) {
            startActivity(new Intent(HospitalActivity.this, HospitalActivity.class));
        } else if (item.getItemId() == R.id.shop_pet) {
            startActivity(new Intent(HospitalActivity.this, PetStoreActivity.class));
        } else if (item.getItemId() == R.id.setup) {
            startActivity(new Intent(HospitalActivity.this, SettingActivity.class));
        }else if (item.getItemId() == R.id.facebook) {
            Toast.makeText(this, "Chưa cập nhật thông tin", Toast.LENGTH_SHORT).show();
        }else if (item.getItemId() == R.id.twitter) {
            Toast.makeText(this, "Chưa cập nhật thông tin", Toast.LENGTH_SHORT).show();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void ThemBenhVien() {
        Hospital hospital = new Hospital("BV01", "Bệnh Viện Ba Lan", "Hà Nội", R.drawable.hospital_item);
        Hospital hospital2 = new Hospital("BV02", "Bệnh Viện Nam Từ Liêm", "TP. Hồ Chí Minh", R.drawable.hospital_item);
        Hospital hospital3 = new Hospital("BV03", "Bệnh Viện Ba Lan", "Hà Nội", R.drawable.hospital_item);
        Hospital hospital4 = new Hospital("BV04", "Bệnh Viện Nam Từ Liêm", "Hà Nội", R.drawable.hospital_item);
        Hospital hospital5 = new Hospital("BV05", "Bệnh Viện Ba Lan", "TP. Hồ Chí Minh", R.drawable.hospital_item);
        Hospital hospital6 = new Hospital("BV06", "Bệnh Viện Ba Lan", "Hà Nội", R.drawable.hospital_item);
        Hospital hospital7 = new Hospital("BV07", "Bệnh Viện Nam Từ Liêm", "TP. Hồ Chí Minh", R.drawable.hospital_item);


        HospitalDAO hospitalDAO = new HospitalDAO(sqLiteDB);
        hospitalDAO.themBenhVien(hospital);
        hospitalDAO.themBenhVien(hospital2);
        hospitalDAO.themBenhVien(hospital3);
        hospitalDAO.themBenhVien(hospital4);
        hospitalDAO.themBenhVien(hospital5);
        hospitalDAO.themBenhVien(hospital6);
        hospitalDAO.themBenhVien(hospital7);

    }

    private void delete() {
        String benhVien[] = new String[]{"BV01", "BV02", "BV03", "BV04"};
        HospitalDAO hospitalDAO = new HospitalDAO(sqLiteDB);
        for (int i = 0; i < benhVien.length; i++) {
            hospitalDAO.xoaBenhVien(benhVien[i]);
        }

    }
}