package vn.poly.sotaythucung.petsnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import vn.poly.sotaythucung.model.News;
import vn.poly.sotaythucung.setting.SettingActivity;
import vn.poly.sotaythucung.sqlite.SQLiteDB;
import vn.poly.sotaythucung.sqlite.NewsDAO;
import vn.poly.sotaythucung.umtility.Exit;
import vn.poly.sotaythucung.petservice.HospitalActivity;
import vn.poly.sotaythucung.petservice.PetStoreActivity;
import vn.poly.sotaythucung.R;
import vn.poly.sotaythucung.home.HomeActivity;

import com.google.android.material.navigation.NavigationView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PetNewsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recyclerViewTinTuc;
    NewsAdapter newsAdapter;
    List<News> newsList;
    ProgressBar progressBar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    TextView checkInternet;
    private SQLiteDB sqLiteDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_news);
        sqLiteDB = new SQLiteDB(this);
        NewsDAO newsDAO = new NewsDAO(sqLiteDB);
        recyclerViewTinTuc = findViewById(R.id.recTinTuc);
        checkInternet = findViewById(R.id.checkInternet);
        Menu();
        progressBar = findViewById(R.id.progressBar);
        newsList = new ArrayList<>();
        recyclerViewTinTuc.setHasFixedSize(true);
        recyclerViewTinTuc.setLayoutManager(new GridLayoutManager(this, 2));
        Content content = new Content();
        content.execute();
//        delete();
        newsList = newsDAO.getAllNews();
        newsAdapter = new NewsAdapter(newsList, this);
        recyclerViewTinTuc.setAdapter(newsAdapter);
    }

    private void Menu() {
        toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("Tin T???c Th?? C??ng");
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


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            newsAdapter.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                try {
                    String url = "https://sotaythucung.blogspot.com/search/label/Th%C3%BA%20c%C6%B0ng";
                    Document document = Jsoup.connect(url).get();
                    Elements data = document.select("div.item-content");
                    for (int i = 0; i < data.size(); i++) {
                        String idNews = "new0" + i;
                        String img = data.select("div.item-thumbnail").select("img").eq(i).attr("src");
                        String title = data.select("div.item-title").select("a").eq(i).text();
                        String urlPage = data.select("div.item-title").select("a").eq(i).attr("href");
                        Log.d("items", " item: " + img + " Title: " + title + "urlPage: " + urlPage);
                        if (!img.isEmpty()) {
                            NewsDAO newsDAO = new NewsDAO(sqLiteDB);
                            newsDAO.addNews(new News(title, "news0" + i, img, urlPage));
                            Log.d("items", " item: " + img + " Title: " + idNews + "urlPage: " + urlPage);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                checkInternet.setText("Kh??ng c?? k???t n???i Internet");
            }
            return null;
        }
    }

    private void delete() {
        String benhVien[] = new String[]{"news01", "news00"};
        NewsDAO benhVienDAO = new NewsDAO(sqLiteDB);
        for (int i = 0; i < benhVien.length; i++) {
            benhVienDAO.deleteNews(benhVien[i]);
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
            Toast.makeText(this, "Ch??a c???p nh???t th??ng tin", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.twitter) {
            Toast.makeText(this, "Ch??a c???p nh???t th??ng tin", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.exit) {
            Exit exit = new Exit();
            exit.Exit(PetNewsActivity.this);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.refresh_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh_page:
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
        }
        return super.onOptionsItemSelected(item);
    }

}
