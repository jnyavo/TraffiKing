package com.udm.traffiking.models;

public class RegisterStatus<T> {



    private T data;
    private Boolean success;
    public RegisterStatus(T data)
    {
        this.data = data;
        success = true;
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
