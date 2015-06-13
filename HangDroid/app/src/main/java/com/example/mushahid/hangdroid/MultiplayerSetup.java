package com.example.mushahid.hangdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MultiplayerSetup extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);

        EditText guessWord = (EditText) findViewById(R.id.guessWord);
        EditText hint = (EditText)findViewById(R.id.hint);
        guessWord.setText("");
        hint.setText("");
        guessWord.setHint("Enter Word");
        hint.setHint("Enter Hint");
    }

   public void multiplayerGameStart(View view){
       EditText wordToGuess = (EditText) findViewById(R.id.guessWord);
       EditText hint = (EditText) findViewById(R.id.hint);
       String theWordToGuess = wordToGuess.getText().toString();
       String theHint = hint.getText(). toString();
       theHint =  theHint.toUpperCase();
       theWordToGuess = theWordToGuess.toUpperCase();

       Intent intent = new Intent(this, GameMultiActivity.class);
       intent.putExtra("word",theWordToGuess); // store thewordtoguess in word to use in multiplayer game
       intent.putExtra("hint",theHint);
       startActivity(intent);
   }
}
