package com.jude.beam.expansion.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.ViewGroup;

import com.jude.beam.expansion.BeamBasePresenter;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.subjects.ReplaySubject;

/**
 * Created by Mr.Jude on 2015/8/17.
 */
public class BeamListActivityPresenter<T extends BeamListActivity,M> extends BeamBasePresenter<T>
        implements RecyclerArrayAdapter.OnLoadMoreListener,SwipeRefreshLayout.OnRefreshListener{
    DataAdapter mAdapter;
    int page = 0;//Re:从0开始的异世界生活
    boolean inited = false;//初始化过了，不用显示ProcessBar了
    Subscription mAdapterSubscription;
    public ReplaySubject<List<M>> mDataSubject = ReplaySubject.create();

    Subscriber<List<M>> mRefreshSubscriber = new Subscriber<List<M>>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            inited = true;
            mDataSubject.onError(e);
        }

        @Override
        public void onNext(List<M> ms) {
            inited = true;
            //用新的替换，表示清空数据
            mDataSubject.onCompleted();
            mDataSubject = ReplaySubject.create();
            mDataSubject.onNext(ms);
            bind();
            page = 1;
        }
    };

    Subscriber<List<M>> mMoreSubscriber = new Subscriber<List<M>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            inited = true;
            mDataSubject.onError(e);
        }

        @Override
        public void onNext(List<M> ms) {
            inited = true;
            mDataSubject.onNext(ms);
            page++;
        }
    };

    private void bind(){
        if (mAdapterSubscription!=null)mAdapterSubscription.unsubscribe();
        //开始与View的各种逻辑绑定。
        mAdapterSubscription = mDataSubject.subscribe(new Subscriber<List<M>>() {
            @Override
            public void onCompleted() {
                //onCompleted过后Subject就会被替换。意思是数据被清空。
                getAdapter().clear();
            }

            @Override
            public void onError(Throwable e) {
                if (mAdapter!=null&&mAdapter.getCount()==0){
                    //没有数据的时候出错，就显示全屏的错误提示
                    getView().stopRefresh();
                    getView().showError(e);
                }else {
                    //有数据时的出错，在最后一条显示错误提示
                    getAdapter().pauseMore();
                }
            }

            @Override
            public void onNext(List<M> m) {
                getAdapter().addAll(m);
            }
        });
    }

    @Override
    protected void onCreateView(@NonNull T view) {
        super.onCreateView(view);
        bind();
    }

    @Override
    protected void onDestroyView() {
        super.onDestroyView();
        mAdapterSubscription.unsubscribe();
        mAdapter = null;//Adapter与View的耦合根本解不开，所以也必须释放。
    }

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

    public DataAdapter getAdapter()  {
        if (getView().mCtx == null)throw new RuntimeException("you shouldn't use getView() at Presenter's onCreate(),onCreateView() may invoke more than once,you should put the config code into onCreateView()");
        if (mAdapter == null)mAdapter = new DataAdapter(getView().mCtx);
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

        public DataAdapter(Context context,List<M> data) {
            super(context,data);
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
