package com.mk.datingappdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.ArrayAdapter;


public class SignUp extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        AppCompatButton signUp = findViewById(R.id.signUpBtn);
        AppCompatSpinner spinner = findViewById(R.id.country_spinner);

        signUp.setOnClickListener(this);
        String[] countries = getResources().getStringArray(R.array.countries);
        spinner.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, android.R.id.text1,countries));
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, EditProfile.class);
        startActivity(intent);
    }
}
