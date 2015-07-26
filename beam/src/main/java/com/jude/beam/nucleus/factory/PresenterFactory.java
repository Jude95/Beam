package com.jude.beam.nucleus.factory;


import com.jude.beam.nucleus.manager.Presenter;

public interface PresenterFactory<T extends Presenter> {
    T createPresenter();
}
