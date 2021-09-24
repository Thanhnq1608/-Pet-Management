package vn.poly.sotaythucung.petservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


import vn.poly.sotaythucung.adapter.StoreAdapter;
import vn.poly.sotaythucung.setting.SettingActivity;
import vn.poly.sotaythucung.umtility.Exit;
import vn.poly.sotaythucung.model.Store;
import vn.poly.sotaythucung.R;
import vn.poly.sotaythucung.petsnews.PetNewsActivity;
import vn.poly.sotaythucung.home.HomeActivity;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

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
        recCuaHangThuCung.setLayoutManager(new LinearLayoutManager(this));
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
        } else if (item.getItemId() == R.id.facebook) {

            Toast.makeText(this, "Chưa cập nhật thông tin", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.twitter) {
//            Intent browerIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://facebook.com/quangvucot"));
//            startActivity(browerIntent);
//            OpenSocial openSocial = new OpenSocial();
//            openSocial.getFacebookPageURL(this);
        } else if (item.getItemId() == R.id.exit) {
            Exit exit = new Exit();
            exit.Exit(PetStoreActivity.this);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void addCuaHang() {
        storeList.add(new Store("CH01", "Cửa Hàng Thú Cưng", "Bán Các loai Động Vật", "Hà Nội", R.drawable.image_store,21.03384013819058, 105.81026261195504));
        storeList.add(new Store("CH02", "Cửa Hàng Thú Cưng", "Bán Các loai Động Vật", "Hà Nội", R.drawable.image_store,21.03384013819058, 105.81026261195504));

    }
}
