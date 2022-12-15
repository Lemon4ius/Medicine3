package com.example.medicine;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.invoke.ConstantCallSite;

public class InformationActivity extends AppCompatActivity {
    TextView text;
    ImageView imginf;

    sqlite SqliteObject;    //object sqlite
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        text = findViewById(R.id.Inf_disk);
        imginf = findViewById(R.id.ImageInf);


        SqliteObject = new sqlite(this);
        sqLiteDatabase = SqliteObject.getWritableDatabase();
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("name");


        Cursor cursor = sqLiteDatabase.rawQuery("Select * From " + sqlite.TABLE_NAME + " Where " + sqlite.KEY_NAME + " == " + "\"" + name + "\"", new String[]{});


        if (cursor.getCount() == 0) {

        } else {
            while (cursor.moveToNext()) {
                text.setText(cursor.getString(5));
                Uri uri = Uri.parse(cursor.getString(4));
                imginf.setImageURI(uri);
            }
        }

    }
}
