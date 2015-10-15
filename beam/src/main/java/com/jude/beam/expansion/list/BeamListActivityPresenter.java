package com.jude.beam.expansion.list;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.ViewGroup;

import com.jude.beam.bijection.Presenter;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import rx.Subscriber;

/**
 * Created by Mr.Jude on 2015/8/17.
 */
public class BeamListActivityPresenter<T extends BeamListActivity,M> extends Presenter<T>
        implements RecyclerArrayAdapter.OnLoadMoreListener,SwipeRefreshLayout.OnRefreshListener{
    DataAdapter mAdapter;
    int page = 0;
    Subscriber<List<M>> mRefreshSubscriber = new Subscriber<List<M>>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            getView().showError();
        }

        @Override
        public void onNext(List<M> ms) {
            getAdapter().clear();
            getAdapter().addAll(ms);
            page = 1;
        }

        @Override
        public void onStart() {
        }
    };

    Subscriber<List<M>> mMoreSubscriber = new Subscriber<List<M>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            getAdapter().pauseMore();
        }

        @Override
        public void onNext(List<M> ms) {
            getAdapter().addAll(ms);
            page++;
        }
    };

    public int getCurPage(){
        return page;
    }

    public void setCurPage(int page){
        this.page = page;
    }

    public Subscriber<List<M>> getRefreshSubscriber(){
        return mRefreshSubscriber;
    }

    public Subscriber<List<M>> getMoreSubscriber(){
        return mMoreSubscriber;
    }

    DataAdapter createDataAdapter(){
        return mAdapter = new DataAdapter(getView());
    }

    public DataAdapter getAdapter(){
        if (mAdapter == null)mAdapter = new DataAdapter(getView());
        return mAdapter;
    }

    @Override
    public void onLoadMore() {
    }

    @Override
    public void onRefresh() {
    }

    public class DataAdapter extends RecyclerArrayAdapter<M> {

        public DataAdapter(Context context) {
            super(context);
        }

        @Override
        public int getViewType(int position) {
            return getView().getViewType(position);
        }

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return getView().getViewHolder(parent, viewType);
        }
    }



}
