package com.example.covid19tracker;

public class ExampleItem {

    private String mActive;
    private String mState;
    private String mconf;
    private String mDec;
    private String mRec;

    public ExampleItem(String mActive, String mState, String mconf, String mDec, String mRec) {
        this.mActive = mActive;
        this.mState = mState;
        this.mconf = mconf;
        this.mDec = mDec;
        this.mRec = mRec;
    }

    public String getmActive() {
        return mActive;
    }

    public void setmActive(String mActive) {
        this.mActive = mActive;
    }

    public String getmState() {
        return mState;
    }

    public void setmState(String mState) {
        this.mState = mState;
    }

    public String getMconf() {
        return mconf;
    }

    public void setMconf(String mconf) {
        this.mconf = mconf;
    }

    public String getmDec() {
        return mDec;
    }

    public void setmDec(String mDec) {
        this.mDec = mDec;
    }

    public String getmRec() {
        return mRec;
    }

    public void setmRec(String mRec) {
        this.mRec = mRec;
    }
}
