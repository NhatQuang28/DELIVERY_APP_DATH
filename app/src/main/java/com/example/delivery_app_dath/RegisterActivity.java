package com.example.delivery_app_dath;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.delivery_app_dath.Modal.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    ImageView imgv_backRegister;
    EditText edt_phoneNumberRegister;
    EditText edt_emailRegister;
    EditText edt_passwordRegister;
    EditText edt_verifyPasswordRegister;
    EditText edt_nameRegister;
    Button btn_register;
    TextView txt_login;
    private ProgressDialog progressDialog;

    private String urlFirebase = " https://dath-delivery-app-default-rtdb.asia-southeast1.firebasedatabase.app";

    private void unitIU(){
        imgv_backRegister =  findViewById(R.id.imgv_backRegister);
        edt_phoneNumberRegister= findViewById(R.id.edt_phoneNumberRegister);
        edt_emailRegister=findViewById(R.id.edt_emailRegister);
        edt_passwordRegister= findViewById(R.id.edt_passwordRegister);
        edt_verifyPasswordRegister = findViewById(R.id.edt_verifyPasswordRegister);
        edt_nameRegister = findViewById(R.id.edt_NameRegister);
        btn_register=findViewById(R.id.btn_register);
        txt_login=findViewById(R.id.txt_login);
        progressDialog = new ProgressDialog(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //ẩn actionbar
        getSupportActionBar().hide();

        unitIU();

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
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            RegisterUser();
            }
        });
    }

    private void RegisterUser() {
        String phonenumber = edt_phoneNumberRegister.getText().toString().trim();
        String email = edt_emailRegister.getText().toString().trim();
        String password = edt_passwordRegister.getText().toString().trim();
        String passwordVetify = edt_verifyPasswordRegister.getText().toString().trim();
        String name = edt_nameRegister.getText().toString().trim();

        if (name.isEmpty()) {
            edt_nameRegister.setError("Họ tên trống");
            edt_nameRegister.requestFocus();
            return;
        }

        if (phonenumber.isEmpty()) {
            edt_phoneNumberRegister.setError("Số điện thoại trống");
            edt_phoneNumberRegister.requestFocus();
            return;
        }
        //kiểm tra đầu vào số điện thoại
        if (!Patterns.PHONE.matcher(phonenumber).matches()) {
            edt_phoneNumberRegister.setError("Số điện thoại không hợp lệ");
            edt_phoneNumberRegister.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            edt_emailRegister.setError("Email Trống");
            edt_emailRegister.requestFocus();
            return;
        }

        //kiểm tra đầu vào có phải kiểu email
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edt_emailRegister.setError("Email không hợp lệ");
            edt_emailRegister.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            edt_passwordRegister.setError("Mật Khẩu Trống");
            edt_passwordRegister.requestFocus();
            return;
        }
//        if (passwordVetify.isEmpty()) {
//            edt_verifyPasswordRegister.setError("Xác thực mật khẩu trống");
//            edt_verifyPasswordRegister.requestFocus();
//        }else {
//            if (!passwordVetify.equals(password)) {
//                edt_verifyPasswordRegister.setError("Mật khẩu không khớp");
//                edt_verifyPasswordRegister.requestFocus();
//            } else {
//                return;
//            }
//
//        }

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            User user = new User(name,phonenumber,email,password);
                            FirebaseDatabase database = FirebaseDatabase.getInstance(urlFirebase);
                            DatabaseReference myRef = database.getReference("users");
                            myRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user);
                            Intent intent = new Intent(RegisterActivity.this, MainUserActivity.class);
                            startActivity(intent);
                            finishAffinity();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        }
    }