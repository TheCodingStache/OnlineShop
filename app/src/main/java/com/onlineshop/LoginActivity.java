package com.onlineshop;

import android.annotation.SuppressLint;
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
import com.onlineshop.Prevalent.CategoryActivity;
import com.onlineshop.Prevalent.Prevalent;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
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
        username = findViewById(R.id.username);
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
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                loginButton.setText("Seller log in");
                sellerPanel.setVisibility(View.INVISIBLE);
                clientPanel.setVisibility(View.VISIBLE);
                parentDatabaseName = "Sellers";
            }
        });
        clientPanel.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                loginButton.setText("Client log in");
                clientPanel.setVisibility(View.INVISIBLE);
                sellerPanel.setVisibility(View.VISIBLE);
                parentDatabaseName = "Clients";
            }
        });
    }

    private void UserLogin() {
        String name = username.getText().toString();
        String pass = password.getText().toString();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please write your username...", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_LONG).show();
        } else {
            loadingBar.setTitle("Please Login");
            loadingBar.setMessage("Please wait while we are checking the credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            allowAccessToAccount(name, pass);
        }
    }

    private void allowAccessToAccount(final String username, final String password) {
        if (checkBox.isChecked()) {
            Paper.book().write(Prevalent.Username, username);
            Paper.book().write(Prevalent.UserPasswordKey, password);
        }
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference().child("Shop");
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(parentDatabaseName).child(username).exists()) {
                    Users usersData = dataSnapshot.child(parentDatabaseName).child(username).getValue(Users.class);
                    if (usersData.getUsername() != null && usersData.getUsername().equals(username)) {
                        if (usersData.getPassword().equals(password)) {
                            if (parentDatabaseName.equals("Sellers")) {
                                Toast.makeText(LoginActivity.this, "Welcome Admin, you are logged in Successfully...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Intent intent = new Intent(LoginActivity.this, CategoryActivity.class);
                                startActivity(intent);
                            } else if (parentDatabaseName.equals("Clients")) {
                                Toast.makeText(LoginActivity.this, "logged in Successfully...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                Prevalent.currentOnlineUser = usersData;
                                startActivity(intent);
                            }
                        } else {
                            loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, "Password is incorrect.", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Account with " + username + " username, doesn't exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
