package com.example.delivery_app_dath;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.delivery_app_dath.Modal.Order;
import com.example.delivery_app_dath.Modal.User;
import com.example.delivery_app_dath.Modal.Vehicle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CompleteOrderActivity extends AppCompatActivity {
    TextView tv_distance, tv_phone, tv_price;
    Button btn_completeOder;
    FirebaseUser userCurrent = FirebaseAuth.getInstance().getCurrentUser();
    String nameVehicle;

    private void unitIU() {
        tv_distance = findViewById(R.id.tv_distance);
        tv_phone = findViewById(R.id.tv_phoneNumber);
        tv_price = findViewById(R.id.tv_price);
        btn_completeOder = findViewById(R.id.btn_completeOrder);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_order);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        unitIU();
        DecimalFormat f = new DecimalFormat("##.0");
        DecimalFormat fmoney = new DecimalFormat("##.00");

        //get current phone number user
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child(userCurrent.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                tv_phone.setText(user.getPhonenumber());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        //open order
        Order order = Parcels.unwrap(getIntent().getParcelableExtra("order"));
        nameVehicle = order.getVehicle();
        readData(new MyCallback() {
            @Override
            public void onCallback(Vehicle value) {
                Vehicle vehicle = value;
                if (order.getDistance() <= 1) {
                    tv_price.setText(fmoney.format(vehicle.getAmountperkm()) + " VNĐ");
                    order.setUnitPrice(vehicle.getAmountperkm());
                    order.setPhoneNumber(String.valueOf(tv_phone.getText()));
                    tv_distance.setText(f.format(order.getDistance()) + " km");
                } else {
                    tv_price.setText(fmoney.format(vehicle.getAmountperkm() * order.getDistance()) + " VNĐ");
                    order.setUnitPrice(vehicle.getAmountperkm() * order.getDistance());
                    order.setPhoneNumber(String.valueOf(tv_phone.getText()));
                    tv_distance.setText(f.format(order.getDistance()) + " km");
                }
            }
        });
        btn_completeOder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putOrderData(order);
                Intent intent = new Intent(CompleteOrderActivity.this, MainUserActivity.class);
                startActivity(intent);
            }
        });

    }

    public void putOrderData(Order order) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        order.setCreateDay(dtf.format(now));
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("users");
        reference.child(userCurrent.getUid()).child("order").push().setValue(order);

        //thong bao thanh cong
        Toast myToast = Toast.makeText(CompleteOrderActivity.this, "Đặt đơn thành công", Toast.LENGTH_LONG);
        myToast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        myToast.show();
    }

//    public double calculatorPrice(Order order) {
//        readData(new MyCallback() {
//            @Override
//            public void onCallback(Vehicle value) {
//             vehicle = value;
//            }
//        });
//        double amountperkm = vehicle.getAmountperkm();
//        if (order.getDistance() <= 1) {
//            return amountperkm;
//        } else {
//            return amountperkm*order.getDistance();
//        }
//
//    }

    public interface MyCallback {
        void onCallback(Vehicle value);
    }

    public void readData(MyCallback myCallback) {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("vehicle");
        Query query = mDatabase.orderByChild("name").equalTo(nameVehicle);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    myCallback.onCallback(snapshot.getValue(Vehicle.class));
                }
                Vehicle vehicle = dataSnapshot.getValue(Vehicle.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}


