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
import android.view.View
import android.widget.ImageButton
import com.example.wildlifegym.services.BackgroundMusicService

/**
 * This is the Main activity
 * It provides the basic functionality of the app
 * It hosts the fragmentView for all available fragments
 */
open class MainActivity : AppCompatActivity() {

    /** Initialize a navController for fragment usage */
    private lateinit var navController: NavController

    /** Initialize sharedPreferences for shared preference usage */
    lateinit var sharedPreferences: SharedPreferences

    /**
     * Initialize the mediaPlayer for playing sounds
     * Set listeners to make the player close after finishing
     */
    private val mediaPlayer = MediaPlayer().apply {
        setOnPreparedListener {
            start()
        }
        setOnCompletionListener {
            reset()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        /** Initialize the fragment view used in this activity */
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        hideNavigationBar()

        playBackgroundSound()
    }

    override fun onResume() {
        super.onResume()
        hideNavigationBar()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopBackgroundSound()
    }


    /**
     * This method checks the state of given shared preference
     * @param keyName The shared preference key name
     */
    fun checkPreferences(keyName: String): Boolean {
        sharedPreferences = this.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(keyName, false)
    }

    /**
     * This method checks the user preferences and hides the given button
     * @param button The imageButton that is to be turned off
     */
    fun hideButton(button: ImageButton) {
        if (checkPreferences("BACKOFF")) {
            button.visibility = View.INVISIBLE
        } else {
            button.visibility = View.VISIBLE
        }
    }

    /**
     * This method starts the BackgroundMusicService
     * It also checks the user preferences state
     */
    fun playBackgroundSound() {
        if (checkPreferences("MUSICOFF")) {
            return
        }
        val intent = Intent(this@MainActivity, BackgroundMusicService::class.java)
        startService(intent)
    }

    /**
     * This method stops the BackgroundMusicService
     * It also checks the user preferences state
     */
    fun stopBackgroundSound() {
        if (!checkPreferences("MUSICOFF")) {
            return
        }
        val intent = Intent(this@MainActivity, BackgroundMusicService::class.java)
        stopService(intent)
    }

    /**
     * This method checks the user preferences and plays one of the defined sounds
     * @param soundName The name of the played sound
     */
    fun makeSound(soundName: String) {
        if (checkPreferences("SOUNDOFF")) {
            return
        }
        when (soundName) {
            "button" -> playSound(R.raw.buttonclick)
            "rightanswer" -> playSound(R.raw.rightanswer)
            "wronganswer" -> playSound(R.raw.badanswer)
            else -> return
        }
    }

    /**
     * This method plays a sound of the given id from the raw folder using mediaPlayer
     * @param resId An id of the raw sound
     */
    private fun playSound(resId: Int) {
        val assetFileDescriptor = this.resources.openRawResourceFd(resId) ?: return
        mediaPlayer.run {
            reset()
            setDataSource(assetFileDescriptor.fileDescriptor, assetFileDescriptor.startOffset, assetFileDescriptor.declaredLength)
            prepareAsync()
        }
    }

    /**
     * This method hides the navigation bar and shows the translucent bar
     * The translucent bar can be summoned by swiping up the screen
     */
    fun hideNavigationBar() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView.findViewById(android.R.id.content)).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}