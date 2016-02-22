package com.jude.beamdemo.app;

import android.app.Application;

import com.jude.beam.Beam;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.beamdome.R;
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
        Beam.init(this);
        ListConfig.setDefaultListConfig(
                new ListConfig().
                        setRefreshAble(true).
                        setContainerLayoutRes(R.layout.activity_recyclerview).
                        setLoadmoreAble(true));
    }
}
