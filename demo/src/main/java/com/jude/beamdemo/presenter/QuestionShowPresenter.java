package com.jude.beamdemo.presenter;

import android.os.Bundle;

import com.jude.beam.expansion.data.BeamDataActivityPresenter;
import com.jude.beamdemo.model.QuestionModel;
import com.jude.beamdemo.model.bean.Question;

/**
 * Created by Mr.Jude on 2015/9/3.
 */
public class QuestionShowPresenter extends BeamDataActivityPresenter<QuestionShowActivity,Question> {

    @Override
    protected void onCreate(QuestionShowActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        QuestionModel.getInstance().getQuestion(1).subscribe(this);
    }
}
