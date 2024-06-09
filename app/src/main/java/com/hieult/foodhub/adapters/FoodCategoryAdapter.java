package com.hieult.foodhub.adapters;

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
import com.hieult.foodhub.model.FoodPopularHorModel;

import java.util.List;

public class FoodCategoryAdapter extends RecyclerView.Adapter<FoodCategoryAdapter.ViewHolder> {

    Context context;
    List<FoodPopularHorModel> list;
    private FoodCartVerAdapter.OnItemClickListener onItemClickListener;

    public FoodCategoryAdapter(Context context, List<FoodPopularHorModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FoodCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.food_category_vertical,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodCategoryAdapter.ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.rate.setText(list.get(position).getRate());
        holder.price.setText(list.get(position).getPrice());
        Glide.with(holder.imageView.getContext())
                .load(list.get(position).getImage())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.google.firebase.database.R.drawable.common_google_signin_btn_icon_dark)
                .into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                    notifyDataSetChanged();
                }
            }
        });

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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_foot_category);
            price = itemView.findViewById(R.id.txt_price_category);
            rate = itemView.findViewById(R.id.txt_rate_category);
            name = itemView.findViewById(R.id.txt_category_name);
        }
    }
    public void setOnItemClickListener(FoodCartVerAdapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}

