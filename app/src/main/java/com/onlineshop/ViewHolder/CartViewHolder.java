package com.onlineshop.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.onlineshop.Interface.ItemClickListener;
import com.onlineshop.R;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView textProductName, textProductShipping, textProductAddress;
    private ItemClickListener itemClickListener;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        textProductName = itemView.findViewById(R.id.cart_product_name);
        textProductShipping = itemView.findViewById(R.id.cart_product_shipping);
        textProductAddress = itemView.findViewById(R.id.cart_product_address);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
