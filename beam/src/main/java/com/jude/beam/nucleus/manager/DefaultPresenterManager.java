package com.jude.beam.nucleus.manager;

import android.os.Bundle;
import android.util.Printer;

import com.jude.beam.nucleus.factory.PresenterFactory;

import java.util.HashMap;
import java.util.Map;


/**
 * This is the default implementation of {@link PresenterManager}.
 */
public class DefaultPresenterManager extends PresenterManager {
    private static final String PRESENTER_ID_KEY = "id";
    private static final String PRESENTER_STATE_KEY = "state";

    private HashMap<String, Presenter> idToPresenter = new HashMap<>();
    private HashMap<Presenter, String> presenterToId = new HashMap<>();
    private int nextId;

    /**
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T extends Presenter> T provide(PresenterFactory<T> presenterFactory, Bundle savedState,Object view) {

        String id = providePresenterId(savedState);
        if (idToPresenter.containsKey(id))
            return (T)idToPresenter.get(id);

        Presenter presenter = presenterFactory.createPresenter();

        idToPresenter.put(id, presenter);
        presenterToId.put(presenter, id);
        presenter.view = view;
        presenter.create(savedState == null ? null : savedState.getBundle(PRESENTER_STATE_KEY));
        return (T)presenter;
    }

    /**
     */
    @Override
    public Bundle save(Presenter presenter) {
        Bundle bundle = new Bundle();
        bundle.putString(PRESENTER_ID_KEY, presenterToId.get(presenter));
        Bundle presenterState = new Bundle();
        presenter.save(presenterState);
        bundle.putBundle(PRESENTER_STATE_KEY, presenterState);
        return bundle;
    }

    @Override
    public void destroy(Presenter presenter) {
        presenter.destroy();
        idToPresenter.remove(presenterToId.remove(presenter));
    }

    @Override
    public void print(Printer printer) {
        for (Map.Entry<String, Presenter> entry : idToPresenter.entrySet()) {
            Object view = entry.getValue().getView();
            printer.println("id: " + entry.getKey() + (view == null ? "" : " => view: " + view.toString()));
        }
    }

    private String providePresenterId(Bundle savedState) {
        return savedState != null ? savedState.getString(PRESENTER_ID_KEY) :
            "" + nextId++ + "/" + System.nanoTime() + "/" + (int)(Math.random() * Integer.MAX_VALUE);
    }
}
