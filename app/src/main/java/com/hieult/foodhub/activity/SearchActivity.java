package com.hieult.foodhub.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.hieult.foodhub.R;
import com.hieult.foodhub.adapters.FoodSearchVerAdapter;
import com.hieult.foodhub.model.FoodSearchVerModel;

public class SearchActivity extends AppCompatActivity {
    RecyclerView foodSearchVerticalRec;
    FoodSearchVerAdapter foodSearchVerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        foodSearchVerticalRec = findViewById(R.id.search_ver_rec);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        foodSearchVerticalRec.setLayoutManager(gridLayoutManager);
        FirebaseRecyclerOptions<FoodSearchVerModel> options1 =
                new FirebaseRecyclerOptions.Builder<FoodSearchVerModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("searchFood").child("Food"), FoodSearchVerModel.class)
                        .build();
        foodSearchVerAdapter = new FoodSearchVerAdapter(options1);
        foodSearchVerticalRec.setAdapter(foodSearchVerAdapter);
        foodSearchVerAdapter.setOnItemClickListener(new FoodSearchVerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent foodDetail = new Intent(SearchActivity.this, FoodDetailActivity.class);
                foodDetail.putExtra("foodPosition", position);
                startActivity(foodDetail);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        foodSearchVerAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        foodSearchVerAdapter.stopListening();
    }


}