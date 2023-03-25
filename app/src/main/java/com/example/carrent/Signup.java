package com.example.carrent;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText mFullName,mEmail,mPassword,mPhone;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        mFullName = findViewById(R.id.name);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mPhone = findViewById(R.id.phone);
        mAddress = findViewById(R.id.address);
        mRegisterBtn=findViewById(R.id.register);
        mLoginBtn = findViewById(R.id.login);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        if(fAuth.getCurrentUser() != null)
        {
            Intent intent = new Intent(Signup.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        mRegisterBtn.setOnClickListener(v -> {
                    String email = mEmail.getText().toString().trim();
                    String password = mPassword.getText().toString().trim();
                    String name = mFullName.getText().toString().trim();
                    String phone = mPhone.getText().toString().trim();zAZ

            if(TextUtils.isEmpty(password))
            {
                mPassword.setError("Password is Required.");
                return;
            }

            if(password.length() < 8)
            {
                mPassword.setError("Password Must be >=8 Characters");
                return;
            }

            fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Toast.makeText(Signup.this, "User Created.", Toast.LENGTH_SHORT) .show();
                    userID = fAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = fStore.collection("users").document(userID);
                    Map<String,Object> user = new HashMap<>();
                    user.put("name",name);
                    user.put("email",email);
                    user.put("phone",phone);
                    user.put("address",address);
                    documentReference.set(user).addOnSuccessListener(aVoid -> {
                        Log.d(TAG,"onSuccess: user Profile is created for "+ userID);
                        Toast.makeText(Signup.this, "Registered Successfully.", Toast.LENGTH_SHORT) .show();
                    });

                    Intent intent = new Intent(Signup.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Signup.this, "Error!" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        });

        mLoginBtn.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), LogIn.class));
        });
    }
}
