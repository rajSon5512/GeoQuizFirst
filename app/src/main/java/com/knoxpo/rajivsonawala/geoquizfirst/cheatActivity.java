package com.knoxpo.rajivsonawala.geoquizfirst;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class cheatActivity extends AppCompatActivity {


    private final static String Extra_Answer_Is_True="com.knoxpo.rajivsonawala.geoQuizfirst.answer_is_true";

    private TextView mtextAnswer;
    private Button mShowAnswerButton;
    private boolean mvalueIsTrue;
    private static final String Extra_code_Answer_Shown="com.knoxpo.rajivsonawala.geoQuizfirst.answer_shown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mShowAnswerButton=(Button)findViewById(R.id.show_answer_button);
        mtextAnswer=(TextView)findViewById(R.id.answer_text_view);

        mvalueIsTrue= getIntent().getBooleanExtra(Extra_Answer_Is_True,false);

        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mvalueIsTrue) {
                    mtextAnswer.setText(R.string.true_button);
                }
                else
                {
                    mtextAnswer.setText(R.string.false_button);

                }
                setAnswerShownResult(true);
            }
        });


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


         




}

