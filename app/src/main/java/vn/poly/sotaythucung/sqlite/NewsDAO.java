package vn.poly.sotaythucung.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vn.poly.sotaythucung.model.News;

public class NewsDAO {
    private SQLiteDB sqLiteDB;

    public NewsDAO(SQLiteDB sqLiteDB) {
        this.sqLiteDB = sqLiteDB;
    }

    public void addNews(News news) {
        SQLiteDatabase sqLiteDatabase = sqLiteDB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tinTucID", news.getIdNews());
        contentValues.put("tenTinTuc", news.getTitleNews());
        contentValues.put("anhTinTuc", news.getImgHeaderNews());
        contentValues.put("urlNews", news.getUrlNews());
        sqLiteDatabase.insert("tinTuc", null, contentValues);
    }

    public List<News> getAllNews() {
        List<News> newsList = new ArrayList<>();
        String query_getAll = "SELECT * FROM tinTuc";
        Cursor cursor = sqLiteDB.getWritableDatabase().rawQuery(query_getAll, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String tinTucID = cursor.getString(0);
                String tenTinTuc = cursor.getString(1);
                String anhTinTuc = cursor.getString(2);
                String urlNews = cursor.getString(3);
                News news = new News();
                news.setIdNews(tinTucID);
                news.setTitleNews(tenTinTuc);
                news.setImgHeaderNews(anhTinTuc);
                news.setUrlNews(urlNews);
                newsList.add(news);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return newsList;
    }

    public void deleteNews(String tinTucID) {
        SQLiteDatabase sqLiteDatabase = sqLiteDB.getWritableDatabase();
        sqLiteDatabase.delete("tinTuc", "tinTucID=?", new String[]{tinTucID});
    }
}
