package com.example.delivery_app_dath.fragment;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.delivery_app_dath.ListVehicleActivity;
import com.example.delivery_app_dath.Modal.Order;
import com.example.delivery_app_dath.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import org.parceler.Parcels;

import java.util.List;
import java.util.Locale;

public class StartOrderFragment extends Fragment {
    Button btn_pickVehicle;
    EditText edt_addressPickup, edt_addressRecive;
    public static Double latitudePickup;
    public static Double longitudePickup ;
    public static Double latitudeRecive;
    public static Double longitudeRecive ;
    int PLACE_PICKER_REQUEST_PICKUP = 1;
    int PLANCE_PICKER_REQUEST_RECIVE = 2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStop() {
        super.onStop();
//        if(edt_addressPickup.getText().toString().trim().matches("") ){
//            btn_pickVehicle.setEnabled(false);
//        }else{
//            btn_pickVehicle.setEnabled(true);
//        }
    }

    @Override
    public void onStart() {
        super.onStart();
//        btn_pickVehicle.setEnabled(false);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start_order, container, false);
        btn_pickVehicle = view.findViewById(R.id.btn_pickVehicle);
        edt_addressPickup = view.findViewById(R.id.edt_addressPickup);
        edt_addressRecive = view.findViewById(R.id.edt_addressRecive);

        edt_addressPickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST_PICKUP);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();

                }

            }

        });

        edt_addressRecive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(getActivity()),PLANCE_PICKER_REQUEST_RECIVE);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });


        btn_pickVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListVehicleActivity.class);
                Order order = new Order();
                order.setLatitudePickup(latitudePickup);
                order.setLongitudePickup(longitudePickup);
                order.setLatitudeRecive(latitudeRecive);
                order.setLongitudeRecive(longitudeRecive);
                order.setDistance(distance(latitudePickup,longitudePickup,latitudeRecive,longitudeRecive));
                Bundle bundle = new Bundle();
                bundle.putParcelable("order", Parcels.wrap(order));
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PLANCE_PICKER_REQUEST_RECIVE) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, getActivity());
                latitudeRecive = place.getLatLng().latitude;
                longitudeRecive = place.getLatLng().longitude;
                String address_location =  getCompleteAddressString(latitudeRecive,longitudeRecive);
//                edt_addressRecive.setText(stringBuilder.toString());
                edt_addressRecive.setText(address_location);
            }
        }
        if (requestCode == PLACE_PICKER_REQUEST_PICKUP){
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, getActivity());
                latitudePickup = place.getLatLng().latitude;
                longitudePickup = place.getLatLng().longitude;
                String address_location =  getCompleteAddressString(latitudePickup,longitudePickup);
                edt_addressPickup.setText(address_location);
            }
        }

    }

    //chuyển đổi latitude(vĩ độ) longitude(tung độ) thành address
    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.w("My Current loction address", strReturnedAddress.toString());
            } else {
                Log.w("My Current loction address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("My Current loction address", "Canont get Address!");
        }
        return strAdd;
    }

    // Tính khoản cách 2 địa chỉ
    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        //chuyển đổi quá km
        dist = dist * 1.609344;
        return dist;
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

}