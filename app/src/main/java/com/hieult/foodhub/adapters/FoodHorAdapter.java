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

import com.hieult.foodhub.R;
import com.hieult.foodhub.model.FoodHorModel;

import java.util.List;

public class FoodHorAdapter extends RecyclerView.Adapter<FoodHorAdapter.ViewHolder>{
    Context context;
    List<FoodHorModel> foodList;
    private int selectedItem = 0;
    private String selectedCategory;

    private OnItemClickListener onItemClickListener;


    public FoodHorAdapter(Context context, List<FoodHorModel> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.food_select_horizontal,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.imageView.setImageResource(foodList.get(position).getImage());
        holder.name.setText(foodList.get(position).getName());
        boolean isSelected = (position == selectedItem);

        if (isSelected){
            holder.itemView.setBackgroundResource(R.color.light_orange_color);
        }else {
            holder.itemView.setBackgroundResource(R.color.white);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodList.get(position).setSelect(true);
                String foodType = foodList.get(position).getName();

                // Đánh dấu item trước đó (nếu có) là chưa được click
                if (selectedItem != -1 && selectedItem != position ) {
                    foodList.get(selectedItem).setSelect(false);
                }
                selectedItem = position;
                onItemClickListener.onItemClick(position,foodType);
                notifyDataSetChanged();
            }


        });


    }
    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img_berger);
            name = itemView.findViewById(R.id.txt_berger);
        }
    }



    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position,String foodTyoe);
    }



}
