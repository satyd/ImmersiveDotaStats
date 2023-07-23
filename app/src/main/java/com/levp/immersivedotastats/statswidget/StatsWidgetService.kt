package com.levp.immersivedotastats.statswidget

import android.app.Service
import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.IBinder
import android.widget.RemoteViews
import com.levp.immersivedotastats.R


class StatsWidgetService : Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStart(intent, startId)
        updateMood(intent)
        stopSelf(startId)
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    private fun updateMood(intent: Intent?) {
        if (intent != null) {
            val requestedAction = intent.action
            if (requestedAction != null) {

                val widgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, 0)
                val appWidgetMan = AppWidgetManager.getInstance(this)
                val item = RemoteViews(this.packageName, R.layout.widget_list_item)
                val list = RemoteViews(this.packageName, R.layout.widget_layout)

                //views.setTextViewText(R.id.widgetMood, currentMood)
                //appWidgetMan.updateAppWidget(widgetId, views)
            }
        }
    }
}