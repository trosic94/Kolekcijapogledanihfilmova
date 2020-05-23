package com.example.kolekcijapogledanihfilmova.ui;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.kolekcijapogledanihfilmova.R;

public class FragmentPodesavanja extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}
