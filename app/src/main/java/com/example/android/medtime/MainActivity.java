package com.example.android.medtime;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private TextView mMed1TextView , mMed2TextView, mMed3TextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mMed1TextView=findViewById(R.id.tv_med1);
        mMed2TextView = findViewById(R.id.tv_med2);
        mMed3TextView = findViewById(R.id.tv_med3);
        setupSharedPreferences();
    }

    //unregistering the SharedPreferences
    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    // COMPLETED (1) Change the name of default setup to setupSharedPreferences
    private void setupSharedPreferences() {
        // Get all of the values from shared preferences to set it up
        // COMPLETED (2) Get a reference to the default shared preferences from the PreferenceManager class
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        // COMPLETED (3) Get the value of the show_bass checkbox preference and use it to call setShowBass
        Med1Visible(sharedPreferences.getBoolean("show_med1",true));
        Med2Visible(sharedPreferences.getBoolean("show_med2",true));
        Med3Visible(sharedPreferences.getBoolean("show_med3",true));

   //     loadColorSharedPreferences(sharedPreferences);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

//
//    public void loadColorSharedPreferences(SharedPreferences sharedPreferences){
//        mMed1TextView.setBackgroundColor(sharedPreferences.getInt(getString(R.string.pref_color_key),
//                                     getResources().getColor(R.color.red)));
//    }

    private void Med1Visible(boolean b){
        if(b){
            mMed1TextView.setVisibility(View.VISIBLE);
        }
        else{
            mMed1TextView.setVisibility(View.INVISIBLE);
        }
    }

    private void Med2Visible(boolean b){
        if(b){
            mMed2TextView.setVisibility(View.VISIBLE);
        }
        else{
            mMed2TextView.setVisibility(View.INVISIBLE);
        }
    }
    private void Med3Visible(boolean b){
        if(b){
            mMed3TextView.setVisibility(View.VISIBLE);
        }
        else{
            mMed3TextView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        int itemId = item.getItemId();
        if(itemId== R.id.action_settings){
            Intent intent = new Intent(MainActivity.this , SettingsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if (key.equals("show_med1")) {
            Med1Visible(sharedPreferences.getBoolean(key, true));
        } else if (key.equals("show_med2")) {
            Med2Visible(sharedPreferences.getBoolean(key, true));

        } else if (key.equals("show_med3")) {
            Med3Visible(sharedPreferences.getBoolean(key, true));
        }
    }
}