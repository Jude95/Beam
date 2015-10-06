package com.jude.beamdemo.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jude.beam.Beam;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.beamdome.R;
import com.jude.http.RequestManager;
import com.jude.utils.JUtils;

/**
 * Created by Mr.Jude on 2015/7/28.
 */
public class APP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JUtils.initialize(this);
        JUtils.setDebug(true, "BeamTest");
        Fresco.initialize(this);
        Beam.init(this);
        Beam.setActivityLifeCycleDelegateProvider(new MineActivityLifeCycleDelegate.MineActivityLifeCycleDelegateProvider());
        RequestManager.getInstance().init(this);
        RequestManager.getInstance().setDebugMode(true, "BeamNet");
        ListConfig.setDefaultListConfig(
                new ListConfig().
                        setRefreshAble(true).
                        setContainerLayoutRes(R.layout.activity_recyclerview).
                        setLoadmoreAble(true));
    }
}
