package com.jude.beamdemo.model.callback;


import com.google.gson.Gson;
import com.jude.beamdemo.config.API;
import com.jude.utils.JUtils;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;

/**
 * Created by zhuchenxi on 15/5/11.
 */
public abstract class DataCallback<T> extends LinkCallback {

    @Override
    public void onRequest() {
        super.onRequest();
    }

    @Override
    public void onSuccess(String s) {
        JSONObject jsonObject;
        int status = 0;
        String info = "";
        T data = null;
        try {
            jsonObject = new JSONObject(s);
            status = jsonObject.getInt(API.KEY.STATUS);
            info = jsonObject.getString(API.KEY.INFO);
            if (status == API.CODE.SUCCEED){
                Gson gson = new Gson();
                data = gson.fromJson(jsonObject.getString(API.KEY.DATA), ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
            }
        } catch (Exception e) {
            JUtils.Log(e.getLocalizedMessage());
            error("数据解析错误");
            return ;
        }
        result(status, info);
        if (status == API.CODE.SUCCEED){
            success(info,data);
        }else if (status == API.CODE.PERMISSION_DENIED){
            authorizationFailure();
        }else if (status == API.CODE.Failure){
            failure(info);
        }else{
            error(info);
        }
        super.onSuccess(s);
    }

    @Override
    public void onError(String s) {
        result(-1,"网络错误");
        error("网络错误");
        super.onError(s);
    }

    public void result(int status, String info){}
    public abstract void success(String info,T data);
    public void failure(String info){
        JUtils.Toast(info);
    }
    public void authorizationFailure(){}
    public void error(String errorInfo){
        JUtils.Toast(errorInfo);
    }

}
