package com.udm.traffiking;



import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.udm.traffiking.controllers.LoginControllers;
import com.udm.traffiking.models.FormStatus;
import com.udm.traffiking.models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {
    TextView register;
    Button loginBtn;
    ImageView googleBtn;

    private Map<String,String> userdata;
    private User user;
    private FormStatus status;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = new User();
        user.addObserver(this);
        userdata = new HashMap<String,String>();
        status = new FormStatus(userdata);
        setContentView(R.layout.activity_main);
        register = findViewById(R.id.already);
        loginBtn = findViewById(R.id.btnLogin);
        googleBtn = findViewById(R.id.BtnGoogle);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        register.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,RegisterActivity.class)));
        loginBtn.setOnClickListener(v-> LoginControllers.validateForm(this));
        googleBtn.setOnClickListener(v->{});
    }

    public Map<String, String> getUserdata() {
        return userdata;
    }

    public User getUser() {
        return user;
    }

    public FormStatus getStatus() {
        return status;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void update(Observable observable, Object o) {
        LoginControllers.loginUser(this);
    }
}