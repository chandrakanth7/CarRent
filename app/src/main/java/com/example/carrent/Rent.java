package com.example.carrent;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class Rent extends AppCompatActivity
{
    EditText mFullName,mCarModel,mDescription,mPhone,myear;
    Button mSubmitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rent);
        mFullName = findViewById(R.id.carname);
        mCarModel = findViewById(R.id.carModel);
        mPhone = findViewById(R.id.phone);
        mDescription = findViewById(R.id.description);
        myear = findViewById(R.id.year);
        mSubmitBtn=findViewById(R.id.submit);


        mSubmitBtn.setOnClickListener(v -> {
            Toast.makeText(this, "This functionality will be implemented shortly", Toast.LENGTH_SHORT).show();
            String fullname = mFullName.getText().toString().trim();
            String carmodel= mCarModel.getText().toString().trim();
            String description= mDescription.getText().toString().trim();
            String year= myear.getText().toString().trim();
            String phone= mPhone.getText().toString().trim();
            String type= "Renter";
        });
    }
}
