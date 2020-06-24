package com.softmaticbd.helloassistant.shared;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.softmaticbd.helloassistant.model.UserContactInfo;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPref {

    private Context context;
    private SharedPreferences  sharedPreferences;
    private UserContactInfo userContactInfo;
    private static final String SHARED_PREFERENCE = "CurrentUser";
    private Type type;


    public SharedPref(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE,context.MODE_PRIVATE);
    }

    public boolean setUserContactInfo(UserContactInfo user){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString("UserInfo",json);
        editor.apply();
        return true;
    }

    public UserContactInfo getUserContactInfo() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString("UserInfo", null);
        if (json != null){
            type = new TypeToken<UserContactInfo>() {
            }.getType();
        }
        userContactInfo = gson.fromJson(json, type);
        return userContactInfo;
    }


    /*    public List<SalesOrderModel> getSalesOrderFromCard() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString("cardSalesOrder", null);
        if (json == null)
            return new ArrayList<SalesOrderModel>();

        Type type = new TypeToken<List<SalesOrderModel>>() {
        }.getType();

        List<SalesOrderModel> salesOrderCarts = new ArrayList<SalesOrderModel>();
        List<SalesOrderModel> tempSalesOrderCarts = gson.fromJson(json, type);
        for (int i = 0; i < tempSalesOrderCarts.size(); i++)
            if (tempSalesOrderCarts.get(i).Qty > 0)
                salesOrderCarts.add(tempSalesOrderCarts.get(i));

//        Collections.sort(salesOrderCarts, new Comparator<SalesOrderModel>() {
//            @Override
//            public int compare(SalesOrderModel lhs, SalesOrderModel rhs) {
//                return lhs.Name.toLowerCase().compareTo(rhs.Name.toLowerCase());
//
//            }
//        });

        return salesOrderCarts;
    }*/


}
