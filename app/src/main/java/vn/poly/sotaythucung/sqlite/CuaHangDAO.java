package vn.poly.sotaythucung.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vn.poly.sotaythucung.model.Store;

class CuaHangDAO {
    private SQLiteDB sqLiteDB;

    public CuaHangDAO(SQLiteDB sqLiteDB) {
        this.sqLiteDB = sqLiteDB;
    }

    public void addStore(Store store) {
        SQLiteDatabase sqLiteDatabase = sqLiteDB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("cuaHangID", store.getIdCuaHang());
        contentValues.put("tenCuaHang", store.getTenCuaHang());
        contentValues.put("dichVuCuaHang", store.getDichVuCuaHang());
        contentValues.put("diaDiemCuaHang", store.getDiaChiCuaHang());
        contentValues.put("kinhDoCuaHang", store.getKinhDoCuaHang());
        contentValues.put("vidoCuaHang", store.getViDoCuaHang());
        sqLiteDatabase.insert("cuaHang", null, contentValues);
    }

    public List<Store> getAllStore() {
        List<Store> storeList = new ArrayList<>();
        String query_getAllCuaHang = "SELECT * FROM cuaHang";
        Cursor cursor = sqLiteDB.getWritableDatabase().rawQuery(query_getAllCuaHang, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String tenCuaHang = cursor.getString(1);
                String dichVuCuaHang = cursor.getString(2);
                String diaDiemCuaHang = cursor.getString(3);
                String kinhDoCuaHang = cursor.getString(4);
                String viDoCuaHang = cursor.getString(5);
                String anhCuaHang = cursor.getString(6);
                Store store = new Store();
                store.setTenCuaHang(tenCuaHang);
                store.setDiaChiCuaHang(diaDiemCuaHang);
                store.setDichVuCuaHang(dichVuCuaHang);
                store.setKinhDoCuaHang(Double.parseDouble(kinhDoCuaHang));
                store.setViDoCuaHang(Double.parseDouble(viDoCuaHang));
                store.setAnhCuaHang(Integer.parseInt(anhCuaHang));
                storeList.add(store);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return storeList;
    }

    public void xoaCuaHang(String cuaHangID) {
        SQLiteDatabase sqLiteDatabase = sqLiteDB.getWritableDatabase();
        sqLiteDatabase.delete("cuaHang", "cuaHangID=?", new String[]{cuaHangID});
    }
}
