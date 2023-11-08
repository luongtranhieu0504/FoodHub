package com.hieult.foodhub.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.hieult.foodhub.R;
import com.hieult.foodhub.model.AddressVerModel;
import com.hieult.foodhub.model.FeaturedResHorModel;
import com.hieult.foodhub.model.FoodCartVerModel;

import java.util.List;

public class AddressVerAdapter extends RecyclerView.Adapter<AddressVerAdapter.MyViewHolder> {
    Context context;
    List<AddressVerModel> addressList;
    private OnItemClickListener onItemClickListener;

    public AddressVerAdapter(Context context, List<AddressVerModel> addressList) {
        this.context = context;
        this.addressList = addressList;
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.addressTitle.setText(addressList.get(position).getAddressTitle());
        holder.addressType.setText(addressList.get(position).getAddressType());
        holder.address.setText(addressList.get(position).getAddress());
        holder.choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null){
                    onItemClickListener.onItemClick(position);

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_vertical,parent,false);
        return new MyViewHolder(view);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView addressTitle;
        TextView addressType;
        TextView address;
        RadioButton choose;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            addressTitle = itemView.findViewById(R.id.txt_choo_address_title);
            addressType = itemView.findViewById(R.id.txt_choo_address_type);
            address = itemView.findViewById(R.id.txt_choo_address);
            choose = itemView.findViewById(R.id.rd_address);

        }
    }
    public void setOnItemClickListener(AddressVerAdapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
