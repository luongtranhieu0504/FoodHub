package com.hieult.foodhub.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hieult.foodhub.R;
import com.hieult.foodhub.data.DatabaseHelper;
import com.hieult.foodhub.model.FoodCartVerModel;

import java.util.List;

public class StateDeliveryActivity extends AppCompatActivity {
    Button complete;
    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_delivery);
        complete = findViewById(R.id.btn_track_order);
        dbHelper = new DatabaseHelper(this);
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StateDeliveryActivity.this, MainActivity.class);
                List<FoodCartVerModel> cartItems = dbHelper.getCartItems();
                for (FoodCartVerModel cartItem : cartItems) {
                    dbHelper.addToComplete(cartItem.getNameCart(), cartItem.getPriceCart(), cartItem.getImageCart(), cartItem.getNumberOrderCart());
                }
                dbHelper.deleteAllFromCart();
                startActivity(intent);
            }
        });
    }
}