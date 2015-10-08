package com.jude.beamdemo.model.service;

import com.jude.beamdemo.config.API;
import com.jude.beamdemo.model.bean.AnswerResult;
import com.jude.beamdemo.model.bean.QuestionResult;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Headers;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by Mr.Jude on 2015/8/23.
 * 服务器接口
 */
public interface Service {
    @FormUrlEncoded
    @POST(API.URL.GetQuestionList)
    Observable<QuestionResult> getQuestions(@Field("page") int page);


    @FormUrlEncoded
    @POST(API.URL.GetAnswerList)
    Observable<AnswerResult> getAnswers(
            @Field("page")int page,
            @Field("questionId")String questionsId,
            @Field("desc")boolean desc);
}
