package com.onlineshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class AddProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        Toast.makeText(AddProductActivity.this, "Welcome to sellers' page!", Toast.LENGTH_LONG).show();
    }
}
