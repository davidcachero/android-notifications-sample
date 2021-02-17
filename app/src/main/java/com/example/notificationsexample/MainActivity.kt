package com.example.notificationsexample

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    val channelId = "CanalNotificationExample"
    var notificationManager:NotificationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var canal = NotificationChannel(channelId, "Nombre canal", NotificationManager.IMPORTANCE_DEFAULT)
        canal.description = "Descripcion"
        canal.enableVibration(true)
        canal.vibrationPattern = longArrayOf(100,100,500,200)

        notificationManager!!.createNotificationChannel(canal)
    }

    fun sendNotification(view: View) {
        val notificationId = 101

        val pendingIntent = PendingIntent.getActivity(this, 0, Intent(this, SecondActivity::class.java), PendingIntent.FLAG_UPDATE_CURRENT)

        val icon = Icon.createWithResource(this, android.R.drawable.ic_dialog_alert)

        val action = Notification.Action.Builder(icon, "ABRIR", pendingIntent).build()

        val notificacionBuilder = Notification.Builder(this, channelId)
                .setContentTitle("Titulo notificacion")
                .setContentText("Texto de la notificacion")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentIntent(pendingIntent)
            .setActions(action)

        notificationManager!!.notify(notificationId, notificacionBuilder.build())
    }
}