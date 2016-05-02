package com.example.natashafinnegan.nihapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class loginSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_settings);
        setTitle(getResources().getString(R.string.title));   // optional

        // Display the game settings PreferenceFragment
        FragmentManager fragmentMgr = getFragmentManager();
        FragmentTransaction fragmentTrnsctn = fragmentMgr.beginTransaction();

        PrefsFrag settingsPrefsFragment = new PrefsFrag();
        fragmentTrnsctn.replace(android.R.id.content, settingsPrefsFragment);
        fragmentTrnsctn.commit();
    }

    public static class PrefsFrag extends PreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.loginsettings);
        }
    }
}
