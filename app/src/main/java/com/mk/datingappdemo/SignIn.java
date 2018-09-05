package com.mk.datingappdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignIn extends AppCompatActivity implements View.OnClickListener {
    private EditText emailEt;
    private EditText passwordEt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        emailEt = findViewById(R.id.email);
        passwordEt = findViewById(R.id.password);
        AppCompatButton signInButton = findViewById(R.id.signinButton);
        AppCompatTextView forgotPasswordTv = findViewById(R.id.forgotPassword);
        AppCompatTextView signUpTv = findViewById(R.id.dontHaveAccount);
        signInButton.setOnClickListener(this);
        forgotPasswordTv.setOnClickListener(this);
        signUpTv.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.signinButton: {
                String email = emailEt.getText().toString().trim();
                String password = passwordEt.getText().toString().trim();
                if (email.equals("admin") && password.equals("admin")) {
                    Intent intent = new Intent(this, Home.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(this, "Login Failed!!", Toast.LENGTH_SHORT).show();
                }
            }
            break;
            case R.id.dontHaveAccount: {
                Intent intent = new Intent(this, SignUp.class);
                startActivity(intent);
            }
            break;
            case R.id.forgotPassword : {
                Toast.makeText(this, "Forgot password clicked!!", Toast.LENGTH_SHORT).show();

            }
            break;
        }
    }
}
