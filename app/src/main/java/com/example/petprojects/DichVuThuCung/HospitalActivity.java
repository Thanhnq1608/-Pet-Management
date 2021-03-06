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
        toolbar.setTitle("B???nh Vi???n Th?? C??ng");
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
//        BenhVien benhVien = new BenhVien("BV01", "B???nh Vi???n Th?? C??ng", "S??? 3 Nguy???n Tr???i", R.drawable.hospital_item))
//        ;
////        benhVienList.add(new BenhVien("BV01", "Th?? C??ng H?? N???i", "S??? 3 Nguy???n Tr???i", R.drawable.hospital_item));
////        benhVienList.add(new BenhVien("BV01", "B???nh Vi???n Lan Anh", "S??? 3 Nguy???n Tr???i", R.drawable.hospital_item));
////        benhVienList.add(new BenhVien("BV01", "Pets Hospital", "S??? 3 Nguy???n Tr???i", R.drawable.hospital_item));
////        benhVienList.add(new BenhVien("BV01", "Animal Viet Nam", "S??? 3 Nguy???n Tr???i", R.drawable.hospital_item));
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
            Toast.makeText(this, "Ch??a c???p nh???t th??ng tin", Toast.LENGTH_SHORT).show();
        }else if (item.getItemId() == R.id.twitter) {
            Toast.makeText(this, "Ch??a c???p nh???t th??ng tin", Toast.LENGTH_SHORT).show();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void ThemBenhVien() {
        Hospital hospital = new Hospital("BV01", "B???nh Vi???n Ba Lan", "H?? N???i", R.drawable.hospital_item);
        Hospital hospital2 = new Hospital("BV02", "B???nh Vi???n Nam T??? Li??m", "TP. H??? Ch?? Minh", R.drawable.hospital_item);
        Hospital hospital3 = new Hospital("BV03", "B???nh Vi???n Ba Lan", "H?? N???i", R.drawable.hospital_item);
        Hospital hospital4 = new Hospital("BV04", "B???nh Vi???n Nam T??? Li??m", "H?? N???i", R.drawable.hospital_item);
        Hospital hospital5 = new Hospital("BV05", "B???nh Vi???n Ba Lan", "TP. H??? Ch?? Minh", R.drawable.hospital_item);
        Hospital hospital6 = new Hospital("BV06", "B???nh Vi???n Ba Lan", "H?? N???i", R.drawable.hospital_item);
        Hospital hospital7 = new Hospital("BV07", "B???nh Vi???n Nam T??? Li??m", "TP. H??? Ch?? Minh", R.drawable.hospital_item);


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