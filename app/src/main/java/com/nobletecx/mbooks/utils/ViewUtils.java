package com.nobletecx.mbooks.utils;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class ViewUtils {

    public static void hideActionBar(AppCompatActivity activity) {
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.hide();
    }

    public static void showActionBar(AppCompatActivity activity) {
        ActionBar actionBar = activity.getSupportActionBar();
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        actionBar.show();
    }
}
