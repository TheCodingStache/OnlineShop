package com.onlineshop;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.onlineshop.Model.Products;
import com.squareup.picasso.Picasso;

public class ProductDetailsActivity extends AppCompatActivity {
    private String productID = "";
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapse);
        productID = getIntent().getStringExtra("pid");
        String collapseProductName = getIntent().getExtras().getString("pname");
        String collapseDescription = getIntent().getExtras().getString("description");
//        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
//        collapsingToolbarLayout.setTitleEnabled(true);
        imageView = findViewById(R.id.collapse_product_image);
        TextView description = findViewById(R.id.product_description);
        TextView productName = findViewById(R.id.product_name);
        description.setText(collapseDescription);
        productName.setText(collapseProductName);
        getProductDetails(productID);
    }

    private void getProductDetails(final String productID) {
        DatabaseReference productsRef = FirebaseDatabase.getInstance().getReference().child("Products");
        productsRef.child(productID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (dataSnapshot.child("image").exists()) {
                        Products products = dataSnapshot.getValue(Products.class);
                        Picasso.get().load(products.getImage()).into(imageView);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
