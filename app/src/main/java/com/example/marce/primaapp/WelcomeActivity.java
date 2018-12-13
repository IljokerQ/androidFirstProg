package com.example.marce.primaapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "WelcomeActivity";
    TextView emailTV;
    String email;
    String openedEmail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        emailTV = findViewById(R.id.tv_benvenuto);
        emailTV.setOnClickListener(this);
        emailTV.setText(getIntent().getStringExtra("benvenuto"));

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