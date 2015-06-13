package com.example.mushahid.hangdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainScreen extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }

    //This goes to the single player game screen
    public void StartGame(View v){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    //This goes to the multiplayer setup screen
    public void multiplayerSetup(View view){
        Intent intent = new Intent(this, MultiplayerSetup.class);
        startActivity(intent);
    }

    //This goes to the score screen
    public void scorefcn(View view){
        Intent intent = new Intent(this,ScoreActivity.class);
        startActivity(intent);
    }


}
