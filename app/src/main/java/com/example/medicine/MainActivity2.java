package com.example.medicine;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;
import java.util.ArrayList;
import java.util.logging.ConsoleHandler;


public class MainActivity2 extends AppCompatActivity {
    private ArrayList<String> name, disc, price;
    private ArrayList<String> image;
    HelpAdapter.ClickListener clickListener;

    Spinner spinner;
    String[] SortList = {"Сортировка по названию", "Сортировка по цене", "Сортировка по рецепту"};
    TextView text;
    TextView DiskText;

    private static final int RQS_OPEN_IMAGE = 1;


    HelpAdapter helpAdapter; // adapter
    sqlite SqliteObject;    //object sqlite
    SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);


        SqliteObject = new sqlite(MainActivity2.this);

        name = new ArrayList<>();
        disc = new ArrayList<>();
        image = new ArrayList<>();
        price = new ArrayList<>();
        sqLiteDatabase = SqliteObject.getWritableDatabase();


        RecyclerView recyclerView = findViewById(R.id.activity_main2_List_medicine);

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + sqlite.TABLE_NAME + " ORDER BY " + sqlite.KEY_PRICE, new String[]{});
        displaydate(cursor);

        setOnClickLitener();
        helpAdapter = new HelpAdapter(MainActivity2.this, name, image, price, clickListener);
        recyclerView.setAdapter(helpAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity2.this));


    }

    private void setOnClickLitener() {
        clickListener = new HelpAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(getApplicationContext(), InformationActivity.class);
                intent.putExtra("name", name.get(position));
                startActivity(intent);
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();

        spinner = (Spinner) findViewById(R.id.medicineSpiner);

        ArrayAdapter<?> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, SortList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = (String) adapterView.getItemAtPosition(i);
                if (item.equals(SortList[0])) {
                    text = (TextView) findViewById(R.id.edittext);
                    text.setText(SortList[0]);
                    image.clear();
                    name.clear();
                    price.clear();
                    Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + sqlite.TABLE_NAME + " ORDER BY " + sqlite.KEY_NAME, new String[]{});
                    displaydate(cursor);
                    RecyclerView recyclerView = findViewById(R.id.activity_main2_List_medicine);
                    setOnClickLitener();
                    helpAdapter = new HelpAdapter(MainActivity2.this, name, image, price, clickListener);
                    recyclerView.setAdapter(helpAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity2.this));
                }
                if (item.equals(SortList[1])) {
                    image.clear();
                    name.clear();
                    price.clear();
                    text = (TextView) findViewById(R.id.edittext);
                    text.setText(SortList[1]);
                    Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + sqlite.TABLE_NAME + " ORDER BY " + sqlite.KEY_PRICE, new String[]{});
                    displaydate(cursor);
                    RecyclerView recyclerView = findViewById(R.id.activity_main2_List_medicine);
                    setOnClickLitener();
                    helpAdapter = new HelpAdapter(MainActivity2.this, name, image, price, clickListener);
                    recyclerView.setAdapter(helpAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity2.this));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        };
        spinner.setOnItemSelectedListener(itemSelectedListener);

    }

    void displaydate(Cursor cursor) {


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

