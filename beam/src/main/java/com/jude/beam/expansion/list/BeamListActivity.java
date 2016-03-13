package com.jude.beam.expansion.list;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.jude.beam.R;
import com.jude.beam.Utils;
import com.jude.beam.expansion.BeamBaseActivity;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Mr.Jude on 2015/8/17.
 */
public abstract class BeamListActivity<T extends BeamListActivityPresenter, M> extends BeamBaseActivity<T> {

    private ListConfig mListConfig;
    private EasyRecyclerView mListView;
    private BeamListActivityPresenter.DataAdapter mAdapter;

    public EasyRecyclerView getListView() {
        return mListView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListConfig = getConfig();
        createRecycler();
        findRecycler();
        initList();
        if (mListConfig.mStartWithProgress&&!getPresenter().inited) mListView.setAdapterWithProgress(mAdapter = getPresenter().getAdapter());
        else mListView.setAdapter(mAdapter = getPresenter().getAdapter());
        initAdapter();
    }

    public void stopRefresh(){
        if(mListView!=null)
            mListView.getSwipeToRefresh().setRefreshing(false);
    }
    public void showError(Throwable e){
        if (mListView!=null)
            mListView.showError();
    }

    public int getLayout(){
        return 0;
    }

    private void findRecycler(){
        mListView = (EasyRecyclerView) findViewById(R.id.recycler);
        if (mListView==null)throw new RuntimeException("No found recycler with id \"recycler\"");
        mListView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void createRecycler(){
        if (getLayout()!=0){
            setContentView(getLayout());
        }else if (mListConfig.mContainerLayoutRes!=0){
            setContentView(mListConfig.mContainerLayoutRes);
        }else if (mListConfig.mContainerErrorView!=null){
            setContentView(mListConfig.mContainerLayoutView);
        }else{
            EasyRecyclerView mListView = new EasyRecyclerView(this);
            mListView.setId(R.id.recycler);
            mListView.setLayoutManager(new LinearLayoutManager(this));
            mListView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            setContentView(mListView);
        }
    }

    private void initList(){
        if (mListConfig.mRefreshAble)mListView.setRefreshListener(getPresenter());
        if (mListConfig.mContainerProgressAble){
            if (mListConfig.mContainerProgressView != null)mListView.setProgressView(mListConfig.mContainerProgressView);
            else mListView.setProgressView(mListConfig.mContainerProgressRes);
        }
        if (mListConfig.mContainerErrorAble){
            if (mListConfig.mContainerErrorView != null)mListView.setErrorView(mListConfig.mContainerErrorView);
            else mListView.setErrorView(mListConfig.mContainerErrorRes);
        }
        if (mListConfig.mContainerEmptyAble){
            if (mListConfig.mContainerEmptyView != null)mListView.setEmptyView(mListConfig.mContainerEmptyView);
            else mListView.setEmptyView(mListConfig.mContainerEmptyRes);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && mListConfig.mPaddingNavigationBarAble && Utils.hasSoftKeys(this)){
            mListView.setRecyclerPadding(0,0,0,Utils.getNavigationBarHeight(this));
        }
    }

    private void initAdapter(){
        if (mListConfig.mNoMoreAble){
            if (mListConfig.mNoMoreView != null)mAdapter.setNoMore(mListConfig.mNoMoreView);
            else mAdapter.setNoMore(mListConfig.mNoMoreRes);
        }
        if (mListConfig.mLoadmoreAble){
            if (mListConfig.mLoadMoreView != null)mAdapter.setMore(mListConfig.mLoadMoreView, getPresenter());
            else mAdapter.setMore(mListConfig.mLoadMoreRes, getPresenter());
        }
        if (mListConfig.mErrorAble){
            View errorView;
            if (mListConfig.mErrorView != null)errorView = mAdapter.setMore(mListConfig.mErrorView,getPresenter());
            else errorView = mAdapter.setError(mListConfig.mErrorRes);
            if (mListConfig.mErrorTouchToResumeAble)errorView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAdapter.resumeMore();
                }
            });
        }
    }

    protected ListConfig getConfig(){
        return ListConfig.Default.clone();
    }

    public int getViewType(int position){
        return 0;
    }

    protected abstract BaseViewHolder getViewHolder(ViewGroup parent,int viewType);


}
