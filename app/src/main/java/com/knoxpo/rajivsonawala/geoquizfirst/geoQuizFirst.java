package com.knoxpo.rajivsonawala.geoquizfirst;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class geoQuizFirst extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private Question[] mQuestionsBank=new Question[]{new Question(R.string.question_australia,true),new Question(R.string.question_ocean,true),
            new Question(R.string.question_mideast,false),new Question(R.string.question_africa,false),new Question(R.string.question_americas,true)
            ,new Question(R.string.question_asia,false)};

    private int mCurrentIndex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_quiz_first);

        mTrueButton=(Button)findViewById(R.id.tButton);
        mFalseButton=(Button)findViewById(R.id.fButton);
        mNextButton=(Button)findViewById(R.id.nextButton);
        mQuestionTextView=(TextView)findViewById(R.id.question_text_view);
        int question=mQuestionsBank[mCurrentIndex].getTextReadId();

        mQuestionTextView.setText(question);


        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    checkAnswer(true);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    checkAnswer(false);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mCurrentIndex==mQuestionsBank.length-1)
                {
                    Toast.makeText(geoQuizFirst.this,R.string.noQuestion,Toast.LENGTH_SHORT).show();
                    mCurrentIndex=(-1);

                }
                else
                {
                    updateQuestion();
                }

            }
        });


    }


    public void updateQuestion(){

        mCurrentIndex=mCurrentIndex+1;
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

        }
        else{

            myAns=R.string.incorrect_toast;
        }

        Toast.makeText(geoQuizFirst.this,myAns,Toast.LENGTH_SHORT).show();
    }

}
