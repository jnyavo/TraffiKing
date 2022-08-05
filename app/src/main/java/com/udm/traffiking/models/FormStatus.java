package com.udm.traffiking.models;

public class FormStatus<T> {



    private T data;
    private Boolean success;
    public FormStatus(T data)
    {
        this.data = data;
        success = false;
    }

    public T getData()
    {
        return data;
    }
    public Boolean isSuccess()
    {
        return success;
    }
    public void invalidate()
    {
        success = false;
    }
    public void validate()
    {
        success = true;
    }


}
