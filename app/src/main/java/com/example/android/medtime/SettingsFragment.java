package com.example.android.medtime;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import androidx.preference.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        addPreferencesFromResource(R.xml.pref_main);
    }
}
