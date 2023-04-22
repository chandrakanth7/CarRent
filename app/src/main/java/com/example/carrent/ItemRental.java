package com.example.carrent;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class ItemRental extends AppCompatActivity
{
    String name[] = {"Chandu","Abhijith","John"};
    String phone[] = {"6605281899","2051230856","9747829474"};
    String description[] = {"Good", "Better", "Excellent"};
    String location[] = {"123.45 223.74","174.86 994.13", "637.274 974.66"};
    String model[] = {"2019", "2020", "2022"};

    ListView listView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pickup);
        listView = findViewById(R.id.customListView);
        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(),name,phone,
                                                    description, location, model);
        listView.setAdapter(customBaseAdapter);
    }
}
