package com.example.wildlifegym.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.example.wildlifegym.R

/**
 * This is the Music service
 * It plays music on the background of this application
 */
class MusicService : Service() {

    /** Initialize the MediaPlayer */
    private lateinit var player: MediaPlayer

    /**
     * This method creates and sets up the MediaPlayer
     */
    override fun onCreate() {
        super.onCreate()
        player = MediaPlayer.create(this, R.raw.happy_mistake);
        player.isLooping = true
        player.setVolume(75f, 75f)

    }

    /**
     * This method starts the MediaPlayer
     */
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        player.start()
        return START_STICKY
    }

    /**
     * This method is called when the user ends this activity
     * It stops and releases the MediaPlayer
     */
    override fun onDestroy() {
        player.stop()
        player.release()
    }

    override fun onBind(arg0: Intent): IBinder? {
        return null
    }

    fun onUnBind(arg0: Intent): IBinder? {
        return null
    }

    companion object {
        private val TAG: String? = null
    }
}