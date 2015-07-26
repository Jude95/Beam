package com.jude.beam.nucleus.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.jude.beam.nucleus.factory.PresenterFactory;
import com.jude.beam.nucleus.manager.Presenter;
import com.jude.beam.nucleus.manager.PresenterManager;


/**
 * A helper class to control presenter's lifecycle.
 *
 */
public class PresenterHelper<PresenterType extends Presenter> {

    private PresenterFactory<PresenterType> presenterFactory;
    private Bundle presenterState;

    private PresenterType presenter;

    public PresenterHelper(PresenterFactory<PresenterType> presenterFactory) {
        this.presenterFactory = presenterFactory;
    }

    public void setPresenterState(Bundle presenterState) {
        if (presenter != null)
            throw new IllegalArgumentException("setPresenterState() should be called before getPresenter() and before onTakeView()");
        this.presenterState = presenterState;
    }

    public PresenterType getPresenter(Object view) {
        if (presenter == null && presenterFactory != null) {
            presenter = PresenterManager.getInstance().provide(presenterFactory, presenterState,view);
            presenterState = null;
        }
        return presenter;
    }

    /**
     * Destroys a presenter that is currently attached to the View.
     */
    public void destroyPresenter() {
        if (presenter != null) {
            PresenterManager.getInstance().destroy(presenter);
            presenter = null;
        }
    }

    public Bundle savePresenter() {
        return presenter == null ? null : PresenterManager.getInstance().save(presenter);
    }

    public void createView(final Object view){
        getPresenter(view);
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                if (presenter != null){
                    presenter.createView(view);
                }
            }
        });
    }

    public void takeView(Object view) {
        getPresenter(view);
        if (presenter != null){
            presenter.takeView(view);
        }
    }

    public void dropView(boolean destroy) {
        if (presenter != null)
            presenter.dropView();
        if (destroy)
            destroyPresenter();
    }

    public void result(int requestCode, int resultCode, Intent data){
        presenter.result(requestCode,resultCode,data);
    }
}
