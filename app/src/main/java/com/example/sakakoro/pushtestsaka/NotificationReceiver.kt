package com.example.sakakoro.pushtestsaka

import android.app.ActivityManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.nifcloud.mbaas.core.NCMB
import com.nifcloud.mbaas.core.NCMBPush
import org.apache.commons.lang.StringUtils

class NotificationReceiver:BroadcastReceiver() {



    private var active = false
    private var foreground = false
    companion object {
        val richPushForMain: Variable<String> = Variable<String>("")
        val richPushForWeb1: Variable<String> = Variable<String>("")

    }

    override fun onReceive(context: Context?, intent: Intent?) {


        // NCMB初期化
        NCMB.initialize(context,applicationKey,clientKey)
        // 開封
        NCMBPush.trackAppOpened(intent)

        active = MyLifeCycleHandler.isApplicationActive()
        foreground = MyLifeCycleHandler.isForeground()

        if (!active) {
            context?.let {
                val intent_to_main = Intent(it, MainActivity::class.java)
                intent_to_main.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

                // rich push 未起動
                intent?.let {
                    val url = it.getStringExtra(CustomFirebaseMessagingService.RICH_PUSH_URL_KEY_FROM_NOTICE)
                    intent_to_main.putExtra(CustomFirebaseMessagingService.RICH_PUSH_URL_KEY_FROM_NOTICE, url)
                }

                context?.let { it.startActivity(intent_to_main) }
            }
        } else if ((active) && (!foreground)) {
            context?.let { moveTaskToFront(it) }
        }

        // rich push 起動中
        if (active) {
            intent?.let {
                val current = MyLifeCycleHandler.isApplicationActivityName()
                val url = it.getStringExtra(CustomFirebaseMessagingService.RICH_PUSH_URL_KEY_FROM_NOTICE)

                if (StringUtils.isEmpty(url)) return

                when (current) {
                    "MainActivity" -> richPushForMain.value = url
                    "WebView_1Activity" -> richPushForWeb1.value = url
                }
            }
        }

    }

    private fun moveTaskToFront(context: Context) {
        val id = getMyAppId(context)
        if (id > 0) {
            val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            activityManager.moveTaskToFront(id, ActivityManager.MOVE_TASK_WITH_HOME)
        }
    }

    private fun getMyAppId(context: Context): Int {
        val id = -1
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val recentTasks = activityManager.getRunningTasks(Integer.MAX_VALUE)
        for (i in recentTasks.indices) {
            if (recentTasks[i].baseActivity.packageName == "jp.co.yukoyuko.android.yukoyuko_android") return recentTasks[i].id
        }
        return id
    }





}