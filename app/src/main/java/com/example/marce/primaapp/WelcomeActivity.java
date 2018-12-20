package com.example.marce.primaapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener, FoodAdapter.OnQuantityChange {
    private static final String TAG = "WelcomeActivity";
    TextView emailTV, total;

    String email, openedEmail;

    RecyclerView recyclerView;

    LinearLayoutManager layoutManager;
    FoodAdapter adapter;

    Button buy;

    ProgressBar progressBar, loading;

    int totale;

    int progress= 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        emailTV = findViewById(R.id.tv_benvenuto);
        emailTV.setOnClickListener(this);
        emailTV.setText(getIntent().getStringExtra("benvenuto"));
        loading = findViewById(R.id.loading);

        progressBar = findViewById(R.id.determinateBar);
        buy = findViewById(R.id.buy_btn);
        recyclerView = findViewById(R.id.food_RW);
        total = findViewById(R.id.total);
        layoutManager = new LinearLayoutManager(this);

        progressBar.setMax(5);
        progressBar.setProgress(0);

        adapter = new FoodAdapter(this);
        adapter.setOnQuantityChange(this);


        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        getProducts();

        if (getIntent().getStringExtra("EMAIL") != null){

            email= getIntent().getStringExtra("EMAIL");
            emailTV.setText(email);
        } else if (getIntent().getData() != null){

            openedEmail=getIntent().getData().toString().substring(7);
            emailTV.setText(openedEmail);
        }
    }


    public void enableBuy(){
        buy.setEnabled(totale > 5);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_benvenuto) {
            Intent i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto",getIntent().getStringExtra("benvenuto"), null));
            startActivity(Intent.createChooser(i, "Choose an Email client :"));
        }
    }
    private void getProducts(){


        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://5ba19290ee710f0014dd764c.mockapi.io/Food#";


        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       Log.d("success", response);
                        try {

                            JSONArray responseJSON = new JSONArray(response);
                            ArrayList<Food> foodArrayList = new ArrayList<>();
                            for (int i =0 ; i<responseJSON.length();i++){

                                Food food = new Food(responseJSON.getJSONObject(i));
                                if(!food.available) continue;
                                foodArrayList.add(food);
                            }
                            adapter.setData(foodArrayList);
                            loading.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.getMessage());
                loading.setVisibility(View.GONE);
            }
        });


        queue.add(stringRequest);
    }

    @Override
    public void onItemAdded(float price) {
        totale += price;
        progressBar.setProgress(totale);
        total.setText(String.valueOf(totale));
        enableBuy();

    }

    @Override
    public void onItemRemoved(float price) {
        if (totale == 0) return;
        totale -= price;
        progressBar.setProgress(totale);
        total.setText(String.valueOf(totale));
        enableBuy();


    }
}