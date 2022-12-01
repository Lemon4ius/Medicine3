package com.example.medicine;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity2 extends AppCompatActivity {
    private ArrayList<String> name, disc, price;
    private ArrayList<String> image;


    Spinner spinner;
    String[] SortList = {"Сортировка по названию", "Сортировка по цене", "Сортировка по рецепту"};


    private static final int RQS_OPEN_IMAGE = 1;


    HelpAdapter helpAdapter; // adapter
    sqlite SqliteObject;    //object sqlite



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);


        RecyclerView recyclerView = findViewById(R.id.activity_main2_List_medicine);
        SqliteObject = new sqlite(MainActivity2.this);

        name = new ArrayList<>();
        disc = new ArrayList<>();
        image = new ArrayList<>();
        price= new ArrayList<>();

        displaydate();
        helpAdapter = new HelpAdapter(MainActivity2.this, name, image, price);
        recyclerView.setAdapter(helpAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity2.this));


        spinner = (Spinner) findViewById(R.id.medicineSpiner);
        ArrayAdapter<?> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, SortList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }


    void displaydate() {
        Cursor cursor = SqliteObject.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Не добавлено ", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {

                image.add(cursor.getString(4));
                name.add(cursor.getString(1));
                price.add(cursor.getString(2));
            }
        }

    }


    public void onClickNextPage(View v) {
        Intent intent2 = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(intent2);
    }

    @Override
    public void onBackPressed() {

    }
}

