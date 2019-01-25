package com.example.sakakoro.pushtestsaka

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import com.google.firebase.messaging.RemoteMessage
import com.nifcloud.mbaas.core.NCMBFirebaseMessagingService
import android.support.v4.app.NotificationCompat
import kotlin.random.Random


class CustomFirebaseMessagingService : NCMBFirebaseMessagingService() {


    val TITLE_KEY = "title"
    val MESSAGE_KEY = "message"
    val PUSH_ID_KEY = "com.nifcloud.mbaas.PushId"

    companion object {
        val RICH_PUSH_URL_KEY_FROM_NOTICE = "com.nifty.RichUrl"
        val RICH_PUSH_URL_KEY_TO_DIALOG = "com.nifcloud.mbaas.RichUrl"
    }




    //  端末にpushきたらここに入る!(この時点でまだ通知は表示されてない)
    override fun onMessageReceived(remoteMessage: RemoteMessage){


        val notificationBuilder = notificationSettings(remoteMessage)


        val bigtextStyle = NotificationCompat.BigTextStyle(notificationBuilder)
        remoteMessage.data.get(TITLE_KEY)?.let { bigtextStyle.setBigContentTitle(it) }
        remoteMessage.data.get(MESSAGE_KEY)?.let { bigtextStyle.bigText(it) }

        var appInfo: ApplicationInfo? = null
        try {
            appInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
//
        appInfo?.let {
            val containsKey = it.metaData.containsKey("notificationOverlap")
            val overlap = it.metaData.getInt("notificationOverlap")
            //デフォルト複数表示
            var notificationId = Random.nextInt()
            //最新のみ表示
            if (overlap == 0 && containsKey) notificationId = 0
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.notify(notificationId, bigtextStyle.build())

            notificationManager.notify(notificationId,bigtextStyle.build())
        }

    }


    fun notificationSettings(pushData: RemoteMessage): NotificationCompat.Builder {

        var pushTitle:String? = pushData.data.get(TITLE_KEY)
        var pushBody:String? = pushData.data.get(MESSAGE_KEY)
        var pushID:String? = pushData.data.get(PUSH_ID_KEY)
        var richURL:String? = pushData.data.get(RICH_PUSH_URL_KEY_FROM_NOTICE)

        val intent = Intent("tap")
        pushID.let { intent.putExtra(PUSH_ID_KEY, it) }
        richURL.let { intent.putExtra(RICH_PUSH_URL_KEY_FROM_NOTICE, it) }
        val pendingIntent = PendingIntent.getBroadcast(applicationContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(applicationContext)

        builder
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.drawable.icon)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.icon))
            .setDefaults(Notification.DEFAULT_ALL)
            .setOnlyAlertOnce(true)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setContentTitle(pushTitle)
            .setContentText(pushBody)
            .setTicker(pushTitle)
            .setCategory(Notification.CATEGORY_SERVICE)
            .setPriority(Notification.PRIORITY_HIGH)
            .setVibrate(longArrayOf(60000, 100))

        return builder
    }


}