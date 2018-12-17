package com.example.marce.primaapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "WelcomeActivity";
    TextView emailTV;

    String email, openedEmail;

    RecyclerView recyclerView;

    LinearLayoutManager layoutManager;
    FoodAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        emailTV = findViewById(R.id.tv_benvenuto);
        emailTV.setOnClickListener(this);
        emailTV.setText(getIntent().getStringExtra("benvenuto"));

        recyclerView = findViewById(R.id.food_RW);

        layoutManager = new LinearLayoutManager(this);

        ArrayList<Food> foodAd = new ArrayList<>();

        foodAd.add(new Food("milk","1,2"));

        foodAd.add(new Food("potato","3,5"));

        adapter = new FoodAdapter(this,foodAd);


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


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_benvenuto) {
            Intent i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto",getIntent().getStringExtra("benvenuto"), null));
            startActivity(Intent.createChooser(i, "Choose an Email client :"));
        }
    }
}