package com.jude.beamdemo.app;

import android.os.Bundle;

import com.jude.beam.bijection.ActivityLifeCycleDelegate;
import com.jude.utils.JUtils;

/**
 * Created by Mr.Jude on 2015/8/23.
 */
    public class MineActivityLifeCycleDelegate extends ActivityLifeCycleDelegate {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            JUtils.Log("onPause"+getActivity().getClass().getName());
        }

        @Override
        protected void onPause() {
            super.onPause();
            JUtils.Log("onPause");
        }

        @Override
        protected void onResume() {
            super.onResume();
            JUtils.Log("onResume");
        }
    }
