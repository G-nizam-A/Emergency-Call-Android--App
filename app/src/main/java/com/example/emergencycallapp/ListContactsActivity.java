package com.example.emergencycallapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListContactsActivity extends AppCompatActivity {

    ImageView imageViewBackIcon;

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageview;
    TextView no_data;
    ArrayList<String> contact_id,contact_name, contact_num, contact_email;
    MyDatabaseHelper myDB;
    CustomAdapter customAdapter;
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
        setContentView(R.layout.activity_list_contacts);

        imageViewBackIcon=findViewById(R.id.imageViewBackIcon);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        no_data = findViewById(R.id.no_data);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListContactsActivity.this, ContactsActivity.class);
                startActivity(intent);
            }
        });
        imageViewBackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(ListContactsActivity.this,LandingActivity.class);
                startActivity(myIntent);
                finish();
            }
        });

        myDB = new MyDatabaseHelper(ListContactsActivity.this);
        contact_id= new ArrayList<>();
        contact_name= new ArrayList<>();
        contact_num= new ArrayList<>();
        contact_email= new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(ListContactsActivity.this,this, contact_id,contact_name, contact_num, contact_email);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListContactsActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            //empty_imageview.setVisibility(View.VISIBLE);
            //no_data.setVisibility(View.VISIBLE);
            System.out.println("cursor.getcount is equal to zero");
        }else{
            while (cursor.moveToNext()){
                contact_id.add(cursor.getString(0));
                contact_name.add(cursor.getString(1));
                contact_num.add(cursor.getString(2));
                contact_email.add(cursor.getString(3));
            }

            no_data.setVisibility(View.GONE);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(ListContactsActivity.this);
                myDB.deleteAllData();
                //Refresh Activity
                Intent intent = new Intent(ListContactsActivity.this, ListContactsActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}