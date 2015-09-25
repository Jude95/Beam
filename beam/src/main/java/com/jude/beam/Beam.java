package com.jude.beam;

import android.content.Context;

import com.jude.beam.bijection.ActivityLifeCycleDelegate;
import com.jude.beam.model.ModelManager;

/**
 * Created by Mr.Jude on 2015/7/26.
 */
public final class Beam {
    private static Class<?> activityLifeCycleDelegateClass;

    public static Class<?> getActivityLifeCycleDelegateClass() {
        return activityLifeCycleDelegateClass;
    }

    public static void init(Context ctx){
        ModelManager.init(ctx);
    }
    public static <T extends ActivityLifeCycleDelegate> void registerActivityLifeCycleDelegate(Class<T> activityLifeCycleDelegateClass){
        Beam.activityLifeCycleDelegateClass = activityLifeCycleDelegateClass;
    }
}
