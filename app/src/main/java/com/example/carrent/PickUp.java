package com.example.carrent;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class PickUp extends AppCompatActivity
{
    Button msubmitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pick);

        msubmitBtn = findViewById(R.id.submit);
        msubmitBtn.setOnClickListener( v -> {
            Toast.makeText(this, "This functionality will be implemented soon", Toast.LENGTH_SHORT).show();
        });
    }
}