package com.hufs.hw6_2_moviedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyMovies.db";
    public static final String MOVIES_COLUMN_ID = "id";
    public static final String MOVIES_COLUMN_NAME = "name";

    public DBHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE movies " +
                "(id integer PRIMARY KEY,name text, director text, year text, nation text, rating text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS movies");
        onCreate(db);
    }

    public boolean insertMovie(String name, String director, String year, String nation, String rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("director", director);
        contentValues.put("year", year);
        contentValues.put("nation", nation);
        contentValues.put("rating", rating);

        db.insert("movies", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from movies where id=" + id + "",null);
        return res;
    }

    public boolean updateMovie(Integer id, String name, String director, String year, String nation, String rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("director",director);
        contentValues.put("year", year);
        contentValues.put("nation", nation);
        contentValues.put("rating", rating);

        db.update("movies", contentValues, "id = ?", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteMovie(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("movies", "id = ?", new String[]{Integer.toString(id)});
    }

    public ArrayList getAllMovies() {
        ArrayList array_list = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from movies", null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(MOVIES_COLUMN_ID))+ " "+
                    res.getString(res.getColumnIndex(MOVIES_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
}

