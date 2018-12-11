package com.example.marce.primaapp;

import android.util.Log;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements OnClickListener {
    private static final String TAG = "RegisterActivity";

    EditText registerEmailET;
    EditText registerPasswordET;

    Button registerdueBtn;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrer);

        registerEmailET = findViewById(R.id.registeremail_et);
        registerPasswordET = findViewById(R.id.registerpassword_et);

        registerdueBtn = findViewById(R.id.registerdue_btn);
        registerdueBtn.setOnClickListener(this);


    }
    private boolean isValidEmail() {


        String email = registerEmailET.getText().toString();
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private boolean isValidPassword() {
        //TODO check the password's length
        return registerPasswordET.getText().toString().length() > 6;

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
        if(v.getId() == R.id.registerdue_btn)
            if((isValidEmail()== true)&&(isValidPassword())) {

                showSuccessMessage();
            }else{
                showErrorMessage();
            }

    }
}


