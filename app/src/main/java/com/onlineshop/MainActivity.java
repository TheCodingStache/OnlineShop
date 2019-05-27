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
import com.onlineshop.Login.LoginActivity;
import com.onlineshop.Model.Users;
import com.onlineshop.Prevalent.CurrentUser;
import com.onlineshop.Register.SelectUserOptionActivity;

import io.paperdb.Paper;
public class MainActivity extends AppCompatActivity {
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button joinNowButton = findViewById(R.id.main_register_button);
        Button loginButton = findViewById(R.id.main_login_button);
        loadingBar = new ProgressDialog(this);
        Paper.init(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        joinNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SelectUserOptionActivity.class);
                startActivity(intent);
            }
        });


        String Username = Paper.book().read(CurrentUser.Username);
        String UserPasswordKey = Paper.book().read(CurrentUser.UserPasswordKey);
        if (Username != null && UserPasswordKey != null) {
            if (!TextUtils.isEmpty(Username) && !TextUtils.isEmpty(UserPasswordKey)) {
                AllowClientAccess(Username, UserPasswordKey);
                loadingBar.setTitle("Already Logged in");
                loadingBar.setMessage("Please wait.....");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
            }
        }
    }

    private void AllowClientAccess(final String username, final String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Clients").child(username).exists()) {
                    Users usersData = dataSnapshot.child("Clients").child(username).getValue(Users.class);
                    if (usersData.getUsername().equals(username)) {
                        if (usersData.getPassword().equals(password)) {
                            Toast.makeText(MainActivity.this, "Please wait, you are already logged in...", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            CurrentUser.currentOnlineUser = usersData;
                            startActivity(intent);
                        } else {
                            loadingBar.dismiss();
                            Toast.makeText(MainActivity.this, "Password is incorrect.", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Account with this " + username + " do not exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}