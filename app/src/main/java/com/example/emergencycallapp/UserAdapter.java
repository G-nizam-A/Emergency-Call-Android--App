package com.example.emergencycallapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> userList;
    private Context context;

    private SharedPreferences sharedPreferences;
    public UserAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_card, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        String userName = user.getName();
        boolean isSignalGreen = user.isSignalGreen();

        holder.textViewName.setText(userName);
        holder.textViewDepartment.setText(user.getDepartment());

        // Check if the user has a telephone number
        if (user.hasTelephone()) {
            holder.buttonTelephone.setVisibility(View.VISIBLE);
        } else {
            // Hide the telephone button for users without a telephone number
            holder.buttonTelephone.setVisibility(View.GONE);
        }

        if (isSignalGreen) {
            holder.imageViewGreenSignal.setVisibility(View.VISIBLE);
            holder.imageViewRedSignal.setVisibility(View.GONE);
            scheduleSignalReset(userName, holder);
        } else {
            holder.imageViewGreenSignal.setVisibility(View.GONE);
            holder.imageViewRedSignal.setVisibility(View.VISIBLE);
        }

        holder.buttonMobile.setOnClickListener(v -> {
            if (!isSignalGreen && makePhoneCall(user.getMobile())) {
                user.setSignalGreen(true);
                notifyItemChanged(position);
                storeSignalState(userName, true);
                holder.imageViewGreenSignal.setVisibility(View.VISIBLE);
                holder.imageViewRedSignal.setVisibility(View.GONE);
                scheduleSignalReset(userName, holder);
            }
        });

        holder.buttonTelephone.setOnClickListener(v -> {
            if (!isSignalGreen && makePhoneCall(user.getTelephone())) {
                user.setSignalGreen(true);
                notifyItemChanged(position);
                storeSignalState(userName, true);
                holder.imageViewGreenSignal.setVisibility(View.VISIBLE);
                holder.imageViewRedSignal.setVisibility(View.GONE);
                scheduleSignalReset(userName, holder);
            }
        });
    }

    private void scheduleSignalReset(String userName, UserViewHolder holder) {
        new Handler().postDelayed(() -> {
            User user = findUserByName(userName);
            if (user != null) {
                user.setSignalGreen(false);
                storeSignalState(userName, false);
                notifyItemChanged(userList.indexOf(user));
                holder.imageViewGreenSignal.setVisibility(View.GONE);
                holder.imageViewRedSignal.setVisibility(View.VISIBLE);
            }
        }, 1 * 60 * 1000); // 1 minute in milliseconds
    }

    private User findUserByName(String userName) {
        for (User user : userList) {
            if (user.getName().equals(userName)) {
                return user;
            }
        }
        return null;
    }

    private void storeSignalState(String userName, boolean isSignalGreen) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(userName, isSignalGreen);
        editor.apply();
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

//    private void makePhoneCall(String phoneNumber) {
//        try {
//            String dial = "tel:" + phoneNumber;
//            context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
//        } catch (Exception e) {
//            // Handle permission denied or other exceptions here
//
//        }
//    }
    private boolean makePhoneCall(String phoneNumber) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            try {
                String dial = "tel:" + phoneNumber;
                context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                return true;
            } catch (Exception e) {
                // Handle any exceptions here
                Toast.makeText(context, "Call failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            // Handle permission denied
            Toast.makeText(context, "Permission denied to make phone calls", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public TextView textViewDepartment;
        public Button buttonMobile;
        public Button buttonTelephone;
        public ImageView imageViewGreenSignal;
        public ImageView imageViewRedSignal;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewDepartment = itemView.findViewById(R.id.textViewDepartment);
            buttonMobile = itemView.findViewById(R.id.buttonMobile);
            buttonTelephone = itemView.findViewById(R.id.buttonTelephone);
            imageViewGreenSignal = itemView.findViewById(R.id.imageViewGreenSignal);
            imageViewRedSignal = itemView.findViewById(R.id.imageViewRedSignal);
        }
    }
}
