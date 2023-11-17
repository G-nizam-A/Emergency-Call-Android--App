package com.example.emergencycallapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ContactTwo extends AppCompatActivity {
        private RecyclerView recyclerView;
        private UserAdapter userAdapter;
        private ImageView imageViewBackIcon;
        public void onBackPressed() {
            // Start the MainActivity
            Intent intent = new Intent(this, ContactList.class);
            startActivity(intent);

            // Close the current EmergencyContactsActivity
            finish();
        }
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_contact_two);

            recyclerView = findViewById(R.id.userRecyclerView);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 1)); // 1 column

            // Create a list of User objects with the provided data
            List<User> userList = new ArrayList<>();

            userList.add(new User("SOMNATH FULURI", "DIRECTOR - KOSAN CRISPLANT", "","9849994857", this));
            userList.add(new User("NAVENTH", "COUNTRY HSSC HEAD - KOSAN CRISPLANT", "","9456561556", this));
            userList.add(new User("SHANMUGA SUNDARAM", "OPERATION CO-ORDINATOR - KOSAN CRISPLANT", "","9442631372", this));
            userList.add(new User("VINOD PATIL", "PLANT MANAGER DHARWAD - KOSAN", "","9963733299", this));
            userList.add(new User("ARJUN VENUGOPAL", "SAFETY OFFICER DHARWAD - KOSAN", "","7012870553", this));
            userList.add(new User("MANJUNATH U", "OPERATION OFFICER DHARWAD-KOSAN", "","9036543368", this));

            // Create the UserAdapter and set it to the RecyclerView
            userAdapter = new UserAdapter(userList, this);
            recyclerView.setAdapter(userAdapter);
        }
    protected void onStart() {
        super.onStart();
        // listeners

        // back arrow
        imageViewBackIcon = findViewById(R.id.imageViewBackIcon);

        imageViewBackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactTwo.this, ContactList.class);
                startActivity(intent);
                finish();
            }
        });
    }
    }
