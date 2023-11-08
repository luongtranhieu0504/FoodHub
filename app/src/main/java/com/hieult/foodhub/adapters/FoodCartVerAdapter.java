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
import com.hieult.foodhub.data.DatabaseHelper;
import com.hieult.foodhub.model.FeaturedResHorModel;
import com.hieult.foodhub.model.FoodCartVerModel;
import com.hieult.foodhub.model.FoodHorModel;

import java.util.List;

public class FoodCartVerAdapter extends RecyclerView.Adapter<FoodCartVerAdapter.ViewHolder>{
    Context context;
    List<FoodCartVerModel> foodList;
    private int selectedItem = 0;
    private int numberOrder = 1;
    private OnItemClickListener onItemClickListener;


    public FoodCartVerAdapter(Context context, List<FoodCartVerModel> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.food_cart_horizontal,parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(foodList.get(position).getNameCart());
        holder.price.setText(foodList.get(position).getPriceCart());
        Glide.with(holder.imageView.getContext())
                .load(foodList.get(position).getImageCart())
                .into(holder.imageView);
        holder.number_order.setText(foodList.get(position).getNumberOrderCart());
        holder.btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null){
                    onItemClickListener.onItemClick(position);
                    notifyDataSetChanged();
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView,btn_minus,btn_plus,btn_close;
        TextView name;
        TextView price;
        TextView number_order;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_cart);
            name = itemView.findViewById(R.id.txt_name_cart);
            price = itemView.findViewById(R.id.txt_price_cart);
            number_order = itemView.findViewById(R.id.txt_number_order);
            btn_minus = itemView.findViewById(R.id.btn_minus_cart);
            btn_plus = itemView.findViewById(R.id.btn_plus_cart);
            btn_close = itemView.findViewById(R.id.img_close);

        }
    }

    public void setOnItemClickListener(FoodCartVerAdapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }



}
