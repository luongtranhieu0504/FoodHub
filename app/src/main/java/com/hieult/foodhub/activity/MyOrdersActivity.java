package com.hieult.foodhub.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.hieult.foodhub.R;
import com.hieult.foodhub.adapters.LastedOrderVerAdapter;
import com.hieult.foodhub.adapters.OrderVerAdapter;
import com.hieult.foodhub.model.LastedOrdersVerModel;
import com.hieult.foodhub.model.OrdersVerModel;

import java.util.ArrayList;
import java.util.List;

public class MyOrdersActivity extends AppCompatActivity {
    RecyclerView orderVerRec;
    List<OrdersVerModel> ordersVerModelList;
    OrderVerAdapter orderVerAdapter;

    RecyclerView lastedOrderVerRec;
    List<LastedOrdersVerModel> lastedOrdersVerModelList;
    LastedOrderVerAdapter lastedOrderVerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        orderVerRec = findViewById(R.id.order_vertical_rec);
        ordersVerModelList = new ArrayList<>();
        ordersVerModelList.add(new OrdersVerModel(R.drawable.img_stabuck,"3 items","#264100","Starbuck ","25","Food on the way"));
        orderVerAdapter = new OrderVerAdapter(this,ordersVerModelList);
        orderVerRec.setAdapter(orderVerAdapter);
        orderVerRec.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
        orderVerRec.setHasFixedSize(true);
        orderVerRec.setNestedScrollingEnabled(true);

        lastedOrderVerRec = findViewById(R.id.lt_order_vertical_rec);
        lastedOrdersVerModelList = new ArrayList<>();
        lastedOrdersVerModelList.add(new LastedOrdersVerModel(R.drawable.img_jimmy_john,"20 Jun, 10:30","3 Items","$17.10","Jimmy John’s","Order Delivered"));
        lastedOrdersVerModelList.add(new LastedOrdersVerModel(R.drawable.img_stabuck,"19 Jun, 11:50","2 Items","$20.50","Subway","Order Delivered"));
        lastedOrderVerAdapter = new LastedOrderVerAdapter(this,lastedOrdersVerModelList);
        lastedOrderVerRec.setAdapter(lastedOrderVerAdapter);
        lastedOrderVerRec.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
        lastedOrderVerRec.setHasFixedSize(true);
        lastedOrderVerRec.setNestedScrollingEnabled(true);
    }
}