package com.example.delivery_app_dath.fragment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delivery_app_dath.Modal.Vehicle;
import com.example.delivery_app_dath.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<Vehicle> listVehicle;

    public RecyclerViewAdapter(Context context, ArrayList<Vehicle> listVehicle) {
        this.context = context;
        this.listVehicle = listVehicle;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vehicle_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Vehicle vehicle = listVehicle.get(position);

        holder.txt_nameVehicle.setText(vehicle.getName());
        holder.txt_boxSize.setText(vehicle.getBox());
        holder.txt_discription.setText(vehicle.getDescription());
        holder.txt_weight.setText(vehicle.getWeight());
    }

    @Override
    public int getItemCount() {
        return listVehicle.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_nameVehicle;
        TextView txt_discription;
        TextView txt_boxSize;
        TextView txt_weight;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_nameVehicle = itemView.findViewById(R.id.txt_nameVehicle);
            txt_discription = itemView.findViewById(R.id.txt_descriptionVehicle);
            txt_boxSize = itemView.findViewById(R.id.txt_boxSize);
            txt_weight = itemView.findViewById(R.id.txt_weightVehicle);
        }
    }
}
