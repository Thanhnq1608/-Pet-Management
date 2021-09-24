package com.example.petprojects.TinTucThuCung;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.petprojects.CaiDat.SettingActivity;
import com.example.petprojects.DichVuThuCung.HospitalActivity;
import com.example.petprojects.DichVuThuCung.PetStoreActivity;
import com.example.petprojects.ModelThuCung.Parse;
import com.example.petprojects.TrangChu.HomeActivity;
import com.google.android.material.navigation.NavigationView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import vn.poly.sotaythucung.R;

public class PetNewsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recyclerViewTinTuc;
    ParseAdapter parseAdapter;
    List<Parse> parseList;
    //    ProgressBar progressBar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_news);
        recyclerViewTinTuc = findViewById(R.id.recTinTuc);
        Menu();

//        progressBar = findViewById(R.id.progressBar);
        parseList = new ArrayList<>();
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerViewTinTuc.setHasFixedSize(true);
        recyclerViewTinTuc.setLayoutManager(new GridLayoutManager(this, 2));
        parseAdapter = new ParseAdapter(parseList, this);
        recyclerViewTinTuc.setAdapter(parseAdapter);
        Content content = new Content();
        content.execute();

    }

    private void Menu() {
        toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("Tin Tức Thú Cưng");
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerTinTuc);
        navigationView = findViewById(R.id.navigation_view);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    public class Content extends AsyncTask<Void, Void, Void> {


        //        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            progressBar.setVisibility(View.VISIBLE);
//            progressBar.startAnimation(AnimationUtils.loadAnimation(TinTucThuCungActivity.this, android.R.anim.fade_in));
//
//        }
//
        @Override
        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//            progressBar.setVisibility(View.GONE);
//            progressBar.startAnimation(AnimationUtils.loadAnimation(TinTucThuCungActivity.this, android.R.anim.fade_out));
            parseAdapter.notifyDataSetChanged();
        }

//        @Override
//        protected void onCancelled() {
//            super.onCancelled();
//        }

        @Override
        protected Void doInBackground(Void... voids) {

                try {
                    String url = "https://petshopsaigon.vn/nhom-tin/blog-thu-cung";
                    Document document = Jsoup.connect(url).get();
                    Elements data = document.select("div.noi_dung");
                    for (int i = 0; i < 5; i++) {
                        String title = data.select("h3").select("a").eq(i).text();
                        String img = data.select("div.img").select("img").eq(i).attr("src");
                        if (!img.isEmpty()) {
                            parseList.add(new Parse(img, title));
                            Log.d("items", " item: " + img + " Title: " + title);
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return null;
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home_pet) {
            startActivity(new Intent(PetNewsActivity.this, HomeActivity.class));
        } else if (item.getItemId() == R.id.news_pet) {
            startActivity(new Intent(PetNewsActivity.this, PetNewsActivity.class));
        } else if (item.getItemId() == R.id.hospital_pet) {
            startActivity(new Intent(PetNewsActivity.this, HospitalActivity.class));
        } else if (item.getItemId() == R.id.shop_pet) {
            startActivity(new Intent(PetNewsActivity.this, PetStoreActivity.class));
        } else if (item.getItemId() == R.id.setup) {
            startActivity(new Intent(PetNewsActivity.this, SettingActivity.class));
        } else if (item.getItemId() == R.id.facebook) {
            Toast.makeText(this, "Chưa cập nhật thông tin", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.twitter) {
            Toast.makeText(this, "Chưa cập nhật thông tin", Toast.LENGTH_SHORT).show();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
private void internetCheck(){

}
}
