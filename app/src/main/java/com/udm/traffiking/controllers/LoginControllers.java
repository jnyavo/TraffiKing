package com.udm.traffiking.controllers;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.Build;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.udm.traffiking.HomeActivity;
import com.udm.traffiking.MainActivity;
import com.udm.traffiking.R;
import com.udm.traffiking.data.LoginData;

public class LoginControllers {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void validateForm(MainActivity loginActivity)
    {
        loginActivity.getStatus().validate();
        for (String col: LoginData.fieldIds.keySet())
        {
            EditText field = loginActivity.findViewById(LoginData.fieldIds.get(col));
            String value = field.getText().toString();
            if(field != null)
            if(value.equals(""))
            {
                loginActivity.getStatus().invalidate();
                    try
                    {
                        field.setError(loginActivity.getResources().getString(LoginData.fieldErrorIds.get(col)));
                    }
                    catch(Resources.NotFoundException e){}
                continue;
            }
            loginActivity.getUserdata().put(col,value);

        }
        if (!loginActivity.getStatus().isSuccess())
        {
            return;
        }
        loginActivity.getUser().replaceData(loginActivity.getUserdata());
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void loginUser(MainActivity activity)
    {
        ProgressDialog dialog = new ProgressDialog(activity);

        dialog.setMessage(activity.getResources().getString(R.string.login_progress));
        dialog.setTitle(activity.getResources().getString(R.string.login_label));
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        activity.getUser().login().addOnCompleteListener(task->{
            if(task.isSuccessful()) {
                dialog.setMessage(activity.getResources().getString(R.string.login_success));
                dialog.dismiss();
                NavigationControllers.sendUserToNextActivity(activity, HomeActivity.class);

            }
            else
            {
                dialog.dismiss();
                Toast.makeText(activity,""+task.getException(),Toast.LENGTH_SHORT).show();
            }

        });
    }
}
