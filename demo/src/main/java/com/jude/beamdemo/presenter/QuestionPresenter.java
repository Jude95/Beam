package com.jude.beamdemo.presenter;

import android.os.Bundle;

import com.jude.beam.expansion.list.BeamListActivityPresenter;
import com.jude.beamdemo.model.QuestionModel;
import com.jude.beamdemo.model.bean.Question;
import com.jude.beamdemo.model.bean.QuestionResult;
import com.jude.beamdemo.model.service.ServiceResponse;
import com.jude.beamdemo.view.QuestionActivity;

/**
 * Created by zhuchenxi on 15/6/7.
 */
    public class QuestionPresenter extends BeamListActivityPresenter<QuestionActivity,Question> {
        int page;
        @Override
        protected void onCreate(QuestionActivity view, Bundle savedState) {
            super.onCreate(view, savedState);
            onRefresh();
        }


        @Override
        public void onLoadMore() {
            QuestionModel.getInstance().getQuestions(page).subscribe(new ServiceResponse<QuestionResult>() {
                @Override
                public void onServiceError(int status, String info) {
                    getAdapter().pauseMore();
                }

                @Override
                public void onNext(QuestionResult questionResult) {
                    getAdapter().addAll(questionResult.getQuestions());
                    if (questionResult.getTotalPage() == page) getAdapter().stopMore();
                    page++;
                }
            });
        }

        @Override
        public void onRefresh() {
            QuestionModel.getInstance().getQuestions(0).subscribe(new ServiceResponse<QuestionResult>() {
                @Override
                public void onServiceError(int status, String info) {
                    getView().showError();
                }

                @Override
                public void onNext(QuestionResult questionResult) {
                    getAdapter().clear();
                    getAdapter().addAll(questionResult.getQuestions());
                    if (questionResult.getTotalPage()  == page) getAdapter().stopMore();
                    page = 1;
                }
            });
        }
    }
