package com.example.android.medtime;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

      // To save the state after the recreation of activity
        Med1Visible(sharedPreferences.getBoolean("show_med1",true));
        Med2Visible(sharedPreferences.getBoolean("show_med2",true));
        Med3Visible(sharedPreferences.getBoolean("show_med3",true));
        setColor(sharedPreferences.getString("color","#FF0000"));

        loadSizeFromPreference(sharedPreferences);
        //Registering preference
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

    }

    private void loadSizeFromPreference(SharedPreferences sharedPreferences){
        float size = Float.parseFloat(sharedPreferences.getString("size", "15"));
        setSize(size);
    }

    private void setSize(float i){
        mMed1TextView.setTextSize(i);
    }


   private void setColor(String colorString){

       mMed1TextView.setTextColor(Color.parseColor(colorString));
       mMed2TextView.setTextColor(Color.parseColor(colorString));
       mMed3TextView.setTextColor(Color.parseColor(colorString));

   }

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
        else if(key.equals("color")){
          setColor(sharedPreferences.getString(key, "#FF0000"));

        }else if(key.equals("size")){
            loadSizeFromPreference(sharedPreferences);
        }
    }
}