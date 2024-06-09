package com.hieult.foodhub.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hieult.foodhub.R;
import com.hieult.foodhub.data.HelperClass;

public class MyProfileActivity extends AppCompatActivity {
    EditText txt_name,txt_email,txt_phone;
    ImageView btn_back;
    String name,email,phoneNumber,password;
    Button btnSave;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        txt_name = findViewById(R.id.txt_edit_name);
        txt_email = findViewById(R.id.txt_edit_email);
        txt_phone = findViewById(R.id.txt_edit_phone_number);
        btnSave = findViewById(R.id.btn_edit_save);
        btn_back = findViewById(R.id.btn_my_profile_back);
        SharedPreferences sharedPreferences = getSharedPreferences("MyUser", Context.MODE_PRIVATE);
        name = sharedPreferences.getString("name","Ronaldo");
        email = sharedPreferences.getString("email","ronaldo@gmail.com");
        phoneNumber = sharedPreferences.getString("phone","0283748594");
        password = sharedPreferences.getString("password","12021");
        txt_name.setText(name);
        txt_email.setText(email);
        txt_phone.setText(phoneNumber);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checkName = txt_name.getText().toString();
                String checkEmail = txt_email.getText().toString();
                String checkPhone = txt_phone.getText().toString();
                if (!checkName.equals(name)||!checkEmail.equals(email)||!checkPhone.equals(phoneNumber)){
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    reference = database.getReference("users");
                    HelperClass helperClass = new HelperClass(checkName,checkEmail,password,checkPhone);
                    reference.child(name).setValue(helperClass);
                    Toast.makeText(MyProfileActivity.this, "You have change user successfully",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MyProfileActivity.this, ProfileActivity.class);
                    intent.putExtra("NEW_NAME", checkName);
                    intent.putExtra("NEW_EMAIL", checkEmail);
                    intent.putExtra("NEW_PHONE", checkPhone);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(MyProfileActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}