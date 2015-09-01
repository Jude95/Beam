package com.jude.beamdemo.model.service;

import com.jude.beamdemo.config.API;
import com.jude.beamdome.BuildConfig;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Mr.Jude on 2015/8/24.
 * 服务器连接客户端
 */
public class ServiceClient {
    private static Service mService;

    public static Service getService(){
        if (mService == null){
            createService();
        }
        return mService;
    }

    private static void createService(){
        mService = createAdapter().create(Service.class);
    }

    private static RestAdapter createAdapter(){
        OkHttpClient okHttpClient = new OkHttpClient();
        return new RestAdapter.Builder()
                .setEndpoint(API.URL.BASEURL)
                .setLogLevel(BuildConfig.DEBUG?RestAdapter.LogLevel.FULL:RestAdapter.LogLevel.NONE)
                .setConverter(new WrapperConverter())
                .setClient(new OkClient(okHttpClient))
                .build();
    }
}
