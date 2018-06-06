package com.knoxpo.rajivsonawala.geoquizfirst;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class geoQuizFirst extends AppCompatActivity {

    private Button mCheatButton;
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;
    private int REQUEST_CODE_FOR_CHEAT;
    private TextView mQuestionTextView;
    private boolean mIsCheatAnswer;
    private String messageResId;
    private int mNoOfCheat;
    private final static String mNoIndex="NoOfCheat";

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
            mNoOfCheat=savedInstanceState.getInt(mNoIndex,0);
        }

        Log.d(TAG,"your oncreate Method Is Called.");
        mTrueButton=(Button)findViewById(R.id.tButton);
        mFalseButton=(Button)findViewById(R.id.fButton);
        mNextButton=(ImageButton)findViewById(R.id.nextButton);
        mCheatButton=(Button)findViewById(R.id.cheat_button);




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
                mIsCheatAnswer=false;
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

                if(mCurrentIndex!=0)
                {
                    disableButton(false);
                }

                mCurrentIndex=mCurrentIndex-1;
                updateQuestion();

            }
        });

        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start Activity
               // Intent intent=new Intent(geoQuizFirst.this,cheatActivity.class);
                boolean answerIsTrue = mQuestionsBank[mCurrentIndex].isAnswereTrue();
                Intent intent = cheatActivity.newIntent(geoQuizFirst.this, answerIsTrue);
               // startActivity(intent);
                startActivityForResult(intent,REQUEST_CODE_FOR_CHEAT);

            }
        });


    }


    protected void onActivityResult(int requestCode,int resultCode,Intent data){

            if(resultCode!= Activity.RESULT_OK)
            {
                return;
            }

            if(requestCode==REQUEST_CODE_FOR_CHEAT)
            {
                if(data==null){
                    return;
                }
                mIsCheatAnswer=cheatActivity.wasAnswerShown(data);

            /*    if(mNoOfCheat==2)
                {
                    mCheatButton.setEnabled(false);
                    Toast.makeText(geoQuizFirst.this,"Cheat is Disable",Toast.LENGTH_SHORT).show();
                }

               mNoOfCheat=mNoOfCheat+1;*/



            }

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


        if(mIsCheatAnswer){
            String toastString = getString(R.string.judgment_Toast);
           Toast.makeText(geoQuizFirst.this,toastString,Toast.LENGTH_SHORT).show();
        }
        else {

            if (answer == tempAns) {
                myAns = R.string.correct_toast;
                disableButton(false);
                mCorrent = mCorrent + 1;
            } else {

                myAns = R.string.incorrect_toast;
                disableButton(false);
                mIncorrent = mIncorrent + 1;

            }



            Toast.makeText(geoQuizFirst.this, myAns, Toast.LENGTH_SHORT).show();

            if (mCurrentIndex == 5) {
                String st = new String();
                int precentage = (mCorrent * 100) / mQuestionsBank.length;
                Toast.makeText(geoQuizFirst.this, "Correct:" + st.valueOf(precentage) + "%" + "Incorrect:" + st.valueOf(100 - precentage) + "%", Toast.LENGTH_LONG).show();
                mCorrent = 0;
                mIncorrent = 0;

            }

        }

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
        Log.i(mNoIndex,"Your Cheat value");
        savedInstanceState.putInt(mNoIndex,mNoOfCheat);

    }



    


}

