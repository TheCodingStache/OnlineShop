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
    private EditText username;
    private EditText password;
    private Button loginButton;
    private ProgressDialog loadingBar;
    private String parentDatabaseName = "Users";
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        loadingBar = new ProgressDialog(this);
        checkBox = findViewById(R.id.remember_me);
        Paper.init(this);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserLogin();
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

    private void allowAccessToAccount(final String phone, final String password) {
        if(checkBox.isChecked()){
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
                    if (userData.getPhone().equals(phone)) {
                        if (userData.getPassword().equals(password)) {
                            Toast.makeText(LoginActivity.this, "Logged in successfully!", Toast.LENGTH_LONG).show();
                            loadingBar.dismiss();
                            Intent openHomeActivity = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(openHomeActivity);
                        }
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Account with this " + username + " number do not exist", Toast.LENGTH_LONG).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
