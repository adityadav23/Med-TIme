package com.example.android.medtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.os.Bundle;
import android.view.MenuItem;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ActionBar actionBar = this.getSupportActionBar();
        //if ActionBar is nut equal to null
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }





    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        int itemId= item.getItemId();
        if(itemId== android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
}