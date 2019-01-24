package com.example.sakakoro.pushtestsaka

import android.app.Application

class MyApplication : Application(){

    override fun onCreate() {

        super.onCreate()

        registerActivityLifecycleCallbacks(MyLifeCycleHandler())
    }
}