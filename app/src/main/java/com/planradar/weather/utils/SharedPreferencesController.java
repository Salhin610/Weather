package com.planradar.weather.utils;

import android.content.SharedPreferences;

public class SharedPreferencesController implements Constants {
    public static SharedPreferences mPrefs;


    public static boolean isFirstTime(){
        return mPrefs.getBoolean(prefAppOpenedBefore, false);
    }
    public static void setAppOpenedBefore(){
        SharedPreferences.Editor prefsEditor = mPrefs.edit();

        prefsEditor.putBoolean(prefAppOpenedBefore, true);

        prefsEditor.commit();

    }
}
