package com.jude.beam.expansion.overlay;

import android.content.Context;
import android.widget.FrameLayout;

import com.jude.beam.expansion.BeamBaseActivity;

/**
 * Created by Mr.Jude on 2015/8/17.
 */
public abstract class ViewExpansionDelegateProvider {
    public abstract ViewExpansionDelegate createViewExpansionDelegate(BeamBaseActivity activity, FrameLayout container);

    public static ViewExpansionDelegateProvider DEFAULT = new ViewExpansionDelegateProvider() {
        @Override
        public ViewExpansionDelegate createViewExpansionDelegate(BeamBaseActivity activity, FrameLayout container) {
            return new DefaultViewExpansionDelegate(activity,container);
        }
    };

}
