package com.jude.beam.nucleus.manager;

import android.os.Bundle;
import android.util.Printer;

import com.jude.beam.nucleus.factory.PresenterFactory;


/**
 * A singleton that manages presenter's creation and state persistence.
 * This class is intended for usage by base classes implementing View part of MVP (NucleusLayout, NucleusActivity)
 * and should not be used by normal View classes.
 */
public abstract class PresenterManager {

    private static PresenterManager instance = new DefaultPresenterManager();

    public static PresenterManager getInstance() {
        return instance;
    }

    public static void setInstance(PresenterManager instance) {
        PresenterManager.instance = instance;
    }

    /**
     * Finds a Presenter for a given view or restores it from the saved state.
     * There can be three cases when this method is being called:
     * 1. First creation of a view;
     * 2. Restoring of a view when the process has NOT been destroyed (configuration change, other activity recreation cases);
     * 3. Restoring of a view when entire process has been destroyed.
     */
    public abstract <T extends Presenter> T provide(PresenterFactory<T> presenterFactory, Bundle savedState,Object view);


    public abstract Bundle save(Presenter presenter);


    public abstract void destroy(Presenter presenter);


    public abstract void print(Printer printer);
}
