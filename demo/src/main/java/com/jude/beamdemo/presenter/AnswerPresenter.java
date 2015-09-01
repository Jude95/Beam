package com.jude.beamdemo.presenter;

import android.os.Bundle;

import com.jude.beam.expansion.list.BeamListActivityPresenter;
import com.jude.beamdemo.model.QuestionModel;
import com.jude.beamdemo.model.bean.Answer;
import com.jude.beamdemo.model.bean.AnswerResult;
import com.jude.beamdemo.model.bean.Question;
import com.jude.beamdemo.model.service.ServiceResponse;
import com.jude.beamdemo.view.AnswerActivity;


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
        QuestionModel.getInstance().getAnswers(0, question.getId(), false).subscribe(new ServiceResponse<AnswerResult>() {

            @Override
            public void onError(Throwable e) {
                getView().showError();
            }

            @Override
            public void onNext(AnswerResult answerResult) {
                getAdapter().clear();
                getAdapter().addAll(answerResult.getAnswers());
                if (answerResult.getTotalPage() == page) getAdapter().stopMore();
                page = 1;
            }
        });
    }

    @Override
    public void onLoadMore() {
        QuestionModel.getInstance().getAnswers(page, question.getId(), false).subscribe(new ServiceResponse<AnswerResult>() {
            @Override
            public void onError(Throwable e) {
                getAdapter().pauseMore();
            }

            @Override
            public void onNext(AnswerResult answerResult) {
                getAdapter().addAll(answerResult.getAnswers());
                if (answerResult.getTotalPage() == page) getAdapter().stopMore();
                page++;
            }
        });
    }
}
