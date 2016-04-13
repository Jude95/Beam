package com.jude.beam.model;

import android.content.Context;

/**
 * Created by zhuchenxi on 15/6/7.
 */
public abstract class AbsModel {
    public static final <T extends AbsModel> T getInstance(Class<T> clazz){
       return ModelManager.getInstance(clazz);
    }
    protected void onAppCreate(Context ctx){}
    protected void onAppCreateOnBackThread(Context ctx){}
    protected final void runOnBackThread(Runnable runnable){
        ModelManager.runOnBackThread(runnable);
    }
}
