package com.example.delivery_app_dath;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    ImageView imgv_backLogin;
    TextView txt_register;
    EditText edt_emailLogin, edt_passwordLogin;
    Button btn_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //ẩn actionbar
        getSupportActionBar().hide();

        imgv_backLogin = (ImageView) findViewById(R.id.imgv_backLogin);
        txt_register = (TextView) findViewById(R.id.txt_register);
        edt_passwordLogin= (EditText) findViewById(R.id.edt_passwordLogin);
        edt_emailLogin=(EditText) findViewById(R.id.edt_emailLogin);
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