package com.hieult.foodhub.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.Query;
import com.hieult.foodhub.R;
import com.hieult.foodhub.model.FoodSearchVerModel;

import java.util.List;

public class FoodSearchVerAdapter extends FirebaseRecyclerAdapter<FoodSearchVerModel,FoodSearchVerAdapter.MyViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    private OnItemClickListener onItemClickListener;
    private FirebaseRecyclerOptions<FoodSearchVerModel> originalOptions;

    public FoodSearchVerAdapter(@NonNull FirebaseRecyclerOptions options) {
        super(options);
        this.originalOptions = options;
    }

    @SuppressLint("RecyclerView")
    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull FoodSearchVerModel model) {
        holder.name.setText(model.getName());
        holder.delivery.setText(model.getDelivery());
        holder.time.setText(model.getTime());
        holder.rating.setText(model.getRating());
        holder.description.setText(model.getDescription());
        holder.price.setText(model.getPrice());
        Glide.with(holder.img.getContext())
                .load(model.getImage())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.google.firebase.database.R.drawable.common_google_signin_btn_icon_dark)
                .into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null){
                    onItemClickListener.onItemClick(position);
                    notifyDataSetChanged();
                }
            }
        });
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_search_vertical,parent,false);
        return new MyViewHolder(view);
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView name;
        TextView delivery;
        TextView time;
        TextView rating;
        TextView description;
        TextView price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_food_search);
            name = itemView.findViewById(R.id.txt_name_search);
            delivery = itemView.findViewById(R.id.txt_delivery_search);
            time = itemView.findViewById(R.id.txt_time_search);
            rating = itemView.findViewById(R.id.txt_rate_search);
            description = itemView.findViewById(R.id.txt_description_search);
            price = itemView.findViewById(R.id.txt_price_search);
        }
    }

    public void setOnItemClickListener(FoodSearchVerAdapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

}
