package com.example.medicine;

import androidx.appcompat.app.AppCompatActivity;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText name;
    EditText price;
    EditText recept;
    EditText image;
    EditText descreption;


    Button add;
    Button read;
    Button clear;


    sqlite Sqlite;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.editTextTextPersonName);
        price =(EditText) findViewById(R.id.price);
        recept =(EditText) findViewById(R.id.recept);
        image =(EditText) findViewById(R.id.image);
        descreption =(EditText) findViewById(R.id.descr);


        add=(Button)findViewById(R.id.buttonadd);
        add.setOnClickListener(this);


        read=(Button)findViewById(R.id.buttonread);
        read.setOnClickListener(this);


        clear=(Button)findViewById(R.id.Clear);
        clear.setOnClickListener(this);

        Sqlite=new sqlite(this);


        Button next=(Button) findViewById(R.id.activity_main_next);
        View.OnClickListener onBtnGoToSexAct= new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        };

        next.setOnClickListener(onBtnGoToSexAct);
        }

    @Override
    public void onClick(View v) {

       String Name = name.getText().toString();
       String Price = price.getText().toString() , Recept= recept.getText().toString();
       String Image = image.getText().toString();
       String Descript = descreption.getText().toString();

        SQLiteDatabase database = Sqlite.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        switch (v.getId()){

            case R.id.buttonadd:
                contentValues.put(sqlite.KEY_NAME,Name);
                contentValues.put(sqlite.KEY_PRICE,Price);
                contentValues.put(sqlite.KEY_RECEPT,Recept);
                contentValues.put(sqlite.KEY_IMAGE,Image);
                contentValues.put(sqlite.KEY_DISCR,Descript);

                database.insert(sqlite.TABLE_NAME, null, contentValues);

                break;
            case R.id.buttonread:

                Cursor cursor =database.query(sqlite.TABLE_NAME, null,null,null, null, null, null);
                 if (cursor.moveToFirst()){

                     int idIndex=cursor.getColumnIndex(sqlite.KEY_ID);
                     int nameIndex = cursor.getColumnIndex(sqlite.KEY_NAME);
                     int priceIndex = cursor.getColumnIndex(sqlite.KEY_PRICE);
                     int receptIndex = cursor.getColumnIndex(sqlite.KEY_RECEPT);
                     int imageIndex = cursor.getColumnIndex(sqlite.KEY_IMAGE);
                     int discrIndex = cursor.getColumnIndex(sqlite.KEY_DISCR);
                     do {
                         Log.d("mLog", "ID = " + cursor.getInt(idIndex)
                         + ", name = " + cursor.getString(nameIndex)
                         + ", price = " + cursor.getDouble(priceIndex)
                         + ", recept = " + cursor.getInt(receptIndex)
                         + ", image = " + cursor.getString(imageIndex)
                         +  ", discription = " + cursor.getString(discrIndex));
                     }while (cursor.moveToNext());

                 }else
                     Log.d("mLoh", "0 rows");
                 cursor.close();
                break;
            case R.id.Clear:
                database.delete(sqlite.TABLE_NAME, null,null);
                break;
        }
        Sqlite.close();

    }





}