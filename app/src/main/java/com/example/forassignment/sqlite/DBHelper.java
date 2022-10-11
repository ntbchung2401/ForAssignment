package com.example.forassignment.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME="ASM";
    public static final int DB_VERSION = 1;

    public DBHelper( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql= "CREATE TABLE trip (id text primary key, name text not null, destination text not null, "+
                "dot date not null, assesments boolean not null, description text)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS trip";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }
}
