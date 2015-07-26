package com.jude.beam.model;

import android.os.Handler;
import android.os.Looper;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Mr.Jude on 2015/7/26.
 */
class BackThread extends Thread {
    private Handler handler;
    private Queue<Runnable> queue = new LinkedBlockingQueue<>();

    @Override
    public void run() {
        Looper.prepare();
        handler = new Handler();
        for (Runnable runnable : queue) {
            handler.post(runnable);
        }
        Looper.loop();
    }

    void execute(Runnable runnable){
        if (handler ==  null)queue.offer(runnable);
        else handler.post(runnable);
    }

    void quit(){
        handler.post(new Runnable() {
            @Override
            public void run() {
                Looper.myLooper().quit();
            }
        });
    }
}
