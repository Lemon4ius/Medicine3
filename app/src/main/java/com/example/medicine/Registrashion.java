package com.example.medicine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.MessagePattern;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Registrashion extends AppCompatActivity {
    EditText login, password;
    sqlite Object_sqlite;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrashion);

    }
    public void AddReadDb(View v){
        sqLiteDatabase = Object_sqlite.getWritableDatabase();
        String Login = login.getText().toString();
        String Password = password.getText().toString();

        ContentValues contentValues  =new ContentValues();

        contentValues.put(RegistrConst.KEY_LOGIN, Login);
        contentValues.put(RegistrConst.KEY_PASSWORD, Password);
        sqLiteDatabase.insert(RegistrConst.SECONDTABLEUSERS,null,contentValues);
//        Cursor cursor = sqLiteDatabase.query(RegistrConst.SECONDTABLEUSERS,  null,
//                null,null,null,null,null);



    }

}