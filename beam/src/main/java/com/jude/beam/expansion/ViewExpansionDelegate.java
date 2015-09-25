package com.jude.beam.expansion;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Mr.Jude on 2015/8/17.
 */
public abstract class ViewExpansionDelegate {
    private Context context;
    private FrameLayout container;

    public ViewExpansionDelegate(Context context, FrameLayout container) {
        this.context = context;
        this.container = container;
    }

    public final Context getContext() {
        return context;
    }

    public final FrameLayout getContainer() {
        return container;
    }

    public void showProgressDialog(String title){}
    public void dismissProgressDialog(){}
    public View showProgressPage(){return  null;}
    public void dismissProgressPage(){}
    public View showErrorPage(){return  null;}
    public void dismissErrorPage(){}
    public void addCustomOverlayView(View view){}
    public void removeCustomOverlayView(View view){}

}
