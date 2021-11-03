package com.example.notificationsapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var etNotificationMSG:EditText
    lateinit var btShowNotification:Button
    private val channelId = "myapp.notifications"
    private val description = "Notification App Example"
    lateinit var builder:Notification.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etNotificationMSG=findViewById(R.id.etNotificationMSG)
        btShowNotification=findViewById(R.id.btShowNotification)

        btShowNotification.setOnClickListener {
            val notificationMessage=etNotificationMSG.text.toString()
            if (notificationMessage.isNotEmpty()){
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val intent = Intent(this, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                var notificationChannel = NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(notificationChannel)

               builder = Notification.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                    .setContentIntent(pendingIntent)
                    .setContentTitle("My Notification")
                    .setContentText(notificationMessage)
            } else {
                 builder = Notification.Builder(this)
                    .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                    .setContentIntent(pendingIntent)
                    .setContentTitle("My Notification")
                    .setContentText((notificationMessage))
            }
            notificationManager.notify(1234, builder.build())
        }else{
            Toast.makeText(this,"Enter a message ",Toast.LENGTH_LONG).show()
        }
        }

    }
}