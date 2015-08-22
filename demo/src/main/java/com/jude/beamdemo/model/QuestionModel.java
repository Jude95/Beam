package com.jude.beamdemo.model;

import android.content.Context;
import android.os.Handler;

import com.jude.beam.model.AbsModel;
import com.jude.beamdemo.config.API;
import com.jude.beamdemo.model.bean.AnswerResult;
import com.jude.beamdemo.model.bean.QuestionResult;
import com.jude.beamdemo.model.callback.DataCallback;
import com.jude.http.RequestManager;
import com.jude.http.RequestMap;

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

    @Override
    protected void onAppCreateOnBackThread(Context ctx) {
    }

    public void getQuestionsFromServer(final int page, final DataCallback<QuestionResult> callback){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                RequestManager.getInstance().post(API.URL.GetQuestionList, new RequestMap("page", page + ""), callback);
            }
        }, 1000);
    }


    public void getAnswerFromServer(final int page, final String questionId, final boolean desc, final DataCallback<AnswerResult> callback){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                        RequestMap params = new RequestMap();
                        params.put("page", page + "");
                        params.put("questionId", questionId);
                        params.put("desc", desc ? "true" : "false");
                        RequestManager.getInstance().post(API.URL.GetAnswerList, params, callback);
            }
        }, 1000);
    }

}
