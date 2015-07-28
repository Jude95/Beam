package com.jude.beamdemo;

import android.app.Application;

import com.jude.beam.Beam;

/**
 * Created by Mr.Jude on 2015/7/28.
 */
public class APP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Beam.init(this);
    }
}
