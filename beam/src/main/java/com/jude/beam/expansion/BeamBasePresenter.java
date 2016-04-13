package com.jude.beam.expansion;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

import com.jude.beam.bijection.Presenter;

/**
 * Created by Mr.Jude on 2016/3/20.
 */
public class BeamBasePresenter<T> extends Presenter<T> {
    public static final String KEY_ID = "Beam_id";
    public static final String KEY_DATA = "Beam_data";

    private Activity getActivity(){
        Activity activity = null;
        if (getView() instanceof Activity)activity = (Activity) getView();
        else if (getView() instanceof Fragment)activity = ((Fragment) getView()).getActivity();
        else throw new RuntimeException("No View Found"+(getView()==null?"null":getView().getClass().getName()));
        return activity;
    }

    public String getIdFromIntent(){
        return getActivity().getIntent().getStringExtra(KEY_ID);
    }

    public <M> M getDataFromIntent(){
        return getActivity().getIntent().getParcelableExtra(KEY_DATA);
    }

    public void startActivity(Intent intent){
        getActivity().startActivity(intent);
    }

    public void startActivity(Class<? extends Activity> clazz){
        Activity activity = getActivity();
        activity.startActivity(new Intent(activity,clazz));
    }

    public void startActivityWithData(String id,Class<? extends Activity> clazz){
        Activity activity = getActivity();
        Intent i = new Intent(activity,clazz);
        i.putExtra(KEY_ID,id);
        activity.startActivity(i);
    }
    public void startActivityWithData(String id, Parcelable data,Class<? extends Activity> clazz){
        Activity activity = getActivity();
        Intent i = new Intent(activity,clazz);
        i.putExtra(KEY_ID,id);
        i.putExtra(KEY_DATA,data);
        activity.startActivity(i);
    }
    public void startActivityWithData(Parcelable data,Class<? extends Activity> clazz){
        Activity activity = getActivity();
        Intent i = new Intent(activity,clazz);
        i.putExtra(KEY_DATA,data);
        activity.startActivity(i);
    }

}
