package com.hieult.foodhub.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hieult.foodhub.R;
import com.hieult.foodhub.adapters.NotifyVerAdapter;
import com.hieult.foodhub.model.NotifyVerModel;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ImageView ivReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationView.setSelectedItemId(R.id.bt_nav_notify);
        ivReturn=findViewById(R.id.ivReturn);
        RecyclerView listnotify = findViewById(R.id.rcviewNotify);

        listnotify.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        List<NotifyVerModel> notifyVerModel= new ArrayList<>();
        notifyVerModel.add(new NotifyVerModel("Welcome","Hey"));
        notifyVerModel.add(new NotifyVerModel("Welcome1","Hey1"));
        NotifyVerAdapter notifyVerAdapter = new NotifyVerAdapter(this,notifyVerModel);
        listnotify.setAdapter(notifyVerAdapter);

        ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId==R.id.bt_nav_notify){
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
                else if (itemId == R.id.bt_nav_account){
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    return true;
                }
                return false;
            }
        });
    }
}