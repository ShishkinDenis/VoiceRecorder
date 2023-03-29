package com.example.voicerecorder.audiorecoder

import android.app.*
import android.content.Intent
import android.os.IBinder
import com.example.voicerecorder.R
import com.example.voicerecorder.ui.MainActivity

//TODO const strings
// TODO add buttons play and stop recording on notification
// TODO use coroutine?
class AudioRecorderService : Service() {

    private val recorder by lazy {
        AndroidAudioRecorder(applicationContext)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        TODO fun
        createNotificationChannel()
        val pendingIntent = createPendingIntent()
        val notification = createNotification(pendingIntent)
        recorder.start()
        startForeground(1, notification)
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        recorder.stop()
        super.onDestroy()
    }

    private fun createNotificationChannel() {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("CHANNEL_DEFAULT_IMPORTANCE", "name", importance)
        channel.description = "descriptionText"
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun createPendingIntent(): PendingIntent {
        return Intent(this, MainActivity::class.java).let { notificationIntent ->
            PendingIntent.getActivity(
                this, 0, notificationIntent,
                PendingIntent.FLAG_IMMUTABLE
            )
        }
    }

    private fun createNotification(pendingIntent: PendingIntent): Notification {
        return Notification.Builder(this, "CHANNEL_DEFAULT_IMPORTANCE")
            .setContentTitle("Recording")
            .setContentText("Recording")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentIntent(pendingIntent)
            .setTicker("Recording")
            .build()
    }
}