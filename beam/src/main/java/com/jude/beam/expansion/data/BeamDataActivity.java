package com.jude.beam.expansion.data;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;

/**
 * Created by Mr.Jude on 2015/8/20.
 */
@RequiresPresenter(BeamDataActivityPresenter.class)
public class BeamDataActivity<T extends BeamDataActivityPresenter,M> extends BeamBaseActivity<T>{

    public void setData(M data){}
    public void setError(Throwable e){}

}
