package com.example.henriqueribeirodealmeida.nutre.Data;

import android.content.Context;
import android.content.SharedPreferences;

public class UserInfoContainer {

    private final static String USER_INFO_CONTAINER = "USER_INFO_CONTAINER";

    private final static String NAME_KEY = "NAME_KEY";
    private final static String AGE_KEY = "AGE_KEY";
    private final static String HEIGHT_KEY = "HEIGHT_KEY";
    private final static String WEIGHT_KEY = "WEIGHT_KEY";
    private final static String PHYSICAL_ACTIVITY_KEY = "PHYSICAL_ACTIVITY_KEY";
    private final static String GENDER_KEY = "GENDER_KEY";

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

    public static int getPhysicalActivityIntensity(Context c){
        SharedPreferences sp = c.getSharedPreferences(USER_INFO_CONTAINER, Context.MODE_PRIVATE);
        if (sp.getInt(PHYSICAL_ACTIVITY_KEY, 0) == 0){
            setPhysicalActivityIntensity(c, 0);
        }
        return sp.getInt(PHYSICAL_ACTIVITY_KEY, 0);
    }

    public static void setPhysicalActivityIntensity(Context c, int intensity){
        SharedPreferences sp = c.getSharedPreferences(USER_INFO_CONTAINER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(PHYSICAL_ACTIVITY_KEY, intensity);
        editor.apply();
    }

    public static int getGender(Context c){
        SharedPreferences sp = c.getSharedPreferences(USER_INFO_CONTAINER, Context.MODE_PRIVATE);
        if (sp.getInt(GENDER_KEY, 0) == 0){
            setGender(c, 0);
        }
        return sp.getInt(GENDER_KEY, 0);
    }

    public static void setGender(Context c, int gender){
        SharedPreferences sp = c.getSharedPreferences(USER_INFO_CONTAINER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(GENDER_KEY, gender);
        editor.apply();
    }
}
