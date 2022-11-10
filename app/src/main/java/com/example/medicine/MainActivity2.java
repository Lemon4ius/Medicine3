package com.example.medicine;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private ArrayList<String> name, disc;
    private ArrayList<Bitmap> image;


    private static final int RQS_OPEN_IMAGE = 1;
    
    SQLiteDatabase sqLiteDatabase;
    HelpAdapter helpAdapter; // adapter
    sqlite SqliteObject;    //object sqlite
    @Override
    public void onBackPressed() {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);


        RecyclerView recyclerView = findViewById(R.id.activity_main2_List_medicine);
        SqliteObject = new sqlite(MainActivity2.this);

        name = new ArrayList<>();
        disc = new ArrayList<>();
        image = new ArrayList<>();


        displaydate();
        helpAdapter = new HelpAdapter(MainActivity2.this, name, disc, image);
        recyclerView.setAdapter(helpAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity2.this));





    }

    Uri uri2;
    void displaydate() {
        Cursor cursor = SqliteObject.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Не добавлено ", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
               int imageIndex = cursor.getColumnIndex(sqlite.KEY_IMAGE);
                 uri2 = Uri.parse(cursor.getString(imageIndex));

                try {
                    InputStream inputStream = getBaseContext().getContentResolver().openInputStream(uri2);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    image.add(bitmap);
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                name.add(cursor.getString(1));
                disc.add(cursor.getString(5));
            }
        }

    }


    public void onClickNextPage(View v) {
        Intent intent2 = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(intent2);
    }


//    String path;
//    Uri uri;
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == Activity.RESULT_OK) {
//            if (requestCode==RQS_OPEN_IMAGE){
//                path = data.getData().getPath();
//                uri = data.getData();
//                try {
//                    InputStream inputStream = getBaseContext().getContentResolver().openInputStream(uri);
//                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                    image.add(bitmap);
//                }
//                catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }
//    }
    





}

