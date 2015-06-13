package com.example.mushahid.hangdroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class GameOver extends ActionBarActivity {
    int npoints;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        int points = getIntent().getIntExtra("score_intent", 0);
        TextView totalScore = (TextView) findViewById(R.id.totalScore);
        totalScore.setText("Total Score: " + points);
        npoints = points;

        String wordToGuess = getIntent().getStringExtra("wordToGuess");
        TextView wordtoGuess = (TextView) findViewById(R.id.wordToGuess);
        wordtoGuess.setText(wordToGuess);


    }
    public void mainMenu(View view){
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
        finish();

    }

    public void saveScorefcn(View view){
        SharedPreferences preferences = getSharedPreferences("MYPREFERENCES",Context.MODE_PRIVATE);
        EditText editText = (EditText) findViewById(R.id.nameEditText);
        String name = editText.getText().toString();
        SharedPreferences.Editor editor = preferences.edit();
        String previousScore = preferences.getString("NAME","");
        editor.putString("NAME","Name: " + name + "\n" + "Score: " + npoints + "\n\n" + previousScore);
        editor.commit();

        finish();
}



}
