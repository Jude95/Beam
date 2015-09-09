package com.jude.beam.expansion.list;

import android.view.View;

import com.jude.beam.R;

public class ListConfig{
    static ListConfig Default = new ListConfig();
    public static void setDefaultListConfig(ListConfig config){
        Default = config;
    }

    boolean mRefreshAble;
    boolean mLoadmoreAble;
    boolean mNoMoreAble = true;
    boolean mErrorAble = true;
    boolean mErrorTouchToResumeAble = true;
    boolean mContainerProgressAble = true;
    boolean mContainerEmptyAble = true;
    boolean mContainerErrorAble = true;

    View mContainerLayoutView;
    int mContainerLayoutRes = 0;
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


    public ListConfig setContainerLayoutView(View mContainerLayoutView) {
        this.mContainerLayoutView = mContainerLayoutView;
        return this;
    }

    public ListConfig setContainerLayoutRes(int mContainerLayoutRes) {
        this.mContainerLayoutRes = mContainerLayoutRes;
        return this;
    }
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

    public ListConfig setContainerEmptyView(View mContainerEmptyView) {
        this.mContainerEmptyAble = true;
        this.mContainerEmptyView = mContainerEmptyView;
        return this;
    }

    public ListConfig setContainerEmptyRes(int mContainerEmptyRes) {
        this.mContainerEmptyAble = true;
        this.mContainerEmptyRes = mContainerEmptyRes;
        return this;
    }

    public ListConfig setContainerProgressView(View mContainerProgressView) {
        this.mContainerProgressAble = true;
        this.mContainerProgressView = mContainerProgressView;
        return this;
    }

    public ListConfig setContainerProgressRes(int mContainerProgressRes) {
        this.mContainerProgressAble = true;
        this.mContainerProgressRes = mContainerProgressRes;
        return this;
    }

    public ListConfig setContainerErrorView(View mContainerErrorView) {
        this.mContainerErrorAble = true;
        this.mContainerErrorView = mContainerErrorView;
        return this;
    }
    public ListConfig setContainerErrorRes(int mContainerErrorRes) {
        this.mContainerErrorAble = true;
        this.mContainerErrorRes = mContainerErrorRes;
        return this;
    }
    public ListConfig setContainerProgressAble(boolean mContainerProgressAble) {
        this.mContainerProgressAble = mContainerProgressAble;
        return this;
    }

    public ListConfig setContainerNoMoreAble(boolean mContainerNoMoreAble) {
        this.mContainerEmptyAble = mContainerNoMoreAble;
        return this;
    }

    public ListConfig setContainerErrorAble(boolean mContainerErrorAble) {
        this.mContainerErrorAble = mContainerErrorAble;
        return this;
    }
}