package com.example.budgetwisesolutions.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.budgetwisesolutions.model.UserModel;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class UserDb extends SQLiteOpenHelper {
    public static final String DB_NAME = "users_db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "users";

    //khai bao ten cac cot trong bang du lieu
    public static final String ID_COL = "id";
    public static final String USERNAME_COL = "username";
    public static final String PASSWORD_COL = "password";
    public static final String EMAIL_COL = "email";
    public static final String PHONE_COL = "phone";
    public static final String ADDRESS_COL = "address";
    public static final String CREATED_COL = "created_at";
    public static final String UPDATE_COL = "update_at";



    public UserDb(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //di tao bang du lieu SQLite
        String query = "CREATE TABLE " + TABLE_NAME + " ( "
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USERNAME_COL + " VARCHAR(60) NOT NULL, "
                + PASSWORD_COL + " VARCHAR(200) NOT NULL, "
                + EMAIL_COL + " VARCHAR(60) NOT NULL, "
                + PHONE_COL + " VARCHAR(20) NOT NULL, "
                + ADDRESS_COL + " TEXT, "
                + CREATED_COL + " DATETIME, "
                + UPDATE_COL + " DATETIME) ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public long addNewAccountUser(String username, String password, String email, String phone, String adress){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZonedDateTime now = ZonedDateTime.now();
        String currentDay = dtf.format(now);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME_COL, username);
        values.put(PASSWORD_COL, password);
        values.put(EMAIL_COL, email);
        values.put(PHONE_COL, phone);
        values.put(ADDRESS_COL, adress);
        values.put(CREATED_COL, currentDay);
        long insert = db.insert(TABLE_NAME, null,values);
        db.close();
        return insert;
    }
    public UserModel checkUserLogin(String email, String password){
        UserModel user = new UserModel();
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            String[] colunms = {ID_COL, USERNAME_COL, EMAIL_COL, PHONE_COL, ADDRESS_COL};
            String condition = USERNAME_COL + " =? " + " AND " + PASSWORD_COL + " =? ";
            String[] params = { email, password };
            Cursor cursor = db.query(TABLE_NAME, colunms, condition,params, null, null,null);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return user;
    }
}
