package com.example.wildlifegym.animalactivities

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.VideoView
import com.example.wildlifegym.MainActivity
import com.example.wildlifegym.R

/**
 * This is the Poem activity
 * It lets the user play the poem for the currently selected animal
 */
class VideoActivity : MainActivity() {

    /** Initialize the videoView */
    var simpleVideoView: VideoView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        /** Hide the navigation bar */
        hideNavigationBar()

        /**
         * Collect the argument passed as an intent from the Animal fragment
         * It contains the name of the "current" animal as String
         */
        val animal = intent.getStringExtra("animal")

        /** Set up all the buttons available on this fragment */
        val buttonAboutBack = this.findViewById<ImageButton>(R.id.image_button_about_back)

        /** Play the video with the current animal */
        playVideo(animal!!)

        /**
         * Set up the onClickListeners for all of the buttons available
         *
         * buttonAboutBack navigates back to the Animal fragment
         */
        buttonAboutBack.setOnClickListener {
            makeSound("button")
            finish()
        }
    }

    /**
     * This method makes sure the player is released after closing this activity
     */
    override fun onDestroy() {
        super.onDestroy()
        simpleVideoView!!.suspend()
    }

    /**
     * This method plays the video of animal defined in parameters using the simpleVideoView
     *
     * @param animal Name of the current animal as String
     */
    private fun playVideo(animal: String) {
        simpleVideoView = findViewById<View>(R.id.video_view_video) as VideoView
        simpleVideoView!!.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + resources.getIdentifier(animal, "raw", this.packageName)))
        simpleVideoView!!.requestFocus()
        simpleVideoView!!.start()
    }
}