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

import com.example.petprojects.Adapter.StoreAdapter;
import com.example.petprojects.CaiDat.SettingActivity;
import com.example.petprojects.ModelThuCung.Store;
import com.example.petprojects.TinTucThuCung.PetNewsActivity;
import com.example.petprojects.TrangChu.HomeActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import vn.poly.sotaythucung.R;

public class PetStoreActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    RecyclerView recCuaHangThuCung;
    StoreAdapter storeAdapter;
    List<Store> storeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_store);
        recCuaHangThuCung = findViewById(R.id.recCuaHangThuCung);
        storeList = new ArrayList<>();
        addCuaHang();
        storeAdapter = new StoreAdapter(storeList, this);
        recCuaHangThuCung.setLayoutManager(new GridLayoutManager(this, 2));
        recCuaHangThuCung.setAdapter(storeAdapter);
        Menu();
    }

    private void Menu() {
        toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("Cửa Hàng Thú Cưng");
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawerTC);
        navigationView = findViewById(R.id.navigation_view);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home_pet) {
            startActivity(new Intent(PetStoreActivity.this, HomeActivity.class));
        } else if (item.getItemId() == R.id.news_pet) {
            startActivity(new Intent(PetStoreActivity.this, PetNewsActivity.class));
        } else if (item.getItemId() == R.id.hospital_pet) {
            startActivity(new Intent(PetStoreActivity.this, HospitalActivity.class));
        } else if (item.getItemId() == R.id.shop_pet) {
            startActivity(new Intent(PetStoreActivity.this, PetStoreActivity.class));
        } else if (item.getItemId() == R.id.setup) {
            startActivity(new Intent(PetStoreActivity.this, SettingActivity.class));
        }else if (item.getItemId() == R.id.facebook) {
            Toast.makeText(this, "Chưa cập nhật thông tin", Toast.LENGTH_SHORT).show();
        }else if (item.getItemId() == R.id.twitter) {
            Toast.makeText(this, "Chưa cập nhật thông tin", Toast.LENGTH_SHORT).show();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void addCuaHang() {
        storeList.add(new Store("CH01", "Cửa Hàng Thú Cưng", "Bán Các loai Động Vật", "Hà Nội", R.drawable.image_store));
        storeList.add(new Store("CH01", "Cửa Hàng Thú Cưng", "Bán Các loai Động Vật", "Hà Nội", R.drawable.image_store));
        storeList.add(new Store("CH01", "Cửa Hàng Thú Cưng", "Bán Các loai Động Vật", "Hà Nội", R.drawable.image_store));
    }
}
