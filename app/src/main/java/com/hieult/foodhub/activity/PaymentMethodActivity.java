package com.hieult.foodhub.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.hieult.foodhub.R;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.Stripe;
import com.stripe.android.model.PaymentMethod;
import com.stripe.android.paymentsheet.PaymentSheet;
import com.stripe.android.paymentsheet.PaymentSheetResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class PaymentMethodActivity extends AppCompatActivity {
    RadioButton cardRd, cashRd;
    RadioGroup paymentMethod;
    Button btnNext;
    ImageView btnBack;

    String PublishableKey = "pk_test_51O9TRUAH8wvxx7Hg3bF4m1aPLxoCyx4jFojgZhkOApUkrqdQnPPIhRNPCXQfYO4oInVY095fVejvEFho0TajMexi00rD6stjqJ";
    String SecretKey = "sk_test_51O9TRUAH8wvxx7Hg63AAyO380sHL4Jc6sTVcUTFSsDmbaGS4AO3mokCaXwCrLHdB7kptHau8OU42xEIV40FluP0400EL5osuGs";
    String CustomerId;
    String EnphericalKey;
    String ClientSecret;
    PaymentSheet paymentSheet;
    double cartTotal;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        cardRd = findViewById(R.id.rd_card);
        cashRd = findViewById(R.id.rd_cash);
        btnNext = findViewById(R.id.btn_next);
        paymentMethod = findViewById(R.id.rdg_payment_method);
        btnBack = findViewById(R.id.btn_payment_method_back);
        PaymentConfiguration.init(this,PublishableKey);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentMethodActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });
        paymentSheet = new PaymentSheet(this, paymentSheetResult -> {
           onPaymentResult(paymentSheetResult);
        });
        SharedPreferences sharedPreferences = getSharedPreferences("PaymentMethod", Context.MODE_PRIVATE);
        cartTotal = sharedPreferences.getFloat("cartTotal", 0);
        Log.d("payment","carttotal: " + cartTotal );
        StringRequest request = new StringRequest(Request.Method.POST, "https://api.stripe.com/v1/customers",
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    CustomerId = object.getString("id");
                    Toast.makeText(PaymentMethodActivity.this,CustomerId,Toast.LENGTH_SHORT).show();
                    getEmphericalKey();
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PaymentMethodActivity.this,error.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<>();
                header.put("Authorization","Bearer " + SecretKey);
                return header;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cardRd.isChecked()) {
                    if (CustomerId != null) {
                        paymentFlow();
                    } else {
                        // Handle the case when customerId is null, e.g., show an error message or retry the request.
                    }
                } else{
                    Intent intent = new Intent(PaymentMethodActivity.this, PaymentActivity.class);
                    startActivity(intent);
                }
            }

        });

    }

    private void paymentFlow() {
        paymentSheet.presentWithPaymentIntent(ClientSecret,new PaymentSheet.Configuration("Learn with Arvind", new PaymentSheet.CustomerConfiguration(
                CustomerId,
                EnphericalKey
        )));
    }

    private void onPaymentResult(PaymentSheetResult paymentSheetResult) {
        if (paymentSheetResult instanceof PaymentSheetResult.Completed){
            Intent intent = new Intent(PaymentMethodActivity.this, StateDeliveryActivity.class);
            startActivity(intent);
            Toast.makeText(this,"Payment Success", Toast.LENGTH_SHORT).show();
        }
    }

    private void getEmphericalKey() {
        StringRequest request = new StringRequest(Request.Method.POST, "https://api.stripe.com/v1/ephemeral_keys",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            EnphericalKey = object.getString("id");
                            Toast.makeText(PaymentMethodActivity.this,CustomerId,Toast.LENGTH_SHORT).show();
                            getClientSecret(CustomerId,EnphericalKey);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PaymentMethodActivity.this,error.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<>();
                header.put("Authorization", "Bearer " + SecretKey);
                header.put("Stripe-Version","2023-10-16");
                return header;
            }

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("customer",CustomerId);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void getClientSecret(String customerId, String enphericalKey) {
        StringRequest request = new StringRequest(Request.Method.POST, "https://api.stripe.com/v1/payment_intents",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            ClientSecret = object.getString("client_secret");
                            Toast.makeText(PaymentMethodActivity.this,ClientSecret,Toast.LENGTH_SHORT).show();

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PaymentMethodActivity.this,error.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<>();
                header.put("Authorization","Bearer " + SecretKey);
                return header;
            }

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("customer",CustomerId);
                params.put("amount",String.valueOf((int) (cartTotal * 100)));
                params.put("currency","USD");
                params.put("automatic_payment_methods[enabled]","true");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }


}



