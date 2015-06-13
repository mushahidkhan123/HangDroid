package com.example.mushahid.hangdroid;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class GameActivity extends ActionBarActivity implements NumberPicker.OnValueChangeListener {
    int lstofUsedLettersElement = 0;
    List<String> lstOfUsedLetters = new ArrayList<String>();
    String hint = "";
    String playerLetter = "A"; //Initial letter of player from the letter picker
    String word = "";  //Initially the word to guess is null
    int fails = 0; //fails is the number of letters guessed wrong
    int score = 0; //score is the total score so far for a player
    int scoretemp = 0; //scoretemp is the score for the guessing the current word
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.hangdroid_0);

        //We will change the number picker to a letter picker
        NumberPicker numPicker = (NumberPicker) findViewById(R.id.letterPicker); //identifying the number picker
        String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        numPicker.setMaxValue(25);//maximum index is 25
        numPicker.setMinValue(0);//minimum index is 0
        numPicker.setDisplayedValues(alphabet);
        numPicker.setOnValueChangedListener(this);
        randomWord(); //This will enable us to pick a random word to guess

     }

    //User will choose a letter which is playerLetter and here it iwll be checked if the letter is in the word we hve to guess. This will be called after the user touches the check letter button
    public void introduceLetter (View v){
        if(!lstOfUsedLetters.contains(playerLetter)) {
            lstOfUsedLetters.add(lstofUsedLettersElement,playerLetter);
            lstofUsedLettersElement++;
            boolean letterGuessed = false;
            TextView scoretext = (TextView) findViewById(R.id.score);
            for (int i = 0; i < word.length(); i++) {
                if (playerLetter.charAt(0) == word.charAt(i)) {
                    score++;
                    scoretemp++;
                    scoretext.setText("SCORE: " + score);
                    letterGuessed = true;
                    showLetterAtIndex(Character.toString((playerLetter.charAt(0))), i); //here we will show the letter we have guessed at index i
                }
            }
            if (!letterGuessed) { //go here if we have not guessed a letter
                letterFailed(playerLetter);
            } else if (scoretemp == word.length()) {
                clearScreen();
                scoretext.setVisibility(View.INVISIBLE);

            }
        }
        else if(lstOfUsedLetters.contains((playerLetter))){
            Toast.makeText(getApplicationContext(), "You have already picked this letter. Please pick again.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void clearScreen(){
         lstofUsedLettersElement = 0;
        lstOfUsedLetters.clear();
        TextView hintsinglePlayer = (TextView)findViewById(R.id.hintSinglePlayer);
        TextView fail = (TextView) findViewById(R.id.failedtext);
        TextView triesleft = (TextView) findViewById(R.id.triesLeft);
        TextView scorewinText = (TextView) findViewById(R.id.score);
        scoretemp = 0;
        fail.setText("Wrong Letters: ");
        triesleft.setText("Tries Left: 6 ");
        LinearLayout layout = (LinearLayout) findViewById(R.id.LayoutLetters);
        TextView winpageScore = (TextView) findViewById(R.id.textView11);
        winpageScore.setText("Total Score: " + score);
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
        scorewinText.setText("Total Score: " + score);
        hintsinglePlayer.setVisibility(View.INVISIBLE);


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
        } else if (fails == 6) {//with 6 fails, its time to go to the game over screen
            Intent gameoverIntent = new Intent(this, GameOver.class);
            gameoverIntent.putExtra("score_intent", score);
            gameoverIntent.putExtra("wordToGuess", word);
            startActivity(gameoverIntent);
            finish();
        }
    }



    public void showLetterAtIndex(String s, int i){
        LinearLayout layoutLetter = (LinearLayout) findViewById(R.id.LayoutLetters);
        TextView textView = (TextView) layoutLetter.getChildAt(i); //finding blank i
        textView.setText(s);
    }

    public void continuefcn (View view){//if user continues to play
        randomWord();
        TextView hintsinglePlayer = (TextView)findViewById(R.id.hintSinglePlayer);
        TextView scoretxt = (TextView) findViewById(R.id.score);
        TextView fails = (TextView) findViewById(R.id.failedtext);
        TextView tries = (TextView) findViewById(R.id.triesLeft);
        LinearLayout youwin = (LinearLayout) findViewById(R.id.youWin);
        youwin.setVisibility(View.INVISIBLE);
        scoretxt.setVisibility(View.VISIBLE);
        fails.setVisibility(View.VISIBLE);
        tries.setVisibility(View.VISIBLE);
        hintsinglePlayer.setVisibility(View.VISIBLE);
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {//built in function that will keep track of new value on the letter picker
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

    public void randomWord(){
        String wordList[][]={{"aahed","expressing pleasurse"},{"aback","towards the rear"},{"abase","degrading someone"},{"abash","embarrased"},
                {"ample","plentiful"},{"anger","emotion"},{"amigo","friend"},{"anime","japan's cartoon"},{"bacon","oink"},{"babel","noisy confusion"},
                {"banks","finance"},{"bangs","hair"},{"basin","drainage"},{"beans","food"},{"black","color"},{"boxes","storage"},{"build","verb"},
                {"cabel", "television"},{"cages","contain"},{"calls","phone"},{"cause","make happen"},{"champ","match"},{"chimp","planet of the apes"},
                {"china","country"},{"clock","time"},{"color","rainbow"},{"court","law"},{"crawl","toddler"},{"crime","against the law"},
                {"dances","music"},{"daddy","father"},{"debit","card"},{"debts","liabilities"},{"delta","change"},{"disco","music"},{"dirty","not clean"},
                {"donut","cops"},{"drink","water"},{"elvis","singer"},{"eagle","bird"},{"eight","number"},{"elbow","body part"},
                {"facts","information"},{"facet","one side"},{"fable","story"},{"favor","kindness"},{"fairy","magic"},
                {"filth","dirty"},{"fishy","suspicion"},{"goods","items"},{"games", "fun"},{"gases","can't breathe"},
                {"gifts","presents"},{"gills","fish"},{"germs","dirty"},{"gated","confined"},{"hades","underworld"},
                {"habit","can't stop doing"},{"hairy","hair"},{"halos","angel"},{"halts","stops"},{"hater","dislike"},
                {"hiker","one who hikes"},{"hippo","large animal"},{"house", "home"},{"idiot","dumb"},{"image","picture"},{"index","sign"},
                {"jimmy","neutron"},{"juice","made from fruits"},{"juicy","full of juice"},{"joker","batman"},{"kabob","food"},{"kendo","sword"},
                {"kills","taking lives"},{"kelps","seaweeds"},{"lover","one who loves"},{"labor","worker"},{"label","information"},{"lapel","coat part"},
                {"legal","allowed by law"},{"later","after"},{"laser","beam"},{"learn","understand"},{"ledge","surface"},
                {"macho","manly"},{"magic","illusion"},{"major","university"},{"marks","result"},{"meals","food"},{"merry","christmas"},
                {"messy","out of order"},{"micro","small"},{"month","date"},{"music","tunes"},{"nacho","food"},{"nails","on hands"},
                {"nerds","smart people"},{"noise","sound"},{"nerdy","nerd"},{"nurse","hospital"},{"ocean","water"},{"onion","vegetable"},
                {"opera","singing"},{"ounce","weight"},{"peter","pan"},{"pants","clothing"},{"peace","no violence"},{"piano","music"},
                {"point","tip"},{"poker","card game"},{"qatar","place"},{"quack","duck"},{"racer","race"},{"rainy","wet"},{"raven","bird"},
                {"river","water"},{"scary","monster"},{"scout","knots"},{"shout","loud"},{"skill","talent"},{"skull","bones"},
                {"tacos","food"},{"tasty","yummy"},{"tease","annoy"},{"today","calendar"},{"trial","court"},{"users","use"},{"urban","city"},
                {"viper","poison",},{"virus","computer"},{"whale","animal"},{"weird","strange"},{"whole","complete"},{"yahoo","mail"},
                {"zebra","animal"}};





       // String[] wordsList = words.split(" ");
        int randomNumber = (int) (Math.random() * wordList.length);
        String chosenWord = wordList[randomNumber][0];
        word = chosenWord.toUpperCase();
        hint = wordList[randomNumber][1];
        TextView hintsinglePlayer = (TextView)findViewById(R.id.hintSinglePlayer);
        hintsinglePlayer.setText("HINT: " + hint.toUpperCase());
            }

    public void mainMenu(View view){
        Intent intent = new Intent( this,MainScreen.class);
        startActivity(intent);
    }
}
