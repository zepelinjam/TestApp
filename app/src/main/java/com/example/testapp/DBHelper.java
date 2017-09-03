package com.example.testapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.testapp.Model.Product;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1; // версия базы
    public static final String DB_NAME = "TestDB.sqlite"; // имя БД

    public static  final String TABLE_PRODUCTS = "products"; // название таблицы
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_PRICE = "price";
    public static final String KEY_IMAGE = "image";

    private SQLiteDatabase db;
    private String sqlQuery;
    private List<Product> storeProducts;
    private Cursor cursor;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // запрос созадния таблицы
        db.execSQL("create table " + TABLE_PRODUCTS + "(" + KEY_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT,"
                + KEY_NAME + " text,"
                + KEY_DESCRIPTION + " text,"
                + KEY_PRICE + " integer,"
                + KEY_IMAGE + " integer" + ")" );
        Log.d("Database operations", "Table created...");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // пересоздание таблицы, если меняется номер версии БД
        db.execSQL("drop table if exists " + TABLE_PRODUCTS);
        onCreate(db);
        Log.d("Database operations", "Table updated...");
    }

    public List<Product> listProducts(){
        sqlQuery = "select * from " + TABLE_PRODUCTS;
        db = this.getReadableDatabase();
        storeProducts = new ArrayList<>();
        cursor = db.rawQuery(sqlQuery, null);
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                int price = Integer.parseInt(cursor.getString(3));
                int image_link = Integer.parseInt(cursor.getString(4));
                storeProducts.add(new Product(id, name, description, price, image_link));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeProducts;
    }
}
