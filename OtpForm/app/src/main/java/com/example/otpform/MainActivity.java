package com.example.otpform;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText phoneNum;
    Button btnOtp;

    final int min = 1111;
    final int max = 9999;
    final int random = new Random().nextInt((max - min) + 1) + min;
    String OTP = String.valueOf(random);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNum = (EditText) findViewById(R.id.phNum);
        btnOtp = (Button) findViewById(R.id.btnOTP);

        btnOtp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String phoneNumber = phoneNum.getText().toString();
                if(ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.SEND_SMS)  == PackageManager.PERMISSION_GRANTED)
                    sendSMS(phoneNumber, OTP);
                else{
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{android.Manifest.permission.SEND_SMS},100);
                }
                Intent intent  = new Intent(getApplicationContext(), OtpActivity.class);
                intent.putExtra("otp", OTP);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            phoneNum = (EditText) findViewById(R.id.phNum);
            String phoneNumber2 = phoneNum.getText().toString();
            sendSMS(phoneNumber2,OTP);
        }
        else{
            Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show();
        }
    }

    private void sendSMS(String phoneNum, String otp){
        if(!phoneNum.isEmpty()){
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNum,null,otp,null,null);
            Toast.makeText(this,"OTP sent successfully",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"OTP NOT sent",Toast.LENGTH_SHORT).show();
        }

    }
}