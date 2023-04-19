package com.example.carrent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    CardView rent, pick, logout, carmap, about, contact, mypin, profile;
    FirebaseAuth fAuth;

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
        profile = findViewById(R.id.cardProfile);

        fAuth= FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser() ==null)
        {
            Intent intent = new Intent(MainActivity.this, LandingPage.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        rent.setOnClickListener(v -> {
                    Intent intent = new Intent(getApplicationContext(), Rent.class);
                    startActivity(intent);
                });

        pick.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), PickUp.class);
            startActivity(intent);
        });
        carmap.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CarMap.class);
            startActivity(intent);
        });
        about.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), About.class);
            startActivity(intent);
        });
        contact.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Contact.class);
            startActivity(intent);
        });
        logout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), LandingPage.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        profile.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Profile.class);
            startActivity(intent);
        });
    }
}