package com.example.carrent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    CardView rent, pick, logout, carmap, about, contact, mypin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rent = findViewById(R.id.cardRent);
        pick = findViewById(R.id.cardPick);
        logout = findViewById(R.id.cardLogout);
        carmap = findViewById(R.id.cardCarMap);
        about = findViewById(R.id.cardAboutus);
        contact = findViewById(R.id.cardContact);

        Intent intent = new Intent(MainActivity.this, LandingPage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        rent.setOnClickListener(v -> {
            Intent intent1 = new Intent(getApplicationContext(), Rent.class);
            startActivity(intent1);
        });
        pick.setOnClickListener(v -> {
            Intent intent2 = new Intent(getApplicationContext(), PickUp.class);
            startActivity(intent2);
        });
        carmap.setOnClickListener(v -> {
            Intent intent3 = new Intent(getApplicationContext(), CarMap.class);
            startActivity(intent3);
        });
        about.setOnClickListener(v -> {
            Intent intent4 = new Intent(getApplicationContext(), About.class);
            startActivity(intent4);
        });
        contact.setOnClickListener(v -> {
            Intent intent5 = new Intent(getApplicationContext(), Contact.class);
            startActivity(intent5);
        });
        logout.setOnClickListener(v -> {
            Intent intent6 = new Intent(MainActivity.this, LandingPage.class);
            intent6.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent6);
        });

    }
}