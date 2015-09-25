package com.jude.beam.expansion.overlay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jude.beam.R;

/**
 * Created by Mr.Jude on 2015/8/18.
 */
public class DefaultViewExpansionDelegate extends ViewExpansionDelegate {
    private ViewConfig mConfig;

    private MaterialDialog mProgressDialog;
    private OnRetryListener mRetryListener;
    private View mProgressView;
    private View mErrorView;

    public DefaultViewExpansionDelegate(Context context, FrameLayout container) {
        super(context, container);
        mConfig = getConfig();
    }

    public ViewConfig getConfig(){
        return ViewConfig.Default.clone();
    }

    @Override
    public void showProgressDialog(String title) {
        mProgressDialog = new MaterialDialog.Builder(getContext())
                .title(title)
                .content(mConfig.mProgressTitle)
                .progress(true, 100)
                .cancelable(false)
                .show();
    }

    @Override
    public void dismissProgressDialog() {
        if (mProgressDialog!=null)
        mProgressDialog.dismiss();
    }

    @Override
    public View showProgressPage() {
        if (mProgressView ==null){
            if (mConfig.mProgressView!=null) mProgressView = mConfig.mProgressView;
            else mProgressView = LayoutInflater.from(getContext()).inflate(mConfig.mProgressRes,getContainer(),false);
        }
        if (mProgressView.getParent()==null)getContainer().addView(mProgressView);
        return mProgressView;
    }

    @Override
    public void dismissProgressPage() {
        if (mProgressView !=null)
            getContainer().removeView(mProgressView);
    }

    @Override
    public View showErrorPage() {
        if (mErrorView ==null){
            if (mConfig.mErrorView!=null) mErrorView = mConfig.mErrorView;
            else mErrorView = LayoutInflater.from(getContext()).inflate(mConfig.mErrorRes,getContainer(),false);
        }
        if (mErrorView.getParent()==null)getContainer().addView(mErrorView);
        mErrorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRetryListener != null) mRetryListener.onRetry();
            }
        });
        return mErrorView;
    }

    @Override
    public void setErrorRetryListener(OnRetryListener listener) {
        mRetryListener = listener;
    }

    @Override
    public void dismissErrorPage() {
        getContainer().removeView(mErrorView);
    }

    @Override
    public void addCustomOverlayView(View view){
        getContainer().addView(view);
    }

    @Override
    public void removeCustomOverlayView(View view){
        getContainer().removeView(view);
    }
}
