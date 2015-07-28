package com.jude.beamdemo;

import android.content.Context;
import android.util.Log;

import com.jude.beam.model.AbsModel;

/**
 * Created by Mr.Jude on 2015/7/26.
 */
public class CommonModel extends AbsModel {
    @Override
    protected void onAppCreate(Context ctx) {
        Log.i("test","这里是UI线程:"+Thread.currentThread().getName());
    }

    @Override
    protected void onAppCreateOnBackThread(Context ctx) {
        Log.i("test","这里是后台线程"+Thread.currentThread().getName());
    }
}
