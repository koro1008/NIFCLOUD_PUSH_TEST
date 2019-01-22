package com.example.sakakoro.pushtestsaka

import com.google.firebase.messaging.RemoteMessage
import com.nifcloud.mbaas.core.NCMBFirebaseMessagingService

class CustomFirebaseMessagingService : NCMBFirebaseMessagingService() {



    //  端末にpushきたらここに入る!(この時点でまだ通知は表示されてない)
    override fun onMessageReceived(remoteMessage: RemoteMessage){

        // TODO ペイロードデータの取得？？？？
        var dataString = remoteMessage.data

        super.onMessageReceived(remoteMessage)
    }

}