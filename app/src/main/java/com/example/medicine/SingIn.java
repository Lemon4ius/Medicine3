package com.example.medicine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
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

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);
        Object_Sqlite = new sqlite(this);

        reglog =  findViewById(R.id.Reg_Login);
        regpass = findViewById(R.id.Reg_Password);
        input = findViewById(R.id.singin);

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
//        Cursor cursor = liteDatabase.rawQuery("SELECT * FROM " + RegistrConst.SECONDTABLEUSERS+ " WHERE "+RegistrConst.KEY_ID,null);

        Cursor cursor = liteDatabase.query(RegistrConst.SECONDTABLEUSERS, null,null,
                null,null,null,null);


        int nameindex = cursor.getColumnIndex(RegistrConst.KEY_LOGIN);
        int passindex = cursor.getColumnIndex(RegistrConst.KEY_PASSWORD);

        while (cursor.moveToNext()){
            if (Login.equals(cursor.getString(nameindex)) && Password.equals(cursor.getString(passindex))){

                Intent intent = new Intent(SingIn.this,MainActivity2.class);
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