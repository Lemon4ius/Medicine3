package com.example.medicine;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Registrashion extends AppCompatActivity {
    Button check;
    EditText login, password;
    sqlite Object_sqlite;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrashion);
        check = findViewById(R.id.enter);
        login = findViewById(R.id.Login);
        password = findViewById(R.id.password);
        Object_sqlite = new sqlite(this);
        sqLiteDatabase = Object_sqlite.getWritableDatabase();

    }

    public void AddReadDb(View v) {
        if (login.getText().length() < 5)
            Toast.makeText(this, "В логине должно быть не менее 5 символов", Toast.LENGTH_SHORT).show();
        else if (password.getText().length() < 5)
            Toast.makeText(this, "В пароле должно быть не менее 5 символов", Toast.LENGTH_SHORT).show();
        else {

            String Login = login.getText().toString();
            String Password = password.getText().toString();
            Login.replace(" ", "");
            Password.replace(" ", "");
            ContentValues contentValues = new ContentValues();

            contentValues.put(RegistrConst.KEY_LOGIN, Login);

            contentValues.put(RegistrConst.KEY_PASSWORD, Password);

            Cursor cursor = sqLiteDatabase.query(RegistrConst.SECONDTABLEUSERS, null, null,
                    null, null, null, null);
            int Logind = cursor.getColumnIndex(RegistrConst.KEY_LOGIN);
            int Passind = cursor.getColumnIndex(RegistrConst.KEY_PASSWORD);
            while (cursor.moveToNext()) {
                if (login.getText().toString().equals(cursor.getString(Logind))) {
                    Toast.makeText(this, "Такой логин уже существует", Toast.LENGTH_SHORT).show();
                    return;
                } else if (password.getText().toString().equals(cursor.getString(Passind))) {
                    Toast.makeText(this, "Такой пароль уже существует", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            sqLiteDatabase.insert(RegistrConst.SECONDTABLEUSERS, null, contentValues);
            Intent intent = new Intent(this, SingIn.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String pat = data.getData().getPath();
        System.out.println(pat);
    }
}