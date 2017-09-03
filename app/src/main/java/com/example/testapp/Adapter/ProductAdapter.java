package com.example.testapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.testapp.DBHelper;
import com.example.testapp.DetailsActivity;
import com.example.testapp.Model.Product;
import com.example.testapp.ProductViewHolder;
import com.example.testapp.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private Context context;
    private List<Product> listProducts;
    private DBHelper mDatabase;
    private Intent mIntent;

    public ProductAdapter(Context context, List<Product> listProducts) {
        this.context = context;
        this.listProducts = listProducts;
        mDatabase = new DBHelper(context);
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final Product singleProduct = listProducts.get(position);
        holder.tv_name.setText(singleProduct.getName());
        holder.tv_description.setText(singleProduct.getDescription());
        holder.tv_price.setText(String.valueOf(singleProduct.getPrice()) + "$");
        holder.iv_artwork.setImageResource(singleProduct.getArtwork_link());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // обработчик нажатия на товаре
                // создаем намерение и помещаем в него значения выбранного элемента
                mIntent = new Intent(context, DetailsActivity.class);
                mIntent.putExtra("Name", singleProduct.getName());
                mIntent.putExtra("Description", singleProduct.getDescription());
                mIntent.putExtra("Price", singleProduct.getPrice());
                mIntent.putExtra("Artwork", singleProduct.getArtwork_link());
                context.startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listProducts.size();
    }
}
