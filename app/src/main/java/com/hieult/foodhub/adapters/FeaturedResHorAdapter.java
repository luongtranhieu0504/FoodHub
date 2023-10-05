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
import com.hieult.foodhub.model.FeaturedResHorModel;

import java.util.List;

public class FeaturedResHorAdapter extends RecyclerView.Adapter<FeaturedResHorAdapter.ViewHolder> {

    Context context;
    List<FeaturedResHorModel> list;

    public FeaturedResHorAdapter(Context context, List<FeaturedResHorModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FeaturedResHorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_restaurant_horizontal,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedResHorAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());
        holder.delivery.setText(list.get(position).getDelivery());
        holder.time.setText(list.get(position).getTime());
        holder.rate.setText(list.get(position).getRate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView delivery;
        TextView time;
        TextView rate;;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img_foot_featured);
            name = itemView.findViewById(R.id.txt_featured_name);
            delivery = itemView.findViewById(R.id.txt_shipper);
            time = itemView.findViewById(R.id.txt_time);
            rate = itemView.findViewById(R.id.txt_rate);
        }
    }
}
