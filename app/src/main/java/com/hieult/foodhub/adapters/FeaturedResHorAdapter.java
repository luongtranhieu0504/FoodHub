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
import com.hieult.foodhub.R;
import com.hieult.foodhub.model.FeaturedResHorModel;

import java.util.List;
import java.util.zip.Inflater;

public class FeaturedResHorAdapter extends FirebaseRecyclerAdapter<FeaturedResHorModel,FeaturedResHorAdapter.MyViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    private OnItemClickListener onItemClickListener;
    public FeaturedResHorAdapter(@NonNull FirebaseRecyclerOptions options) {
        super(options);
    }

    @SuppressLint("RecyclerView")
    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull FeaturedResHorModel model) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_restaurant_horizontal,parent,false);
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
            img = itemView.findViewById(R.id.img_foot_featured);
            name = itemView.findViewById(R.id.txt_featured_name);
            delivery = itemView.findViewById(R.id.txt_shipper);
            time = itemView.findViewById(R.id.txt_time);
            rating = itemView.findViewById(R.id.txt_featured_rate);
            price = itemView.findViewById(R.id.txt_price);
            description = itemView.findViewById(R.id.txt_description);
        }
    }
    public void setOnItemClickListener(FeaturedResHorAdapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}