package com.jude.beam.bijection;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;

/**
 * Created by Mr.Jude on 2015/8/22.
 */
public class ActivityLifeCycleDelegate{
    private Activity act;

    public ActivityLifeCycleDelegate(Activity act) {
        this.act = act;
    }

    public Activity getActivity() {
        return act;
    }

    protected void onCreate(Bundle savedInstanceState) {
    }

    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
    }

    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
    }

    protected void onPostCreate(Bundle savedInstanceState) {
    }

    protected void onStart() {
    }

    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
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

    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
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
}
