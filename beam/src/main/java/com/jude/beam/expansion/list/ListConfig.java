package com.jude.beam.expansion.list;

import android.view.View;

import com.jude.beam.R;

public class ListConfig{
    boolean mRefreshAble;
    boolean mLoadmoreAble;
    boolean mNoMoreAble;
    boolean mErrorAble = true;
    boolean mErrorTouchToResumeAble = true;
    boolean mContainerProgressAble = true;
    boolean mContainerEmptyAble = true;
    boolean mContainerErrorAble = true;

    View mContainerEmptyView;
    int mContainerEmptyRes = R.layout.beam_view_list_con_empty;
    View mContainerProgressView;
    int mContainerProgressRes = R.layout.beam_view_list_con_progress;
    View mContainerErrorView;
    int mContainerErrorRes = R.layout.beam_view_list_con_error;
    View mLoadMoreView;
    int mLoadMoreRes = R.layout.beam_view_list_more;
    View mNoMoreView;
    int mNoMoreRes = R.layout.beam_view_list_nomore;
    View mErrorView;
    int mErrorRes = R.layout.beam_view_list_error;


    public ListConfig setErrorAble(boolean mErrorAble) {
        this.mErrorAble = mErrorAble;
        return this;
    }

    public ListConfig setErrorView(View mErrorView) {
        this.mErrorView = mErrorView;
        this.mErrorAble = true;
        return this;
    }

    public ListConfig setErrorRes(int mErrorRes) {
        this.mErrorRes = mErrorRes;
        this.mErrorAble = true;
        return this;
    }

    public ListConfig setErrorTouchToResumeAble(boolean mErrorTouchToResumeAble) {
        this.mErrorTouchToResumeAble = mErrorTouchToResumeAble;
        if (mErrorTouchToResumeAble && mErrorRes == R.layout.beam_view_list_error)
            mErrorRes = R.layout.beam_view_list_error_retry;
        else if(!mErrorTouchToResumeAble && mErrorRes == R.layout.beam_view_list_error_retry)
            mErrorRes = R.layout.beam_view_list_error;
        return this;
    }

    public ListConfig setRefreshAble(boolean mRefreshAble) {
        this.mRefreshAble = mRefreshAble;
        return this;
    }

    public ListConfig setLoadmoreAble(boolean mLoadmoreAble) {
        this.mLoadmoreAble = mLoadmoreAble;
        return this;
    }

    public ListConfig setLoadMoreView(View mLoadMoreView) {
        this.mLoadMoreView = mLoadMoreView;
        this.mLoadmoreAble = true;
        return this;
    }

    public ListConfig setLoadMoreRes(int mLoadMoreRes) {
        this.mLoadMoreRes = mLoadMoreRes;
        this.mLoadmoreAble = true;
        return this;
    }

    public ListConfig setNoMoreView(View mNoMoreView) {
        this.mNoMoreView = mNoMoreView;
        this.mNoMoreAble = true;
        return this;
    }

    public ListConfig setNoMoreAble(boolean mNoMoreAble) {
        this.mNoMoreAble = mNoMoreAble;
        return this;
    }
    public ListConfig setNoMoreRes(int mMoMoreRes) {
        this.mNoMoreRes = mMoMoreRes;
        this.mNoMoreAble = true;
        return this;
    }

    public void setContainerEmptyView(View mContainerEmptyView) {
        this.mContainerEmptyAble = true;
        this.mContainerEmptyView = mContainerEmptyView;
    }

    public void setContainerEmptyRes(int mContainerEmptyRes) {
        this.mContainerEmptyAble = true;
        this.mContainerEmptyRes = mContainerEmptyRes;
    }

    public void setContainerProgressView(View mContainerProgressView) {
        this.mContainerProgressAble = true;
        this.mContainerProgressView = mContainerProgressView;
    }

    public void setContainerProgressRes(int mContainerProgressRes) {
        this.mContainerProgressAble = true;
        this.mContainerProgressRes = mContainerProgressRes;
    }

    public void setContainerErrorView(View mContainerErrorView) {
        this.mContainerErrorAble = true;
        this.mContainerErrorView = mContainerErrorView;
    }
    public void setContainerErrorRes(int mContainerErrorRes) {
        this.mContainerErrorAble = true;
        this.mContainerErrorRes = mContainerErrorRes;
    }
    public void setContainerProgressAble(boolean mContainerProgressAble) {
        this.mContainerProgressAble = mContainerProgressAble;
    }

    public void setContainerNoMoreAble(boolean mContainerNoMoreAble) {
        this.mContainerEmptyAble = mContainerNoMoreAble;
    }

    public void setContainerErrorAble(boolean mContainerErrorAble) {
        this.mContainerErrorAble = mContainerErrorAble;
    }
}