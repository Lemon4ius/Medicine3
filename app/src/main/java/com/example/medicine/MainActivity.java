package com.example.medicine;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.service.controls.Control;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //View
    EditText name;
    EditText price;
    EditText recept;
    EditText descreption;

    Button add;
    Button read;
    Button clear;
    Button addimage;


    //Database
    sqlite Sqlite;
    SQLiteDatabase database;


    private static final int RQS_OPEN_IMAGE = 1;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.editTextTextPersonName);
        price = (EditText) findViewById(R.id.price);
        recept = (EditText) findViewById(R.id.recept);

        descreption = (EditText) findViewById(R.id.descr);


        add = (Button) findViewById(R.id.buttonadd);
        add.setOnClickListener(this);


        clear = (Button) findViewById(R.id.Clear);
        clear.setOnClickListener(this);

        addimage = (Button) findViewById(R.id.addimage);


        Sqlite = new sqlite(this);


        Button next = (Button) findViewById(R.id.activity_main_next);
        View.OnClickListener onBtnGoToSexAct = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        };

        next.setOnClickListener(onBtnGoToSexAct);
    }

    @Override
    public void onClick(View v) {

        if (name.getText().length() == 0 || price.getText().length() == 0 || recept.getText().length() == 0 || descreption.getText().length() == 0 || uri == null && uri.equals(Uri.EMPTY)) {
            Toast.makeText(this, "Заполните все данные", Toast.LENGTH_SHORT).show();
        } else {
            String Name = name.getText().toString();
            String Price = price.getText().toString();
            String Recept = recept.getText().toString();
            String Descript = descreption.getText().toString();
            database = Sqlite.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            switch (v.getId()) {

                case R.id.buttonadd:

                    contentValues.put(sqlite.KEY_NAME, Name);
                    contentValues.put(sqlite.KEY_PRICE, Price);
                    contentValues.put(sqlite.KEY_IMAGE, uri.toString());
                    contentValues.put(sqlite.KEY_RECEPT, Recept);
                    contentValues.put(sqlite.KEY_DISCR, Descript);
                    database.insert(sqlite.TABLE_NAME, null, contentValues);
                    break;

                case R.id.Clear:
                    database.delete(sqlite.TABLE_NAME, null, null);
                    break;
            }
            Sqlite.close();
        }
    }

    Uri uri = null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == RQS_OPEN_IMAGE) {
                uri = data.getData();
            }
        }
    }

    public void AddImage(View view) {
        try {

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startActivityForResult(intent, RQS_OPEN_IMAGE);

        } catch (SQLException s) {
            Toast.makeText(this, "Выберите изображение", Toast.LENGTH_SHORT).show();
        }

    }
}
