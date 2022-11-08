package com.example.medicine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class sqlite extends SQLiteOpenHelper {
    private Context context;
    public  static final int DATABASE_VERSION=5;
    public  static final String DATABASE_NAME="general";
    public  static final String TABLE_NAME="item";


    public  static final String KEY_ID="_id";
    public  static final String KEY_NAME="name";
    public  static final String KEY_PRICE="price";
    public  static final String KEY_RECEPT="recept";
    public  static  final String KEY_IMAGE ="image";
    public static final String KEY_DISCR= "discr";

    public sqlite(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+TABLE_NAME+ "("
                + KEY_ID    + " integer primary key autoincrement,"
                + KEY_NAME  + " text not null unique,"
                + KEY_PRICE + " text not null,"
                + KEY_RECEPT+ " integer not null, "
                + KEY_IMAGE + " text,"
                + KEY_DISCR + " text not null"
                + ")");

        db.execSQL("create table "+RegistrConst.SECONDTABLEUSERS+ "("
                + RegistrConst.KEY_ID    + " integer primary key autoincrement,"
                + RegistrConst.KEY_LOGIN  + " text NOT NULL unique,"
                + RegistrConst.KEY_PASSWORD + " text NOT NULL unique,"
                + RegistrConst.KEY_REG + " integer"
                + ")");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        db.execSQL("drop table if exists " + RegistrConst.SECONDTABLEUSERS);
        onCreate(db);
    }
    Cursor readAllData() {
        String qeury = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(qeury, null);
        }
            return cursor;
    }

}
