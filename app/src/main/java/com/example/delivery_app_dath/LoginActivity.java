package com.example.delivery_app_dath;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    ImageView imgv_backLogin;
    TextView txt_register;
    EditText edt_phoneNumberLogin, edt_passwordLogin;
    Button btn_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //ẩn actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        imgv_backLogin = (ImageView) findViewById(R.id.imgv_backLogin);
        txt_register = (TextView) findViewById(R.id.txt_register);
        edt_passwordLogin= (EditText) findViewById(R.id.edt_passwordLogin);
        edt_phoneNumberLogin=(EditText) findViewById(R.id.edt_phoneNumberLogin);
        btn_login =(Button) findViewById(R.id.btn_login);

        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        imgv_backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}