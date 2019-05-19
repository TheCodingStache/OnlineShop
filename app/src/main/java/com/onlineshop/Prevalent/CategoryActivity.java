package com.onlineshop.Prevalent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.onlineshop.AddProductActivity;
import com.onlineshop.R;

@SuppressLint("Registered")
public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        ImageView tShirts = findViewById(R.id.t_shirts);
        ImageView sportsTShirts = findViewById(R.id.sports_t_shirts);
        ImageView femaleDresses = findViewById(R.id.female_dresses);
        ImageView sweaters = findViewById(R.id.sweaters);

        ImageView glasses = findViewById(R.id.glasses);
        ImageView hatsCaps = findViewById(R.id.hats_caps);
        ImageView walletsBagsPurses = findViewById(R.id.purses_bags_wallets);
        ImageView shoes = findViewById(R.id.shoes);

        tShirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, AddProductActivity.class);
                intent.putExtra("category", "tShirts");
                startActivity(intent);
            }
        });


        sportsTShirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, AddProductActivity.class);
                intent.putExtra("category", "Sports tShirts");
                startActivity(intent);
            }
        });


        femaleDresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, AddProductActivity.class);
                intent.putExtra("category", "Female Dresses");
                startActivity(intent);
            }
        });


        sweaters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, AddProductActivity.class);
                intent.putExtra("category", "Sweaters");
                startActivity(intent);
            }
        });

        glasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(CategoryActivity.this, AddProductActivity.class);
                intent.putExtra("category", "Glasses");
                startActivity(intent);
            }
        });


        hatsCaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(CategoryActivity.this, AddProductActivity.class);
                intent.putExtra("category", "Hats Caps");
                startActivity(intent);
            }
        });



        walletsBagsPurses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(CategoryActivity.this, AddProductActivity.class);
                intent.putExtra("category", "Wallets Bags Purses");
                startActivity(intent);
            }
        });


        shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(CategoryActivity.this, AddProductActivity.class);
                intent.putExtra("category", "Shoes");
                startActivity(intent);
            }
        });

    }
}
