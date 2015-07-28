package com.jude.beamdemo;

import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jude.beam.nucleus.manager.Presenter;


/**
 * Created by Mr.Jude on 2015/7/26.
 */
public class MainPresenter extends Presenter<MainActivity> {

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        Fresco.initialize(getView());
        PersonModel.getInstance().getPersons();
    }

    public void loadMore(){

    }

    public void refresh(){

    }
}
