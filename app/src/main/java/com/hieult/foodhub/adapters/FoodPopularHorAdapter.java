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
import com.hieult.foodhub.model.FoodPopularHorModel;

import java.util.List;

public class FoodPopularHorAdapter extends RecyclerView.Adapter<FoodPopularHorAdapter.ViewHolder> {
    Context context;
    List<FoodPopularHorModel> list;

    public FoodPopularHorAdapter(Context context, List<FoodPopularHorModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FoodPopularHorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.foot_popular_horizontal,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodPopularHorAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());
        holder.rate.setText(list.get(position).getRate());
        holder.price.setText(list.get(position).getPrice());
        holder.subName.setText(list.get(position).getSubName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView price;
        TextView rate;
        TextView name;
        TextView subName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_foot_popular);
            price = itemView.findViewById(R.id.txt_price);
            rate = itemView.findViewById(R.id.txt_rate_popular);
            name = itemView.findViewById(R.id.txt_popular_name);
            subName = itemView.findViewById(R.id.txt_popular_subname);
        }
    }
}
