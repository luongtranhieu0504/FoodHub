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
import com.hieult.foodhub.model.OrdersVerModel;
import com.hieult.foodhub.model.ReviewsVerModel;

import java.util.List;

public class ReviewVerAdapter extends RecyclerView.Adapter<ReviewVerAdapter.ViewHolder> {
    Context context;
    List<ReviewsVerModel> list;

    public ReviewVerAdapter(Context context, List<ReviewsVerModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ReviewVerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.reviews_vertical,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ReviewVerAdapter.ViewHolder holder, int position) {
        holder.image.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());
        holder.rating.setText(list.get(position).getRating());
        holder.date.setText(list.get(position).getDate());
        holder.comment.setText(list.get(position).getComment());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView rating;
        TextView name;
        TextView date;
        TextView comment;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.img_profile_review);
            rating = itemView.findViewById(R.id.txt_review_rating);
            name = itemView.findViewById(R.id.txt_name_review);
            date = itemView.findViewById(R.id.txt_date_review);
            comment = itemView.findViewById(R.id.txt_comment_review);
        }
    }
}
