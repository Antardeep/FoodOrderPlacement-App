package com.example.placeorderapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Order extends SQLiteOpenHelper{
    public static final String TAG = "order";

    public static final String TABLE_NAME = "order_table";
    public static final String order_id_col = "ID";
    public static final String user_id_col = "user_id";
    public static final String food_name_col = "food_name";
    public static final String topping_col = "topping";
    public static final String size_col = "size";
    public static final String spicy_col = "spicy";
    public static final String sauce_col = "sauce";
    public static final String soda_col = "soda";

    //SQL for creating users table
    public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_NAME
            + " ( "
            + order_id_col + " INTEGER PRIMARY KEY, "
            + user_id_col + " TEXT, "
            + food_name_col + " TEXT, "
            + topping_col + " TEXT, "
            + size_col + " TEXT, "
            + spicy_col + " TEXT, "
            + sauce_col + " TEXT, "
            + soda_col + " TEXT"
            + " ) ";


    public Order(Context context) { super(context, TABLE_NAME, null, 2);    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create Table when oncreate gets called
        db.execSQL(SQL_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop table to create new one if database version updated
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addData(String temp_id, String item1, String item2, String item3, String item4, String item5, String item6){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(user_id_col, temp_id);
        contentValues.put(food_name_col, item1);
        contentValues.put(topping_col, item2);
        contentValues.put(size_col, item3);
        contentValues.put(spicy_col, item4);
        contentValues.put(sauce_col, item5);
        contentValues.put(soda_col, item6);

        Log.d(TAG, "addName: Adding " + item1+" & "+item2+ " to " + TABLE_NAME);

        db.insert(TABLE_NAME, null, contentValues);

    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    //Returns the ID that matches the name passed in
    public Cursor getItemID(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + order_id_col+", "+ food_name_col+", "+ topping_col+", "+ size_col+", "+ spicy_col+", "+ sauce_col+ ", "+soda_col + " FROM " + TABLE_NAME +
                " WHERE " + user_id_col + " = '" + id + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

}
