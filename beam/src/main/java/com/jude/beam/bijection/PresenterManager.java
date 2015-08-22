package com.jude.beam.bijection;


public abstract class PresenterManager {

    private static PresenterManager instance = new DefaultPresenterManager();

    public static PresenterManager getInstance() {
        return instance;
    }

    public abstract <T extends Presenter> T create(Object view);

    public abstract <T extends Presenter> T get(String id);

    public abstract void destroy(String id);

}
