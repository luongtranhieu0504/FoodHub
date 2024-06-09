package com.hieult.foodhub.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hieult.foodhub.R;
import com.hieult.foodhub.adapters.FoodCartVerAdapter;
import com.hieult.foodhub.data.DatabaseHelper;
import com.hieult.foodhub.model.FoodCartVerModel;

import java.util.List;


public class MyOrderFragment extends Fragment {

    private RecyclerView recyclerView;
    List<FoodCartVerModel> foodCartVerModelList;
    DatabaseHelper dbHelper;
    FoodCartVerAdapter foodCartVerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_order, container, false);
        RecyclerView myOrderRec = view.findViewById(R.id.recyclerView);
        myOrderRec.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        foodCartVerModelList = dbHelper.getCartItems();
        foodCartVerAdapter = new FoodCartVerAdapter(getContext(), foodCartVerModelList);
        myOrderRec.setAdapter(foodCartVerAdapter);

        // Thay đổi dữ liệu của RecyclerView dựa trên nút được chọn (position)
        Bundle args = getArguments();
        if (args != null) {
            int position = args.getInt("position", 0);
            updateRecyclerViewData(position);
        }

        return view;
    }

    private void updateRecyclerViewData(int position) {
        // Thực hiện logic để cập nhật dữ liệu của RecyclerView dựa trên nút được chọn
        // Ví dụ: nếu position là 0, hiển thị danh sách mục 1; nếu là 1, hiển thị mục 2, vv.
        // Dùng adapter.notifyDataSetChanged() để cập nhật giao diện người dùng.
    }
}