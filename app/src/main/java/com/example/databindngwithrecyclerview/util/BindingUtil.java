package com.example.databindngwithrecyclerview.util;

public class BindingUtil {
    private static final String TAG = "BindingUtil";

    public static String convertToSuffix(long count){

        if (count < 1000) return "" + count;
        int exp = (int) (Math.log(count) / Math.log(1000));
        return String.format("%.1f%c",
                count / Math.pow(1000, exp),
                "kmgtpe".charAt(exp - 1));
    }
}
