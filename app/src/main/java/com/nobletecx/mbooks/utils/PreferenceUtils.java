package com.nobletecx.mbooks.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.nobletecx.mbooks.Constants;

import static android.content.Context.MODE_PRIVATE;

public class PreferenceUtils {

    private static final String TAG = PreferenceUtils.class.getSimpleName();

    public static void savePreferenceBoolean(Context context, String key, boolean value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Constants.MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void savePreferenceString(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Constants.MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void savePreferenceInteger(Context context, String key, int value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Constants.MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static boolean getPreferenceBoolean(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(Constants.MY_PREFS_NAME, MODE_PRIVATE);
        Log.d(TAG, "getPreferenceString: " + preferences.getBoolean(key, false));
        return preferences.getBoolean(key, false);
    }

    public static int getPreferenceInteger(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(Constants.MY_PREFS_NAME, MODE_PRIVATE);
        Log.d(TAG, "getPreferenceString: " + preferences.getInt(key, 0));
        return preferences.getInt(key, 0);
    }

    public static String getPreferenceString(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(Constants.MY_PREFS_NAME, MODE_PRIVATE);
        Log.d(TAG, "getPreferenceString: " + preferences.getString(key, null));
        return preferences.getString(key, null);
    }

    public static void deletePreferenceKey(Context context, String key) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Constants.MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.remove(key);
        editor.apply();
    }
}