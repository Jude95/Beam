package com.jude.beam.bijection;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

public class Presenter<ViewType> {
    /**
     * activity 第一次create直到到主动退出Activity之前都不会调用。
     */
    protected void onCreate(@NonNull ViewType view, Bundle savedState) {
    }
    /**
     * presenter销毁时的回调。代表着activity正式退出
     */
    protected void onDestroy() {
    }

    /**
     * activity$OnCreate的回调,但执行延迟到OnCreate之后。
     */
    protected void onCreateView(@NonNull ViewType view) {
        this.view = view;
    }
    /**
     * activity$OnDestory的回调
     */
    protected void onDestroyView() {
    }
    protected void onResume() {
    }
    protected void onPause() {
    }

    protected void onSave(Bundle state) {
    }
    protected void onResult(int requestCode, int resultCode, Intent data) {
    }

    String id;
    ViewType view;
    public final ViewType getView() {
        return view;
    }

    void create(ViewType view,Bundle savedState){
        this.view = view;
        onCreate(view,savedState);
    }
}
