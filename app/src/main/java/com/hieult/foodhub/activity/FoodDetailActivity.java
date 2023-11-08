package com.hieult.foodhub.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hieult.foodhub.R;

public class FoodDetailActivity extends AppCompatActivity {
    TextView food_name, food_rate, food_price, food_content, number_order;
    ImageView food_image, btn_minus, btn_plus, img_heart;
    CheckBox cb_favorite;
    int numberOrder = 1;
    String numberCart;
    Button btn_cart;

    FirebaseDatabase database;
    DatabaseReference foods;
    private String foodName, foodRate, foodPrice, foodContent, foodImageUrl;
    private Double priceValue;
    private String newStringPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        int foodPosition = getIntent().getIntExtra("foodPosition", -1);
        String foodType = getIntent().getStringExtra("foodType");
        database = FirebaseDatabase.getInstance();
        if (foodType!=null){
            foods = database.getReference().child("categories").child(foodType);
        }else {
            foods = database.getReference().child("searchFood").child("Food");
        }

        food_name = findViewById(R.id.txt_food_detail_name);
        food_rate = findViewById(R.id.txt_food_detail_rate);
        food_price = findViewById(R.id.txt_price_cart);
        food_content = findViewById(R.id.txt_food_detail_content);
        food_image = findViewById(R.id.img_food_detail);
        number_order = findViewById(R.id.txt_number_order);
        btn_plus = findViewById(R.id.btn_plus_cart);
        btn_minus = findViewById(R.id.btn_minus_cart);
        btn_cart = findViewById(R.id.btn_add_cart);
        cb_favorite = findViewById(R.id.cb_favorite);
        img_heart = findViewById(R.id.icon_favorite);

        Intent intent = getIntent();
        Log.d("MyIntent", intent.toString());
        boolean showFavorites = intent.getBooleanExtra("showFavorites", false);
        if (showFavorites){
            foodName = intent.getStringExtra("foodName");
            foodPrice = intent.getStringExtra("foodPrice");
            foodRate = intent.getStringExtra("foodRating");
            foodImageUrl = intent.getStringExtra("foodImage");
            Log.d("MyData", "showFavorites: " + showFavorites);
            Log.d("MyData", "foodName: " + foodName);
            Log.d("MyData", "foodPrice: " + foodPrice);
            Log.d("MyData", "foodRate: " + foodRate);
            Log.d("MyData", "foodImage: " + foodImageUrl);
            Glide.with(getBaseContext()).load(foodImageUrl)
                    .into(food_image);
            food_name.setText(foodName);
            food_rate.setText(foodRate);
            food_price.setText(foodPrice);
        } else {
            foods.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    DataSnapshot foodSnapshot = snapshot.child(Integer.toString(foodPosition));
                    foodName = foodSnapshot.child("name").getValue(String.class);
                    foodRate = foodSnapshot.child("rating").getValue(String.class);
                    foodPrice = foodSnapshot.child("price").getValue(String.class);
                    foodContent = foodSnapshot.child("description").getValue(String.class);
                    foodImageUrl = foodSnapshot.child("image").getValue(String.class);
                    Glide.with(getBaseContext()).load(foodImageUrl)
                            .into(food_image);
                    food_name.setText(foodName);
                    food_rate.setText(foodRate);
                    food_price.setText(foodPrice);
                    food_content.setText(foodContent);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder = numberOrder + 1;
                number_order.setText("" + numberOrder);
                numberCart = number_order.getText().toString();
            }
        });
        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder = numberOrder - 1;
                number_order.setText("" + numberOrder);
                numberCart = number_order.getText().toString();
            }
        });

        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent foodCart = new Intent(FoodDetailActivity.this, CartActivity.class);
                foodCart.putExtra("foodName", foodName);
                priceValue = Double.parseDouble(foodPrice.replace("$", ""));
                newStringPrice = String.format("$%.2f", priceValue * numberOrder);
                foodCart.putExtra("foodPrice", newStringPrice);
                foodCart.putExtra("foodImage", foodImageUrl);
                foodCart.putExtra("foodNumberOrder", numberCart);
                startActivity(foodCart);
            }
        });

        cb_favorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    img_heart.setVisibility(buttonView.VISIBLE);
                    AnimationSet animationSet = new AnimationSet(true);
                    animationSet.addAnimation(AnimationUtils.loadAnimation(getBaseContext(),R.anim.heart_animation));
                    img_heart.startAnimation(animationSet);
                    SharedPreferences sharedPreferences = getSharedPreferences("MyFavorite", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("foodName", foodName);
                    editor.putString("foodPrice", foodPrice);
                    editor.putString("foodImage", foodImageUrl);
                    editor.putString("foodRating", foodRate);
                    editor.apply();

                    animationSet.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            img_heart.setVisibility(View.INVISIBLE);
                            Toast.makeText(getBaseContext(),"You added food in favorite",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                }
            }
        });

    }


    public void btnBack(View view) {
        Intent intent = new Intent(FoodDetailActivity.this, MainActivity.class);
        startActivity(intent);
    }
}