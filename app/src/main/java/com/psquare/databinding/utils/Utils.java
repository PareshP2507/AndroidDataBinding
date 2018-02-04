package com.psquare.databinding.utils;

import android.os.Build;

/**
 * Created by Paresh on 03-02-2018
 */

public class Utils {

    public static boolean isAndroid5() {
        return  Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP;
    }
}
