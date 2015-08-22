package com.jude.beamdemo.presenter;

import android.os.Bundle;

import com.jude.beam.expansion.list.BeamListActivityPresenter;
import com.jude.beamdemo.view.QuestionActivity;
import com.jude.beamdemo.model.QuestionModel;
import com.jude.beamdemo.model.bean.Question;
import com.jude.beamdemo.model.bean.QuestionResult;
import com.jude.beamdemo.model.callback.DataCallback;
import com.jude.utils.JUtils;

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
            QuestionModel.getInstance().getQuestionsFromServer(page, new DataCallback<QuestionResult>() {

                @Override
                public void success(String info, QuestionResult data) {
                    getAdapter().addAll(data.getQuestions());
                    if (data.getTotalPage()-1 == page)getAdapter().stopMore();
                    page++;
                }

                @Override
                public void error(String errorInfo) {
                    JUtils.Log("onError:" + errorInfo);
                    getAdapter().pauseMore();
                }
            });
        }

        @Override
        public void onRefresh() {
            QuestionModel.getInstance().getQuestionsFromServer(0, new DataCallback<QuestionResult>() {

                @Override
                public void success(String info, QuestionResult data) {
                    getAdapter().clear();
                    getAdapter().addAll(data.getQuestions());
                    if (data.getTotalPage()-1 == page)getAdapter().stopMore();
                    page=1;
                }

                @Override
                public void error(String errorInfo) {
                    getView().showError();
                }

            });
        }
    }
