package com.example.sakakoro.pushtestsaka

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nifcloud.mbaas.core.*


//********** APIキーの設定 **********
const val applicationKey:String = "5819cd7c34b2cc4dafce27d0bc28a0c99ecb19d3e587415633482d7cee7d58e1"
const val clientKey:String = "d07bb9a4cafc7538828aacf324e4e5e320163d5b4f22d062de59534bcf630509"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //********** SDKの初期化 **********
//        NCMB.initialize(applicationContext, applicationKey, clientKey)

        // TODO これだけで配信端末情報を登録できるらしい??fcmに？う〜ん
        NCMB.initialize(this.getApplicationContext(), applicationKey, clientKey)

        //▼▼▼起動時に処理される▼▼▼

        //▲▲▲起動時に処理される▲▲▲

        val installation = NCMBInstallation.getCurrentInstallation()

        // TODO getRegistrationIdInBackground処理は不要らしい

        // Fcmに端末情報登録
        installation.saveInBackground { e ->
            if (e == null) {
                //保存成功
//                registerSuccess()
            } else if (NCMBException.DATA_NOT_FOUND == e.code) {
                //保存失敗 : 端末情報の該当データがない
//                reRegistInstallation(installation)
            } else if (NCMBException.DUPLICATE_VALUE == e.code) {
                //保存失敗 : registrationID重複
//                updateInstallation(installation)
            } else {
                //保存失敗 : その他
//                registerError()
            }
        }




    }
}
