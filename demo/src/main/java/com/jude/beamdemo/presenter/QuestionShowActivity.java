package com.jude.beamdemo.presenter;

import com.jude.beam.expansion.data.BeamDataActivity;
import com.jude.beamdemo.model.bean.Question;

/**
 * Created by Mr.Jude on 2015/9/3.
 */
public class QuestionShowActivity extends BeamDataActivity<QuestionShowPresenter,Question> {
    @Override
    public void setData(Question data) {
        //显示数据
    }

    @Override
    public void setError(Throwable e) {
        //显示错误
    }
}
