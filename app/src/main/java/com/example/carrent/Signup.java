package com.example.carrent;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText mFullName,mEmail,mPassword,mPhone;
    Button mRegisterBtn;
    TextView mLoginBtn;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        mFullName = findViewById(R.id.name);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mPhone = findViewById(R.id.phone);
        mRegisterBtn=findViewById(R.id.register);
        mLoginBtn = findViewById(R.id.login);

            Intent intent = new Intent(Signup.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        mRegisterBtn.setOnClickListener(v -> {
            String email = mEmail.getText().toString().trim();
            String password= mPassword.getText().toString().trim();
            String name= mFullName.getText().toString().trim();
            String phone= mPhone.getText().toString().trim();

            if(TextUtils.isEmpty(email))
            {
                mEmail.setError("Email is Required.");
                return;
            }

            if(TextUtils.isEmpty(password))
            {
                mPassword.setError("Password is Required.");
                return;
            }

            if(password.length() < 6) {
                mPassword.setError("Password Must be >=6 Characters");
                return;
            }

                    Intent intent1 = new Intent(Signup.this, MainActivity.class);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent1);

        });
        mLoginBtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), LogIn.class)));
    }
}
