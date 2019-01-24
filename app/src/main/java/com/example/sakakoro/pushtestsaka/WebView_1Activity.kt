package com.example.sakakoro.pushtestsaka

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nifcloud.mbaas.core.NCMBPush
import io.reactivex.android.schedulers.AndroidSchedulers

class WebView_1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)


        settingForRichPush()
    }



    private fun settingForRichPush() {
        NotificationReceiver.richPushForWeb1.value = ""
        NotificationReceiver.richPushForWeb1
            .asObservable()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ url -> url?.let {
                // rich push によるURLを表示
                intent.putExtra(CustomFirebaseMessagingService.RICH_PUSH_URL_KEY_TO_DIALOG, url)
                NCMBPush.richPushHandler(this, intent)
            } })
    }

}
