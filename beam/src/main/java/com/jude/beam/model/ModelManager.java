package com.jude.beam.model;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by Mr.Jude on 2015/7/26.
 */
public class ModelManager {
    
    final static HashMap<Class<?>,AbsModel> mModelMap = new HashMap<>();
    final static BackThread mBackThread = new BackThread();
    public static void init(final Context ctx){
        mBackThread.start();
        Class<?>[] models = null;
        try {
            ApplicationInfo appInfo = ctx.getPackageManager()
                    .getApplicationInfo(ctx.getPackageName(),
                            PackageManager.GET_META_DATA);
            if (appInfo.metaData == null||appInfo.metaData.getString("MODEL") == null||appInfo.metaData.getString("MODEL").isEmpty()){
                Log.e("Beam","MODEL No Found!Have you declare MODEL in the manifests?");
                return;
            }
            String modelStr = appInfo.metaData.getString("MODEL").trim();
            String[] modelStrs = modelStr.split(",");
            models = new Class[modelStrs.length];
            for (int i = 0; i < modelStrs.length; i++) {
                try{
                    models[i] = Class.forName(modelStrs[i].trim());
                }catch (ClassNotFoundException e){
                    Log.e("Beam",modelStrs[i].trim()+" Class No Found!");
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            return ;
        }

        for (Class m:models) {
            if (m!=null && AbsModel.class.isAssignableFrom(m)){
                try {
                    AbsModel instance = (AbsModel) (m.newInstance());
                    mModelMap.put(m, instance);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }else{
                Log.e("Beam","your model must extends AbsModel");
            }
        }
        //后台调用
        final Class<?>[] finalModels = models;
        mBackThread.execute(new Runnable() {
            @Override
            public void run() {
                for (Class m: finalModels) {
                    if (m!=null)
                    mModelMap.get(m).onAppCreateOnBackThread(ctx);
                }
            }
        });

        //前台调用
        for (Class m:models) {
            if (m!=null && mModelMap.get(m)!=null)
            mModelMap.get(m).onAppCreate(ctx);
        }
    }

    static void runOnBackThread(Runnable runnable){
        mBackThread.execute(runnable);
    }
}
