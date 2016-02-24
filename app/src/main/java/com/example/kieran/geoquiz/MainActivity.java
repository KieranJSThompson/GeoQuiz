package com.example.kieran.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    public static int mScore;

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button previousButton;

    private int mCurrentIndex = 0;


    private Intent myIntent;



    //Array of Questions created in the strings.xml file
    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_ocean, true),
            new TrueFalse(R.string.question_mideast, false),
            new TrueFalse(R.string.question_africa, false),
            new TrueFalse(R.string.question_americas, true),
            new TrueFalse(R.string.question_asia, true)
    };

    private TextView mQuestionTextView;

    //updates the question text
    public void updateQuestion(){
        mQuestionTextView = (TextView)findViewById(R.id.mQuestionTextView);
        int question = mQuestionBank[mCurrentIndex].getmQuestion();
        mQuestionTextView.setText(question);
    }

    //tests to see if question is true or not based on its boolean input above
    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].ismTrueQuestion();

        int messageResId = 0;

        if(userPressedTrue == answerIsTrue){
            messageResId = R.string.correct_toast;

        } else{
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
    //updates score if the answer is correct!
    private void updateScore(){

        if(mQuestionBank[mCurrentIndex].ismTrueQuestion() == true){
            mScore++;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //creates action listener for trueButton
        trueButton = (Button)findViewById(R.id.true_button);
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                updateScore();
            }

        });

        //creates action listener for falseButton
        falseButton = (Button)findViewById(R.id.false_button);
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                updateScore();
            }
        });

        //creates an action listener for nextButton allowing it to change questions
        nextButton = (Button)findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if the current question is the last of the questions it goes to the finished page
                if(mCurrentIndex == mQuestionBank.length){
                    myIntent = new Intent(MainActivity.this, FinishedActivity.class);
                    MainActivity.this.startActivity(myIntent);
                }
                else {
                    mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                    updateQuestion();
                }
            }
        });
        updateQuestion();

        previousButton = (Button)findViewById(R.id.previous_button);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex == 0) {
                    Toast.makeText(MainActivity.this, "This is the first question", Toast.LENGTH_SHORT).show();
                }
                else{
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;

                    updateQuestion();
                }
            }
        });
        updateQuestion();
    }
}

