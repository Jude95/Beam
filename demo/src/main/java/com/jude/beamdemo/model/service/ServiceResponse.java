package com.jude.beamdemo.model.service;

import com.jude.utils.JUtils;

import retrofit.converter.ConversionException;
import rx.Observer;

/**
 * Created by Mr.Jude on 2015/8/24.
 * 服务器返回的回调
 */
public abstract class ServiceResponse<T> implements Observer<T> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ServiceException){
            onServiceError(((ServiceException)e).getStatus(),((ServiceException) e).getInfo());
        }else if (e instanceof ConversionException){
            onServiceError(-888,"数据解析错误");
        }else{
            onServiceError(-999,"网络错误");
        }
    }

    public void onServiceError(int status,String info){
        JUtils.Toast(info);
    }

}
