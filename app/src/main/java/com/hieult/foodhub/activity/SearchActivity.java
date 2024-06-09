package com.hieult.foodhub.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hieult.foodhub.R;
import com.hieult.foodhub.adapters.FoodSearchVerAdapter;
import com.hieult.foodhub.model.FoodSearchVerModel;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    RecyclerView foodSearchVerticalRec;
    FoodSearchVerAdapter foodSearchVerAdapter;
    SearchView searchView;
    ImageView btnBack;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = findViewById(R.id.search_view);
        btnBack = findViewById(R.id.btn_search_back);
        String search = getIntent().getStringExtra("searchFood");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("searchFood").child("Food");
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
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("searchQuery")) {
            String searchQuery = intent.getStringExtra("searchQuery");
            // Cập nhật SearchView và thực hiện truy vấn
            searchView.setQuery(searchQuery, true);
            queryFoodList(searchQuery);
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                queryFoodList(newText);
                return true;
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void queryFoodList(String searchText) {
        Query query = FirebaseDatabase.getInstance().getReference()
                .child("searchFood")
                .child("Food")
                .orderByChild("name")
                .startAt(searchText)
                .endAt(searchText + "\uf8ff" + "\uf8ff");

        FirebaseRecyclerOptions<FoodSearchVerModel> options =
                new FirebaseRecyclerOptions.Builder<FoodSearchVerModel>()
                        .setQuery(query, FoodSearchVerModel.class)
                        .build();
        foodSearchVerAdapter.updateOptions(options);
        foodSearchVerAdapter.notifyDataSetChanged();
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