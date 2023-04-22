package com.example.carrent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity
{
    private TextView profileName;
    private TextView profileEmail;
    private TextView profilePhone;
    private TextView profilePassword;
    private Button profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        profileName = findViewById(R.id.name);
        profileEmail = findViewById(R.id.email);
        profilePhone = findViewById(R.id.phone);
        profilePassword = findViewById(R.id.password);
        profileButton = findViewById(R.id.btn);

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(getApplicationContext(), LandingPage.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
            }
        });

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser != null)
        {
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(currentUser.getUid());
            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // Retrieve user data from Firebase database
                    String email = dataSnapshot.child("email").getValue(String.class);
                    String name = dataSnapshot.child("name").getValue(String.class);
                    //String password = dataSnapshot.child("password").getValue(String.class);
                    String phone = dataSnapshot.child("phone").getValue(String.class);

                    profileName.setText("Name: "+name);
                    profileEmail.setText("Email: "+email);
                    profilePhone.setText("Phone: "+phone);
                    //profilePassword.setText("Password: "+password);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(Profile.this, "No data to display", Toast.LENGTH_SHORT).show();
                }
            });
        }

//        if(user != null)
//        {
//            String name = user.getDisplayName();
//            String email = user.getEmail();
//            String phone = user.getPhoneNumber();
//
//            profileName.setText("Name: "+name);
//            profileEmail.setText("Email: "+email);
//            profilePhone.setText("Phone: "+phone);
//        }
    }
}
