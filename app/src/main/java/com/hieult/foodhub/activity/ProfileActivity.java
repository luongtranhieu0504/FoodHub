package com.hieult.foodhub.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hieult.foodhub.R;

public class ProfileActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ImageView btnBack;
    Button btnEditProfile;
    TextView txt_name,txt_email,txt_phoneNumber;
    String name,email,phoneNumber;
    CardView favorScreen,myOrder,coupons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationView.setSelectedItemId(R.id.bt_nav_account);
        btnBack = findViewById(R.id.btn_profile_back);
        btnEditProfile = findViewById(R.id.btn_edit_profile);
        txt_name = findViewById(R.id.txt_my_profile_name);
        txt_email = findViewById(R.id.txt_my_profile_email);
        txt_phoneNumber = findViewById(R.id.txt_my_profile_number);
        favorScreen = findViewById(R.id.cardview_my_favorite);
        myOrder = findViewById(R.id.cardview_my_order);
        coupons = findViewById(R.id.cardview_coupons);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId==R.id.bt_nav_account){
                    return true;
                }else if (itemId == R.id.bt_nav_home){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    return true;
                }
                else if (itemId == R.id.bt_nav_favor){
                    startActivity(new Intent(getApplicationContext(), FavoritesActivity.class));
                    return true;
                }
                else if (itemId == R.id.bt_nav_shop_cart){
                    startActivity(new Intent(getApplicationContext(), CartActivity.class));
                    return true;
                }
                else if (itemId == R.id.bt_nav_notify){
                    startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
                    return true;
                }
                return false;
            }
        });
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("NEW_NAME") && intent.hasExtra("NEW_EMAIL") && intent.hasExtra("NEW_PHONE")) {
            String newName = intent.getStringExtra("NEW_NAME");
            String newEmail = intent.getStringExtra("NEW_EMAIL");
            String newPhone = intent.getStringExtra("NEW_PHONE");
            txt_name.setText(newName);
            txt_email.setText(newEmail);
            txt_phoneNumber.setText(newPhone);
        } else {
            SharedPreferences sharedPreferences = getSharedPreferences("MyUser", Context.MODE_PRIVATE);
            name = sharedPreferences.getString("name","Ronaldo");
            email = sharedPreferences.getString("email","ronaldo@gmail.com");
            phoneNumber = sharedPreferences.getString("phone","0283748594");
            txt_name.setText(name);
            txt_email.setText(email);
            txt_phoneNumber.setText(phoneNumber);
        }
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MyProfileActivity.class);
                startActivity(intent);
            }
        });
        favorScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, FavoritesActivity.class);
                startActivity(intent);
            }
        });
        myOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
//        favorScreen.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ProfileActivity.this, Coupons.class);
//                startActivity(intent);
//            }
//        });
    }
}