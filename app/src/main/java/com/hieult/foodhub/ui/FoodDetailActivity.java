package com.hieult.foodhub.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.hieult.foodhub.R;
import com.hieult.foodhub.adapters.FoodPopularHorAdapter;
import com.hieult.foodhub.model.FoodPopularHorModel;

import java.util.ArrayList;
import java.util.List;

public class FoodDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
    }


}