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

//        txtView2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String number = txtView2.getText().toString();
//                Intent callIntent = new Intent(Intent.ACTION_CALL);
//                callIntent.setData(Uri.parse("tel:"+number));
//                startActivity(callIntent);zz
//                //Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();
//            }
//        });

        return convertView;
    }
}
