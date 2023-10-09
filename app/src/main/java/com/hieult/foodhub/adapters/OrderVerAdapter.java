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

import java.util.List;

public class OrderVerAdapter extends RecyclerView.Adapter<OrderVerAdapter.ViewHolder> {
    Context context;
    List<OrdersVerModel> list;

    public OrderVerAdapter(Context context, List<OrdersVerModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public OrderVerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.food_order_vertical,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull OrderVerAdapter.ViewHolder holder, int position) {
        holder.image.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());
        holder.items.setText(list.get(position).getItems());
        holder.id.setText(list.get(position).getId());
        holder.time.setText(list.get(position).getTime());
        holder.state.setText(list.get(position).getState());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView items;
        TextView id;
        TextView name;
        TextView time;
        TextView state;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.img_lasted_order);
            items = itemView.findViewById(R.id.txt_order_items);
            id = itemView.findViewById(R.id.txt_order_id);
            name = itemView.findViewById(R.id.txt_order_name);
            time = itemView.findViewById(R.id.txt_order_time);
            state = itemView.findViewById(R.id.txt_order_state1);
        }
    }
}
