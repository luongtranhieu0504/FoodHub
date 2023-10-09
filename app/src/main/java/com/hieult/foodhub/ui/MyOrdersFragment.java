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
import com.hieult.foodhub.adapters.LastedOrderVerAdapter;
import com.hieult.foodhub.adapters.OrderVerAdapter;
import com.hieult.foodhub.model.FoodPopularHorModel;
import com.hieult.foodhub.model.LastedOrdersVerModel;
import com.hieult.foodhub.model.OrdersVerModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyOrdersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyOrdersFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView orderVerRec;
    List<OrdersVerModel> ordersVerModelList;
    OrderVerAdapter orderVerAdapter;

    RecyclerView lastedOrderVerRec;
    List<LastedOrdersVerModel> lastedOrdersVerModelList;
    LastedOrderVerAdapter lastedOrderVerAdapter;

    public MyOrdersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyOrdersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyOrdersFragment newInstance(String param1, String param2) {
        MyOrdersFragment fragment = new MyOrdersFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_orders, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        orderVerRec = view.findViewById(R.id.order_vertical_rec);
        ordersVerModelList = new ArrayList<>();
        ordersVerModelList.add(new OrdersVerModel(R.drawable.img_stabuck,"3 items","#264100","Starbuck ","25","Food on the way"));
        orderVerAdapter = new OrderVerAdapter(getContext(),ordersVerModelList);
        orderVerRec.setAdapter(orderVerAdapter);
        orderVerRec.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
        orderVerRec.setHasFixedSize(true);
        orderVerRec.setNestedScrollingEnabled(false);

        lastedOrderVerRec = view.findViewById(R.id.lasted_order_vertical_rec);
        lastedOrdersVerModelList = new ArrayList<>();
        lastedOrdersVerModelList.add(new LastedOrdersVerModel(R.drawable.img_jimmy_john,"20 Jun, 10:30","3 Items","$17.10","Jimmy John’s","Order Delivered"));
        lastedOrdersVerModelList.add(new LastedOrdersVerModel(R.drawable.img_stabuck,"19 Jun, 11:50","2 Items","$20.50","Subway","Order Delivered"));
        lastedOrderVerAdapter = new LastedOrderVerAdapter(getContext(),lastedOrdersVerModelList);
        lastedOrderVerRec.setAdapter(lastedOrderVerAdapter);
        lastedOrderVerRec.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
        lastedOrderVerRec.setHasFixedSize(true);
        lastedOrderVerRec.setNestedScrollingEnabled(false);


    }
}