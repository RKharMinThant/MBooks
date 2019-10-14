package com.nobletecx.mbooks.utils;

import android.content.Context;
import android.widget.Toast;

public class CommonUtils {

    public static void showToast(Context context, String string) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
    }
}