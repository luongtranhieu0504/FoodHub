package com.hieult.foodhub.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.hieult.foodhub.R;
import com.stripe.android.model.PaymentMethod;

public class PaymentTestActivity extends AppCompatActivity {
    RadioButton cardRd, cashRd;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_test);
        cardRd = findViewById(R.id.rd_card);
        cashRd = findViewById(R.id.rd_cash);
        btnNext = findViewById(R.id.btn_next1);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentTestActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });
    }
}