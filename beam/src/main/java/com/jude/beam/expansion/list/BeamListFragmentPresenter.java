package com.jude.beam.expansion.list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.ViewGroup;

import com.jude.beam.bijection.Presenter;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import rx.Subscriber;

/**
 * Created by Mr.Jude on 2015/8/17.
 */
public class BeamListFragmentPresenter<T extends BeamListFragment,M> extends Presenter<T>
        implements RecyclerArrayAdapter.OnLoadMoreListener,
        SwipeRefreshLayout.OnRefreshListener {
    DataAdapter mAdapter;
    int page = 0;
    Subscriber<M[]> mRefreshSubscriber = new Subscriber<M[]>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            getView().showError();
        }

        @Override
        public void onNext(M[] ms) {
            getAdapter().clear();
            getAdapter().addAll(ms);
            page = 1;
        }
    };
    Subscriber<M[]> mMoreSubscriber = new Subscriber<M[]>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            getAdapter().pauseMore();
        }

        @Override
        public void onNext(M[] ms) {
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

    public Subscriber<M[]> getRefreshSubscriber(){
        return mRefreshSubscriber;
    }

    public Subscriber<M[]> getMoreSubscriber(){
        return mMoreSubscriber;
    }

    @Override
    protected void onCreate(T view, Bundle savedState) {
        super.onCreate(view, savedState);
        mAdapter = new DataAdapter(getView().getActivity());
    }

    public DataAdapter getAdapter(){
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
