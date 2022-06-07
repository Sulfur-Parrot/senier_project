package utils;

import android.util.Log;

public class CustomLogger {
    public static boolean IS_DEBUG = true;

    public static void info(String message) {
        if (!IS_DEBUG) {
            return;
        }

        Log.i("DM", message);
    }

    public static void error(String message) {
        if (!IS_DEBUG) {
            return;
        }

        Log.e("DM", message);
    }

    public static void logException(Exception e) {
        if (IS_DEBUG) {
            error(e.getMessage());
        }
    }
}