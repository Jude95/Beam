package com.jude.beam.expansion.data;

import android.os.Bundle;

import com.jude.beam.expansion.BeamBasePresenter;

import rx.Subscriber;
import rx.Subscription;
import rx.subjects.BehaviorSubject;

/**
 * Created by Mr.Jude on 2015/8/20.
 */
public class BeamDataActivityPresenter<T extends BeamDataActivity,M> extends BeamBasePresenter<T> {
    /**
     *      setData(M)   onNext(M)  Subscriber<M></>()
     *         \              |         /
     *          \             |        /
     *           BehaviorSubject(mData)
     *                        |
     *                       -+-（会断开或者重新绑定）
     *                        |
     *                   Subscriber（这里回调调用View各方法）
     */

    //用于缓存数据的Subscriber
    BehaviorSubject<M>  mData = BehaviorSubject.create();
    //View的订阅关系，View被销毁时自动取消订阅。
    Subscription mSubscription;

    private Subscriber<M> mSubscriber = new Subscriber<M>() {
        @Override
        public void onCompleted() {
            mData.onCompleted();
        }

        @Override
        public void onError(Throwable e) {
            mData.onError(e);
        }

        @Override
        public void onNext(M m) {
            mData.onNext(m);
        }
    };

    @Override
    protected void onCreate(T view, Bundle savedState) {
        super.onCreate(view, savedState);
        //如果默认数据字段有内容则自动发布数据
        M m = getDataFromIntent();
        if (m !=null) setData(m);
    }


    @Override
    protected void onCreateView(T view) {
        super.onCreateView(view);
        mSubscription = mData.unsafeSubscribe(new Subscriber<M>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                getView().setError(e);
            }

            @Override
            public void onNext(M m) {
                getView().setData(m);
            }
        });
    }



    @Override
    protected void onDestroyView() {
        super.onDestroyView();
        mSubscription.unsubscribe();
    }


    /**
     * 获取缓存数据的Subscriber
     * 可以通过 `getDataSubject().getValue();` 获取缓存数据。
     * @return
     */
    public BehaviorSubject<M> getDataSubject(){
        return mData;
    }


    /**
     * 获取存放数据的subscriber，通常用于
     *
     *     Observable.unsafeSubscribe(getDataSubscriber());
     *
     * @return
     */
    public Subscriber<M> getDataSubscriber(){
        return mSubscriber;
    }




    /**
     * 手动发布错误
     * @param e
     */
    public void publishError(Throwable e){
        mData.onError(e);
    }

    /**
     * 直接获取数据
     * @return
     */
    public M getData(){
        return mData.getValue();
    }

    /**
     * 手动发布数据
     * @param data
     */
    public void setData(M data){
        mData.onNext(data);
    }

    /**
     * 更新数据
     */
    public void refresh(){
        setData(getData());
    }


    /**
     * use {@link #setData}
     * @param data
     */
    @Deprecated
    public void publishObject(M data){
        mData.onNext(data);
    }
}
