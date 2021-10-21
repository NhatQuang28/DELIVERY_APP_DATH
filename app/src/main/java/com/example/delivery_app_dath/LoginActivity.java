package com.example.delivery_app_dath;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    ImageView imgv_backLogin;
    TextView txt_register;
    EditText edt_emailLogin, edt_passwordLogin;
    Button btn_login;

    ProgressDialog progressDialog;


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
        progressDialog = new ProgressDialog(this);

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

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSignIn();
            }
        });
    }

    private void onClickSignIn(){

        String email = edt_emailLogin.getText().toString().trim();
        String password = edt_passwordLogin.getText().toString().trim();

        if (email.isEmpty()) {
            edt_emailLogin.setError("Email Trống");
            edt_emailLogin.requestFocus();
            return;
        }

        //kiểm tra đầu vào có phải kiểu email
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edt_emailLogin.setError("Email không hợp lệ");
            edt_emailLogin.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            edt_passwordLogin.setError("Mật Khẩu Trống");
            edt_passwordLogin.requestFocus();
            return;
        }
        progressDialog.show();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Intent intent = new Intent(LoginActivity.this,MainUserActivity.class);
                            startActivity(intent);
                            finishAffinity();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w( "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}