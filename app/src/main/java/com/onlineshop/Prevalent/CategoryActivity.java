package com.onlineshop.Prevalent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.onlineshop.AddProductActivity;
import com.onlineshop.AdminCheckingOrders.AdminNewOrdersActivity;
import com.onlineshop.AdminMaintainActivity;
import com.onlineshop.HomeActivity;
import com.onlineshop.R;

import io.paperdb.Paper;

@SuppressLint("Registered")
public class CategoryActivity extends AppCompatActivity {
    private boolean doubleBackToExitPressedOnce = false;
    private Button maintain;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Paper.book().destroy();
            finishActivity(R.layout.activity_category);
            finish();
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click back again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Button checkOrders = findViewById(R.id.check_orders);
        maintain = findViewById(R.id.maintain_product);
        ImageView tShirts = findViewById(R.id.t_shirts);
        ImageView sportsTShirts = findViewById(R.id.sports_t_shirts);
        ImageView femaleDresses = findViewById(R.id.female_dresses);
        ImageView sweaters = findViewById(R.id.sweaters);

        ImageView glasses = findViewById(R.id.glasses);
        ImageView hatsCaps = findViewById(R.id.hats_caps);
        ImageView walletsBagsPurses = findViewById(R.id.purses_bags_wallets);
        ImageView shoes = findViewById(R.id.shoes);

        ImageView pants = findViewById(R.id.trousers);
        ImageView shorts = findViewById(R.id.shorts);

        tShirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, AddProductActivity.class);
                intent.putExtra("category", "tShirts");
                startActivity(intent);
                finish();
            }
        });


        sportsTShirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, AddProductActivity.class);
                intent.putExtra("category", "Sports tShirts");
                startActivity(intent);
                finish();
            }
        });


        femaleDresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, AddProductActivity.class);
                intent.putExtra("category", "Female Dresses");
                startActivity(intent);
                finish();
            }
        });


        sweaters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, AddProductActivity.class);
                intent.putExtra("category", "Sweaters");
                startActivity(intent);
                finish();
            }
        });

        glasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, AddProductActivity.class);
                intent.putExtra("category", "Glasses");
                startActivity(intent);
                finish();
            }
        });


        hatsCaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, AddProductActivity.class);
                intent.putExtra("category", "Hats Caps");
                startActivity(intent);
            }
        });


        walletsBagsPurses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, AddProductActivity.class);
                intent.putExtra("category", "Wallets Bags Purses");
                startActivity(intent);
                finish();
            }
        });


        shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, AddProductActivity.class);
                intent.putExtra("category", "Shoes");
                startActivity(intent);
                finish();
            }
        });

        pants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, AddProductActivity.class);
                intent.putExtra("category", "Pants");
                startActivity(intent);
                finish();
            }
        });

        shorts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, AddProductActivity.class);
                intent.putExtra("category", "Shorts");
                startActivity(intent);
                finish();
            }
        });
        maintain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, HomeActivity.class);
                intent.putExtra("Admin", "Admin");
                startActivity(intent);
            }
        });
        checkOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, AdminNewOrdersActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}
