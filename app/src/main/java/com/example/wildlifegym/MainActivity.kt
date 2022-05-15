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
import com.example.wildlifegym.services.MusicService
import com.example.wildlifegym.utils.Animal
import com.example.wildlifegym.utils.AppDatabase

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

        /** Hide the navigation bar */
        hideNavigationBar()

        /** Start background music, if allowed */
        playBackgroundSound()
    }

    override fun onResume() {
        super.onResume()

        /** Hide the navigation bar */
        hideNavigationBar()

        /** Start background music, if allowed */
        playBackgroundSound()
    }

    override fun onPause() {
        super.onPause()

        /** Stop background music */
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
        val intent = Intent(this@MainActivity, MusicService::class.java)
        startService(intent)
    }

    /**
     * This method stops the BackgroundMusicService
     */
    fun stopBackgroundSound() {
        val intent = Intent(this@MainActivity, MusicService::class.java)
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
            "rightAnswer" -> playSound(R.raw.rightanswer)
            "wrongAnswer" -> playSound(R.raw.badanswer)
            else -> return
        }
    }

    /**
     * This method plays a sound of the given id from the raw folder using mediaPlayer
     * @param resId An id of the raw sound
     */
    fun playSound(resId: Int) {
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

    /**
     * This method adds given amount of points to each games record in the database
     * In case the game is already finished, it does nothing
     *
     * @param animal The animal name as a String
     * @param currGame The name of current game as a String
     * @param pointMemory Number of points to be added to the Memory record
     * @param pointDifference Number of points to be added to the Difference record
     * @param pointShadow Number of points to be added to the Shadow record
     * @param pointQuiz Number of points to be added to the Quiz record
     */
    fun addPoints(animal: String, currGame: String, pointMemory: Int, pointDifference: Int, pointShadow: Int, pointQuiz: Int) {
        Thread {
            val db = AppDatabase.getDatabase(this)
            var currPoints = 0
            when (currGame) {
                "memory" -> currPoints = db.databaseDao().getResMemory(animal)
                "difference" -> currPoints = db.databaseDao().getResDifference(animal)
                "shadow" -> currPoints = db.databaseDao().getResShadow(animal)
                "quiz" -> currPoints = db.databaseDao().getResQuiz(animal)
            }
            if (currPoints == 0) {
                db.databaseDao().updateAnimal(Animal(animal,
                    db.databaseDao().getPoints(animal) + 1,
                    db.databaseDao().getResQuiz(animal) + pointQuiz,
                    db.databaseDao().getResShadow(animal) + pointShadow,
                    db.databaseDao().getResDifference(animal) + pointDifference,
                    db.databaseDao().getResMemory(animal) + pointMemory,
                    db.databaseDao().getResVideo(animal),
                    db.databaseDao().getResPoem(animal),
                    db.databaseDao().getResEncyclopedia(animal)))
            }
        }.start()
    }
}