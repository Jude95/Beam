package com.jude.beamdemo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jude.beam.Beam;
import com.jude.http.RequestManager;
import com.jude.utils.JUtils;

/**
 * Created by Mr.Jude on 2015/7/28.
 */
public class APP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        Beam.init(this);
        RequestManager.getInstance().init(this);
        RequestManager.getInstance().setDebugMode(true,"BeamNet");
        JUtils.initialize(this);
        JUtils.setDebug(true,"BeamTest");
    }
}
