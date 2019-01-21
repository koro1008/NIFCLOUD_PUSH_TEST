package com.example.sakakoro.pushtestsaka

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nifcloud.mbaas.core.NCMB
import com.nifcloud.mbaas.core.NCMBInstallation
import com.nifcloud.mbaas.core.NCMBException;
import com.nifcloud.mbaas.core.NCMBObject;
import com.nifcloud.mbaas.core.DoneCallback;



//********** APIキーの設定 **********
const val applicationKey:String = "5819cd7c34b2cc4dafce27d0bc28a0c99ecb19d3e587415633482d7cee7d58e1"
const val clientKey:String = "d07bb9a4cafc7538828aacf324e4e5e320163d5b4f22d062de59534bcf630509"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //********** SDKの初期化 **********
//        NCMB.initialize(applicationContext, applicationKey, clientKey)
        NCMB.initialize(applicationContext, applicationKey, clientKey)

        //▼▼▼起動時に処理される▼▼▼

        //▲▲▲起動時に処理される▲▲▲

        val installation = NCMBInstallation.getCurrentInstallation()

//FCMからRegistrationIdを取得
//        installation.getRegistrationIdInBackground("786302876536") { e ->
//            if (e == null) {
//                //端末情報をデータストアに登録
//                installation.saveInBackground { saveErr ->
//                    if (saveErr != null) {
//                        //端末情報登録時のエラー処理
//                    }
//                }
//            } else {
//                //RegistrationId取得時のエラー処理
//            }
//        }
    }
}
