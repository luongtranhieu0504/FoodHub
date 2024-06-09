package com.hieult.foodhub.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hieult.foodhub.R;
import com.hieult.foodhub.model.NotifyVerModel;

import java.util.List;

public class NotifyVerAdapter extends RecyclerView.Adapter<NotifyVerAdapter.ViewHolder> {
    Context context;
    List<NotifyVerModel> notifylist;

    public NotifyVerAdapter(Context context, List<NotifyVerModel> notifylist) {
        this.context = context;
        this.notifylist = notifylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.notify_design, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.titlenotify.setText(notifylist.get(position).getTitlenotify());
        holder.contentnotify.setText(notifylist.get(position).getContentnotify());

    }

    @Override
    public int getItemCount() {
        return notifylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titlenotify;
        TextView contentnotify;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titlenotify = itemView.findViewById(R.id.tvTitleNotify);
            contentnotify = itemView.findViewById(R.id.tvContentNotify);
        }
    }
}
