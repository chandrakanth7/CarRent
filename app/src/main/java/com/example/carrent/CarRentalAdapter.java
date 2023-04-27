package com.example.carrent;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.util.List;

public class CarRentalAdapter extends ArrayAdapter<CarRental> {

    public CarRentalAdapter(Context context, List<CarRental> rentals){
        super(context, 0, rentals);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CarRental rental = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rental_item, parent, false);
        }

        TextView txtView1 = convertView.findViewById(R.id.name_textview);
        TextView txtView2 = convertView.findViewById(R.id.phone_textview);
        TextView txtView3 = convertView.findViewById(R.id.desc_textview);
        TextView txtView4 = convertView.findViewById(R.id.location_textview);
        TextView txtView5 = convertView.findViewById(R.id.model_textview);

        txtView1.setText(rental.fullName);
        txtView2.setText(rental.phone);
        txtView3.setText(rental.description);
        txtView4.setText(rental.location.toString());
        txtView5.setText(rental.carModel);

        return convertView;
    }
}
