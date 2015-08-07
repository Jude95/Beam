package com.jude.beam.nucleus.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.beam.nucleus.factory.PresenterFactory;
import com.jude.beam.nucleus.factory.ReflectionPresenterFactory;
import com.jude.beam.nucleus.manager.Presenter;

/**
 * This view is an example of how a view should control it's presenter.
 * You can inherit from this class or copy/paste this class's code to
 * create your own view implementation.
 *
 */
public class NucleusFragment<PresenterType extends Presenter> extends Fragment {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null)
            helper.setPresenterState(bundle.getBundle(PRESENTER_STATE_KEY));
        helper.createView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        helper.createView(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBundle(PRESENTER_STATE_KEY, helper.savePresenter());
    }

    @Override
    public void onResume() {
        super.onResume();
        helper.takeView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        helper.dropView(getActivity().isFinishing());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        helper.result(requestCode,resultCode,data);
    }

    // The following section can be copy & pasted into any View class, just update their description if needed.

    /**
     * The factory class used to create the presenter. Defaults  to create the presenter
     * using a no arg constructor.
     * Subclasses can override this to provide presenters in other ways, like via their dependency injector.
     *
     */
    public PresenterFactory<PresenterType> getPresenterFactory() {
        return ReflectionPresenterFactory.fromViewClass(getClass());
    }

    /**
     * Returns a current attached presenter.
     * This method is guaranteed to return a non-null value between
     * onResume/onPause and onAttachedToWindow/onDetachedFromWindow calls
     * if the presenter factory returns a non-null value.
     *
     */
    public PresenterType getPresenter() {
        return helper.getPresenter(this);
    }

    /**
     * Destroys a presenter that is currently attached to the View.
     */
    public void destroyPresenter() {
        helper.destroyPresenter();
    }

    private static final String PRESENTER_STATE_KEY = "presenter_state";
    private PresenterHelper<PresenterType> helper = new PresenterHelper<>(getPresenterFactory());
}
