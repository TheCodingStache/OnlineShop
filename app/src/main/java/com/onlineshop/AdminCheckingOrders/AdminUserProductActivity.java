package com.onlineshop.AdminCheckingOrders;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.onlineshop.Model.Cart;
import com.onlineshop.Model.Products;
import com.onlineshop.R;
import com.onlineshop.ViewHolder.CartViewHolder;

public class AdminUserProductActivity extends AppCompatActivity {
    private RecyclerView productList;
    RecyclerView.LayoutManager layoutManager;
    private DatabaseReference cartListRef;
    private String userID = null;
    private String productID = null;
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Cart> options = new FirebaseRecyclerOptions.Builder<Cart>()
                .setQuery(cartListRef, Cart.class)
                .build();
        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter = new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull Cart model) {
                holder.textProductName.setText(model.getPname());
                holder.textProductShipping.setText("Shipping cost: " + model.getPrice());
                holder.textProductAddress.setText("Product Address: " + model.getAddress());
            }

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items_layout, parent, false);
                return new CartViewHolder(view);
            }
        };
        productList.setAdapter(adapter);
        adapter.startListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_product);
        userID = getIntent().getStringExtra("uid");
        productID = getIntent().getStringExtra("pid");
        productList = findViewById(R.id.products_list);
        productList.hasFixedSize();
        layoutManager = new LinearLayoutManager(this);
        productList.setLayoutManager(layoutManager);
        cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List")
                .child("Admin View").child(userID).child("Products");

    }
}
