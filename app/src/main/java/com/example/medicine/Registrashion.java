package com.example.medicine;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;

import android.net.Uri;
import android.net.ipsec.ike.exceptions.IkeNetworkLostException;
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

    public void AddReadDb(View v) {
        if (login.getText().length() < 5)
            Toast.makeText(this, "В логине должно быть не менее 5 символов", Toast.LENGTH_SHORT).show();
        else if (password.getText().length() < 5)
            Toast.makeText(this, "В пароле должно быть не менее 5 символов", Toast.LENGTH_SHORT).show();
        else {

            String Login = login.getText().toString();
            String Password = password.getText().toString();
            Login.replace(" ", "");
            Password.replace(" ", "");
            ContentValues contentValues = new ContentValues();

            contentValues.put(RegistrConst.KEY_LOGIN, Login);

            contentValues.put(RegistrConst.KEY_PASSWORD, Password);

            Cursor cursor = sqLiteDatabase.query(RegistrConst.SECONDTABLEUSERS, null, null,
                    null, null, null, null);
            int Logind = cursor.getColumnIndex(RegistrConst.KEY_LOGIN);
            int Passind = cursor.getColumnIndex(RegistrConst.KEY_PASSWORD);
            while (cursor.moveToNext()) {
                if (login.getText().toString().equals(cursor.getString(Logind))) {
                    Toast.makeText(this, "Такой логин уже существует", Toast.LENGTH_SHORT).show();
                    return;
                } else if (password.getText().toString().equals(cursor.getString(Passind))) {
                    Toast.makeText(this, "Такой пароль уже существует", Toast.LENGTH_SHORT).show();
                    return;
                }


            }
            sqLiteDatabase.insert(RegistrConst.SECONDTABLEUSERS, null, contentValues);
            Intent intent = new Intent(this, SingIn.class);
            startActivity(intent);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String pat = data.getData().getPath();
        System.out.println(pat);

    }

    public void Check(View view) {


        List<String> lol = new ArrayList<>();
        boolean log = false;

        String Login = login.getText().toString();
        String Password = password.getText().toString();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + RegistrConst.SECONDTABLEUSERS + " WHERE " + RegistrConst.KEY_ID, null);

        int nameindex = cursor.getColumnIndex(RegistrConst.KEY_LOGIN);
        int passindex = cursor.getColumnIndex(RegistrConst.KEY_PASSWORD);
        int idindex = cursor.getColumnIndex(RegistrConst.KEY_ID);

        while (cursor.moveToNext()) {
            if (Login.equals(cursor.getString(nameindex)) && Password.equals(cursor.getString(passindex))) {
                check.setBackgroundColor(000000);


            }

            lol.add(cursor.getString(nameindex));
            lol.add(cursor.getString(passindex));
            for (String item : lol) {
                System.out.println(item);
            }
        }
        cursor.close();
    }
}