package com.softmaticbd.helloassistant.utils;

import android.content.Context;
import android.util.Patterns;

public class UserUtility {

    private Context context;

    public UserUtility(Context context) {
        this.context = context;
    }

    public boolean isMobileValid(String phone){
        return  Patterns.PHONE.matcher(phone).matches();
    }
    public boolean isEmailValid(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
