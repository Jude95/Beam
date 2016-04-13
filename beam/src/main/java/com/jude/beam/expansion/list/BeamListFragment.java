package com.jude.beam.expansion.list;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.jude.beam.R;
import com.jude.beam.Utils;
import com.jude.beam.bijection.BeamFragment;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Mr.Jude on 2015/8/17.
 */
public abstract class BeamListFragment<T extends BeamListFragmentPresenter, M> extends BeamFragment<T> {
    View mRootView;
    private ListConfig mListConfig;
    private EasyRecyclerView mListView;
    private BeamListFragmentPresenter.DataAdapter mAdapter;
    public EasyRecyclerView getListView() {
        return mListView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mListConfig = getConfig();
        createRecycler(container);
        findRecycler();
        initList();
        if (mListConfig.mStartWithProgress&&!getPresenter().inited) mListView.setAdapterWithProgress(mAdapter = getPresenter().getAdapter());
        else mListView.setAdapter(mAdapter = getPresenter().getAdapter());
        initAdapter();
        return mRootView;
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
        mListView = (EasyRecyclerView) mRootView.findViewById(R.id.recycler);
        mListView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }



    private void createRecycler(ViewGroup container){
        if (getLayout()!=0){
            mRootView = LayoutInflater.from(getActivity()).inflate(getLayout(), container, false);
        }else if (mListConfig.mContainerLayoutRes!=0){
            mRootView = LayoutInflater.from(getActivity()).inflate(mListConfig.mContainerLayoutRes,container,false);
        }else if (mListConfig.mContainerErrorView!=null){
            mRootView = mListConfig.mContainerLayoutView;
        }else{
            EasyRecyclerView mListView = new EasyRecyclerView(getActivity());
            mListView.setId(R.id.recycler);
            mListView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mListView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            mRootView = mListView;
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && mListConfig.mPaddingNavigationBarAble && Utils.hasSoftKeys(getContext())){
            mListView.setRecyclerPadding(0,0,0,Utils.getNavigationBarHeight(getContext()));
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

    public int getViewType(int type){
        return 0;
    }

    protected abstract BaseViewHolder getViewHolder(ViewGroup parent,int viewType);


}
