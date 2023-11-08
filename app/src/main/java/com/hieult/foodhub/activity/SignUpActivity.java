package com.hieult.foodhub.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hieult.foodhub.R;
import com.hieult.foodhub.data.HelperClass;


public class SignUpActivity extends AppCompatActivity {
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        final EditText fullName = findViewById(R.id.txt_full_name);
        final EditText registerEmail = findViewById(R.id.txt_register_email);
        final EditText registerPassword = findViewById(R.id.txt_register_password);
        final Button signUpBtn = findViewById(R.id.btn_sign_up);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference("users");

                String name = fullName.getText().toString();
                String email = registerEmail.getText().toString();
                String password = registerPassword.getText().toString();
                HelperClass helperClass = new HelperClass(name,email,password);
                reference.child(name).setValue(helperClass);
                   Toast.makeText(SignUpActivity.this, "You have sign up successfully",Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                   startActivity(intent);
            }
        });


    }

    public void login(View view) {
        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
    }
}