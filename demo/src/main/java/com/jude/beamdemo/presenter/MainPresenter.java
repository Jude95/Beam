package com.jude.beamdemo.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.jude.beam.bijection.Presenter;
import com.jude.beamdemo.model.DataModel;
import com.jude.beamdemo.ui.MainActivity;

/**
 * Created by Mr.Jude on 2016/2/22.
 */
public class MainPresenter extends Presenter<MainActivity> {
    @Override
    protected void onCreate(@NonNull MainActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        DataModel.getInstance().hashCode();
    }
}
