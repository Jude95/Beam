package com.jude.beamdemo.view;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.beamdemo.model.bean.Question;
import com.jude.beamdemo.utils.RecentDateFormat;
import com.jude.beamdome.R;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.utils.JTimeTransform;

/**
 * Created by zhuchenxi on 15/6/7.
 */
    public class QuestionViewHolder extends BaseViewHolder<Question> {
        private TextView title;
        private TextView name;
        private SimpleDraweeView face;
        private TextView date;
        private TextView content;
        private TextView answerCount;
        private Question question;
        public QuestionViewHolder(final ViewGroup parent) {
            super(parent, R.layout.item_question);
            title = $(R.id.title);
            name = $(R.id.name);
            face = $(R.id.face);
            date = $(R.id.date);
            content = $(R.id.content);
            answerCount = $(R.id.answer);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(parent.getContext(),AnswerActivity.class);
                    i.putExtra("question",question);
                    parent.getContext().startActivity(i);
                }
            });
        }

        @Override
        public void setData(Question data) {
            question = data;
            title.setText(data.getTitle());
            name.setText(data.getAuthorName());
            face.setImageURI(Uri.parse(data.getAuthorFace()));
            answerCount.setText(data.getAnswerCount()+"个回答");
            if(data.getRecent()!=null){
                date.setText("最近回答 "+new JTimeTransform().parse("yyyy-MM-dd HH:mm:ss",data.getRecent()).toString(new RecentDateFormat()));
            }else{
                date.setText(new JTimeTransform().parse("yyyy-MM-dd HH:mm:ss",data.getDate()).toString(new RecentDateFormat()));
            }
            content.setText(data.getContent());
        }
    }
