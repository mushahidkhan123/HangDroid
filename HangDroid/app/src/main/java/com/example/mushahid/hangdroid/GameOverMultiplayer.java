package com.example.mushahid.hangdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class GameOverMultiplayer extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_multiplayer);

        String wordToGuess = getIntent().getStringExtra("wordToGuess");
        TextView wordtoGuess = (TextView) findViewById(R.id.wordToGuess);
        wordtoGuess.setText(wordToGuess);

    }


    public void playAgain(View view){
        Intent intent = new Intent(this, MultiplayerSetup.class);
        startActivity(intent);
        finish();
    }

    public void mainMenu(View view){
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
        finish();
    }
}
