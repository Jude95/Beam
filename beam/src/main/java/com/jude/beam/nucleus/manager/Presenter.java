package com.jude.beam.nucleus.manager;

import android.content.Intent;
import android.os.Bundle;

import java.util.concurrent.CopyOnWriteArrayList;

public class Presenter<ViewType> {

    /**
     * activity 第一次create到主动finish之前。只会调用一次。用于数据加载。避免横竖屏，内存回收导致重复加载
     */
    protected void onCreate(Bundle savedState) {
    }

    /**
     * activity$OnCreate的回调,但执行延迟到OnCreate之后。
     */
    protected void onCreateView(ViewType view) {
    }


    /**
     * Activity$OnDestroy的回调
     */
    protected void onDestroy() {
    }


    protected void onSave(Bundle state) {
    }


    protected void onTakeView(ViewType view) {
    }

    /**
     * Activity$onPause的回调
     */
    protected void onDropView() {
    }

    /**
     * Activity$onActivityResult的回调
     */
    protected void onResult(int requestCode, int resultCode, Intent data) {
    }



    /**
     * A callback to be invoked when a presenter is about to be destroyed.
     */
    public interface OnDestroyListener {

        void onDestroy();
    }


    public void addOnDestroyListener(OnDestroyListener listener) {
        onDestroyListeners.add(listener);
    }


    public void removeOnDestroyListener(OnDestroyListener listener) {
        onDestroyListeners.remove(listener);
    }

    ViewType view;
    private CopyOnWriteArrayList<OnDestroyListener> onDestroyListeners = new CopyOnWriteArrayList<>();


    public ViewType getView() {
        return view;
    }

    /**
     * Creates a presenter.
     * This method is called from {@link DefaultPresenterManager#provide} and should not be called directly.
     */
    public void create(Bundle bundle) {
        onCreate(bundle);
    }

    /**
     * Destroys a presenter.
     * This method is called from {@link DefaultPresenterManager#destroy(Presenter)} and should not be called directly.
     */
    public void destroy() {
        for (OnDestroyListener listener : onDestroyListeners)
            listener.onDestroy();
        onDestroy();
    }

    /**
     * Saves a presenter.
     * This method is called from {@link DefaultPresenterManager#save} and should not be called directly.
     */
    public void save(Bundle state) {
        onSave(state);
    }


    public void takeView(ViewType view) {
        this.view = view;
        onTakeView(view);
    }


    public void dropView() {
        onDropView();
        //this.view = null; //WHY
    }

    public void createView(ViewType view) {
        this.view = view;
        onCreateView(view);
    }


    public void result(int requestCode, int resultCode, Intent data){
        onResult(requestCode,resultCode,data);
    }

    public int onDestroyListenerCount() {
        return onDestroyListeners.size();
    }
}
