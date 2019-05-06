package com.example.henriqueribeirodealmeida.nutre.Data;

import android.content.Context;
import android.content.SharedPreferences;

public class UserInfoContainer {

    private final static String USER_INFO_CONTAINER = "com.uticket.uticket.WebClient.USER_INFO_CONTAINER";

    private final static String NAME_KEY = "com.uticket.uticket.WebClient.NAME_KEY";
    private final static String AGE_KEY = "com.uticket.uticket.WebClient.AGE_KEY";
    private final static String HEIGHT_KEY = "com.uticket.uticket.WebClient.HEIGHT_KEY";
    private final static String WEIGHT_KEY = "com.uticket.uticket.WebClient.WEIGHT_KEY";
    private final static String PHYSICAL_ACTIVITY_KEY = "com.uticket.uticket.WebClient.WEIGHT_KEY";

    public static String getName(Context c){
        SharedPreferences sp = c.getSharedPreferences(USER_INFO_CONTAINER, Context.MODE_PRIVATE);
        if (sp.getString(NAME_KEY, null) == null){
            setName(c, "");
        }
        return sp.getString(NAME_KEY, "");
    }

    public static void setName(Context c, String name){
        SharedPreferences sp = c.getSharedPreferences(USER_INFO_CONTAINER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(NAME_KEY, name);
        editor.apply();
    }

    public static int getAge(Context c){
        SharedPreferences sp = c.getSharedPreferences(USER_INFO_CONTAINER, Context.MODE_PRIVATE);
        if (sp.getInt(AGE_KEY, 0) == 0){
            setAge(c, 0);
        }
        return sp.getInt(AGE_KEY, 0);
    }

    public static void setAge(Context c, int age){
        SharedPreferences sp = c.getSharedPreferences(USER_INFO_CONTAINER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(AGE_KEY, age);
        editor.apply();
    }

    public static int getHeight(Context c){
        SharedPreferences sp = c.getSharedPreferences(USER_INFO_CONTAINER, Context.MODE_PRIVATE);
        if (sp.getInt(HEIGHT_KEY, 0) == 0){
            setHeight(c, 0);
        }
        return sp.getInt(HEIGHT_KEY, 0);
    }

    public static void setHeight(Context c, int height){
        SharedPreferences sp = c.getSharedPreferences(USER_INFO_CONTAINER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(HEIGHT_KEY, height);
        editor.apply();
    }

    public static int getWeight(Context c){
        SharedPreferences sp = c.getSharedPreferences(USER_INFO_CONTAINER, Context.MODE_PRIVATE);
        if (sp.getInt(WEIGHT_KEY, 0) == 0){
            setWeight(c, 0);
        }
        return sp.getInt(WEIGHT_KEY, 0);
    }

    public static void setWeight(Context c, int weight){
        SharedPreferences sp = c.getSharedPreferences(USER_INFO_CONTAINER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(WEIGHT_KEY, weight);
        editor.apply();
    }

    public static String getPhysicalActivityIntensity(Context c){
        SharedPreferences sp = c.getSharedPreferences(USER_INFO_CONTAINER, Context.MODE_PRIVATE);
        if (sp.getString(PHYSICAL_ACTIVITY_KEY, null) == null){
            setPhysicalActivityIntensity(c, "");
        }
        return sp.getString(PHYSICAL_ACTIVITY_KEY, "");
    }

    public static void setPhysicalActivityIntensity(Context c, String intensity){
        SharedPreferences sp = c.getSharedPreferences(USER_INFO_CONTAINER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(PHYSICAL_ACTIVITY_KEY, intensity);
        editor.apply();
    }
}
