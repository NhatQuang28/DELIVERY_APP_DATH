package com.example.delivery_app_dath;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//đùng để kiếm tra người dùng có đăng nhập hay chưa
//và show logo thương hiệu cho user

public class SplastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splast);
        //ẩn action bar
        getSupportActionBar().hide();


        // để người dùng nhìn giao diện trong khoản 2s
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                nextActivity();
            }
            //thực hiện sau khi delay 2s
        },2000);
    }

    private void nextActivity(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            Intent intent = new Intent(SplastActivity.this, RegisterActivity.class);
            startActivity(intent);

        }else{
            Intent intent = new Intent(SplastActivity.this, MainUserActivity.class);
            startActivity(intent);

        }
    }
}