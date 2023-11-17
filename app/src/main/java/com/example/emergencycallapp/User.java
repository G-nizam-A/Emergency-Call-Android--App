package com.example.emergencycallapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

// User.java
public class User {
    private String name;
    private String department;
    private String telephone;
    private String mobile;
    private boolean isSignalGreen;
    private boolean hasTelephone;

    public User(String name, String department, String telephone, String mobile, Context context) {
        this.name = name;
        this.department = department;
        this.telephone = telephone;
        this.mobile = mobile;
        this.hasTelephone = !telephone.isEmpty();

        // Load the signal state from shared preferences, or initialize as false (red) by default
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        isSignalGreen = sharedPreferences.getBoolean(name, false);
    }

    public boolean hasTelephone() {
        return hasTelephone;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public boolean isSignalGreen() {
        return isSignalGreen;
    }

    public void setSignalGreen(boolean signalGreen) {
        isSignalGreen = signalGreen;
    }
}
