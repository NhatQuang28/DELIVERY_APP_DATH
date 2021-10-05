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

public class RegisterActivity extends AppCompatActivity {
    ImageView imgv_backRegister;
    EditText edt_phoneNumberRegister;
    EditText edt_emailRegister;
    EditText edt_passwordRegister;
    EditText edt_verifyPasswordRegister;
    Button btn_register;
    TextView txt_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //ẩn actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        imgv_backRegister = (ImageView) findViewById(R.id.imgv_backRegister);
        edt_phoneNumberRegister=(EditText) findViewById(R.id.edt_phoneNumberRegister);
        edt_emailRegister=(EditText) findViewById(R.id.edt_emailRegister);
        edt_passwordRegister=(EditText) findViewById(R.id.edt_passwordRegister);
        edt_verifyPasswordRegister = (EditText) findViewById(R.id.edt_verifyPasswordRegister);
        btn_register=(Button) findViewById(R.id.btn_register);
        txt_login=(TextView) findViewById(R.id.txt_login);

        imgv_backRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}