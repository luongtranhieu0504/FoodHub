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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hieult.foodhub.R;
import com.hieult.foodhub.adapters.FoodCartVerAdapter;
import com.hieult.foodhub.data.DatabaseHelper;
import com.hieult.foodhub.model.FoodCartVerModel;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    TextView cart_subtotal, cart_tax_fees, cart_delivery, cart_total;
    double cartSubtotal = 0.0, cartTaxFees = 5.30, cartDelivery = 10.0, cartTotal = 0.00;
    RecyclerView foodCartVerRec;
    List<FoodCartVerModel> foodCartVerModelList;
    DatabaseHelper dbHelper;
    FoodCartVerAdapter foodCartVerAdapter;
    Button checkOut;
    ImageView btn_back;

    private boolean dataAdded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationView.setSelectedItemId(R.id.bt_nav_shop_cart);
        cart_subtotal = findViewById(R.id.txt_price_subtotal);
        cart_tax_fees = findViewById(R.id.txt_tax_fees);
        cart_delivery = findViewById(R.id.txt_cart_delivery);
        cart_total = findViewById(R.id.txt_cart_total);
        checkOut = findViewById(R.id.btn_checkout);
        btn_back = findViewById(R.id.btn_cart_back);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.bt_nav_shop_cart) {
                    return true;
                } else if (itemId == R.id.bt_nav_home) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    return true;
                } else if (itemId == R.id.bt_nav_favor) {
                    startActivity(new Intent(getApplicationContext(), FavoritesActivity.class));
                    return true;
                } else if (itemId == R.id.bt_nav_notify) {
                    startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
                    return true;
                } else if (itemId == R.id.bt_nav_account) {
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    return true;
                }
                return false;
            }
        });
        dbHelper = new DatabaseHelper(this);
        Intent intent = getIntent();
        String foodName = intent.getStringExtra("foodName");
        String foodPrice = intent.getStringExtra("foodPrice");
        String foodImage = intent.getStringExtra("foodImage");
        String foodNumberOrder = intent.getStringExtra("foodNumberOrder");
        foodCartVerRec = findViewById(R.id.food_cart_rec);
        foodCartVerRec.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        dbHelper.addToCart(foodName, foodPrice, foodImage, foodNumberOrder);
        foodCartVerModelList = dbHelper.getCartItems();
        foodCartVerAdapter = new FoodCartVerAdapter(this, foodCartVerModelList);
        foodCartVerRec.setAdapter(foodCartVerAdapter);
        for (FoodCartVerModel food : foodCartVerModelList) {
            String foodPriceStr = food.getPriceCart();
            if (foodPriceStr != null && !foodPriceStr.isEmpty()) {
                try {
                    double foodPrice1 = Double.parseDouble(foodPriceStr.replace("$", ""));
                    cartSubtotal += foodPrice1;
                    cart_subtotal.setText(String.format("$%.2f", cartSubtotal));
                    cart_tax_fees.setText(String.format("$%.2f", cartTaxFees));
                    cart_delivery.setText(String.format("$%.2f", cartDelivery));
                    cartTotal = cartSubtotal + cartTaxFees + cartDelivery;
                    cart_total.setText(String.format("$%.2f", cartTotal));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        foodCartVerAdapter.setOnItemClickListener(new FoodCartVerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (position != RecyclerView.NO_POSITION){
                    String foodName = foodCartVerModelList.get(position).getNameCart();
                    String foodPrice = foodCartVerModelList.get(position).getPriceCart();
                    double foodPrice2 = Double.parseDouble(foodPrice.replace("$", ""));
                    DatabaseHelper helper = new DatabaseHelper(getBaseContext());
                    helper.deleteFromCart(foodName);
                    foodCartVerModelList.remove(position);
                    cartSubtotal -= foodPrice2;
                    cart_subtotal.setText(String.format("$%.2f", cartSubtotal));
                    cartTotal = cartSubtotal + cartTaxFees + cartDelivery;
                    cart_total.setText(String.format("$%.2f", cartTotal));
                }else {
                    if(position == 0) {
                        cartSubtotal = 0.0;
                        cartTaxFees = 0.0;
                        cartDelivery = 0.0;
                        cart_subtotal.setText(String.format("$%.2f", cartSubtotal));
                        cart_tax_fees.setText(String.format("$%.2f", cartTaxFees));
                        cart_delivery.setText(String.format("$%.2f", cartDelivery));
                        cartTotal = cartSubtotal + cartTaxFees + cartDelivery;
                        cart_total.setText(String.format("$%.2f", cartTotal));
                    }
                }
            }
        });
        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("PaymentMethod", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                float floatCartTotal = (float) cartTotal;
                editor.putFloat("cartTotal", floatCartTotal);
                editor.apply();
                Intent intent = new Intent(CartActivity.this,PaymentActivity.class);
                startActivity(intent);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(CartActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });
    }

}