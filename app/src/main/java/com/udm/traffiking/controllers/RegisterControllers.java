package com.udm.traffiking.controllers;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.Build;
import android.text.Editable;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;


import com.udm.traffiking.HomeActivity;
import com.udm.traffiking.R;
import com.udm.traffiking.RegisterActivity;
import com.udm.traffiking.data.RegisterData;

import java.util.regex.Pattern;

public class RegisterControllers {
    private final static String emailRegex = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}";

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void validateForm(RegisterActivity registerActivity)
    {
        registerActivity.getStatus().validate();

        for (String col: RegisterData.fieldIds.keySet())
        {
            //Toast.makeText(registerActivity,col+' '+registerActivity.getUser().getItem(col),Toast.LENGTH_SHORT).show();
            if(registerActivity.getUser().getItem(col).equals(""))
            {

                registerActivity.getStatus().invalidate();
                EditText field = registerActivity.findViewById(RegisterData.fieldIds.get(col));
                if (field != null)
                    try
                    {
                        field.setError(registerActivity.getResources().getString(RegisterData.fieldErrorIds.get(col)));
                    }
                    catch(Resources.NotFoundException e){}
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void registerUser(RegisterActivity registerActivity)
    {
        //Toast.makeText(registerActivity,registerActivity.getUserdata().toString(),Toast.LENGTH_LONG).show();
        registerActivity.getUser().replaceData(registerActivity.getUserdata());
        //Toast.makeText(registerActivity,registerActivity.getUser().getData().toString(),Toast.LENGTH_LONG).show();
        ProgressDialog dialog = new ProgressDialog(registerActivity);
        if(!(registerActivity.getStatus().isSuccess()))
            return;
        dialog.setMessage(registerActivity.getResources().getString(R.string.registration_progress));
        dialog.setTitle("Registration");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        registerActivity.getUser().createAuth().addOnCompleteListener(task->{
            if(task.isSuccessful())
            {
                dialog.dismiss();
                NavigationControllers.sendUserToNextActivity(registerActivity, HomeActivity.class);
                Toast.makeText(registerActivity,registerActivity.getResources().getString(R.string.registration_success),Toast.LENGTH_SHORT).show();
            }
            else
            {
                dialog.dismiss();
                Toast.makeText(registerActivity,""+task.getException(),Toast.LENGTH_SHORT).show();
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void fieldVerification( EditText target, Editable s, RegisterActivity activity)
    {
        int id = target.getId();
        switch(id)
        {
            case R.id.inputEmail:
                String email = s.toString();
                Pattern pattern = Pattern.compile(emailRegex);
                if (!pattern.matcher(email).matches())
                {
                    target.setError(activity.getResources().getString(R.string.error_email));
                    activity.getStatus().invalidate();
                    break;
                }
                activity.getUserdata().put("email",email);

                break;
            case R.id.inputPassword:
                String password = s.toString();
                if(password.length() < 8)
                {
                    target.setError(activity.getResources().getString(R.string.error_password));
                    activity.getStatus().invalidate();
                    break;
                }
                activity.getUserdata().put("password",password);

                break;
            case R.id.inputConfirmPassword:
                String inputConfirmPassword = s.toString();
                if(!inputConfirmPassword.equals(activity.getUserdata().getOrDefault("password",""))) {
                    target.setError(activity.getResources().getString(R.string.error_passwordConfirm));
                    activity.getStatus().invalidate();
                }
                activity.getUserdata().put("confirmPassword",inputConfirmPassword);
                break;
            default:
                String val = s.toString();
                if(s.toString().equals("")) {
                    target.setError(activity.getResources().getString(R.string.error_field));
                    activity.getStatus().invalidate();
                    break;
                }
                activity.getUserdata().put(activity.getResources().getResourceEntryName(id),val);
        }
    }

}

