package com.example.kieran.geoquiz;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FinishedActivity extends ActionBarActivity {

        private TextView score;
        private TextView finished;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished);

        //shows your score on this new activity
        finished = (TextView)findViewById(R.id.finished);
        finished.setText("FINISHED!!!");
        score = (TextView)findViewById(R.id.score);
        score.setText("Score: \n" + (MainActivity.mScore + 1) + " out of 5!");

    }
}
