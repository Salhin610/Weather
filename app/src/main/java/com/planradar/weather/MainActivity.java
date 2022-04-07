package com.planradar.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import com.planradar.weather.utils.SharedPreferencesController;


public class MainActivity extends AppCompatActivity {
    public MainActivityViewModel mViewModel;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        SharedPreferencesController.mPrefs = getPreferences(MODE_PRIVATE);

        if (!SharedPreferencesController.isFirstTime())// IF not First Time
            mViewModel.setCities();

        observers();

    }

    private void observers() {
        mViewModel.citiesList.observe(this,cities->
                mViewModel.saveCitiesToRoomDB(context, cities));
    }
}