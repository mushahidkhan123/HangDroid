package com.example.mushahid.myfirstapplicatiom;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MyFirstActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_first);
    }


    public void FirstMethod(View v){
        Log.i("MYTAG","method executed");
    findViewById(R.id.myFirstTextView).setVisibility(v.VISIBLE);
    }
}
