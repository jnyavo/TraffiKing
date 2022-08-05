package com.udm.traffiking.data;

import com.udm.traffiking.R;

import java.util.HashMap;
import java.util.Map;

public class LoginData {
    public static Map<String,Integer> fieldIds = new HashMap<String,Integer>() {{
        put("password", R.id.inputPassword);
        put("email", R.id.inputEmail);
    }};
    public static Map<String,Integer> fieldErrorIds = new HashMap<String,Integer>() {{
        put("password", R.string.error_field);
        put("email", R.string.error_field);
    }};
}
