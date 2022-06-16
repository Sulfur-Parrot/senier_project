/*
package com.example.smarthomeapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;

import android.preference.Preference;

public class SettingPreference extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_preference);

        Preference button = (Preference)getPreferenceManager().findPreference("Logout");
        if (button != null) {
            button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference arg0) {
                    SharedPreferences sharedPreferences = getSharedPreferences("autoLogin", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor autoLoginEdit = sharedPreferences.edit();
                    autoLoginEdit.clear();
                    autoLoginEdit.apply();

                    Intent intent = new Intent(SettingPreference.this, LoginActivity.class);
                    startActivity(intent);
                    return true;
                }
            });
        }
    }
}
*/
