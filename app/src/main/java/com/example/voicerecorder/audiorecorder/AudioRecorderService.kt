package com.example.voicerecorder.audiorecorder

import android.app.*
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.Icon
import android.os.IBinder
import com.example.voicerecorder.R
import com.example.voicerecorder.ui.MainActivity
import org.koin.android.ext.android.inject

//TODO const strings
// TODO use coroutine for recording?
class AudioRecorderService : Service() {
    val ACTION_STOP_SERVICE = "STOP"

//    TODO move recorder to ViewModel
    private val recorder: AndroidAudioRecorder by inject()

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()
        val pendingIntent = createPendingIntent()
        val notification = createNotification(pendingIntent)
        recorder.start()
        handleStopButton(intent)
        startForeground(1, notification)
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        recorder.stop()
        super.onDestroy()
    }

    private fun handleStopButton(intent: Intent?) {
        if (intent != null && ACTION_STOP_SERVICE == intent.action) {
            recorder.stop()
            stopSelf()
        }
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

        val stopSelfIntent = Intent(this, AudioRecorderService::class.java)
        stopSelfIntent.action = ACTION_STOP_SERVICE

        val stopSelfPendingIntent = PendingIntent.getService(
            this, 0, stopSelfIntent, PendingIntent.FLAG_IMMUTABLE
        )

        val action: Notification.Action = Notification.Action.Builder(
            Icon.createWithResource(this, R.drawable.ic_launcher_background),
            "Stop", stopSelfPendingIntent
        ).build()

        return Notification.Builder(this, "CHANNEL_DEFAULT_IMPORTANCE")
            .setContentTitle("Recording")
            .setContentText("Recording")
            .setSmallIcon(R.drawable.ic_stat_name)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    this.resources,
                    R.drawable.ic_stat_name
                )
            )
            .addAction(action)
            .setContentIntent(pendingIntent)
            .setTicker("Recording")
            .build()
    }
}