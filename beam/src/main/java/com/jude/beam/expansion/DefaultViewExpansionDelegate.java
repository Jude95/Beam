package com.jude.beam.expansion;

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
    private MaterialDialog mProgressDialog;
    private View mLoadingView;
    private View mError;

    public DefaultViewExpansionDelegate(Context context, FrameLayout container) {
        super(context, container);
    }

    @Override
    public void showProgressDialog(String title) {
        mProgressDialog = new MaterialDialog.Builder(getContext())
                .title(title)
                .content("请稍候")
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
        if (mLoadingView==null)mLoadingView = LayoutInflater.from(getContext()).inflate(R.layout.beam_view_progress,getContainer(),false);
        if (mLoadingView.getParent()==null)getContainer().addView(mLoadingView);
        return mLoadingView;
    }

    @Override
    public void dismissProgressPage() {
        if (mLoadingView!=null)
            getContainer().removeView(mLoadingView);
    }

    @Override
    public View showErrorPage() {
        if (mError==null)mError = LayoutInflater.from(getContext()).inflate(R.layout.beam_view_error,getContainer(),false);
        if (mError.getParent()==null)getContainer().addView(mError);
        return mError;
    }

    @Override
    public void dismissErrorPage() {
        getContainer().removeView(mError);
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
