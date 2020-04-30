package com.example.placeorderapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Food extends SQLiteOpenHelper {
    public static final String TAG = "food";

    public static final String TABLE_NAME = "FOOD_ITEMS";
    public static final String food_id_col = "ID";
    public static final String food_name_col = "food_name";
    public static final String food_desc_col = "food_desc";

    public Food(Context context) { super(context, TABLE_NAME, null, 3);    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + food_name_col + " Text, " + food_desc_col + " Text )";
        db.execSQL(createTable);
        db.execSQL("INSERT INTO FOOD_ITEMS (food_name, food_desc) VALUES('Pizza','Pizza is a savory dish of Italian origin, consisting of a usually round, flattened base of leavened wheat-based dough topped with tomatoes, cheese, and various other ingredients baked at a high temperature, traditionally in a wood-fired oven.');");
        db.execSQL("INSERT INTO FOOD_ITEMS (food_name, food_desc) VALUES('Calzones','The best calzone in Toronto are the ultimate giant pizza pockets; stuffed with melty, gooey mozzarella cheese, marinara sauce and other optional fillings, then baked or fried.');");
        db.execSQL("INSERT INTO FOOD_ITEMS (food_name, food_desc) VALUES('Pasta','Pasta is a staple food of Italian cuisine. Pasta is typically made from an unleavened dough of durum wheat flour mixed with water or eggs, and formed into sheets or various shapes, then cooked by boiling or baking.');");
        db.execSQL("INSERT INTO FOOD_ITEMS (food_name, food_desc) VALUES('Chicken Wings','Chicken Wing is an unbreaded chicken wing section that is generally deep-fried then coated or dipped in a sauce consisting of a vinegar-based cayenne pepper hot sauce and melted butter prior to serving.');");
        db.execSQL("INSERT INTO FOOD_ITEMS (food_name, food_desc) VALUES('French Fries','French fries, or simply fries; chips, finger chips, or french-fried potatoes are batonnet or allumette-cut deep-fried potatoes.');");
        db.execSQL("INSERT INTO FOOD_ITEMS (food_name, food_desc) VALUES('Soda Pop','Coke, Diet Coke, Sprite, Ginger Ale, Orange Crush');");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);


    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    //Returns the ID that matches the name passed in
    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + food_id_col+", "+ food_name_col+ ", "+food_desc_col + " FROM " + TABLE_NAME +
                " WHERE " + food_name_col + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

}
