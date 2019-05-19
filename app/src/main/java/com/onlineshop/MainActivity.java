package com.onlineshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.onlineshop.Model.Users;
import com.onlineshop.Prevalent.Prevalent;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {
    ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button joinButton = findViewById(R.id.main_register_button);
        Button loginButton = findViewById(R.id.main_login_button);
        loadingBar = new ProgressDialog(this);
        Paper.init(this);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openLogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(openLogin);
            }
        });
        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(MainActivity.this, SelectUserOptionActivity.class);
                startActivity(register);
            }
        });
//        String username = Paper.book().read(Prevalent.Username);
//        String passwordKey = Paper.book().read(Prevalent.UserPasswordKey);
//        if (username != null && passwordKey != null) {
//            if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(passwordKey)) {
//                AllowAccess(username, passwordKey);
//                loadingBar.setTitle("Already Logged in");
//                loadingBar.setMessage("Please wait...");
//                loadingBar.setCanceledOnTouchOutside(false);
//                loadingBar.show();
//            }
//        }
//    }
//
//    private void AllowAccess(final String username, final String password) {
//        final DatabaseReference RootRef;
//        RootRef = FirebaseDatabase.getInstance().getReference().child("Users");
//        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.child("Users").child(username).exists()) {
//                    Users userData = dataSnapshot.child("Users").child(username).getValue(Users.class);
//                    if (userData.getUsername() != null && userData.getUsername().equals(username)) {
//                        if (userData.getPassword().equals(password)) {
//                            Toast.makeText(MainActivity.this, "Logged in successfully!", Toast.LENGTH_LONG).show();
//                            loadingBar.dismiss();
//                            Intent openHomeActivity = new Intent(MainActivity.this, HomeActivity.class);
//                            Prevalent.currentOnlineUser = userData;
//                            startActivity(openHomeActivity);
//                        }
//                    }
//                }  else {
//                    Toast.makeText(MainActivity.this, "Account with this " + username + " number do not exist", Toast.LENGTH_LONG).show();
//                    loadingBar.dismiss();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }
}
