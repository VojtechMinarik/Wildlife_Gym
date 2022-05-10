package com.example.wildlifegym

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import com.example.wildlifegym.services.BackgroundMusicService


open class MainActivity : AppCompatActivity() {

    private  lateinit var navController: NavController

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        hideSystemUI()

        playBackgroundSound()
    }

    override fun onResume() {
        super.onResume()
        hideSystemUI()
    }

    fun playBackgroundSound() {
        sharedPreferences = this.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean("MUSICOFF", false)) {
            return
        }
        val intent = Intent(this@MainActivity, BackgroundMusicService::class.java)
        startService(intent)
    }

    fun stopBackgroundSound() {
        val intent = Intent(this@MainActivity, BackgroundMusicService::class.java)
        stopService(intent)
    }

    fun makeSound(soundName: String) {
        sharedPreferences = this.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean("SOUNDOFF", false)) {
            return
        }
        when (soundName) {
            "button" -> playSound(R.raw.buttonclick)
            "rightanswer" -> playSound(R.raw.rightanswer)
            "wronganswer" -> playSound(R.raw.badanswer)
            else -> return
        }
    }

    private val mediaPlayer = MediaPlayer().apply {
        setOnPreparedListener { start() }
        setOnCompletionListener { reset() }
    }

    private fun playSound(rawResId: Int) {
        val assetFileDescriptor = this.resources.openRawResourceFd(rawResId) ?: return
        mediaPlayer.run {
            reset()
            setDataSource(assetFileDescriptor.fileDescriptor, assetFileDescriptor.startOffset, assetFileDescriptor.declaredLength)
            prepareAsync()
        }
    }

    fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window,
            window.decorView.findViewById(android.R.id.content)).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())


            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}