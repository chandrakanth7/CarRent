package com.example.carrent;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class LandingPage extends AppCompatActivity {

    CardView login,register,about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        login = findViewById(R.id.cardLogin);
        register = findViewById(R.id.cardRegister);
        about = findViewById(R.id.cardAboutus);

        login.setOnClickListener(v -> startActivity(new Intent(this, LogIn.class)));

        register.setOnClickListener(v -> startActivity(new Intent(this, Signup.class)));

        about.setOnClickListener(v -> startActivity(new Intent(this, About.class)));

    }
}
