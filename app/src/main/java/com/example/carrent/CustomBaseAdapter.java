package com.example.carrent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomBaseAdapter extends BaseAdapter {

    Context context;
    String name[];
    String phone[];
    String description[];
    String location[];
    String model[];
    LayoutInflater inflater;

    public CustomBaseAdapter(Context context, String[] name, String[] phone, String[] description,
                             String[] location, String[] model)
    {
        this.context = context;
        this.name = name;
        this.phone = phone;
        this.description = description;
        this.location = location;
        this.model = model;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.rental_item, null);
        TextView txtView1 = convertView.findViewById(R.id.name_textview);
        TextView txtView2 = convertView.findViewById(R.id.phone_textview);
        TextView txtView3 = convertView.findViewById(R.id.desc_textview);
        TextView txtView4 = convertView.findViewById(R.id.location_textview);
        TextView txtView5 = convertView.findViewById(R.id.model_textview);

        txtView1.setText(name[position]);
        txtView2.setText(phone[position]);
        txtView3.setText(description[position]);
        txtView4.setText(location[position]);
        txtView5.setText(model[position]);

        return convertView;
    }
}
