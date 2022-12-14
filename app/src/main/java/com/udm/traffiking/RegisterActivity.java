package com.udm.traffiking;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Build;
import android.os.Bundle;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


import com.udm.traffiking.controllers.RegisterControllers;

import com.udm.traffiking.data.RegisterData;
import com.udm.traffiking.listeners.RegisterListener;
import com.udm.traffiking.models.FormStatus;
import com.udm.traffiking.models.User;


import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;



public class RegisterActivity extends AppCompatActivity implements Observer {
    TextView already;
    CheckBox usagePolicy;
    Button btnRegister;


    private Map<String,String> userdata;
    private User user;
    private FormStatus status;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userdata = new HashMap<String,String>();
        user = new User();
        user.addObserver(this);
        status = new FormStatus(userdata);
        setContentView(R.layout.activity_register);
        already = findViewById(R.id.already);

        btnRegister = findViewById(R.id.btnRegister);
        usagePolicy = findViewById(R.id.usageConfirm);


        for (int id:RegisterData.fieldIds.values())
        {
            EditText field = findViewById(id);
            field.addTextChangedListener(new RegisterListener(field,this));
        }
        
        already.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this,MainActivity.class))
        );

        btnRegister.setOnClickListener(v -> RegisterControllers.registerUser(this));

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void update(Observable observable, Object o) {
        RegisterControllers.validateForm(this);
    }


    public FormStatus getStatus()
    {
        return status;
    }
    public User getUser()
    {
        return user;
    }
    public Map<String,String> getUserdata()
    {
        return userdata;
    }


}