package com.example.smarthomeapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

public class SettingFragment extends PreferenceFragmentCompat {

    private static final String SETTING_PUSH = "Push_notification";
    private static final String SETTING_VIB = "Vib_notification";
    private static final String SETTING_LANGUAGE = "Language";
    private static final String SETTING_LOGOUT = "Logout";
    SharedPreferences prefs;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings_preference);
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        prefs.registerOnSharedPreferenceChangeListener(prefListener);
    }

    SharedPreferences.OnSharedPreferenceChangeListener prefListener =
            new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                    // key값에 해당하는 명령 넣기
                    if (key.equals(SETTING_PUSH)) {
                        Log.d("TAG", key + "SELECTED");
                    } else if (key.equals(SETTING_VIB)) {
                        Log.d("TAG", key + "SELECTED");
                    } else if (key.equals(SETTING_LANGUAGE)) {
                        Log.d("TAG", key + "SELECTED");
                    } else if (key.equals(SETTING_LOGOUT)) {
                        Log.d("TAG", key + "SELECTED");
                    }

                }
            };
}
