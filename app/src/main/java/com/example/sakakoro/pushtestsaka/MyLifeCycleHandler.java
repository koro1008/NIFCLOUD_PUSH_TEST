package com.example.sakakoro.pushtestsaka;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

public class MyLifeCycleHandler implements Application.ActivityLifecycleCallbacks {

    private static String activityName;
    private static int create;
    private static int resumed;
    private static int paused;
    private static int started;
    private static int stopped;
    private static int destroy;
    private static boolean isForeground = false;

    public static boolean isApplicationActive(){
        return create > destroy;
    }

    public static boolean isForeground() {
        return isForeground;
    }

    public static String isApplicationActivityName(){
        return activityName;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        create++;
    }

    @Override
    public void onActivityStarted(Activity activity) {
        ++started;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        if (activity != null) activityName = activity.getLocalClassName();
        ++resumed;
        if (activity instanceof BaseActivity || activity instanceof MainActivity) isForeground = true;
    }

    @Override
    public void onActivityPaused(Activity activity) {
        ++paused;
        if (activity instanceof BaseActivity || activity instanceof MainActivity) isForeground = false;
    }

    @Override
    public void onActivityStopped(Activity activity) {
        ++stopped;
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}

    @Override
    public void onActivityDestroyed(Activity activity) {
        destroy++;
    }

}
