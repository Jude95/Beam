package com.jude.beam.expansion.overlay;

import android.view.View;

import com.jude.beam.R;

/**
 * Created by Mr.Jude on 2015/9/25.
 */
public class ViewConfig {
    static ViewConfig Default = new ViewConfig();
    String mProgressTitle;
    View mProgressView;
    int mProgressRes = R.layout.beam_view_progress;
    View mErrorView;
    int mErrorRes = R.layout.beam_view_error;

    public void setmProgressTitle(String mProgressTitle) {
        this.mProgressTitle = mProgressTitle;
    }

    public void setmProgressView(View mProgressView) {
        this.mProgressView = mProgressView;
    }

    public void setmProgressRes(int mProgressRes) {
        this.mProgressRes = mProgressRes;
    }

    public void setmErrorView(View mErrorView) {
        this.mErrorView = mErrorView;
    }

    public void setmErrorRes(int mErrorRes) {
        this.mErrorRes = mErrorRes;
    }


    public void setErrorRes(int mErrorRes) {
        this.mErrorRes = mErrorRes;
    }


    @Override
    public ViewConfig clone(){
        try {
            return (ViewConfig) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return new ViewConfig();
    }
}
