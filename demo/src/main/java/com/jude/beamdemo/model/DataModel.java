package com.jude.beamdemo.model;

import android.content.Context;
import android.util.Log;

import com.jude.beam.model.AbsModel;

/**
 * Created by Mr.Jude on 2016/3/20.
 */
public class DataModel extends AbsModel {
    public static DataModel getInstance() {
        return getInstance(DataModel.class);
    }

    @Override
    protected void onAppCreate(Context ctx) {
        super.onAppCreate(ctx);
        Log.i("Test","onAppCreate");
    }
}
