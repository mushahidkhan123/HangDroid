package com.example.mushahid.test;

import android.annotation.TargetApi;
import android.media.Image;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements NumberPicker.OnValueChangeListener {

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NumberPicker numPicker = (NumberPicker) findViewById(R.id.letterPicker);

        String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        numPicker.setMaxValue(25);
        numPicker.setMinValue(0);
        numPicker.setDisplayedValues(alphabet);

        numPicker.setOnValueChangedListener(this);

    }


    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        picker = (NumberPicker) findViewById(R.id.numberPicker);
        TextView txt = (TextView) findViewById(R.id.editText);
        int x = newVal;
        if(x == 1){
            txt.setText("a");
        }else if(x==2){
            txt.setText("b");
        }

    }
}
