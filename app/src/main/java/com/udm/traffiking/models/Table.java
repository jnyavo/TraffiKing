package com.udm.traffiking.models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.HashMap;
import java.util.Map;



public interface Table{
    Map<String,String> data = new HashMap<String,String>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    default void replaceData(final Map<String, String> userdata)
    {
        data.replaceAll((k,v) -> userdata.getOrDefault(k,""));
    }

    default String getItem(String key)
    {
        return data.get(key);
    }
    default Map<String,String> getData()
    {
        return data;
    }

}
