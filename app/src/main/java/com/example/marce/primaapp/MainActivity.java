package com.example.marce.primaapp;


import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;




public class MainActivity extends AppCompatActivity implements OnClickListener,CompoundButton.OnCheckedChangeListener {
    private static final String TAG = "MainActivity";
    LinearLayout sfondo;

    EditText emailET;
    EditText passwordET;

    Button loginBtn;
    Button registerBtn;

    Switch colorMode;

    SharedPreferences s;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sfondo = findViewById(R.id.sfondo);

        colorMode = findViewById(R.id.color_mode);

        emailET = findViewById(R.id.email_et);
        passwordET = findViewById(R.id.password_et);


        loginBtn = findViewById(R.id.login_btn);
        registerBtn = findViewById(R.id.register_btn);

        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        colorMode.setOnCheckedChangeListener(this);


        Log.i(TAG, "activity created");

        s = getPreferences(Context.MODE_PRIVATE);
        editor = s.edit();


        sfondo.setBackgroundColor(getResources().getColor( s.getInt("current color", 0)));
    }

    private boolean isValidEmail() {


        String email = emailET.getText().toString();
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword() {

        return passwordET.getText().toString().length() > 6;

    }

    private void showErrorMessage() {
        Toast.makeText(this, getString(R.string.login_error), Toast.LENGTH_LONG).show();
        Log.i(TAG, getString(R.string.login_error));
    }

    private void showSuccessMessage() {
        Toast.makeText(this, getString(R.string.login_success), Toast.LENGTH_LONG).show();
        Log.i(TAG, getString(R.string.login_success));
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_btn)
            if ((isValidEmail()) && (isValidPassword())) {

                showSuccessMessage();
                Intent a = new Intent(this, WelcomeActivity.class);
                a.putExtra("benvenuto", emailET.getText().toString());
                startActivity(a);
            } else {
                showErrorMessage();
            }
        else if (v.getId() == R.id.register_btn) {
            Intent i = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(i);

        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (isChecked) {

            sfondo.setBackgroundColor(getResources().getColor(R.color.color_dark));
            editor.putInt("current color", R.color.color_dark);
            editor.commit();
        } else {
            sfondo.setBackgroundColor(getResources().getColor(R.color.color_light));
            editor.putInt("current color", R.color.color_light);
           editor.commit();
            }

    }
}