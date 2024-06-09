package com.hieult.foodhub.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import com.google.android.material.tabs.TabLayout;
import com.hieult.foodhub.R;
import com.hieult.foodhub.adapters.FoodCartVerAdapter;
import com.hieult.foodhub.adapters.MyOrderVerAdapter;
import com.hieult.foodhub.adapters.MyPagerAdapter;
import com.hieult.foodhub.data.DatabaseHelper;
import com.hieult.foodhub.model.FoodCartVerModel;

import java.util.ArrayList;
import java.util.List;

public class MyOrderActivity extends AppCompatActivity {
    RecyclerView myOrderRec;
    ImageView btn_back;
    private LinearLayout buttonContainer;
    ToggleButton tgCurrent,tgComplete,tgCancelled;
    List<FoodCartVerModel> foodCartVerModelList;
    DatabaseHelper dbHelper;
    MyOrderVerAdapter myOrderVerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        myOrderRec = findViewById(R.id.order_rec);
        buttonContainer = findViewById(R.id.buttonContainer);
        tgCurrent = findViewById(R.id.current);
        tgComplete = findViewById(R.id.complete);
        tgCancelled = findViewById(R.id.cancelled);
        btn_back = findViewById(R.id.ivReturn_order);
        dbHelper = new DatabaseHelper(this);
        foodCartVerModelList = new ArrayList<>();

        // Khởi tạo Adapter (chưa có dữ liệu ban đầu)
        myOrderVerAdapter = new MyOrderVerAdapter(getApplicationContext(),foodCartVerModelList);
        myOrderRec.setLayoutManager(new LinearLayoutManager(this));
        myOrderRec.setAdapter(myOrderVerAdapter);

        tgCurrent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    updateRecyclerView();
                }else {
                    clearRecyclerView();
                }
            }
        });
        tgComplete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    foodCartVerModelList.clear();
                    foodCartVerModelList.addAll(dbHelper.getCompleteItems());
                    myOrderVerAdapter.notifyDataSetChanged();
                }else {
                    clearRecyclerView();
                }
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyOrderActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
    private void updateRecyclerView() {
        // Lấy dữ liệu từ cơ sở dữ liệu
        foodCartVerModelList.clear();
        foodCartVerModelList.addAll(dbHelper.getCartItems());
        myOrderVerAdapter.notifyDataSetChanged();
    }

    private void clearRecyclerView() {
        // Làm sạch dữ liệu trong RecyclerView
        foodCartVerModelList.clear();
        myOrderVerAdapter.notifyDataSetChanged();
    }

}