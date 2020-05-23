package com.example.kolekcijapogledanihfilmova.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.kolekcijapogledanihfilmova.R;

public class SplashActivity extends AppCompatActivity {
    private  int SPLASH_DISPLAY_LENGTH = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String brojSekundi = prefs.getString("splashscreentime", "3");

            setContentView(R.layout.activity_splash);
            SPLASH_DISPLAY_LENGTH = Integer.parseInt(brojSekundi) * 1000;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    SplashActivity.this.finish();
                }
            }, SPLASH_DISPLAY_LENGTH);
    }
}
