package com.hieult.foodhub.activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hieult.foodhub.R;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    EditText loginUserName,loginPassword;
    Button login;
    String email,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUserName = findViewById(R.id.txt_login_user_name);
        loginPassword = findViewById(R.id.txt_login_password);
        login = findViewById(R.id.btn_login_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUser();
            }
        });

    }
    public Boolean validateUserName(){
        String val = loginUserName.getText().toString();
        if (val.isEmpty()){
            loginUserName.setError("email cannot empty");
            return false;
        }else {
            loginUserName.setError(null);
            return true;
        }
    }
    public Boolean validatePassword(){
        String val = loginPassword.getText().toString();
        if (val.isEmpty()){
            loginPassword.setError("password cannot empty");
            return false;
        }else {
            loginPassword.setError(null);
            return true;
        }
    }
    public void checkUser(){
        String userUserName = loginUserName.getText().toString().trim();
        String userPassword = loginPassword.getText().toString().trim();
        Log.d("MyLogin", "showPassword: " + userPassword);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("name").equalTo(userUserName);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    loginUserName.setError(null);
                    String passwordFromDB = snapshot.child(userUserName).child("password").getValue(String.class);
                    if (Objects.equals(passwordFromDB,userPassword)){
                        email = snapshot.child(userUserName).child("email").getValue(String.class);
                        phone = snapshot.child(userUserName).child("phone").getValue(String.class);
                        Log.d("MyLogin", "showemail: " + email);
                        Log.d("MyLogin", "showPhone: " + phone);
                        SharedPreferences sharedPreferences = getSharedPreferences("MyUser", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("name", userUserName);
                        editor.putString("email", email);
                        editor.putString("phoneNumber", phone);
                        editor.putString("password",passwordFromDB);
                        editor.apply();
                        loginUserName.setError(null);
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        loginPassword.setError("wrong password");
                        loginUserName.requestFocus();
                    }
                }else {
                    loginUserName.setError("user Name does not exist");
                    loginUserName.requestFocus();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


    }

    public void signUp(View view) {
        startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
    }
}