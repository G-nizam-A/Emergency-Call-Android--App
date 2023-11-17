package com.example.emergencycallapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.emergencycallapp.databinding.ActivityEmergencyContactsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class EmergencyContactsActivity extends AppCompatActivity {

    ActivityEmergencyContactsBinding binding;
    ImageView contact;
    FloatingActionButton add_button;

    private ImageView imageViewBackIcon;
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
        binding = ActivityEmergencyContactsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        contact=findViewById(R.id.imageViewContact);

        // back icon
        imageViewBackIcon = findViewById(R.id.imageViewBackIcon);
        add_button = findViewById(R.id.add_button);
        imageViewBackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmergencyContactsActivity.this, LandingActivity.class);
                startActivity(intent);
                finish();
            }
        });

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmergencyContactsActivity.this, ContactsActivity.class);
                startActivity(intent);
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(EmergencyContactsActivity.this, ListContactsActivity.class);
                startActivity(myIntent);
                finish();
            }
        });

        // database
        Cursor cursor = null;
        List<EmergencyContact> emergencyContactList = new ArrayList<>();
        try {
            MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(EmergencyContactsActivity.this);
            cursor = myDatabaseHelper.readAllData();
        } catch (Exception e) {
        }

        if (cursor == null) {
            String text ="you have no emergency contact";
            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
            toast.show();
        } else {
            if (cursor.getCount() != 0) {
                while (cursor.moveToNext()) {
                    EmergencyContact contact = new EmergencyContact(cursor.getString(1), cursor.getString(2));
                    emergencyContactList.add(contact);
                }
            }

            // emergencyContactList.add(new EmergencyContact("test" ,"0000000"));
            System.out.println("list eme : " + emergencyContactList);

            List<String> fullNames = new ArrayList<>();
            List<String> phoneNumbers = new ArrayList<>();
            for (EmergencyContact contact : emergencyContactList) {
                fullNames.add(contact.getFullName());
                phoneNumbers.add(contact.getPhoneNumber());
            }

            ArrayList<EmergencyContact> contactsList = new ArrayList<>();

            for (int i = 0; i < fullNames.size(); i++) {
                EmergencyContact contact = new EmergencyContact(fullNames.get(i), phoneNumbers.get(i));
                contactsList.add(contact);
            }

            ListAdapter listAdapter = new ListAdapter(EmergencyContactsActivity.this, contactsList);

            binding.listViewContacts.setAdapter(listAdapter);
            binding.listViewContacts.setClickable(true);
            binding.listViewContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String text = fullNames.get(i);
                    String phoneNumber = phoneNumbers.get(i);
                    makePhoneCall(phoneNumber);
                }
            });
        }


    }

    private void makePhoneCall(String number) {
        String dial = "tel:" + number;
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
    }
}