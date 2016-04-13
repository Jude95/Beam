package com.jude.beam.bijection;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * This view is an example of how a view should control it's presenter.
 * You can inherit from this class or copy/paste this class's code to
 * create your own view implementation.
 *
 */
public class BeamFragment<PresenterType extends Presenter> extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        helper.onPostCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        helper.onDestroyView();
        if (getActivity().isFinishing())
            helper.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        helper.onSave(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        helper.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        helper.onPause();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        helper.onResult(requestCode, resultCode, data);
    }

    protected final <E extends View> E $(@NonNull View view,@IdRes int id){
        return (E)view.findViewById(id);
    }

    protected final <E extends View> E viewId(@NonNull View view,@IdRes int id){
        return (E)view.findViewById(id);
    }

    public PresenterType getPresenter() {
        return helper.getPresenter();
    }

    private ViewHelper<PresenterType> helper = new ViewHelper<>(this);
}
