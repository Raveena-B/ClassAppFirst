package com.example.classapp.Database;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.classapp.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "UserInfo.db";     //create database

    public DBHelper(MainActivity context) {         //constructor
        super(context, DATABASE_NAME, null, 1);
    }

    public DBHelper(String user) {
        this();

    }

    @Override    // the methods
    public void onCreate(SQLiteDatabase db) {        //craete the sql databse
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + UserMaster.User.TABLE_NAME + " ( " +
                        UserMaster.User._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                        UserMaster.User.COLUMN_NAME_USERNAME + " TEXT ,"+
                        UserMaster.User.COLUMN_NAME_PASSWORD + "TEXT )";

                    db.execSQL(SQL_CREATE_ENTRIES);     //call the sql database
                    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long addInfo(String username , String password){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserMaster.User.COLUMN_NAME_USERNAME,username);
        values.put(UserMaster.User.COLUMN_NAME_PASSWORD,username);

        long newRowId = db.insert(UserMaster.User.TABLE_NAME,null,values);
        return newRowId;
    }

    public List readAllInfo(String req) {         //List - return list of users
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {  //specifies columns of the database
                UserMaster.User._ID,
                UserMaster.User.COLUMN_NAME_USERNAME,
                UserMaster.User.COLUMN_NAME_PASSWORD
        };

        String sortOrder = UserMaster.User.COLUMN_NAME_USERNAME + " DESC";   //keep space before desc

        Cursor cursor = db.query(
                UserMaster.User.TABLE_NAME,
                projection,    //what are the fields in table
                null,
                null,
                null,
                null,
                sortOrder
        );

        List userNames = new ArrayList<>();
        List passwords = new ArrayList<>();

        while (cursor.moveToNext()) {
            String username = cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.User.COLUMN_NAME_USERNAME));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.User.COLUMN_NAME_PASSWORD));

            userNames.add(username);
            passwords.add(password);
        }

        cursor.close();
        Log.i(TAG, "readAllinfo" + userNames);


        if (req == "user") {
            return userNames;
        } else if (req == "password") {
            return passwords;
        } else {
            return null;
        }
    }

    public int deleteInfo(String username, String s){     //read and delete info in the table
        SQLiteDatabase db = getReadableDatabase();

        String selection = UserMaster.User.COLUMN_NAME_USERNAME + " LIKE ?" ; //what is the thing need to delete
        String[] selectionArgs =  {username };
        db.delete(UserMaster.User.TABLE_NAME,selection,selectionArgs);

        return 0;
    }

    public int updateInfo (String username , String password){
        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserMaster.User.COLUMN_NAME_PASSWORD,password);

        String selection = UserMaster.User.COLUMN_NAME_USERNAME + " LIKE ?"; //what is the thing need to update
        String[] selectionArgs =  {username };

        int count = db.update(UserMaster.User.TABLE_NAME,values,selection,selectionArgs);
        return count;

    }





}