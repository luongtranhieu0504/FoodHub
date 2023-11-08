package com.hieult.foodhub.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.hieult.foodhub.R;

public class PaymentActivity extends AppCompatActivity {
    CardView chooseAddress,payMethod;
    ImageView btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        chooseAddress = findViewById(R.id.cardview_address);
        payMethod = findViewById(R.id.cardview_payment);
        btn_back = findViewById(R.id.btn_payment_back);
        chooseAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentActivity.this,ChooseAddressActivity.class);
                startActivity(intent);
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(PaymentActivity.this,CartActivity.class);
                startActivity(intent1);
            }
        });
    }
}