package com.example.emergencycallapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ContactList extends AppCompatActivity {
    private ImageView imageViewBackIcon;
    private CardView cardView1;
    private CardView cardView2;
    private CardView cardView3;

    @Override
    public void onBackPressed() {
        // Start the MainActivity
        Intent intent = new Intent(this, LandingActivity.class);
        startActivity(intent);

        // Close the current EmergencyContactsActivity
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        cardView1 = findViewById(R.id.cardView1);
        cardView2 = findViewById(R.id.cardView2);
        cardView3 = findViewById(R.id.cardView3);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ContactList.this, ContactOne.class);
                startActivity(myIntent);
                finish();
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ContactList.this, ContactTwo.class);
                startActivity(myIntent);
                finish();
            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ContactList.this, ContactThree.class);
                startActivity(myIntent);
                finish();
            }
        });

    }

    protected void onStart() {
        super.onStart();
        // listeners

        // back arrow
        imageViewBackIcon = findViewById(R.id.imageViewBackIcon);

        imageViewBackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactList.this, LandingActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}