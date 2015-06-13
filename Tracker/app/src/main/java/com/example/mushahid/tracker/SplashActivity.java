package com.example.mushahid.tracker;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Handler;



public class SplashActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Runnable runnable3Seconds = new Runnable() {
            @Override
            public void run() {
                mainActivity();
                finish();
            }
        };

       Handler myHandler = new  Handler();
        myHandler.postDelayed(runnable3Seconds, 3000);


    }

    //This moves the app from the loading screen/splash screen to the main activity
    public void mainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
