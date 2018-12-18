package com.example.marce.primaapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener, FoodAdapter.OnQuantityChange {
    private static final String TAG = "WelcomeActivity";
    TextView emailTV, total;

    String email, openedEmail;

    RecyclerView recyclerView;

    LinearLayoutManager layoutManager;
    FoodAdapter adapter;

    Button buy;

    ProgressBar progressBar;

    int totale;

    int progress= 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        emailTV = findViewById(R.id.tv_benvenuto);
        emailTV.setOnClickListener(this);
        emailTV.setText(getIntent().getStringExtra("benvenuto"));

        progressBar = findViewById(R.id.determinateBar);
        buy = findViewById(R.id.buy_btn);
        recyclerView = findViewById(R.id.food_RW);
        total = findViewById(R.id.total);
        layoutManager = new LinearLayoutManager(this);

        progressBar.setMax(5);
        progressBar.setProgress(0);

        adapter = new FoodAdapter(this,getProducts());
        adapter.setOnQuantityChange(this);


        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


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
    private ArrayList<Food> getProducts(){

        ArrayList<Food> foodAd = new ArrayList<>();

        foodAd.add(new Food("milk",1.2f));

        foodAd.add(new Food("potato",3.5f));
        return foodAd;
    }

    @Override
    public void onItemAdded(float price) {
        totale += price;
        total.setText(String.valueOf(totale));
        enableBuy();
        progressBar.setProgress(totale);
    }

    @Override
    public void onItemRemoved(float price) {
        if (totale == 0) return;
        totale -= price;
        total.setText(String.valueOf(totale));
        enableBuy();
        progressBar.setProgress(totale);

    }
}