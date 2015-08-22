package com.jude.beamdemo.model.callback;


import com.jude.http.RequestListener;

/**
 * Created by Mr.Jude on 2015/6/13.
 */
class LinkCallback implements RequestListener {
    private LinkCallback link;
    public LinkCallback add(LinkCallback other){
        other.setLink(this);
        return other;
    }
    private void setLink(LinkCallback link){
        this.link = link;
    }

    @Override
    public void onRequest() {
        if (link != null)
        link.onRequest();
    }

    @Override
    public void onSuccess(String s) {
        if (link != null)
        link.onSuccess(s);
    }

    @Override
    public void onError(String s) {
        if (link != null)
        link.onError(s);
    }
}
