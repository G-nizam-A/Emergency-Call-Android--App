package com.example.emergencycallapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.Manifest;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class LandingActivity extends AppCompatActivity {

    private static final int REQUEST_PHONE_CALL_PERMISSION = 1; // Define a constant for the permission request

    Button sosbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        sosbtn = findViewById(R.id.sosbtn);

        sosbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if the app has the phone calling permission
                if (ContextCompat.checkSelfPermission(LandingActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    // Permission is already granted, proceed with your action
                    startEmergencyCallActivity();
                } else {
                    // Permission is not granted, request it from the user
                    ActivityCompat.requestPermissions(LandingActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL_PERMISSION);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PHONE_CALL_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted, proceed with your action
                startEmergencyCallActivity();
            } else {
                // Permission is denied, you can inform the user or take appropriate action
                // You can show a message or handle it as needed
                // For example, show a message that the permission is required to make emergency calls
                Toast.makeText(this, "Permission is required to make emergency calls", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startEmergencyCallActivity() {
        Intent myIntent = new Intent(LandingActivity.this, ContactList.class);
        startActivity(myIntent);
        finish();
    }
}
