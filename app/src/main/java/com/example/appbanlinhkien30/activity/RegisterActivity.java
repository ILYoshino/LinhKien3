package com.example.appbanlinhkien30.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.appbanlinhkien30.R;

public class RegisterActivity extends AppCompatActivity {
    EditText edtUsername, edtPassword, edtPhone;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUsername = (EditText) findViewById(R.id.edtUsernameRegister);
        edtPassword = (EditText) findViewById(R.id.edtPasswordRegister);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        btnRegister = (Button) findViewById(R.id.btnCreateAccount);
    }
}