package com.hieult.foodhub.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hieult.foodhub.R;
import com.hieult.foodhub.model.LastedOrdersVerModel;

import java.util.List;

public class LastedOrderVerAdapter extends RecyclerView.Adapter<LastedOrderVerAdapter.ViewHolder> {
    Context context;
    List<LastedOrdersVerModel> list;


    public LastedOrderVerAdapter(Context context, List<LastedOrdersVerModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public LastedOrderVerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.food_lasted_order_vertical,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LastedOrderVerAdapter.ViewHolder holder, int position) {
        holder.image.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());
        holder.items.setText(list.get(position).getItems());
        holder.price.setText(list.get(position).getPrice());
        holder.date.setText(list.get(position).getDate());
        holder.state.setText(list.get(position).getState());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView items;
        TextView price;
        TextView name;
        TextView date;
        TextView state;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.img_lasted_order);
            items = itemView.findViewById(R.id.txt_lasted_order_items);
            price = itemView.findViewById(R.id.txt_lasted_order_price);
            name = itemView.findViewById(R.id.txt_lasted_order_name);
            date = itemView.findViewById(R.id.txt_lasted_order_date);
            state = itemView.findViewById(R.id.txt_lasted_order_state);
        }
    }

}
