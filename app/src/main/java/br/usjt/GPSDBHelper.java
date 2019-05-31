package br.usjt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GPSDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "chamados.db";
    private static final int DB_VERSION = 1;
    SQLiteDatabase db;

    GPSDBHelper (Context context){
        super (context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LocationContract.createTableLocation());

    }

    public void insertLocation(String sql){
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
