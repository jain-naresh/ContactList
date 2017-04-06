package com.example.contact;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class AppPreferences {

    protected static SharedPreferences getSharedPreferences(Context context) {
        SharedPreferences prefrence = PreferenceManager.getDefaultSharedPreferences(context);
        return prefrence;
    }
    /**
     * get string value from preferences
     * @param key- key for which value has to be fetched
     * @return
     */
    public static String getString(String key, Context context) {
        SharedPreferences s = getSharedPreferences(context);
        return s.getString(key, null);
    }

    /**
     * get string value from preferences
     * @param key- key for which value has to be fetched
     * @return
     */
    public static String getString(String key, String defaultValue, Context context) {
        SharedPreferences s = getSharedPreferences(context);
        return s.getString(key, defaultValue);
    }
    /**
     * put string value in preferences
     * @param key- key  for which value has ton stored
     * @param value- value to be stored
     */
    public static void putString(String key, String value,Context context) {
        SharedPreferences s = getSharedPreferences(context);
        SharedPreferences.Editor e = s.edit();
        if (value == null) {
            e.remove(key);
        } else {
            e.putString(key, value);
        }
        e.apply();
    }

    /**
     * put boolean value in preferences
     * @param key- key  for which value has ton stored
     * @param value- value to be stored
     */
    public static void putBoolean(String key, Boolean value,Context context) {
        SharedPreferences s = getSharedPreferences(context);
        SharedPreferences.Editor e = s.edit();
        e.putBoolean(key, value);
        e.apply();
    }

    /**
     * get boolean value from preferences
     * @param key- key for which value has to be fetched
     * @return
     */
    public static boolean getBoolean(String key,Context context) {
        return getBoolean(key,false,context);
    }
    /**
     * get boolean value from preferences
     * @param key- key for which value has to be fetched
     * @return
     */
    public static boolean getBoolean(String key, boolean defaultValue, Context context) {
        SharedPreferences s = getSharedPreferences(context);
        return s.getBoolean(key, defaultValue);
    }

}
