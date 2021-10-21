package com.example.delivery_app_dath;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delivery_app_dath.Modal.Vehicle;
import com.example.delivery_app_dath.fragment.Adapter.RecyclerViewAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListVehicleActivity extends AppCompatActivity {
    RecyclerViewAdapter recyclerViewAdapter;
    DatabaseReference database;
    RecyclerView recyclerView;
    ArrayList<Vehicle> listVehicle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_vehicle);

        recyclerView = findViewById(R.id.rv_listVehicle);
        database = FirebaseDatabase.getInstance().getReference("vehicle");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listVehicle = new ArrayList<>();
        recyclerViewAdapter = new RecyclerViewAdapter(this, listVehicle);
        recyclerView.setAdapter(recyclerViewAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Vehicle vehicle = dataSnapshot.getValue(Vehicle.class);
                    listVehicle.add(vehicle);
                }
                recyclerViewAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}