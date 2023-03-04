package com.example.carrent;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Contact extends AppCompatActivity {

    private EditText nameEditText, emailEditText, messageEditText;
    private TextView confomationText;
    private Button sendButton;

    private String recipientEmail = "example@example.com";
    private String senderEmail = "sender@example.com";
    private String senderPassword = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);

        nameEditText = (EditText) findViewById(R.id.name);
        emailEditText = (EditText) findViewById(R.id.email);
        messageEditText = (EditText) findViewById(R.id.message);
        confomationText = findViewById(R.id.conform);
        sendButton = (Button) findViewById(R.id.submit);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(nameEditText.getText().toString()) && !TextUtils.isEmpty(emailEditText.getText().toString()) && !TextUtils.isEmpty(messageEditText.getText().toString())) {
                    //sendFeedback();
                    confomationText.setText("Thank for your Feedback");
                }
                else{
                    confomationText.setText("Please enter all the details");
                }
            }
        });

    }
}

