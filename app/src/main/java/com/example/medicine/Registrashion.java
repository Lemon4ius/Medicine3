package com.example.medicine;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.lang.invoke.ConstantCallSite;
import java.util.ArrayList;
import java.util.List;

public class Registrashion extends AppCompatActivity {
    Button check;
    EditText login, password;
    sqlite Object_sqlite;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrashion);
        check = findViewById(R.id.enter);
        login = findViewById(R.id.Login);
        password = findViewById(R.id.password);


        Object_sqlite = new sqlite(this);
        sqLiteDatabase = Object_sqlite.getWritableDatabase();

    }
    public void AddReadDb(View v){

        String Login = login.getText().toString();
        String Password = password.getText().toString();
        Login.replace(" ", "");
        Password.replace(" ", "");
        ContentValues contentValues  =new ContentValues();

        contentValues.put(RegistrConst.KEY_LOGIN, Login);
        contentValues.put(RegistrConst.KEY_PASSWORD, Password);
        sqLiteDatabase.insert(RegistrConst.SECONDTABLEUSERS,null,contentValues);

        Cursor cursor = sqLiteDatabase.query(RegistrConst.SECONDTABLEUSERS,  null,
                null,null,null,null,null);
        int nameindex = cursor.getColumnIndex(RegistrConst.KEY_LOGIN);
        int passindex = cursor.getColumnIndex(RegistrConst.KEY_PASSWORD);
        int idindex =  cursor.getColumnIndex(RegistrConst.KEY_ID);
        while (cursor.moveToNext()){

            Log.d("tag ", "id:"+cursor.getString(idindex)
                    +" Login - "+ cursor.getString(nameindex)
                    + "Password -"  +  cursor.getString(passindex));

        }
        cursor.close();



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String pat = data.getData().getPath();
        System.out.println(pat);

    }

    public void Check(View view) {


        List<String> lol = new ArrayList<>();
        boolean log =false;

        String Login = login.getText().toString();
        String Password = password.getText().toString();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + RegistrConst.SECONDTABLEUSERS+ " WHERE "+RegistrConst.KEY_ID,null);

        int nameindex = cursor.getColumnIndex(RegistrConst.KEY_LOGIN);
        int passindex = cursor.getColumnIndex(RegistrConst.KEY_PASSWORD);
        int idindex =  cursor.getColumnIndex(RegistrConst.KEY_ID);

        while (cursor.moveToNext()){
            if (Login.equals(cursor.getString(nameindex)) && Password.equals(cursor.getString(passindex))){
                check.setBackgroundColor(000000);


            }

            lol.add(cursor.getString(nameindex));
            lol.add(cursor.getString(passindex));
            for (String item : lol ) {
                System.out.println(item);
            }
        }
        cursor.close();
    }
}