package com.onlineshop;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.onlineshop.Prevalent.CategoryActivity;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class AdminMaintainActivity extends AppCompatActivity {
    private EditText Name, ShippingCost, Description, Address;
    Button applyChangesButton, deleteProduct;
    private ImageView imageView;
    private String productID = "";
    private DatabaseReference productRef;
    String productName, pDescription, pShipping, pAddress;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AdminMaintainActivity.this, HomeActivity.class);
        startActivity(intent);
        finishAndRemoveTask();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_maintain);
        productID = getIntent().getStringExtra("pid");
        productRef = FirebaseDatabase.getInstance().getReference().child("Products").child(productID);
        imageView = findViewById(R.id.product_image);
        applyChangesButton = findViewById(R.id.apply_changes_maintain);
        deleteProduct = findViewById(R.id.delete_product);
        Name = findViewById(R.id.product_name);
        ShippingCost = findViewById(R.id.product_price);
        Address = findViewById(R.id.product_address);
        Description = findViewById(R.id.product_description);
        displaySpecificProductInfo();
        applyChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applyChanges();
                finish();
            }
        });
        deleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteThisProduct();
            }
        });
    }

    private void deleteThisProduct() {
        productRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(AdminMaintainActivity.this, "Product deleted successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminMaintainActivity.this, HomeActivity.class);
                startActivity(intent);
                finishAndRemoveTask();
            }
        });
    }

    private void applyChanges() {
        productName = Name.getText().toString();
        pDescription = Description.getText().toString();
        pShipping = ShippingCost.getText().toString();
        pAddress = Address.getText().toString();

        if (TextUtils.isEmpty(productName)) {
            Toast.makeText(this, "Write down new product Name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(pDescription)) {
            Toast.makeText(this, "Write down new Description", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(pShipping)) {
            Toast.makeText(this, "Write down new shipping cost", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(pAddress)) {
            Toast.makeText(this, "Write down new product Name", Toast.LENGTH_SHORT).show();
        } else {
            HashMap<String, Object> productMap = new HashMap<>();
            productMap.put("pid", productID);
            productMap.put("description", pDescription);
            productMap.put("shipping", pShipping);
            productMap.put("pname", productName);
            productMap.put("address", pAddress);
            productRef.updateChildren(productMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(AdminMaintainActivity.this, "Changes applied successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AdminMaintainActivity.this, CategoryActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }

    private void displaySpecificProductInfo() {
        productRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String pImage = dataSnapshot.child("image").getValue().toString();
                    String pName = dataSnapshot.child("pname").getValue().toString();
                    String pDescription = dataSnapshot.child("description").getValue().toString();
                    String pShipping = dataSnapshot.child("shipping").getValue().toString();
                    String pAddress = dataSnapshot.child("address").getValue().toString();
                    Name.setText(pName);
                    Description.setText(pDescription);
                    ShippingCost.setText(pShipping);
                    Address.setText(pAddress);
                    Picasso.get().load(pImage).into(imageView);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
