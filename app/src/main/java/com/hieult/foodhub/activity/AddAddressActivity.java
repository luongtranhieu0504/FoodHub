package com.hieult.foodhub.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hieult.foodhub.R;
import com.hieult.foodhub.data.HelperAddressClass;

public class AddAddressActivity extends AppCompatActivity {
    EditText full_name,phone_number,address_title,street_home;
    ImageView btn_back;
    AutoCompleteTextView auto_city,auto_address_type;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        full_name = findViewById(R.id.txt_full_name);
        phone_number = findViewById(R.id.txt_phone_number);
        address_title = findViewById(R.id.txt_address_title);
        street_home = findViewById(R.id.txt_street_home);
        auto_address_type = findViewById(R.id.auto_address_type);
        auto_city = findViewById(R.id.auto_city);
        btn_back = findViewById(R.id.btn_back_address);
        save = findViewById(R.id.btn_save);
        ArrayAdapter<String> addressTypeAdapter = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.address_types));
        auto_address_type.setAdapter(addressTypeAdapter);
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.us_cities));
        auto_city.setAdapter(cityAdapter);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference("addAddress");

                String name = full_name.getText().toString();
                String phoneNumber = phone_number.getText().toString();
                String addressTitle = address_title.getText().toString();
                String addressType = auto_address_type.getText().toString();
                String city = auto_city.getText().toString();
                String streetHome = street_home.getText().toString();
                HelperAddressClass helperClass = new HelperAddressClass(name,phoneNumber,addressTitle,addressType,city,streetHome);
                reference.child(addressTitle).setValue(helperClass);
                Toast.makeText(AddAddressActivity.this, "You have added the address successfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddAddressActivity.this,ChooseAddressActivity.class);
                startActivity(intent);
            }
        });
    }
}