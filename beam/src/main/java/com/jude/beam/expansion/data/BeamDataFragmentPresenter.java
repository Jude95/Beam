package com.jude.beam.expansion.data;

import com.jude.beam.bijection.Presenter;

import rx.Observer;
import rx.Subscription;
import rx.subjects.BehaviorSubject;

/**
 * Created by Mr.Jude on 2015/8/20.
 */
public class BeamDataFragmentPresenter<T extends BeamDataFragment,M> extends Presenter<T> {
    BehaviorSubject<M>  mData = BehaviorSubject.create();
    Subscription mSubscription;

    @Override
    protected void onCreateView(T view) {
        super.onCreateView(view);
        mSubscription = mData.subscribe(new Observer<M>() {
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
    protected void onDestroy() {
        super.onDestroy();
        mSubscription.unsubscribe();
    }

    public void publishObject(M data){
        mData.onNext(data);
    }

    public void publishError(Throwable e){
        mData.onError(e);
    }
}
