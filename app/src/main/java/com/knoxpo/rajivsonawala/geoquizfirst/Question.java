package com.knoxpo.rajivsonawala.geoquizfirst;

public class Question {

    private int mTextReadId;
    private boolean mAnswereTrue;

    public Question(int textResId, boolean answerTrue){

        mTextReadId=textResId;
        mAnswereTrue=answerTrue;


    }

    public int getTextReadId() {
        return mTextReadId;
    }

    public void setTextReadId(int textReadId) {
        mTextReadId = textReadId;
    }

    public boolean isAnswereTrue() {
        return mAnswereTrue;
    }

    public void setAnswereTrue(boolean answereTrue) {
        mAnswereTrue = answereTrue;
    }




}
