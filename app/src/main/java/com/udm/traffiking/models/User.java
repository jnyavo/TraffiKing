package com.udm.traffiking.models;


import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class User extends Observable implements Table {

    public User ()
    {

        data.put("id", "");
        data.put("fname","");
        data.put("lname","");
        data.put("email","");
        data.put("phone","");
        data.put("password","");
        data.put("confirmPassword","");

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public User(final Map<String,String> userdata)
    {
        this();
        this.replaceData(userdata);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void replaceData(final Map<String,String> userdata)
    {
        Table.super.replaceData(userdata);
        setChanged();
        notifyObservers();
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    public Task<AuthResult> createAuth()
    {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        return mAuth.createUserWithEmailAndPassword(getItem("email"),getItem("password"));

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Task<AuthResult> login()
    {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        return mAuth.signInWithEmailAndPassword(getItem("email"),getItem("password"));
    }






}
