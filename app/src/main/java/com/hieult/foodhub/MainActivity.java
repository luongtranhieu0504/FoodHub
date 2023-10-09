package com.hieult.foodhub;


import android.os.Bundle;


import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hieult.foodhub.adapters.FeaturedResHorAdapter;
import com.hieult.foodhub.adapters.FoodHorAdapter;
import com.hieult.foodhub.adapters.FoodPopularHorAdapter;
import com.hieult.foodhub.model.FeaturedResHorModel;
import com.hieult.foodhub.model.FoodHorModel;
import com.hieult.foodhub.model.FoodPopularHorModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    RecyclerView foodHorizontalRec;
    List<FoodHorModel> foodHorModelList;
    FoodHorAdapter foodHorAdapter;
    RecyclerView featuredResHorizontalRec;
    List<FeaturedResHorModel> featuredResHorModelList;
    FeaturedResHorAdapter featuredResHorAdapter;
    RecyclerView foodPopularHorizontalRec;
    List<FoodPopularHorModel> foodPopularHorModelList;
    FoodPopularHorAdapter foodPopularHorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        drawerLayout = findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
//                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();

        foodHorizontalRec = findViewById(R.id.foot_hor_rec);
        foodHorModelList = new ArrayList<>();
        foodHorModelList.add(new FoodHorModel(R.drawable.icon_hamberger,"Burger"));
        foodHorModelList.add(new FoodHorModel(R.drawable.icon_donut,"Donat"));
        foodHorModelList.add(new FoodHorModel(R.drawable.icon_pizza,"Pizza"));
        foodHorModelList.add(new FoodHorModel(R.drawable.icon_hotdog,"Mexican"));
        foodHorModelList.add(new FoodHorModel(R.drawable.icon_chesee,"Asian"));
        foodHorModelList.add(new FoodHorModel(R.drawable.icon_icream,"Asia"));

        foodHorAdapter = new FoodHorAdapter(this,foodHorModelList);
        foodHorizontalRec.setAdapter(foodHorAdapter);
        foodHorizontalRec.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        foodHorizontalRec.setHasFixedSize(true);
        foodHorizontalRec.setNestedScrollingEnabled(false);

        featuredResHorizontalRec = findViewById(R.id.featured_res_hor);
        featuredResHorModelList = new ArrayList<>();
        featuredResHorModelList.add(new FeaturedResHorModel(R.drawable.food_img1,"McDonald’s","Free delivery","10-15 mins","4.5 (25+)"));
        featuredResHorModelList.add(new FeaturedResHorModel(R.drawable.food_img2,"Starbuck","Free delivery","10-15 mins","4.7 (99+)"));

        featuredResHorAdapter = new FeaturedResHorAdapter(this,featuredResHorModelList);
        featuredResHorizontalRec.setAdapter(featuredResHorAdapter);
        featuredResHorizontalRec.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        featuredResHorizontalRec.setHasFixedSize(true);
        featuredResHorizontalRec.setNestedScrollingEnabled(false);

        foodPopularHorizontalRec = findViewById(R.id.food_popular_res_hor);
        foodPopularHorModelList = new ArrayList<>();
        foodPopularHorModelList.add(new FoodPopularHorModel(R.drawable.price_food_img1,"$5.50","4.5","Salmon Salad","Baked salmon fish"));
        foodPopularHorModelList.add(new FoodPopularHorModel(R.drawable.price_food_img2,"S8.25","4.5","Salmon Salad","Baked salmon fish"));

        foodPopularHorAdapter = new FoodPopularHorAdapter(this,foodPopularHorModelList);
        foodPopularHorizontalRec.setAdapter(foodPopularHorAdapter);
        foodPopularHorizontalRec.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        foodPopularHorizontalRec.setHasFixedSize(true);
        foodPopularHorizontalRec.setNestedScrollingEnabled(false);




    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}