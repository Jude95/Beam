package com.jude.beam.bijection;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityEvent;

/**
 * Created by Mr.Jude on 2015/8/22.
 */
public class ActivityLifeCycleDelegate{
    private BeamAppCompatActivity act;

    public ActivityLifeCycleDelegate(BeamAppCompatActivity act) {
        this.act = act;
    }

    public BeamAppCompatActivity getActivity() {
        return act;
    }

    protected void onCreate(Bundle savedInstanceState) {
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
    }

    protected void onPostCreate(Bundle savedInstanceState) {
    }

    protected void onStart() {
    }

    protected void onRestart() {
    }

    protected void onResume() {
    }

    protected void onPostResume() {
    }

    protected void onNewIntent(Intent intent) {
    }

    protected void onSaveInstanceState(Bundle outState) {
    }

    protected void onPause() {
    }

    protected void onUserLeaveHint() {
    }

    protected void onStop() {
    }

    protected void onDestroy() {
    }

    public void onConfigurationChanged(Configuration newConfig) {
    }

    public void onLowMemory() {
    }

    public void onTrimMemory(int level) {
    }

    public void onAttachFragment(Fragment fragment) {
    }

    public void onBackPressed() {
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    public void onContentChanged() {

    }

    public void onResumeFragments() {

    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        return getActivity().dispatchPopulateAccessibilityEventSuper(event);
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        return getActivity().dispatchTouchEventSuper(ev);
    }

    public boolean dispatchTrackballEvent(MotionEvent ev) {
        return getActivity().dispatchTrackballEventSuper(ev);
    }
}
