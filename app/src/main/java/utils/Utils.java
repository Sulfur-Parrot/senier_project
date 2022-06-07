package utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.smarthomeapp.*;

public class Utils {
    public static Boolean isNetworkConnected() {
        try {
            ConnectivityManager cm = (ConnectivityManager) DMApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        } catch (Exception e) {
            CustomLogger.logException(e);
        }
        return false;
    }
}