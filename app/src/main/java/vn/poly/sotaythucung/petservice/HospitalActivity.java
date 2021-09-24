package vn.poly.sotaythucung.petservice;

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

import vn.poly.sotaythucung.setting.SettingActivity;
import vn.poly.sotaythucung.umtility.Exit;
import vn.poly.sotaythucung.model.Hospital;

import vn.poly.sotaythucung.adapter.HospitalAdapter;
import vn.poly.sotaythucung.R;
import vn.poly.sotaythucung.sqlite.HospitalDAO;
import vn.poly.sotaythucung.sqlite.SQLiteDB;
import vn.poly.sotaythucung.petsnews.PetNewsActivity;
import vn.poly.sotaythucung.home.HomeActivity;

import com.google.android.material.navigation.NavigationView;

import java.util.List;


public class HospitalActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recBenhVien;
    HospitalAdapter hospitalAdapter;
    private SQLiteDB sqLiteDB;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    List<Hospital> hospitalList;

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
        recBenhVien.setLayoutManager(new GridLayoutManager(this, 2));
        hospitalList = hospitalDAO.getAllBenhVien();
        hospitalAdapter = new HospitalAdapter(this, hospitalList);
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
        } else if (item.getItemId() == R.id.facebook) {
//            Uri uri = Uri.parse("https://www.facebook.com/quangvucot");
//            startActivity(new Intent(Intent.ACTION_VIEW,uri));
        } else if (item.getItemId() == R.id.twitter) {
            Toast.makeText(this, "Chưa cập nhật thông tin", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.exit) {
            Exit exit = new Exit();
            exit.Exit(HospitalActivity.this);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void ThemBenhVien() {
        Hospital hospital = new Hospital("BV01", "Phòng khám thú y Mỹ Đình", "Mỹ Đình, Nam Từ Liêm, Hà Nội", R.drawable.hospital_item, 4, 21.040240209042555, 105.7667198273969);
        Hospital hospital2 = new Hospital("BV02", "Bệnh Viện Thú Y PetHealth", "Hà Nội", R.drawable.hospital_item, 3, 21.0152059, 105.8232361);
        Hospital hospital3 = new Hospital("BV03", "Bệnh Viện Ba Lan", "Hà Nội", R.drawable.hospital_item, 1, 21.053551700845947, 105.78829908194113);
        Hospital hospital4 = new Hospital("BV04", "Bệnh Viện Xmmm", "Nghệ An", R.drawable.hospital_item, 1, 21.053551700845947, 105.78829908194113);

        HospitalDAO hospitalDAO = new HospitalDAO(sqLiteDB);
        hospitalDAO.themBenhVien(hospital);
        hospitalDAO.themBenhVien(hospital2);
        hospitalDAO.themBenhVien(hospital3);
        hospitalDAO.themBenhVien(hospital4);
//        benhVienDAO.themBenhVien(benhVien5);
//        benhVienDAO.themBenhVien(benhVien6);
//        benhVienDAO.themBenhVien(benhVien7);

    }

    private void delete() {
        String benhVien[] = new String[]{"BV01", "BV02", "BV03"};
        HospitalDAO hospitalDAO = new HospitalDAO(sqLiteDB);
        for (int i = 0; i < benhVien.length; i++) {
            hospitalDAO.xoaBenhVien(benhVien[i]);
        }

    }
}