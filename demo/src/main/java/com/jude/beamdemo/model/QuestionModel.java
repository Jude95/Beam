package com.jude.beamdemo.model;

import android.content.Context;

import com.jude.beam.model.AbsModel;
import com.jude.beamdemo.model.bean.AnswerResult;
import com.jude.beamdemo.model.bean.QuestionResult;
import com.jude.beamdemo.model.service.DefaultTransform;
import com.jude.beamdemo.model.service.ServiceClient;

import rx.Observable;

/**
 * Created by zhuchenxi on 15/6/7.
 */
public class QuestionModel extends AbsModel {
    public static QuestionModel getInstance(){
        return getInstance(QuestionModel.class);
    }
    @Override
    protected void onAppCreate(Context ctx) {

    }

    public Observable<QuestionResult> getQuestions(int page){
        return ServiceClient.getService().getQuestions(page).compose(new DefaultTransform<QuestionResult>());
    }
    public Observable<AnswerResult> getAnswers(int page, String questionsId, boolean desc){
        return ServiceClient.getService().getAnswers(page, questionsId, desc).compose(new DefaultTransform<AnswerResult>());
    }
}
