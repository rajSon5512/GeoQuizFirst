package com.knoxpo.rajivsonawala.geoquizfirst;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class cheatActivity extends AppCompatActivity {


    private final static String Extra_Answer_Is_True="com.knoxpo.rajivsonawala.geoQuizfirst.answer_is_true";

    private TextView mtextAnswer;
    private Button mShowAnswerButton;
    private boolean mvalueIsTrue;
    private final static String TAG="Your Item";
    private final static String mValue="value";
    private static final String Extra_code_Answer_Shown="com.knoxpo.rajivsonawala.geoQuizfirst.answer_shown";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mShowAnswerButton=(Button)findViewById(R.id.show_answer_button);
        mtextAnswer=(TextView)findViewById(R.id.answer_text_view);

        if(savedInstanceState!=null)
        {
            mvalueIsTrue=savedInstanceState.getBoolean(mValue,false);
            showResult(mvalueIsTrue);
        }
        else {

            mvalueIsTrue = getIntent().getBooleanExtra(Extra_Answer_Is_True, false);
        }


        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

               showResult(mvalueIsTrue);

            }
        });

        }



        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void showResult(boolean value)
        {
            if(value)
            {
                mtextAnswer.setText(R.string.true_button);
            }
            else{
                mtextAnswer.setText(R.string.false_button);
            }
            setAnswerShownResult(true);

            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {

                Log.d(TAG,"Version "+Build.VERSION.SDK_INT);
                Log.d(TAG,"Compare Version "+Build.VERSION_CODES.LOLLIPOP);
                int cx = mShowAnswerButton.getWidth() / 2;
                int cy = mShowAnswerButton.getHeight() / 2;
                float redius = mShowAnswerButton.getWidth();
                Animator anim = ViewAnimationUtils.createCircularReveal(mShowAnswerButton, cx, cy, redius, 0);

                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mShowAnswerButton.setVisibility(View.INVISIBLE);

                    }

                });
                anim.start();
            }
        }


        public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
            Intent intent = new Intent(packageContext, cheatActivity.class);
            intent.putExtra(Extra_Answer_Is_True,answerIsTrue);
            return intent;
        }

        public void setAnswerShownResult(boolean tempAnswer){

            Intent nextIntent=new Intent();
            nextIntent.putExtra(Extra_code_Answer_Shown,tempAnswer);
            setResult(RESULT_OK,nextIntent);

        }

         public static boolean wasAnswerShown(Intent result) {
                return result.getBooleanExtra(Extra_code_Answer_Shown, false);
         }


    public void onSaveInstanceState(Bundle savedInstanceState){



        super.onSaveInstanceState(savedInstanceState);
        Log.i(mValue,"Your Value");
        savedInstanceState.putBoolean(mValue,mvalueIsTrue);

    }



    public void onStop()
    {
        super.onStop();
        Log.d(TAG,"Your onStop method called");

    }



}

