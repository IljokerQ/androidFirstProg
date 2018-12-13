package com.example.marce.primaapp;

import android.text.Editable;
import android.text.TextWatcher;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;


public class RegisterActivity extends AppCompatActivity  {
    private static final String TAG = "RegisterActivity";

    EditText registerEmailET;
    EditText registerPasswordET;

    Button registerdueBtn;

    boolean emailValidate;
    boolean passwordValidatet;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrer);

        registerEmailET = findViewById(R.id.registeremail_et);
        registerPasswordET = findViewById(R.id.registerpassword_et);

        registerdueBtn = findViewById(R.id.registerdue_btn);

        registerEmailET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                emailValidate = isValidEmail(s.toString());
                enableButton();
            }
        });
        registerPasswordET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                passwordValidatet = isValidPassword(s.toString());
                enableButton();
            }
        });
}



    private boolean isValidEmail(String x) {


        return android.util.Patterns.EMAIL_ADDRESS.matcher(x).matches();
    }

    private boolean isValidPassword(String y) {

        return y.length() > 6;

    }



    private void enableButton() {
        registerdueBtn.setEnabled(emailValidate && passwordValidatet);
    }

}







