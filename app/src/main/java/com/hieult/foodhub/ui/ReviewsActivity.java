package com.hieult.foodhub.ui;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.hieult.foodhub.R;
import com.hieult.foodhub.adapters.OrderVerAdapter;
import com.hieult.foodhub.adapters.ReviewVerAdapter;
import com.hieult.foodhub.model.OrdersVerModel;
import com.hieult.foodhub.model.ReviewsVerModel;

import java.util.ArrayList;
import java.util.List;

public class ReviewsActivity extends AppCompatActivity {
    RecyclerView reviewVerRec;
    List<ReviewsVerModel> reviewsVerModelList;
    ReviewVerAdapter reviewVerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        reviewVerRec = findViewById(R.id.review_vertical_rec);
        reviewsVerModelList = new ArrayList<>();
        reviewsVerModelList.add(new ReviewsVerModel(R.drawable.img_review_profile1,"5.0","Alyce Lambo","25/06/2020","Really convenient and the points system helps benefit loyalty. Some mild glitches here and there, but nothing too egregious. Obviously needs to roll out to more remote."));
        reviewsVerModelList.add(new ReviewsVerModel(R.drawable.img_review_profile2,"4.5","Gonela Solom","22/06/2020","Been a life saver for keeping our sanity during the pandemic, although they could improve some of their ui and how they handle specials as it often is unclear how to use them or everything is sold out so fast it feels a bit bait and switch. Still I'd be stir crazy and losing track of days without so..."));
        reviewsVerModelList.add(new ReviewsVerModel(R.drawable.img_review_profile3,"2.5","Brian C","21/06/2020","Got an intro offer of 50% off first order that did not work..... I have scaled the app to find a contact us button but only a spend with us button available. "));
        reviewsVerModelList.add(new ReviewsVerModel(R.drawable.img_review_profile4,"3.5","Helsmar E","20/06/2020","Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis. Amet minim mollit non deserunt ullamco est sit."));

        reviewVerAdapter = new ReviewVerAdapter(this,reviewsVerModelList);
        reviewVerRec.setAdapter(reviewVerAdapter);
        reviewVerRec.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
        reviewVerRec.setHasFixedSize(true);
        reviewVerRec.setNestedScrollingEnabled(false);
    }
}