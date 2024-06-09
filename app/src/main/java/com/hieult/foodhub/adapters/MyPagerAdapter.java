package com.hieult.foodhub.adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hieult.foodhub.fragment.MyOrderFragment;

public class MyPagerAdapter extends FragmentPagerAdapter {
    public MyPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        // Tạo và trả về fragment duy nhất chứa RecyclerView
        MyOrderFragment fragment = new MyOrderFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3; // Số lượng nút/tab
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Tên cho mỗi tab (nếu cần)
        return "Tab " + (position + 1);
    }
}
