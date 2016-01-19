package com.jude.beam.bijection;

import java.util.HashMap;


public class DefaultPresenterManager extends PresenterManager {

    //使用id当索引保持所有presenter引用，虽然view与presenter是一一对应关系。但避免保持View引用
    private HashMap<String, Presenter> idToPresenter = new HashMap<>();
    private int nextId;

    @Override
    public <T extends Presenter> T create(Object view) {
        T presenter = PresenterBuilder.fromViewClass(view.getClass());
        if (presenter == null)return null;

        presenter.id = providePresenterId();
        idToPresenter.put(presenter.id, presenter);
        return presenter;
    }

    @Override
    public <T extends Presenter> T get(String id) {
        return (T)idToPresenter.get(id);
    }

    @Override
    public void destroy(String id) {
        idToPresenter.remove(id);
    }

    //最大程度保持线程安全。虽然主线程是非常安全的。
    private String providePresenterId() {
        return  nextId++ + "/" + System.nanoTime() + "/" + (int)(Math.random() * Integer.MAX_VALUE);
    }

}
