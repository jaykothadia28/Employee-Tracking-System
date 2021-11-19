package com.project.employeetrackingsystem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

public class Shared {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    int private_MODE = 0;

    String file_NAME = "userSession";
    String IsUserLoggedIn = "isUserLoggedIn";
    String userType = "userType";
    String userName = "userName";

    public Shared(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(file_NAME, private_MODE);
        editor = sharedPreferences.edit();
    }

    public void secondTime(String type, String usName){
        Log.i("SharedPreferences", "SecondTime called");
        editor.putBoolean(IsUserLoggedIn, true);
        editor.putString(userType, type);
        editor.putString(userName, usName);
        editor.apply();
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IsUserLoggedIn, false);
    }
}
