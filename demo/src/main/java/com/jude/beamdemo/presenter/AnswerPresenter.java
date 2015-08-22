package com.jude.beamdemo.presenter;

import android.os.Bundle;

import com.jude.beam.expansion.list.BeamListActivityPresenter;
import com.jude.beamdemo.view.AnswerActivity;
import com.jude.beamdemo.model.QuestionModel;
import com.jude.beamdemo.model.bean.Answer;
import com.jude.beamdemo.model.bean.AnswerResult;
import com.jude.beamdemo.model.bean.Question;
import com.jude.beamdemo.model.callback.DataCallback;


/**
 * Created by zhuchenxi on 15/6/8.
 */
public class AnswerPresenter extends BeamListActivityPresenter<AnswerActivity,Answer> {
    private Question question;
    int page = 0;

    @Override
    protected void onCreate(AnswerActivity view,Bundle savedState) {
        super.onCreate(view, savedState);
        question = (Question) getView().getIntent().getSerializableExtra("question");
        getAdapter().addHeader(getView().getAnswerHeader(question));
        onRefresh();
    }

    @Override
    public void onRefresh() {
        QuestionModel.getInstance().getAnswerFromServer(0, question.getId(), false, new DataCallback<AnswerResult>() {
            @Override
            public void success(String info, AnswerResult data) {
                getAdapter().clear();
                getAdapter().addAll(data.getAnswers());
                if (data.getTotalPage()-1 == page)getAdapter().stopMore();
                page = 1;
            }

            @Override
            public void onError(String s) {
                getView().showError();
            }
        });
    }

    @Override
    public void onLoadMore() {
        QuestionModel.getInstance().getAnswerFromServer(page, question.getId(), false, new DataCallback<AnswerResult>() {
            @Override
            public void success(String info, AnswerResult data) {
                getAdapter().addAll(data.getAnswers());
                if (data.getTotalPage()-1 == page) getAdapter().stopMore();
                page++;
            }

            @Override
            public void onError(String s) {
                getAdapter().pauseMore();
            }
        });
    }
}
