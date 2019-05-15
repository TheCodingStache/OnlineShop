package com.onlineshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.onlineshop.Model.Users;
import com.onlineshop.Prevalent.Prevalent;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {
    private EditText phone;
    private EditText password;
    private ProgressDialog loadingBar;
    private String parentDatabaseName = "Users";
    private CheckBox checkBox;
    private TextView sellerPanel;
    private TextView clientPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        loadingBar = new ProgressDialog(this);
        checkBox = findViewById(R.id.remember_me);
        sellerPanel = findViewById(R.id.seller_panel);
        clientPanel = findViewById(R.id.client_panel);
        Paper.init(this);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserLogin();
            }
        });
        sellerPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginButton.setText("Seller log in");
                sellerPanel.setVisibility(View.INVISIBLE);
                clientPanel.setVisibility(View.VISIBLE);
                parentDatabaseName = "Admins";
            }
        });
        clientPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginButton.setText("Client log in");
                clientPanel.setVisibility(View.INVISIBLE);
                sellerPanel.setVisibility(View.VISIBLE);
                parentDatabaseName = "Users";
            }
        });
    }

    private void UserLogin() {
        String mobilePhone = phone.getText().toString();
        String pass = password.getText().toString();
        if (TextUtils.isEmpty(mobilePhone)) {
            Toast.makeText(this, "Please write your mobile phone...", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_LONG).show();
        } else {
            loadingBar.setTitle("Please Login");
            loadingBar.setMessage("Please wait while we are checking the credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            allowAccessToAccount(mobilePhone, pass);
        }
    }

    private void allowAccessToAccount(final String phone, final String password) {
        if (checkBox.isChecked()) {
            Paper.book().write(Prevalent.Userphone, phone);
            Paper.book().write(Prevalent.UserPasswordKey, password);
        }
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference().child("posts");
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(parentDatabaseName).child(phone).exists()) {
                    Users userData = dataSnapshot.child(parentDatabaseName).child(phone).getValue(Users.class);
                    assert userData != null;
                    if (userData.getPhone().equals(phone) && userData.getPassword().equals(password)) {
                            if (parentDatabaseName.equals("Admins")) {
                                Toast.makeText(LoginActivity.this, "Hello seller! You logged in successfully!", Toast.LENGTH_LONG).show();
                                loadingBar.dismiss();
                                Intent openHomeActivity = new Intent(LoginActivity.this, AddProductActivity.class);
                                startActivity(openHomeActivity);
                                finish();
                            } else if (parentDatabaseName.equals("Users")) {
                                Toast.makeText(LoginActivity.this, "Logged in successfully!", Toast.LENGTH_LONG).show();
                                loadingBar.dismiss();
                                Intent openHomeActivity = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(openHomeActivity);
                                finish();
                            }
                        } else {
                            loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, "Wrong password", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Account with this" + phone + "number doesn't exists.", Toast.LENGTH_LONG).show();
                        loadingBar.dismiss();
                    }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
