package com.example.medicine;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.service.controls.Control;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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

    ImageView image;

//.................................................................................................

    //Database
    sqlite Sqlite;
    SQLiteDatabase database;



    private static final int RQS_OPEN_IMAGE = 1;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.editTextTextPersonName);
        price =(EditText) findViewById(R.id.price);
        recept =(EditText) findViewById(R.id.recept);

        descreption =(EditText) findViewById(R.id.descr);


        add=(Button)findViewById(R.id.buttonadd);
        add.setOnClickListener(this);


        read=(Button)findViewById(R.id.buttonread);
        read.setOnClickListener(this);


        clear=(Button)findViewById(R.id.Clear);
        clear.setOnClickListener(this);

        addimage= (Button) findViewById(R.id.addimage);

        image = (ImageView) findViewById(R.id.image_1);

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
       String Price = price.getText().toString();
       String Recept= recept.getText().toString();
       String Descript = descreption.getText().toString();

       database = Sqlite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        switch (v.getId()){

            case R.id.buttonadd:

                contentValues.put(sqlite.KEY_NAME,Name);
                contentValues.put(sqlite.KEY_PRICE,Price);
                contentValues.put(sqlite.KEY_IMAGE,path);
                contentValues.put(sqlite.KEY_RECEPT,Recept);
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
                     int discrIndex = cursor.getColumnIndex(sqlite.KEY_DISCR);
                     do {
                         Log.d("mLog", "ID = " + cursor.getInt(idIndex)
                         + ", name = " + cursor.getString(nameIndex)
                         + ", price = " + cursor.getDouble(priceIndex)
                         + ", recept = " + cursor.getInt(receptIndex)
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
    String path;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode==RQS_OPEN_IMAGE){
                 path = data.getData().getPath();
                 Uri uri = data.getData();
                 try {
                    InputStream inputStream = getBaseContext().getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    image.setImageBitmap(bitmap);
                 }
                 catch (FileNotFoundException e) {
                     e.printStackTrace();
                 }

            }
        }
    }


    public void AddImage(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, RQS_OPEN_IMAGE);
    }
}
