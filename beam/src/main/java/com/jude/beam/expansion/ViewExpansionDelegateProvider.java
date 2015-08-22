package com.jude.beam.expansion;

/**
 * Created by Mr.Jude on 2015/8/17.
 */
public interface ViewExpansionDelegateProvider {
    <T extends ViewExpansionDelegate> T createViewExpansionDelegate();
}
