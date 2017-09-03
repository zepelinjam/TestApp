package com.example.testapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductViewHolder extends RecyclerView.ViewHolder {

    public TextView tv_name;
    public TextView tv_description;
    public TextView tv_price;
    public ImageView iv_artwork;

    public ProductViewHolder(View itemView) {
        super(itemView);

        tv_name = (TextView) itemView.findViewById(R.id.tv_name);
        tv_description = (TextView) itemView.findViewById(R.id.tv_description);
        tv_price = (TextView) itemView.findViewById(R.id.tv_price);
        iv_artwork = (ImageView) itemView.findViewById(R.id.iv_artwork);
    }
}
