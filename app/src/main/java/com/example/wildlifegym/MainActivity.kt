package com.example.wildlifegym

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer


open class MainActivity : AppCompatActivity() {

    private  lateinit var navController: NavController

    lateinit var sharedPreferences: SharedPreferences

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        hideSystemUI()

        sharedPreferences = this.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        //val musicPlayer: MediaPlayer? = MediaPlayer.create(this, R.raw.happy_mistake)
        //musicPlayer?.start()
        val musicoff = sharedPreferences.getBoolean("MUSICOFF", false)
        if (!musicoff) {
            PlayBackgroundSound()
        }
    }

    fun PlayBackgroundSound() {
        val intent = Intent(this@MainActivity, BackgroundMusicService::class.java)
        startService(intent)
    }

    fun StopBackgroundSound() {
        val intent = Intent(this@MainActivity, BackgroundMusicService::class.java)
        stopService(intent)
    }

    fun ButtonSound() {
        sharedPreferences = this.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        val soundoff = sharedPreferences.getBoolean("SOUNDOFF", false)
        //val mediaPlayer: MediaPlayer = MediaPlayer.create(this, R.raw.click)
        if (!soundoff) {
            //mediaPlayer.start()
            playSound(R.raw.click)
        }
    }

    private val mediaPlayer = MediaPlayer().apply {
        setOnPreparedListener { start() }
        setOnCompletionListener { reset() }
    }

    fun playSound(rawResId: Int) {
        val assetFileDescriptor = this.resources.openRawResourceFd(rawResId) ?: return
        mediaPlayer.run {
            reset()
            setDataSource(assetFileDescriptor.fileDescriptor, assetFileDescriptor.startOffset, assetFileDescriptor.declaredLength)
            prepareAsync()
        }
    }

    /**
     * This function hides the navigation bar
     */
    @RequiresApi(Build.VERSION_CODES.R)
    fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window,
            window.decorView.findViewById(android.R.id.content)).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())

            // When the screen is swiped up at the bottom
            // of the application, the navigationBar shall
            // appear for some time
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}