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

public class ContactThree extends AppCompatActivity {
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
        setContentView(R.layout.activity_contact_three);

        recyclerView = findViewById(R.id.userRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1)); // 1 column

        // Create a list of User objects with the provided data
        List<User> userList = new ArrayList<>();

        userList.add(new User("FIRE STATION -DHARWAD CITY", "", "0836-2794555", "9480975101", this));
        userList.add(new User("FIRE STATION -AMARGOL", "", "0836-2322555", "9916861279", this));
        userList.add(new User("FIRE STATION -HUBLI CITY", "", "0836-2240789", "9481015007", this));

        userList.add(new User("FIRE BRIGADE-TATAMOTORS", "", "", "8147097100", this));
        userList.add(new User("AMBULANCE", "", "0836-2448111", "108", this));
        userList.add(new User("DY.DIRECTOR OF FACTORIES", "", "", "9740266669", this));

        userList.add(new User("CIVIL HOSPITAL-DHARWAD", "", "0836-2448111", "9986753204", this));
        userList.add(new User("POLICE STATION-GARAG", "", "0836-2233217", "9480804347", this));
        userList.add(new User("HPCL LPG PLANT -DHARWAD", "", "9731754318", "8056771071", this));
        userList.add(new User("TATA HITACHI CONSTRUCTIONS", "", "", "9243701056", this));
        userList.add(new User("GUJARAT NRE COKE LTD", "", "0836-24861501/01", "9980197075", this));
        userList.add(new User("PRAXAIR INDIA PVT LTD", "", "0836-2466671", "9343961585", this));
        userList.add(new User("DY. COMMISSIONER-DHARWAD", "", "0836-2233888", "9448001322", this));
        userList.add(new User("SP OFFICE -DHARWAD", "", "0836-2233205", "9480806237", this));
        userList.add(new User("POLLUTION CONTROL BOARD REGINAL OFFICE", "", "0836-2465676", "9449060674", this));

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
                Intent intent = new Intent(ContactThree.this, ContactList.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
