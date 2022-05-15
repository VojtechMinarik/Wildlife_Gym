package com.example.wildlifegym.activities

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageButton
import com.example.wildlifegym.R

/**
 * This is the Poem activity
 * It lets the user play the poem for the currently selected animal
 */
class PoemActivity : MainActivity() {

    /**
     * Initialize the mediaPlayer for playing poems
     * Set listeners to make the player close after finishing
     */
    private val poemPlayer = MediaPlayer().apply {
        setOnPreparedListener { start() }
        setOnCompletionListener { reset() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poem)

        /** Hide the navigation bar */
        hideNavigationBar()

        /** Set up all the buttons available on this fragment */
        val buttonPoemBack = this.findViewById<ImageButton>(R.id.image_button_poem_back)
        val buttonPoemPlay = this.findViewById<ImageButton>(R.id.image_button_poem_play)

        /**
         * Collect the argument passed as an intent from the Animal fragment
         * It contains the name of the "current" animal as String
         */
        val animal = intent.getStringExtra("animal")

        /**
         * Set up the onClickListeners for all of the buttons available
         *
         * buttonPoemBack navigates back to the Animal fragment
         * buttonPoemPlay plays the poem for current animal
         */
        buttonPoemBack.setOnClickListener {
            makeSound("button")
            finish()
        }
        buttonPoemPlay.setOnClickListener {
            makeSound("button")
            playPoem(resources.getIdentifier("poem_${animal}", "raw", this.packageName))
        }
    }

    /**
     * This method makes sure the player is released after closing this activity
     */
    override fun onDestroy() {
        super.onDestroy()
        poemPlayer.release()
    }

    /**
     * This method plays a poem defined by the parameter
     * If the player is already playing, it is paused
     *
     * @param resId An id of the raw poem file
     */
    private fun playPoem(resId: Int) {
        val assetFileDescriptor = this.resources.openRawResourceFd(resId) ?: return
        if (poemPlayer.isPlaying) {
            poemPlayer.pause()
        } else {
            poemPlayer.run {
                reset()
                setDataSource(assetFileDescriptor.fileDescriptor, assetFileDescriptor.startOffset, assetFileDescriptor.declaredLength)
                prepareAsync()
            }
        }
    }
}