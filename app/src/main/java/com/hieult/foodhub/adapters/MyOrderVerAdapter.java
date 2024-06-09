package com.hieult.foodhub.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hieult.foodhub.R;
import com.hieult.foodhub.model.FoodCartVerModel;

import java.util.List;

public class MyOrderVerAdapter extends RecyclerView.Adapter<MyOrderVerAdapter.ViewHolder>{
    Context context;
    List<FoodCartVerModel> foodList;



    public MyOrderVerAdapter(Context context, List<FoodCartVerModel> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public MyOrderVerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyOrderVerAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.food_my_order_vertical,parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull MyOrderVerAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(foodList.get(position).getNameCart());
        holder.price.setText(foodList.get(position).getPriceCart());
        Glide.with(holder.imageView.getContext())
                .load(foodList.get(position).getImageCart())
                .into(holder.imageView);
        holder.number_order.setText(foodList.get(position).getNumberOrderCart());

    }
    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView price;
        TextView number_order;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_my_order);
            name = itemView.findViewById(R.id.txt_name_order);
            price = itemView.findViewById(R.id.txt_price_order);
            number_order = itemView.findViewById(R.id.txt_number_my_order);

        }
    }





}

