package com.hieult.foodhub.ui;

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
import com.hieult.foodhub.adapters.FoodCategoryAdapter;
import com.hieult.foodhub.model.FoodPopularHorModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView foodCategoryRec;
    List<FoodPopularHorModel> foodCategoryVerModelList;
    FoodCategoryAdapter foodCategoryAdapter;

    public CategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        foodCategoryRec = view.findViewById(R.id.food_category_rec);
//        foodCategoryVerModelList = new ArrayList<>();
//        foodCategoryVerModelList.add(new FoodPopularHorModel(R.drawable.img_food_cate,"$10.35","4.5","Chicken Hawaiian","Chicken, Cheese and pineapple"));
//        foodCategoryVerModelList.add(new FoodPopularHorModel(R.drawable.img_food_cate2,"$10.35","4.5","Chicken Hawaiian","Chicken, Cheese and pineapple"));
//        foodCategoryVerModelList.add(new FoodPopularHorModel(R.drawable.img_food_cate3,"$10.35","4.5","Chicken Hawaiian","Chicken, Cheese and pineapple"));
//        foodCategoryAdapter = new FoodCategoryAdapter(getContext(),foodCategoryVerModelList);
//        foodCategoryRec.setAdapter(foodCategoryAdapter);
//        foodCategoryRec.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
//        foodCategoryRec.setHasFixedSize(true);
//        foodCategoryRec.setNestedScrollingEnabled(false);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }


}