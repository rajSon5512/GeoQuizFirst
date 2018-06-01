package com.knoxpo.rajivsonawala.geoquizfirst;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class geoQuizFirst extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;
    private TextView mQuestionTextView;
    private Question[] mQuestionsBank=new Question[]{new Question(R.string.question_australia,true),new Question(R.string.question_ocean,true),
            new Question(R.string.question_mideast,false),new Question(R.string.question_africa,false),new Question(R.string.question_americas,true)
            ,new Question(R.string.question_asia,false)};

    private int mCorrent=0;
    private int mIncorrent=0;
    private int mCurrentIndex=0;
    private final static String mIndex="Index";
    private static int mTempIndex=0;

    private final static String TAG="GeoQuiz";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_quiz_first);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(mIndex, 0);
        }

        Log.d(TAG,"your oncreate Method Is Called.");
        mTrueButton=(Button)findViewById(R.id.tButton);
        mFalseButton=(Button)findViewById(R.id.fButton);
        mNextButton=(ImageButton)findViewById(R.id.nextButton);




        mQuestionTextView=(TextView)findViewById(R.id.question_text_view);
        int question=mQuestionsBank[mCurrentIndex].getTextReadId();

        mQuestionTextView.setText(question);

        mPreviousButton=(ImageButton)findViewById(R.id.previousButton);

        mTrueButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                    checkAnswer(true);

            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mTrueButton.setEnabled(true);
                mTrueButton.setEnabled(true);
                checkAnswer(false);


            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mCurrentIndex = (mCurrentIndex + 1) % mQuestionsBank.length;
                updateQuestion();
                disableButton(true);


            }
        });

        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mCurrentIndex==0)
                {
                    Toast.makeText(geoQuizFirst.this,"This is First Question.",Toast.LENGTH_SHORT).show();
                    return;
                }
                mCurrentIndex=mCurrentIndex-1;
                updateQuestion();
                disableButton(true);
            }
        });


    }


    public void onStart()
    {
        super.onStart();
        Log.d(TAG,"Your onstart method Called");
    }

    public void onResume()
    {
        super.onResume();
        Log.d(TAG,"Your onResume method was Called.");
    }

    public void onPause()
    {
        super.onPause();
        Log.d(TAG,"Your onPause Method Was called.");
    }

    public void onStop()
    {
        super.onStop();
        Log.d(TAG,"Your onStop method called");

    }

    public void onDestroy(){

        super.onDestroy();
        Log.d(TAG,"Your onDestroy method called.");
    }

    public void onRestart(){

        super.onRestart();
        Log.d(TAG,"Your application Restart called.");
    }



    public void updateQuestion(){


        int tempQuestion=mQuestionsBank[mCurrentIndex].getTextReadId();
        mQuestionTextView.setText(tempQuestion);

    }

    public void checkAnswer(boolean answer)
    {
        boolean tempAns=mQuestionsBank[mCurrentIndex].isAnswereTrue();


        int myAns=0;

        if(answer==tempAns)
        {
            myAns=R.string.correct_toast;
            disableButton(false);


        }
        else{

            myAns=R.string.incorrect_toast;
            disableButton(false);

        }


        Toast.makeText(geoQuizFirst.this,myAns,Toast.LENGTH_SHORT).show();


    }


    public void disableButton(boolean ansButton){

        mTrueButton.setEnabled(ansButton);
        mFalseButton.setEnabled(ansButton);

    }


    public void onSaveInstanceState(Bundle savedInstanceState){


        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG,"Your onSaveInstanceState method.");
        Log.i(mIndex,"onStateInstatanceState");
        savedInstanceState.putInt(mIndex,mCurrentIndex);

    }




}

