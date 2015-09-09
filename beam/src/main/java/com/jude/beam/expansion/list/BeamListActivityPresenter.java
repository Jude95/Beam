package com.jude.beam.expansion.list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.ViewGroup;

import com.jude.beam.bijection.Presenter;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by Mr.Jude on 2015/8/17.
 */
public class BeamListActivityPresenter<T extends BeamListActivity,M> extends Presenter<T>
        implements RecyclerArrayAdapter.OnLoadMoreListener,SwipeRefreshLayout.OnRefreshListener{
    DataAdapter mAdapter;

    @Override
    protected void onCreate(T view, Bundle savedState) {
        super.onCreate(view, savedState);
        mAdapter = new DataAdapter(getView());
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
