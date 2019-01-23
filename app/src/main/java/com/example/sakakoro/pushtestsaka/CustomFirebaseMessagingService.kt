package com.example.sakakoro.pushtestsaka

import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import com.google.firebase.messaging.RemoteMessage
import com.nifcloud.mbaas.core.NCMBFirebaseMessagingService
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import com.google.gson.Gson


class CustomFirebaseMessagingService : NCMBFirebaseMessagingService() {


    val TITLE_KEY = "title"
    val MESSAGE_KEY = "message"
    val PUSH_ID_KEY = "com.nifty.PushId"
    val RICH_PUSH_URL_KEY = "com.nifty.RichUrl"

    companion object {
        var richURL:String? = null
    }




    //  端末にpushきたらここに入る!(この時点でまだ通知は表示されてない)
    override fun onMessageReceived(remoteMessage: RemoteMessage){


//        val notificationBuilder = notificationSettings(remoteMessage)

        val gson = Gson()


        var pushTitle:String? = remoteMessage.data.get(TITLE_KEY)
        var pushBody:String? = remoteMessage.data.get(MESSAGE_KEY)
        richURL = remoteMessage.data.get(RICH_PUSH_URL_KEY)


        super.onMessageReceived(remoteMessage)
    }


//    override fun notificationSettings(data: RemoteMessage): NotificationCompat.Builder {
//
//        var title:String? = data.notification?.title
//        var body:String? = data.notification?.body
//        var url:String? = data.data.get("link")
//
//        val intent = Intent("tap")
//        data.getString(PUSH_ID_KEY)?.let { intent.putExtra(PUSH_ID_KEY, it) }
//        data.getString(RICH_PUSH_URL_KEY)?.let { intent.putExtra(RICH_PUSH_URL_KEY, it) }
//        val pendingIntent = PendingIntent.getBroadcast(applicationContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
//
//        val builder = NotificationCompat.Builder(applicationContext)
//
//        builder
//            .setWhen(System.currentTimeMillis())
//            .setSmallIcon(R.drawable.tab_onsen_active)
//            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.icon))
//            .setDefaults(Notification.DEFAULT_ALL)
//            .setOnlyAlertOnce(true)
//            .setAutoCancel(true)
//            .setContentIntent(pendingIntent)
//            .setContentTitle(title)
//            .setContentText(body)
//            .setTicker(title)
//            .setCategory(Notification.CATEGORY_SERVICE)
//            .setPriority(Notification.PRIORITY_HIGH)
//            .setVibrate(longArrayOf(60000, 100))
//
//        return builder
//    }


}