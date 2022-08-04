package com.udm.traffiking.models;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseConnection {
    private static FirebaseAuth FAuth;
    private FirebaseUser FUser;

    private FirebaseConnection()
    {
        if (this.FAuth == null)
            FAuth = FirebaseAuth.getInstance();
        FUser = FAuth.getCurrentUser();
    }



}
