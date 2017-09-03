package com.example.testapp;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.testapp.Adapter.ProductAdapter;
import com.example.testapp.Model.Product;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private RecyclerView mRecycler;
    private LinearLayout mLinearLayout;
    private LinearLayoutManager mLayoutManager;
    private List<Product> mProductsList;
    private ProductAdapter mProductAdapter;
    private ContentValues contentValues;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();

        insertData(); // заполнение базы

        // работа с RecyclerView и вывод данных из БД
        initializeViews();
        mLayoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(mLayoutManager);
        mRecycler.setHasFixedSize(true); // размер RecyclerView не изменяется от содержимого

        mProductsList = dbHelper.listProducts(); // получаем List со всеми данными из таблицы БД
        if(mProductsList.size() > 0){
            mRecycler.setVisibility(View.VISIBLE);
            mProductAdapter = new ProductAdapter(this, mProductsList);
            mRecycler.setAdapter(mProductAdapter);
        }else {
            mRecycler.setVisibility(View.GONE);
            Toast.makeText(this, "В базе данных нет товаров", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Выход") // заголовок
                .setMessage("Закрыть приложение?")
                .setCancelable(false)
                .setPositiveButton("Да",
                        new DialogInterface.OnClickListener(){ // по нажатии ...
                            public void onClick(DialogInterface dialog, int id) {
                                // очистка базы данных при закрытии приложения
                                database.delete(DBHelper.TABLE_PRODUCTS,null,null);
                                Log.d("Database operations", "Table " + DBHelper.TABLE_PRODUCTS + " was cleared...");
                                finish(); // закрыть приложение
                            }
                        })
                .setNegativeButton("Нет",
                        new DialogInterface.OnClickListener() { // по нажатии ...
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel(); // закрыть диалог
                            }
                        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void initializeViews() {
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        mLinearLayout = (LinearLayout) findViewById(R.id.main_activity);
    }

    // метод, заполняющий базу
    private void insertData(){
        contentValues = new ContentValues();
        // заполнение начений в contentValues
        contentValues.put(DBHelper.KEY_NAME, "Футболка Nike красная");
        contentValues.put(DBHelper.KEY_DESCRIPTION, "Красная мужская футболка.");
        contentValues.put(DBHelper.KEY_PRICE, 35);
        contentValues.put(DBHelper.KEY_IMAGE, getResources().getIdentifier("nike_red" , "drawable", getPackageName()));
        database.insert(DBHelper.TABLE_PRODUCTS, null, contentValues); // вставка значений из contentValues
        Log.d("Database operations", "1 row inserted...");

        contentValues.put(DBHelper.KEY_NAME, "Футболка PSG 2011/12");
        contentValues.put(DBHelper.KEY_DESCRIPTION, "Домашняя футбольная футболка PSG сезона 2011/12.");
        contentValues.put(DBHelper.KEY_PRICE, 85);
        contentValues.put(DBHelper.KEY_IMAGE, getResources().getIdentifier("psg_home2011" , "drawable", getPackageName()));
        database.insert(DBHelper.TABLE_PRODUCTS, null, contentValues);
        Log.d("Database operations", "1 row inserted...");

        contentValues.put(DBHelper.KEY_NAME, "Футболка Adidas  красная");
        contentValues.put(DBHelper.KEY_DESCRIPTION, "Красная футболка с тремя полосками.");
        contentValues.put(DBHelper.KEY_PRICE, 25);
        contentValues.put(DBHelper.KEY_IMAGE, getResources().getIdentifier("adidas_red" , "drawable", getPackageName()));
        database.insert(DBHelper.TABLE_PRODUCTS, null, contentValues);
        Log.d("Database operations", "1 row inserted...");

        contentValues.put(DBHelper.KEY_NAME, "Рюкзак Nike");
        contentValues.put(DBHelper.KEY_DESCRIPTION, "Рюкзак Nike синего цвета.");
        contentValues.put(DBHelper.KEY_PRICE, 65);
        contentValues.put(DBHelper.KEY_IMAGE, getResources().getIdentifier("nike_backpack" , "drawable", getPackageName()));
        database.insert(DBHelper.TABLE_PRODUCTS, null, contentValues);
        Log.d("Database operations", "1 row inserted...");

        contentValues.put(DBHelper.KEY_NAME, "Кроссовки Nike Hurrache");
        contentValues.put(DBHelper.KEY_DESCRIPTION, "Мужские кроссовки новой модели.");
        contentValues.put(DBHelper.KEY_PRICE, 115);
        contentValues.put(DBHelper.KEY_IMAGE, getResources().getIdentifier("nike_hurrache" , "drawable", getPackageName()));
        database.insert(DBHelper.TABLE_PRODUCTS, null, contentValues);
        Log.d("Database operations", "1 row inserted...");

        contentValues.put(DBHelper.KEY_NAME, "Мяч Nike PSG");
        contentValues.put(DBHelper.KEY_DESCRIPTION, "Футбольный мяч с лого PSG.");
        contentValues.put(DBHelper.KEY_PRICE, 45);
        contentValues.put(DBHelper.KEY_IMAGE, getResources().getIdentifier("nike_ball_psg" , "drawable", getPackageName()));
        database.insert(DBHelper.TABLE_PRODUCTS, null, contentValues);
        Log.d("Database operations", "1 row inserted...");

        contentValues.put(DBHelper.KEY_NAME, "Футболка FC Barcelona 2017/18");
        contentValues.put(DBHelper.KEY_DESCRIPTION, "Футболка FC Barcelona сезона 2017/18.");
        contentValues.put(DBHelper.KEY_PRICE, 80);
        contentValues.put(DBHelper.KEY_IMAGE, getResources().getIdentifier("barcelona_blue" , "drawable", getPackageName()));
        database.insert(DBHelper.TABLE_PRODUCTS, null, contentValues);
        Log.d("Database operations", "1 row inserted...");

        contentValues.put(DBHelper.KEY_NAME, "Футболка Nike розовая");
        contentValues.put(DBHelper.KEY_DESCRIPTION, "Футболка из новой коллекции для женщин.");
        contentValues.put(DBHelper.KEY_PRICE, 30);
        contentValues.put(DBHelper.KEY_IMAGE, getResources().getIdentifier("nike_pink" , "drawable", getPackageName()));
        database.insert(DBHelper.TABLE_PRODUCTS, null, contentValues);
        Log.d("Database operations", "1 row inserted...");

        contentValues.put(DBHelper.KEY_NAME, "Футболка PSG 2017/18");
        contentValues.put(DBHelper.KEY_DESCRIPTION, "Красная футбольная футболка PSG 2017/18.");
        contentValues.put(DBHelper.KEY_PRICE, 70);
        contentValues.put(DBHelper.KEY_IMAGE, getResources().getIdentifier("psg_red" , "drawable", getPackageName()));
        database.insert(DBHelper.TABLE_PRODUCTS, null, contentValues);
        Log.d("Database operations", "1 row inserted...");

        contentValues.put(DBHelper.KEY_NAME, "Футболка Adidas белая");
        contentValues.put(DBHelper.KEY_DESCRIPTION, "Футболка из новой коллекции.");
        contentValues.put(DBHelper.KEY_PRICE, 50);
        contentValues.put(DBHelper.KEY_IMAGE, getResources().getIdentifier("adidas_white" , "drawable", getPackageName()));
        database.insert(DBHelper.TABLE_PRODUCTS, null, contentValues);
        Log.d("Database operations", "1 row inserted...");

        contentValues.put(DBHelper.KEY_NAME, "Сумочка Adidas");
        contentValues.put(DBHelper.KEY_DESCRIPTION, "Спортивная ручная сумочка.");
        contentValues.put(DBHelper.KEY_PRICE, 20);
        contentValues.put(DBHelper.KEY_IMAGE, getResources().getIdentifier("adidas_handbag" , "drawable", getPackageName()));
        database.insert(DBHelper.TABLE_PRODUCTS, null, contentValues);
        Log.d("Database operations", "1 row inserted...");

        contentValues.put(DBHelper.KEY_NAME, "Тапочки Nike");
        contentValues.put(DBHelper.KEY_DESCRIPTION, "Мужские тапочки белого цвета.");
        contentValues.put(DBHelper.KEY_PRICE, 45);
        contentValues.put(DBHelper.KEY_IMAGE, getResources().getIdentifier("nike_shoes" , "drawable", getPackageName()));
        database.insert(DBHelper.TABLE_PRODUCTS, null, contentValues);
        Log.d("Database operations", "1 row inserted...");

        contentValues.put(DBHelper.KEY_NAME, "Футбольный мяч Nike");
        contentValues.put(DBHelper.KEY_DESCRIPTION, "Профессиональный футбольный мяч.");
        contentValues.put(DBHelper.KEY_PRICE, 70);
        contentValues.put(DBHelper.KEY_IMAGE, getResources().getIdentifier("nike_ball" , "drawable", getPackageName()));
        database.insert(DBHelper.TABLE_PRODUCTS, null, contentValues);
        Log.d("Database operations", "1 row inserted...");

        contentValues.put(DBHelper.KEY_NAME, "Футболка Nike серая");
        contentValues.put(DBHelper.KEY_DESCRIPTION, "Из коллекции Dangerous.");
        contentValues.put(DBHelper.KEY_PRICE, 40);
        contentValues.put(DBHelper.KEY_IMAGE, getResources().getIdentifier("nike_grey" , "drawable", getPackageName()));
        database.insert(DBHelper.TABLE_PRODUCTS, null, contentValues);
        Log.d("Database operations", "1 row inserted...");

        contentValues.put(DBHelper.KEY_NAME, "Спортивная сумка Bayern Munich");
        contentValues.put(DBHelper.KEY_DESCRIPTION, "Большая спортиваня сумка.");
        contentValues.put(DBHelper.KEY_PRICE, 50);
        contentValues.put(DBHelper.KEY_IMAGE, getResources().getIdentifier("adidas_bag" , "drawable", getPackageName()));
        database.insert(DBHelper.TABLE_PRODUCTS, null, contentValues);
        Log.d("Database operations", "1 row inserted...");
    }
}
