package com.example.examenbbdd;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class ConfigActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
