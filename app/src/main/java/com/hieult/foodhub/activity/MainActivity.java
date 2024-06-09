package com.hieult.foodhub.activity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.FirebaseDatabase;
import com.hieult.foodhub.R;
import com.hieult.foodhub.adapters.FeaturedResHorAdapter;
import com.hieult.foodhub.adapters.FoodHorAdapter;
import com.hieult.foodhub.adapters.FoodPopularHorAdapter;
import com.hieult.foodhub.adapters.SlideAdapter;
import com.hieult.foodhub.data.StateManager;
import com.hieult.foodhub.model.FeaturedResHorModel;
import com.hieult.foodhub.model.FoodHorModel;
import com.hieult.foodhub.model.FoodPopularHorModel;
import com.hieult.foodhub.model.SlideItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageButton openDrawerButton;
    RecyclerView foodHorizontalRec;
    SearchView searchText;
    List<FoodHorModel> foodHorModelList;
    FoodHorAdapter foodHorAdapter;
    RecyclerView featuredResHorizontalRec;
    FeaturedResHorAdapter featuredResHorAdapter;

    BottomNavigationView bottomNavigationView;
    ViewPager2 viewPager2;
    Handler slideHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openDrawerButton = findViewById(R.id.openDrawerLayout);
        navigationView = findViewById(R.id.nav_view );
        drawerLayout = findViewById(R.id.drawer_layout);
        bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationView.setSelectedItemId(R.id.bt_nav_home);
        searchText = findViewById(R.id.searchEditText);
        viewPager2 = findViewById(R.id.card_banner);
        List<SlideItem> slideItems = new ArrayList<>();
        slideItems.add(new SlideItem(R.drawable.slide1));
        slideItems.add(new SlideItem(R.drawable.slide2));
        slideItems.add(new SlideItem(R.drawable.slide3));
        slideItems.add(new SlideItem(R.drawable.slide4));
        viewPager2.setAdapter(new SlideAdapter(slideItems,viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(4);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                slideHandler.removeCallbacks(sliderRunnable);
                slideHandler.postDelayed(sliderRunnable,2000);
            }
        });



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



        foodHorizontalRec = findViewById(R.id.foot_hor_rec);
        foodHorModelList = new ArrayList<>();
        foodHorModelList.add(new FoodHorModel(R.drawable.icon_hamberger,"Burger"));
        foodHorModelList.add(new FoodHorModel(R.drawable.icon_donut,"Donuts"));
        foodHorModelList.add(new FoodHorModel(R.drawable.icon_pizza,"Pizza"));
        foodHorModelList.add(new FoodHorModel(R.drawable.icon_drink,"Drink"));
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
        searchText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("searchQuery", query);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Xử lý sự thay đổi văn bản nếu cần
                return true;
            }
        });






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
    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_order) {
            Intent intent = new Intent(MainActivity.this,MyOrderActivity.class);
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

}