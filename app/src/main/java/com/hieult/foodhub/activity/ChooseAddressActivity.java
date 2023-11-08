package com.hieult.foodhub.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hieult.foodhub.R;
import com.hieult.foodhub.adapters.AddressVerAdapter;
import com.hieult.foodhub.data.HelperAddressClass;
import com.hieult.foodhub.model.AddressVerModel;

import java.util.ArrayList;
import java.util.List;

public class ChooseAddressActivity extends AppCompatActivity {
    RecyclerView addressRec;
    AddressVerAdapter addressVerAdapter;
    String addressTitle,addressType,addressStreet;
    ImageView btn_back;
    CardView addAddress;
    Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_address);
        btn_back = findViewById(R.id.btn_choose_Address_back);
        addAddress = findViewById(R.id.cardview_add_address);
        btnDone = findViewById(R.id.btn_done);
        addressRec = findViewById(R.id.address_rec);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("addAddress");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<AddressVerModel> addressList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    HelperAddressClass address = snapshot.getValue(HelperAddressClass.class);
                    if (address != null) {
                        addressTitle = address.getAddressTitle();
                        addressType = address.getAddressType();
                        addressStreet = address.getStreet() + " " + address.getCity();
                        addressList.add(new AddressVerModel(addressTitle,addressType,addressStreet));
                    }
                }
                addressVerAdapter = new AddressVerAdapter(getBaseContext(),addressList);
                addressRec.setAdapter(addressVerAdapter);
                addressRec.setLayoutManager(new LinearLayoutManager(getBaseContext(),RecyclerView.VERTICAL,false));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseAddressActivity.this,PaymentActivity.class);
                startActivity(intent);
            }
        });
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseAddressActivity.this,PaymentActivity.class);
                //còn logic lấy data sau khi done
                startActivity(intent);
            }
        });
        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseAddressActivity.this,AddAddressActivity.class);
                startActivity(intent);
            }
        });
    }
}