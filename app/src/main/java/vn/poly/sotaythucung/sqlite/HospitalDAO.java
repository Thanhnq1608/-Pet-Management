package vn.poly.sotaythucung.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import vn.poly.sotaythucung.model.Hospital;

import java.util.ArrayList;
import java.util.List;

public class HospitalDAO {
    private SQLiteDB sqLiteDB;

    public HospitalDAO(SQLiteDB sqLiteDB) {
        this.sqLiteDB = sqLiteDB;
    }



    public void xoaBenhVien(String id) {
        SQLiteDatabase sqLiteDatabase = sqLiteDB.getWritableDatabase();
        sqLiteDatabase.delete("benhVien", "benhVienID=?", new String[]{id});
    }

    public void themBenhVien(Hospital hospital) {
        SQLiteDatabase sqLiteDatabase = sqLiteDB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("benhVienID", hospital.getIdBenhVien());
        contentValues.put("tenBenhVien", hospital.getTenBenhVien());
        contentValues.put("anhBenhVien", hospital.getResouceImages());
        contentValues.put("diaChiBenhVien", hospital.getDiaChiBenhVien());
        contentValues.put("kinhDoBenhVien", hospital.getKinhDo());
        contentValues.put("viDoBenhVien", hospital.getViDo());
        contentValues.put("danhGiaBenhVien", hospital.getDanhGiaBenhVien());
        sqLiteDatabase.insert("benhVien", null, contentValues);

    }

    public List<Hospital> getAllBenhVien() {
        List<Hospital> hospitalList = new ArrayList<>();
        String truyVan = "SELECT * FROM benhVien";
        Cursor cursor = sqLiteDB.getWritableDatabase().rawQuery(truyVan, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String tenBenhVien = cursor.getString(1);
                int anhBenhVien = cursor.getInt(2);
                String diaChiBenhVien = cursor.getString(3);

                Hospital hospital = new Hospital();
                hospital.setTenBenhVien(tenBenhVien);
                hospital.setDiaChiBenhVien(diaChiBenhVien);
                hospital.setResouceImages(anhBenhVien);
                hospitalList.add(hospital);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return hospitalList;
    }

    public List<Hospital>getMap() {
        List<Hospital> hospitalList = new ArrayList<>();
        String truyVan = "SELECT * FROM benhVien";
        Cursor cursor = sqLiteDB.getWritableDatabase().rawQuery(truyVan, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String kinhDo = cursor.getString(4);
                String viDo = cursor.getString(5);
                String rate = cursor.getString(6);
                Hospital hospital = new Hospital();
                hospital.setKinhDo(Double.parseDouble(kinhDo));
                hospital.setViDo(Double.parseDouble(viDo));
                hospital.setDanhGiaBenhVien(Integer.parseInt(rate));
                hospitalList.add(hospital);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return hospitalList;
    }
}
