package com.example.sakakoro.pushtestsaka

import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent

class BaseActivity : AppCompatActivity() {

    protected val screenTransitionAction = ScreenTransitionAction()
//    protected val store = ScreenTransitionStore

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode== KeyEvent.KEYCODE_BACK){
//            store.screenMode.oldValue?.let { screenTransitionAction.changeScreen(it) }
            return true
        }
        return false
    }

}