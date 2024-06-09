package com.hieult.foodhub.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hieult.foodhub.R;
import com.hieult.foodhub.adapters.FoodCartVerAdapter;
import com.hieult.foodhub.adapters.FoodCategoryAdapter;
import com.hieult.foodhub.data.DatabaseFavorHelper;
import com.hieult.foodhub.model.FoodPopularHorModel;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity {
    RecyclerView foodCategoryRec;
    ImageView btn_back;
    List<FoodPopularHorModel> foodFavorVerModelList;
    FoodCategoryAdapter foodFaverAdapter;
    BottomNavigationView bottomNavigationView;
    String foodName,foodPrice,foodImage,foodRating;
    DatabaseFavorHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        foodCategoryRec = findViewById(R.id.favorites_food_rec);
        btn_back = findViewById(R.id.btn_favor_back);
        dbHelper = new DatabaseFavorHelper(this);
        SharedPreferences sharedPreferences = getSharedPreferences("MyFavorite",Context.MODE_PRIVATE);
        foodName = sharedPreferences.getString("foodName","Chicken");
        foodPrice = sharedPreferences.getString("foodPrice","$8.80");
        foodImage = sharedPreferences.getString("foodImage", "https://aeonmall-long-bien.com.vn/wp-content/uploads/2020/01/kfc3-750x468.jpg");
        foodRating = sharedPreferences.getString("foodRating","5.0");
        foodCategoryRec.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL,false));
        dbHelper.addToCart(foodName,foodPrice,foodImage,foodRating);
        foodFavorVerModelList = dbHelper.getCartItems();
        foodFaverAdapter = new FoodCategoryAdapter(this,foodFavorVerModelList);
        foodCategoryRec.setAdapter(foodFaverAdapter);
        foodFaverAdapter.setOnItemClickListener(new FoodCartVerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent foodDetail3 = new Intent(FavoritesActivity.this, FoodDetailActivity.class);
                foodDetail3.putExtra("foodName",foodFavorVerModelList.get(position).getName());
                foodDetail3.putExtra("foodPrice",foodFavorVerModelList.get(position).getPrice());
                foodDetail3.putExtra("foodImage",foodFavorVerModelList.get(position).getImage());
                foodDetail3.putExtra("foodRating",foodFavorVerModelList.get(position).getRate());
                foodDetail3.putExtra("showFavorites",true);
                startActivity(foodDetail3);
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FavoritesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });






        bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationView.setSelectedItemId(R.id.bt_nav_favor);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId==R.id.bt_nav_favor){
                    return true;
                }else if (itemId == R.id.bt_nav_home){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
                else if (itemId == R.id.bt_nav_account){
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    return true;
                }
                return false;
            }
        });
    }

}