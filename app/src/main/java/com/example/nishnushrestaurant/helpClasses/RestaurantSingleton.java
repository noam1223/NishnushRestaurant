package com.example.nishnushrestaurant.helpClasses;


import android.content.Context;

import com.example.nishnushrestaurant.R;

public class RestaurantSingleton {

    public static String userFormatted(String s, Context context){
        return s + "@" + context.getString(R.string.EMAIL);
    }

}
