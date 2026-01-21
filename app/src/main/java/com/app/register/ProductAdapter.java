package com.app.register;

import android.view.*;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    List<Product> list;

    public ProductAdapter(List<Product> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder h, int i) {
        Product p = list.get(i);
        h.name.setText(p.name);
        h.price.setText("₹ " + p.price);
        h.category.setText("Category: " + p.category);
        h.stock.setText("Stock: " + p.stock);
        h.status.setText("Status: " + p.status);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, category, stock, status;
        ViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.tvName);
            price = v.findViewById(R.id.tvPrice);
            category = v.findViewById(R.id.tvCategory);
            stock = v.findViewById(R.id.tvStock);
            status = v.findViewById(R.id.tvStatus);
        }
    }
}

