package com.jude.beamdemo.view;

import android.net.Uri;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.beamdemo.model.bean.Answer;
import com.jude.beamdemo.utils.RecentDateFormat;
import com.jude.beamdome.R;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.utils.JTimeTransform;

/**
 * Created by zhuchenxi on 15/6/8.
 */
public class AnswerViewHolder extends BaseViewHolder<Answer> {
    private TextView name;
    private SimpleDraweeView face;
    private TextView date;
    private TextView content;

    public AnswerViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_answer);
        name = $(R.id.name);
        face = $(R.id.face);
        date = $(R.id.date);
        content = $(R.id.content);
    }

    @Override
    public void setData(Answer data) {
        name.setText(data.getAuthorName());
        face.setImageURI(Uri.parse(data.getAuthorFace()));
        date.setText(new JTimeTransform().parse("yyyy-MM-dd HH:mm:ss",data.getDate()).toString(new RecentDateFormat()));
        content.setText(data.getContent());
    }
}
