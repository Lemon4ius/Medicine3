package com.example.medicine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private ArrayList<String> name, disc;
    ImageView image;
    Button button_next;

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
        image = new ImageView(this);

        displaydate();

        helpAdapter = new HelpAdapter(MainActivity2.this, name, disc);
        recyclerView.setAdapter(helpAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity2.this));


        button_next = (Button) findViewById(R.id.activity_main_next);


    }


    void displaydate() {
        Cursor cursor = SqliteObject.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Не добавлено ", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {

                name.add(cursor.getString(1));

                disc.add(cursor.getString(5));
            }
        }

    }


    public void onClickNextPage(View v) {
        Intent intent2 = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(intent2);
    }


}
//    private Medicine generateNewMedicine() {
//        return new Medicine("Новый фильм", "awdad", 0);
//    }

//    private List<Medicine> generateMedicineList() {
//
//        List<Medicine> medicine = new ArrayList<>();
//        medicine.add(new Medicine("awd", sqlite.KEY_DISCR, R.drawable.azirak));
//
//
//            return medicine;
//
//    }

