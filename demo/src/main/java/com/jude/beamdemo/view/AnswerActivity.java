package com.jude.beamdemo.view;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListActivity;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.beamdemo.model.bean.Answer;
import com.jude.beamdemo.model.bean.Question;
import com.jude.beamdemo.presenter.AnswerPresenter;
import com.jude.beamdemo.utils.RecentDateFormat;
import com.jude.beamdome.R;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.utils.JTimeTransform;


/**
 * Created by zhuchenxi on 15/6/8.
 */
@RequiresPresenter(AnswerPresenter.class)
public class AnswerActivity extends BeamListActivity<AnswerPresenter,Answer> {

    @Override
    protected ListConfig getConfig() {
        return super.getConfig()
                .setRefreshAble(true)
                .setNoMoreAble(true)
                .setLoadmoreAble(true);
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new AnswerViewHolder(parent);
    }

    public AnswerHeader getAnswerHeader(Question question){
        return new AnswerHeader(question);
    }

    class AnswerHeader implements RecyclerArrayAdapter.ItemView{
        private Question question;
        public AnswerHeader(Question question){
            this.question = question;
        }

        @Override
        public View onCreateView(ViewGroup parent) {
            View view = LayoutInflater.from(AnswerActivity.this).inflate(R.layout.item_answer_head,parent,false);
            ((TextView)$(view,R.id.title)).setText(question.getTitle());
            ((TextView)$(view,R.id.content)).setText(question.getContent());
            ((TextView)$(view,R.id.name)).setText(question.getAuthorName());
            ((SimpleDraweeView)$(view,R.id.face)).setImageURI(Uri.parse(question.getAuthorFace()));
            ((TextView)$(view,R.id.date)).setText(new JTimeTransform().parse("yyyy-MM-dd HH:mm:ss", question.getDate()).toString(new RecentDateFormat()));
            return view;
        }

        @Override
        public void onBindView(View headerView) {

        }
    }

}
