package com.example.otpform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OtpActivity extends AppCompatActivity {

    EditText otpGiven;
    Button checkOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        otpGiven = (EditText) findViewById(R.id.otp1);
        checkOtp = (Button) findViewById(R.id.btnCheck);

        checkOtp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String otpGivenByUser = otpGiven.getText().toString();
                Intent intent = getIntent();
                String OTP = intent.getStringExtra("otp");

                if(otpGivenByUser.equals(OTP)){
                    Intent newIntent  = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(newIntent);
                }
                else
                    Toast.makeText(OtpActivity.this,"Please enter correct OTP", Toast.LENGTH_SHORT).show();

            }
        });
    }
}