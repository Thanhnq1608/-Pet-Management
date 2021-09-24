package vn.poly.sotaythucung.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vn.poly.sotaythucung.model.Pet;

public class PetDAO {
    private SQLiteDB sqLiteDB;

    public PetDAO(SQLiteDB sqLiteDB) {
        this.sqLiteDB = sqLiteDB;
    }

    public void addPets(Pet pet) {
        SQLiteDatabase sqLiteDatabase = sqLiteDB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("thuCungID", pet.getIdThuCung());
        contentValues.put("tenThucung", pet.getTenThuCung());
        contentValues.put("dacDiemThuCung", pet.getDacDiemThuCung());
        contentValues.put("theLoaiThuCung", pet.getLoaiThuCung());
        contentValues.put("anhThuCung", pet.getImageThuCung());
        contentValues.put("moTaThuCung", pet.getKhac());
        sqLiteDatabase.insert("thuCung", null, contentValues);
    }

    public List<Pet> getAllPets() {
        List<Pet> petList = new ArrayList<>();
        String query_getAll = "SELECT * FROM thuCung";
        Cursor cursor = sqLiteDB.getWritableDatabase().rawQuery(query_getAll, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String tenThucung = cursor.getString(1);
                String dacDiemThuCung = cursor.getString(3);
                String theLoaiThuCung = cursor.getString(4);
                String anhThuCung = cursor.getString(5);
                String moTaThuCung = cursor.getString(6);
                Pet pet = new Pet();
                pet.setTenThuCung(tenThucung);
                pet.setDacDiemThuCung(dacDiemThuCung);
                pet.setLoaiThuCung(theLoaiThuCung);
                pet.setImageThuCung(Integer.parseInt(anhThuCung));
                pet.setKhac(moTaThuCung);
                petList.add(pet);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return petList;
    }
    public void deletepets(String idThuCung){
        SQLiteDatabase sqLiteDatabase = sqLiteDB.getWritableDatabase();
        sqLiteDatabase.delete("thuCung", "thuCungID=?", new String[]{idThuCung});
    }
}
