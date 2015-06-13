package com.example.mushahid.hangdroid;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class GameMultiActivity extends ActionBarActivity implements NumberPicker.OnValueChangeListener {
    int lstofUsedLettersElement = 0;
    List<String> lstOfUsedLetters = new ArrayList<String>();
     String playerLetter = "A";
    String word = "";
    int fails = 0;
    int score = 0;
    int scoretemp = 0;
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_game);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.hangdroid_0);

        NumberPicker numPicker = (NumberPicker) findViewById(R.id.letterPicker);
        String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        numPicker.setMaxValue(25);
        numPicker.setMinValue(0);
        numPicker.setDisplayedValues(alphabet);
        numPicker.setOnValueChangedListener(this);

        String wordSent = getIntent().getStringExtra("word");//get the word from the 4multiplayer set up
        String hint1 = getIntent().getStringExtra("hint"); // get the hint from multiplayer set up
        word = wordSent;

        createTextViews(wordSent);//this will set up the blank texts for the word to guess

        TextView hintOurs = (TextView) findViewById(R.id.ourHint);
        hintOurs.setText(hint1);

        hintOurs.setVisibility(View.VISIBLE);

        TextView hinta = (TextView) findViewById(R.id.hint1);
        hinta.setVisibility(View.VISIBLE);
     }

    public void createTextViews(String s){
        LinearLayout layout = (LinearLayout)findViewById(R.id.LayoutLetters);
        for(int i = 0; i < s.length(); i++){
            TextView myNewText = (TextView)getLayoutInflater().inflate(R.layout.textview, null);
            layout.addView(myNewText);
        }
    }

    public void introduceLetter (View v) {
        if(!lstOfUsedLetters.contains((playerLetter))) {
            lstOfUsedLetters.add(lstofUsedLettersElement,playerLetter);
            lstofUsedLettersElement++;
            boolean letterGuessed = false;
            TextView scoretext = (TextView) findViewById(R.id.score);


            for (int i = 0; i < word.length(); i++) {
                if (playerLetter.charAt(0) == word.charAt(i)) {

                     score++;
                    scoretemp++;
                    letterGuessed = true;
                    showLetterAtIndex(Character.toString((playerLetter.charAt(0))), i);
                }
            }
            if (!letterGuessed) {


                letterFailed(playerLetter);
            } else if (scoretemp == word.length()) {
                clearScreen();

                scoretext.setVisibility(View.INVISIBLE);
            }
        }else if(lstOfUsedLetters.contains((playerLetter))){
                 Toast.makeText(getApplicationContext(), "You have already picked this letter. Please pick again.",
                         Toast.LENGTH_SHORT).show();
             }


 }


    public void clearScreen(){
        lstofUsedLettersElement = 0;
        TextView fail = (TextView) findViewById(R.id.failedtext);
        TextView triesleft = (TextView) findViewById(R.id.triesLeft);
        TextView scorewinText = (TextView) findViewById(R.id.score);
        scoretemp = 0;
        fail.setText("Wrong Letters: ");
        triesleft.setText("Tries Left: 6 ");
        LinearLayout layout = (LinearLayout) findViewById(R.id.LayoutLetters);
        TextView winpageScore = (TextView) findViewById(R.id.textView11);
        for(int i = 0; i < layout.getChildCount(); i++){
            TextView txtview = (TextView) layout.getChildAt(i);
            txtview.setText("_");
        }
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.hangdroid_0);
        LinearLayout youwin = (LinearLayout) findViewById(R.id.youWin);
        youwin.setVisibility(View.VISIBLE);
        fail.setVisibility(View.INVISIBLE);
        triesleft.setVisibility(View.INVISIBLE);
        scorewinText.setVisibility(View.INVISIBLE);
        TextView hintA = (TextView) findViewById(R.id.hint1);
        hintA.setVisibility(View.INVISIBLE);
        TextView hintB = (TextView) findViewById(R.id.ourHint);
        hintB.setVisibility(View.INVISIBLE);
     //   scorewinText.setText("Total Score: " + score);
    }

    public void letterFailed(String s) {
        TextView textView = (TextView) findViewById(R.id.failedtext);
        TextView triesleft = (TextView) findViewById(R.id.triesLeft);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        fails++;

        if (fails == 1) {
            imageView.setImageResource(R.drawable.hangdroid_1);
            triesleft.setText("Tries Left: 5");
            String failedtext = textView.getText().toString() + " " + s ;
            textView.setText(failedtext);
        } else if (fails == 2) {
            imageView.setImageResource(R.drawable.hangdroid_2);
            triesleft.setText("Tries Left: 4");
            String failedtext = textView.getText().toString() + ", " + s ;
            textView.setText(failedtext);
        } else if (fails == 3) {
            imageView.setImageResource(R.drawable.hangdroid_3);
            triesleft.setText("Tries Left: 3");
            String failedtext = textView.getText().toString() + ", " + s ;
            textView.setText(failedtext);
        } else if (fails == 4) {
            imageView.setImageResource(R.drawable.hangdroid_4);
            triesleft.setText("Tries Left: 2");
            String failedtext = textView.getText().toString() + ", " + s ;
            textView.setText(failedtext);
        } else if (fails == 5) {
            imageView.setImageResource(R.drawable.hangdroid_5);
            triesleft.setText("Tries Left: 1");
            String failedtext = textView.getText().toString() + ", " + s ;
            textView.setText(failedtext);
        } else if (fails == 6) {
            Intent gameoverIntents = new Intent(this, GameOverMultiplayer.class);
            gameoverIntents.putExtra("wordToGuess", word);
            startActivity(gameoverIntents);
            finish();
        }
    }



    public void showLetterAtIndex(String s, int i){
        LinearLayout layoutLetter = (LinearLayout) findViewById(R.id.LayoutLetters);
        TextView textView = (TextView) layoutLetter.getChildAt(i);
        textView.setText(s);
    }

    public void continuefcn (View view){
        TextView scoretxt = (TextView) findViewById(R.id.score);
        TextView fails = (TextView) findViewById(R.id.failedtext);
        TextView tries = (TextView) findViewById(R.id.triesLeft);
        LinearLayout youwin = (LinearLayout) findViewById(R.id.youWin);
        youwin.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(this,MultiplayerSetup.class);
        startActivity(intent);
    }


    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        int x = newVal;
        if(x == 0){
            playerLetter = "A";
        }else if (x == 1){
            playerLetter = "B";
        }else if (x == 2){
            playerLetter = "C";
        }else if (x == 3){
            playerLetter = "D";
        }else if (x == 4){
            playerLetter = "E";
        }else if (x == 5){
            playerLetter = "F";
        }else if (x == 6){
            playerLetter = "G";
        }else if (x == 7){
            playerLetter = "H";
        }else if (x == 8){
            playerLetter = "I";
        }else if (x == 9){
            playerLetter = "J";
        }else if (x == 10){
            playerLetter = "K";
        }else if (x == 11){
            playerLetter = "L";
        }else if (x == 12){
            playerLetter = "M";
        }else if (x == 13){
            playerLetter = "N";
        }else if (x == 14){
            playerLetter = "O";
        }else if (x == 15){
            playerLetter = "P";
        }else if (x == 16){
            playerLetter = "Q";
        }else if (x == 17){
            playerLetter = "R";
        }else if (x == 18){
            playerLetter = "S";
        }else if (x == 19){
            playerLetter = "T";
        }else if (x == 20){
            playerLetter = "U";
        }else if (x == 21){
            playerLetter = "V";
        }else if (x == 22){
            playerLetter = "W";
        }else if (x == 23){
            playerLetter = "X";
        }else if (x == 24){
            playerLetter = "Y";
        }else if (x == 25){
            playerLetter = "Z";
        }
    }

    public void mainMenu(View view){
        Intent intent = new Intent( this,MainScreen.class);
        startActivity(intent);
    }
}
