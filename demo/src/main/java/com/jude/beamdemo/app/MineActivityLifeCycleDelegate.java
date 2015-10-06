package com.jude.beamdemo.app;

import android.app.Activity;
import android.os.Bundle;

import com.jude.beam.bijection.ActivityLifeCycleDelegate;
import com.jude.beam.bijection.ActivityLifeCycleDelegateProvider;

/**
 * Created by Mr.Jude on 2015/8/23.
 */
public class MineActivityLifeCycleDelegate extends ActivityLifeCycleDelegate {

    public MineActivityLifeCycleDelegate(Activity act) {
        super(act);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public static class MineActivityLifeCycleDelegateProvider implements ActivityLifeCycleDelegateProvider{

        @Override
        public ActivityLifeCycleDelegate createActivityLifeCycleDelegate(Activity activity) {
            return new MineActivityLifeCycleDelegate(activity);
        }
    }
}
