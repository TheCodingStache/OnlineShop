package com.onlineshop.Register;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.onlineshop.R;

@SuppressLint("Registered")
public class SelectUserOptionActivity extends AppCompatActivity {
    Button seller;
    Button client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_user_selection);
        seller = findViewById(R.id.seller);
        client = findViewById(R.id.client);

        seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openSellerRegistration = new Intent(SelectUserOptionActivity.this, GiveawayRegisterActivity.class);
                startActivity(openSellerRegistration);
                finishAndRemoveTask();
            }
        });
        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openClientRegistration = new Intent(SelectUserOptionActivity.this, ClientRegisterActivity.class);
                startActivity(openClientRegistration);
                finish();
                finishAndRemoveTask();
            }
        });
    }

}
