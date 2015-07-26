package com.jude.beamdome;

import android.content.Context;
import android.util.Log;

import com.jude.beam.model.AbsModel;

/**
 * Created by Mr.Jude on 2015/7/26.
 */
public class CommonModel extends AbsModel {
    @Override
    protected void onAppCreate(Context ctx) {
        Log.i("test","onAppCreate:"+Thread.currentThread().getName());
    }

    @Override
    protected void onAppCreateOnBackThread(Context ctx) {
        Log.i("test","onAppCreateOnBackThread"+Thread.currentThread().getName());
    }
}
