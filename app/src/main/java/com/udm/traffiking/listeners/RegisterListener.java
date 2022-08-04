package com.udm.traffiking.listeners;


import android.os.Build;
import android.text.Editable;
import android.widget.EditText;

import androidx.annotation.RequiresApi;



import com.udm.traffiking.RegisterActivity;
import com.udm.traffiking.controllers.RegisterControllers;


public class RegisterListener extends TextChangedListener<EditText>
{

    private RegisterActivity activity;

    public RegisterListener(EditText target, RegisterActivity activity) {
        super(target);
        this.activity = activity;
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onTextChanged(EditText target, Editable s) {
        RegisterControllers.fieldVerification(target,s,activity);
    }
}