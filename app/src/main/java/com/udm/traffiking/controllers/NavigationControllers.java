package com.udm.traffiking.controllers;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class NavigationControllers {
    public static void sendUserToNextActivity(AppCompatActivity prev, Class<?> next)
    {
        Intent intent = new Intent(prev.getApplicationContext(),next);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        prev.startActivity(intent);
    }
}
