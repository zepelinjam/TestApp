package com.example.testapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    private TextView mName;
    private TextView mDescription;
    private TextView mPrice;
    private ImageView mArtwork;
    private Toolbar toolbar;
    private Bundle mBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initializeViews();
        setSupportActionBar(toolbar);

        // добавление в тулбар кнопки "назад"
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        // получение значений из предыдущего активити
        mBundle = getIntent().getExtras();

        mName.setText(mBundle.getString("Name"));
        mDescription.setText(mBundle.getString("Description"));
        //mPrice.setText(String.valueOf(mBundle.getString("Price")));
        mPrice.setText("Цена: " + String.valueOf(mBundle.getInt("Price")) + "$");
        mArtwork.setImageResource(mBundle.getInt("Artwork"));
    }

    // обработка нажатия стрелочки "назад"
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeViews(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mName = (TextView) findViewById(R.id.tv_name_details);
        mDescription = (TextView) findViewById(R.id.tv_description_details);
        mPrice = (TextView) findViewById(R.id.tv_price_details);
        mArtwork = (ImageView) findViewById(R.id.iv_artwork_details);
    }

}
