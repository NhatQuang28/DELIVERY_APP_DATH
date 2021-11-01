package com.example.delivery_app_dath.fragment.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delivery_app_dath.Interface.IClickItemVehicleListener;
import com.example.delivery_app_dath.Modal.Vehicle;
import com.example.delivery_app_dath.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    ArrayList<Vehicle> listVehicle;
    IClickItemVehicleListener iClickItemVehicleListener;

    public RecyclerViewAdapter(ArrayList<Vehicle> listVehicle,IClickItemVehicleListener listener) {
        this.listVehicle = listVehicle;
        this.iClickItemVehicleListener=listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Vehicle vehicle = listVehicle.get(position);
        if(vehicle == null){
            return;
        }

        holder.txt_nameVehicle.setText(vehicle.getName());
        holder.txt_boxSize.setText(vehicle.getBox());
        holder.txt_discription.setText(vehicle.getDescription());
        holder.txt_weight.setText(vehicle.getWeight());

        holder.layout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemVehicleListener.onClickItemVehicle(vehicle);
            }
        });
    }


    @Override
    public int getItemCount() {
        if(listVehicle != null)
            return listVehicle.size();
        else
            return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        CardView layout_item;
        TextView txt_nameVehicle;
        TextView txt_discription;
        TextView txt_boxSize;
        TextView txt_weight;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_item = itemView.findViewById(R.id.layout_item_vehicle);
            txt_nameVehicle = itemView.findViewById(R.id.txt_nameVehicle);
            txt_discription = itemView.findViewById(R.id.txt_descriptionVehicle);
            txt_boxSize = itemView.findViewById(R.id.txt_boxSize);
            txt_weight = itemView.findViewById(R.id.txt_weightVehicle);
        }
    }
}
