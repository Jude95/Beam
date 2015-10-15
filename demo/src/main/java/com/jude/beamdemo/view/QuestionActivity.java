package com.jude.beamdemo.view;


import android.view.ViewGroup;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListActivity;
import com.jude.beamdemo.model.bean.Question;
import com.jude.beamdemo.presenter.QuestionPresenter;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by zhuchenxi on 15/6/7.
 */
    @RequiresPresenter(QuestionPresenter.class)
    public class QuestionActivity extends BeamListActivity<QuestionPresenter,Question> {


    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new QuestionViewHolder(parent);
    }

}
