package com.example.petprojects.ManHinhChao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.petprojects.DichVuThuCung.HospitalActivity;

import vn.poly.sotaythucung.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        getSupportActionBar().setTitle("Sổ Tay Thú Cưng");
        getSupportActionBar().hide();
        WaitScreen();
    }

    private void WaitScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), HospitalActivity.class));
            }
        }, 3000);
    }

}