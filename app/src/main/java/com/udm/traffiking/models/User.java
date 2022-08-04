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

    Map<String,String> data;



    public User ()
    {
        data = new HashMap<String, String>() {{
            put("id", "");
            put("fname","");
            put("lname","");
            put("email","");
            put("phone","");
            put("password","");
            put("confirmPassword","");
        }};
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

    public Task<AuthResult> update()
    {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        return mAuth.createUserWithEmailAndPassword(getItem("email"),getItem("password"));

    }





}
