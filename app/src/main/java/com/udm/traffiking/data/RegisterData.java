package com.udm.traffiking.data;

import com.udm.traffiking.R;

import java.util.HashMap;
import java.util.Map;

public class RegisterData {
    public static Map<String,Integer> fieldIds = new HashMap<String,Integer>() {{
        put("fname", R.id.fname);
        put("lname", R.id.lname);
        put("password", R.id.inputPassword);
        put("confirmPassword",R.id.inputConfirmPassword);
        put("email", R.id.inputEmail);
    }};
    public static Map<String,Integer> fieldErrorIds = new HashMap<String,Integer>() {{
        put("fname", R.string.error_field);
        put("lname", R.string.error_field);
        put("password", R.string.error_password);
        put("confirmPassword",R.string.error_passwordConfirm);
        put("email", R.string.error_email);
    }};
}
