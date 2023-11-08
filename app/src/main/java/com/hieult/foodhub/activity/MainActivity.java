package com.hieult.foodhub.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.FirebaseDatabase;
import com.hieult.foodhub.R;
import com.hieult.foodhub.adapters.FeaturedResHorAdapter;
import com.hieult.foodhub.adapters.FoodHorAdapter;
import com.hieult.foodhub.adapters.FoodPopularHorAdapter;
import com.hieult.foodhub.model.FeaturedResHorModel;
import com.hieult.foodhub.model.FoodHorModel;
import com.hieult.foodhub.model.FoodPopularHorModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageButton openDrawerButton;
    RecyclerView foodHorizontalRec;
    List<FoodHorModel> foodHorModelList;
    FoodHorAdapter foodHorAdapter;
    RecyclerView featuredResHorizontalRec;
    FeaturedResHorAdapter featuredResHorAdapter;
    RecyclerView foodPopularHorizontalRec;
    List<FoodPopularHorModel> foodPopularHorModelList;
    FoodPopularHorAdapter foodPopularHorAdapter;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openDrawerButton = findViewById(R.id.openDrawerLayout);
        navigationView = findViewById(R.id.nav_view );
        drawerLayout = findViewById(R.id.drawer_layout);
        bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationView.setSelectedItemId(R.id.bt_nav_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId==R.id.bt_nav_home){
                    return true;
                }else if (itemId == R.id.bt_nav_favor){
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
                else if (itemId == R.id.bt_nav_account){
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    return true;
                }
                return false;
            }
        });


        openDrawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navigationView.setNavigationItemSelectedListener(this);


//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
//                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();

        foodHorizontalRec = findViewById(R.id.foot_hor_rec);
        foodHorModelList = new ArrayList<>();
        foodHorModelList.add(new FoodHorModel(R.drawable.icon_hamberger,"Burger"));
        foodHorModelList.add(new FoodHorModel(R.drawable.icon_donut,"Donuts"));
        foodHorModelList.add(new FoodHorModel(R.drawable.icon_pizza,"Pizza"));
        foodHorModelList.add(new FoodHorModel(R.drawable.icon_hotdog,"Drink"));
        foodHorModelList.add(new FoodHorModel(R.drawable.icon_hotdog,"Snack"));
        foodHorAdapter = new FoodHorAdapter(this,foodHorModelList);
        foodHorAdapter.setOnItemClickListener(new FoodHorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position,String typeFood) {
                FirebaseRecyclerOptions<FeaturedResHorModel> options =
                        new FirebaseRecyclerOptions.Builder<FeaturedResHorModel>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("categories").child(typeFood), FeaturedResHorModel.class)
                                .build();
               featuredResHorAdapter.updateOptions(options);
               featuredResHorizontalRec.setAdapter(featuredResHorAdapter);
                featuredResHorAdapter.setOnItemClickListener(new FeaturedResHorAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent foodDetail = new Intent(MainActivity.this, FoodDetailActivity.class);
                        foodDetail.putExtra("foodPosition", position);
                        String foodType = typeFood;
                        foodDetail.putExtra("foodType", foodType);
                        startActivity(foodDetail);
                    }
                });
            }
        });
        foodHorizontalRec.setAdapter(foodHorAdapter);
        foodHorizontalRec.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        foodHorizontalRec.setHasFixedSize(true);
        foodHorizontalRec.setNestedScrollingEnabled(false);

        featuredResHorizontalRec = findViewById(R.id.featured_res_hor);
        featuredResHorizontalRec.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        FirebaseRecyclerOptions<FeaturedResHorModel> options =
                new FirebaseRecyclerOptions.Builder<FeaturedResHorModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("categories").child("Burger"), FeaturedResHorModel.class)
                        .build();
        featuredResHorAdapter = new FeaturedResHorAdapter(options);
        featuredResHorizontalRec.setAdapter(featuredResHorAdapter);
        featuredResHorAdapter.setOnItemClickListener(new FeaturedResHorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent foodDetail = new Intent(MainActivity.this, FoodDetailActivity.class);
                foodDetail.putExtra("foodPosition", position);
                String foodType = "Burger";
                foodDetail.putExtra("foodType", foodType);
                startActivity(foodDetail);
            }
        });



//        foodPopularHorizontalRec = findViewById(R.id.food_popular_res_hor);
//        foodPopularHorModelList = new ArrayList<>();
//        foodPopularHorModelList.add(new FoodPopularHorModel(R.drawable.price_food_img1,"$5.50","4.5","Salmon Salad","Baked salmon fish"));
//        foodPopularHorModelList.add(new FoodPopularHorModel(R.drawable.price_food_img2,"S8.25","4.5","Salmon Salad","Baked salmon fish"));
//
//        foodPopularHorAdapter = new FoodPopularHorAdapter(this,foodPopularHorModelList);
//        foodPopularHorizontalRec.setAdapter(foodPopularHorAdapter);
//        foodPopularHorizontalRec.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
//        foodPopularHorizontalRec.setHasFixedSize(true);
//        foodPopularHorizontalRec.setNestedScrollingEnabled(false);



    }

    @Override
    protected void onStart() {
        super.onStart();
        featuredResHorAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        featuredResHorAdapter.stopListening();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_order) {
            Intent intent = new Intent(MainActivity.this,MyOrdersActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_profile){
            Intent intent = new Intent(MainActivity.this,MyProfileActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_favorite){
            Intent intent = new Intent(MainActivity.this,FavoritesActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_cart){
            Intent intent = new Intent(MainActivity.this,CartActivity.class);
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void searchClick(View view) {
        if (view.getId()==R.id.searchEditText){
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this,"Clicked outside the search box", Toast.LENGTH_SHORT).show();
        }

    }
}