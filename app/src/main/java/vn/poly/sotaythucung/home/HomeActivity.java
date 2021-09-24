package vn.poly.sotaythucung.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import vn.poly.sotaythucung.adapter.HomeAdapter;
import vn.poly.sotaythucung.setting.SettingActivity;
import vn.poly.sotaythucung.umtility.Exit;
import vn.poly.sotaythucung.petservice.HospitalActivity;
import vn.poly.sotaythucung.petservice.PetStoreActivity;
import vn.poly.sotaythucung.model.Pet;
import vn.poly.sotaythucung.R;
import vn.poly.sotaythucung.umtility.SearchFragment;
import vn.poly.sotaythucung.petsnews.PetNewsActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    RecyclerView recTrangChu;
    List<Pet> petList;
    HomeAdapter homeAdapter;
    SearchFragment searchFragment = new SearchFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        anhXa();
        Menu();
        petList = new ArrayList<>();
        addThuCung();
        homeAdapter = new HomeAdapter(this, petList);
        recTrangChu.setLayoutManager(new GridLayoutManager(this, 4));
        recTrangChu.setAdapter(homeAdapter);
    }

    private void anhXa() {
        recTrangChu = findViewById(R.id.recTrangChu);
    }

    private void Menu() {
        toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("Trang Chủ");
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
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
            startActivity(new Intent(HomeActivity.this, HomeActivity.class));
        } else if (item.getItemId() == R.id.news_pet) {
            startActivity(new Intent(HomeActivity.this, PetNewsActivity.class));
        } else if (item.getItemId() == R.id.hospital_pet) {
            startActivity(new Intent(HomeActivity.this, HospitalActivity.class));
        } else if (item.getItemId() == R.id.shop_pet) {
            startActivity(new Intent(HomeActivity.this, PetStoreActivity.class));
        } else if (item.getItemId() == R.id.setup) {
            startActivity(new Intent(HomeActivity.this, SettingActivity.class));
        } else if (item.getItemId() == R.id.exit) {
            System.exit(1);
        } else if (item.getItemId() == R.id.facebook) {
            Toast.makeText(this, "Chưa cập nhật thông tin", Toast.LENGTH_SHORT).show();
        }else if (item.getItemId() == R.id.twitter) {
            Toast.makeText(this, "Chưa cập nhật thông tin", Toast.LENGTH_SHORT).show();
        }else if (item.getItemId() == R.id.exit) {
            Exit exit = new Exit();
            exit.Exit(HomeActivity.this);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void addThuCung() {
        petList.add(new Pet("TC01", "Chó", "Động Vật", "Canh Nhà", "Không", R.drawable.dog));
        petList.add(new Pet("TC01", "Mèo", "Động Vật", "Canh Nhà", "Không", R.drawable.catt));
        petList.add(new Pet("TC01", "Bò", "Động Vật", "Canh Nhà", "Không", R.drawable.cow));
        petList.add(new Pet("TC01", "Cừu", "Động Vật", "Canh Nhà", "Không", R.drawable.sheep));
        petList.add(new Pet("TC01", "Chó", "Động Vật", "Canh Nhà", "Không", R.drawable.dog));
        petList.add(new Pet("TC01", "Mèo", "Động Vật", "Canh Nhà", "Không", R.drawable.catt));
        petList.add(new Pet("TC01", "Bò", "Động Vật", "Canh Nhà", "Không", R.drawable.cow));
        petList.add(new Pet("TC01", "Cừu", "Động Vật", "Canh Nhà", "Không", R.drawable.sheep));
        petList.add(new Pet("TC01", "Chó", "Động Vật", "Canh Nhà", "Không", R.drawable.dog));
        petList.add(new Pet("TC01", "Mèo", "Động Vật", "Canh Nhà", "Không", R.drawable.catt));
        petList.add(new Pet("TC01", "Bò", "Động Vật", "Canh Nhà", "Không", R.drawable.cow));
        petList.add(new Pet("TC01", "Cừu", "Động Vật", "Canh Nhà", "Không", R.drawable.sheep));
        petList.add(new Pet("TC01", "Chó", "Động Vật", "Canh Nhà", "Không", R.drawable.dog));
        petList.add(new Pet("TC01", "Mèo", "Động Vật", "Canh Nhà", "Không", R.drawable.catt));
        petList.add(new Pet("TC01", "Bò", "Động Vật", "Canh Nhà", "Không", R.drawable.cow));
        petList.add(new Pet("TC01", "Cừu", "Động Vật", "Canh Nhà", "Không", R.drawable.sheep));
        petList.add(new Pet("TC01", "Chó", "Động Vật", "Canh Nhà", "Không", R.drawable.dog));
        petList.add(new Pet("TC01", "Mèo", "Động Vật", "Canh Nhà", "Không", R.drawable.catt));
        petList.add(new Pet("TC01", "Bò", "Động Vật", "Canh Nhà", "Không", R.drawable.cow));
        petList.add(new Pet("TC01", "Cừu", "Động Vật", "Canh Nhà", "Không", R.drawable.sheep));
        petList.add(new Pet("TC01", "Chó", "Động Vật", "Canh Nhà", "Không", R.drawable.dog));
        petList.add(new Pet("TC01", "Mèo", "Động Vật", "Canh Nhà", "Không", R.drawable.catt));
        petList.add(new Pet("TC01", "Bò", "Động Vật", "Canh Nhà", "Không", R.drawable.cow));
        petList.add(new Pet("TC01", "Cừu", "Động Vật", "Canh Nhà", "Không", R.drawable.sheep));
        petList.add(new Pet("TC01", "Chó", "Động Vật", "Canh Nhà", "Không", R.drawable.dog));
        petList.add(new Pet("TC01", "Mèo", "Động Vật", "Canh Nhà", "Không", R.drawable.catt));
        petList.add(new Pet("TC01", "Bò", "Động Vật", "Canh Nhà", "Không", R.drawable.cow));
        petList.add(new Pet("TC01", "Cừu", "Động Vật", "Canh Nhà", "Không", R.drawable.sheep));
        petList.add(new Pet("TC01", "Chó", "Động Vật", "Canh Nhà", "Không", R.drawable.dog));
        petList.add(new Pet("TC01", "Mèo", "Động Vật", "Canh Nhà", "Không", R.drawable.catt));
        petList.add(new Pet("TC01", "Bò", "Động Vật", "Canh Nhà", "Không", R.drawable.cow));
        petList.add(new Pet("TC01", "Cừu", "Động Vật", "Canh Nhà", "Không", R.drawable.sheep));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
//        getSupportActionBar().setLogo(R.drawable.ic_search_toolbar);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        boolean temp = true;
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (item.getItemId() == R.id.btn_search_toolbar && temp==true) {
            transaction.add(R.id.frame_layout_frag_search_trangchu, searchFragment);
            transaction.commit();
            temp=false;
        }else if (item.getItemId() == R.id.btn_search_toolbar && temp==false){
            transaction.remove(searchFragment);
            transaction.commit();
            temp=true;
        }
        return true;
    }
}