package com.example.medicine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class SingIn extends AppCompatActivity {

    sqlite Object_Sqlite;
    SQLiteDatabase liteDatabase;
    EditText reglog, regpass;
    Button input;


    private static final String NAME_SHP = "SinginHash";
    private static final String KEY_SHP = "hash";

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);
        Object_Sqlite = new sqlite(this);

        reglog = findViewById(R.id.Reg_Login);
        regpass = findViewById(R.id.Reg_Password);
        input = findViewById(R.id.singin);
        sharedPreferences = getSharedPreferences(NAME_SHP, Context.MODE_PRIVATE);
//        indificator();

    }

    SharedPreferences sharedPreferences ;

    void indificator(){
        if (sharedPreferences.contains(KEY_SHP)) {

            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
        }

    }
    @Override
    protected void onResume() {

        super.onResume();
        liteDatabase = Object_Sqlite.getWritableDatabase();


    }

    public void Input(View view) {
        List<String> lol = new ArrayList<>();


        String Login = reglog.getText().toString();
        String Password = regpass.getText().toString();


        Cursor cursor = liteDatabase.query(RegistrConst.SECONDTABLEUSERS, null, null,
                null, null, null, null);
        ContentValues contentValues = new ContentValues();

        int nameindex = cursor.getColumnIndex(RegistrConst.KEY_LOGIN);
        int passindex = cursor.getColumnIndex(RegistrConst.KEY_PASSWORD);
        sharedPreferences = getSharedPreferences(NAME_SHP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        while (cursor.moveToNext()) {
            if (Login.equals(cursor.getString(nameindex)) && Password.equals(cursor.getString(passindex))) {

                editor.putString(KEY_SHP, String.valueOf(cursor.getString(passindex).hashCode()));
                editor.apply();
                contentValues.put(RegistrConst.KEY_REG, cursor.getString(passindex).hashCode());
                //liteDatabase.update(RegistrConst.SECONDTABLEUSERS, contentValues, RegistrConst.KEY_PASSWORD + " = " + Password, null);
                Intent intent = new Intent(SingIn.this, MainActivity2.class);
                startActivity(intent);

            }


        }
        cursor.close();
    }

    public void Reg(View view) {
        Intent intent = new Intent(SingIn.this, Registrashion.class);
        startActivity(intent);
    }
}