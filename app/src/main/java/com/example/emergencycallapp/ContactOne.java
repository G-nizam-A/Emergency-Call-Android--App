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

public class ContactOne extends AppCompatActivity {
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
        setContentView(R.layout.activity_contact_one);

        recyclerView = findViewById(R.id.userRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1)); // 1 column

        // Create a list of User objects with the provided data
        List<User> userList = new ArrayList<>();
        userList.add(new User("SAHAY RR", "RLM SOUTH", "022-22713331", "8879796256", this));
        userList.add(new User("KUMBHARE REJESH", "STATE HEAD LPG, KARNATAKA", "044-26214337", "9116054888", this));
        userList.add(new User("DEEPAK AGRAWAL", "TERRITORY MANAGER", "0836-2486666", "8130544677", this));
        userList.add(new User("RAJESH P", "PLANT MANAGER", "0836-2486666", "9043724069", this));
        userList.add(new User("MAULIK SHAH", "ASSISTANT MANAGER HSSE (LPG) DHARWAD", "0836-2486666", "9978481366", this));
        userList.add(new User("ALRAZ", "ASSISTANT MANAGER OPS(LPG) DHARWAD", "0836-2486666", "7411873707", this));
        userList.add(new User("SIVAKUMAR G", "MANGAER SALES DHARWAD", "", "8143214377", this));
        userList.add(new User("BHUKYA RAGHU KUMAR", "ASSISTANT MANAGER SALES GULBARGA", "", "9550608059", this));
        userList.add(new User("VIVEK IYER", "BUSSINESS DEVELOPMENT MANGAER LPG KARNATAKA", "080-22975471", "9446433499", this));
        userList.add(new User("JIWAMDAS RAJESH", "OPS & HSSE MGR KARNATAKA", "044-26142125", "7122746158", this));
        userList.add(new User("SOMNATH FULURI", "DIRECTOR - KOSAN CRISPLANT", "", "9849994857", this));
        userList.add(new User("NAVENTH", "COUNTRY HSSC HEAD - KOSAN CRISPLANT", "", "9456561556", this));
        userList.add(new User("SHANMUGA SUNDARAM", "OPERATION CO-ORDINATOR - KOSAN CRISPLANT", "", "9442631372", this));

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
                Intent intent = new Intent(ContactOne.this, ContactList.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
