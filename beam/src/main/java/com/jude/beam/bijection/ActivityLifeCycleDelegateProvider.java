package com.jude.beam.bijection;

import android.app.Activity;

/**
 * Created by zhuchenxi on 15/10/6.
 */
public interface ActivityLifeCycleDelegateProvider {
    ActivityLifeCycleDelegate createActivityLifeCycleDelegate(Activity activity);
}
