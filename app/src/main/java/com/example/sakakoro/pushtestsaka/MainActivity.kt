package com.example.sakakoro.pushtestsaka

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.nifcloud.mbaas.core.*
import io.reactivex.android.schedulers.AndroidSchedulers


//********** APIキーの設定 **********
const val applicationKey:String = "5819cd7c34b2cc4dafce27d0bc28a0c99ecb19d3e587415633482d7cee7d58e1"
const val clientKey:String = "d07bb9a4cafc7538828aacf324e4e5e320163d5b4f22d062de59534bcf630509"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // TODO これだけで配信端末情報を登録できるらしい?
        NCMB.initialize(this.getApplicationContext(), applicationKey, clientKey)


        val installation = NCMBInstallation.getCurrentInstallation()

        // TODO installation.getRegistrationIdInBackground処理は不要らしい
        // TODO installation.saveInBackgroundも不要らしい
        // TODO つまり、initializeのみでFcmからトークン取得+ニフクラに登録できる


        settingForRichPush()

        val urlFromReceiver:String? = intent.getStringExtra(CustomFirebaseMessagingService.RICH_PUSH_URL_KEY_FROM_NOTICE)
        if(!(urlFromReceiver == null||urlFromReceiver.equals(""))){
            NotificationReceiver.richPushForMain.value = urlFromReceiver
        }
    }

    private fun settingForRichPush() {
//        NotificationReceiver.richPushForMain.value = ""
        NotificationReceiver.richPushForMain
            .asObservable()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ richPushForMain -> richPushForMain?.let {
                // rich push によるURLを表示
                intent.putExtra(CustomFirebaseMessagingService.RICH_PUSH_URL_KEY_TO_DIALOG, it)
                NCMBPush.richPushHandler(this, intent)
            } })
    }



    fun onClickToWeb1(view:View){

        val intent_to_web1 = Intent(this,WebView_1Activity::class.java)
        startActivity(intent_to_web1)
    }

    fun onClickTest(view: View){

        val testURL = "https://avex.jp/aaa/"

        NotificationReceiver.richPushForMain.value = testURL
    }
}
