package com.example.android.medtime;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import androidx.annotation.Nullable;
import androidx.preference.CheckBoxPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        addPreferencesFromResource(R.xml.pref_main);

        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        //Getting number of preferences
        int count = preferenceScreen.getPreferenceCount();

        //Iterating through all preferences
        for(int i=0; i<count;i++){
            //getting reference of each preference
            Preference p = preferenceScreen.getPreference(i);

            //Checking if it is not CheckBoxPreference
            if(!(p instanceof CheckBoxPreference))
            {
                //Getting value of preference using getKey() function
                String value = sharedPreferences.getString(p.getKey(),"");
                setPreferenceSummary(p, value);

            }
        }


    }

    //Helper method to set summary in listPreference
    private void setPreferenceSummary(Preference p , String value){
        //Checking if instance of ListPreference
        if(p instanceof ListPreference){
            //Typecasting the preference to ListPreference
            ListPreference listPreference =(ListPreference) p;
            //Fetching index using value
            int prefIndex = listPreference.findIndexOfValue(value);
            //checking if valid index
            if(prefIndex>=0){

                listPreference.setSummary(listPreference.getEntries()[prefIndex]);

            }
        }
    }
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        //Getting preference using key
        Preference preference = findPreference(key);
        //checking if not null
        if(null!= preference){
            //checking if not instance of CheckBoxPreference
            if(!(preference instanceof CheckBoxPreference)){
                //getting value of the preference using getKey function
                String value = sharedPreferences.getString(preference.getKey(),"");
                setPreferenceSummary(preference , value);
            }
        }
    }

    //Registering onSharedPreferenceChangeListener
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }


    //Unregistering onSharedPreferenceChangeListener
    @Override
    public void onDestroy() {
        super.onDestroy();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}


