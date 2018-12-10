package com.example.marce.primaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements OnClickListener {
    private static final String TAG = "MainActivity";
    EditText emailET;
    EditText passwordET;

    Button loginBtn;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailET = findViewById(R.id.email_et);
        passwordET = findViewById(R.id.password_et);

        loginBtn = findViewById(R.id.login_btn);
        registerBtn = findViewById(R.id.register_btn);

        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);


        Log.i(TAG, "activity created");

    }

    private boolean isValidEmail() {
        //TODO how to check if a mail is valid

        String email = emailET.getText().toString();
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword() {
        //TODO check the password's length
        return passwordET.getText().toString().length() > 6;

    }

        private void showErrorMessage() {
            Toast.makeText(this,getString(R.string.login_error),Toast.LENGTH_LONG).show();
            Log.i(TAG, getString(R.string.login_error));
        }
        private void showSuccessMessage() {
            Toast.makeText(this,getString(R.string.login_success),Toast.LENGTH_LONG).show();
            Log.i(TAG, getString(R.string.login_success));
        }



    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.login_btn)
            if((isValidEmail()== true)&&(isValidPassword())) {
                //TODO perform login
                showSuccessMessage();
            }else{
                showErrorMessage();
              }
    }
}