package com.example.wildlifegym.animalactivities

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.MediaController
import android.widget.VideoView
import androidx.annotation.RequiresApi
import com.example.wildlifegym.MainActivity
import com.example.wildlifegym.R

@RequiresApi(Build.VERSION_CODES.R)
class VideoActivity : MainActivity() {

    // declaring a null variable for VideoView
    var simpleVideoView: VideoView? = null

    // declaring a null variable for MediaController
    var mediaControls: MediaController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        hideSystemUI()

        val buttonaboutback = this.findViewById<ImageButton>(R.id.image_button_about_back)
        buttonaboutback.setOnClickListener {
            ButtonSound()

            finish()
        }

        val animal = intent.getStringExtra("animal")

        // assigning id of VideoView from
        // activity_main.xml layout file
        simpleVideoView = findViewById<View>(R.id.video_view_video) as VideoView

        if (mediaControls == null) {
            // creating an object of media controller class
            mediaControls = MediaController(this)

            // set the anchor view for the video view
            mediaControls!!.setAnchorView(this.simpleVideoView)
        }

        // set the media controller for video view
        //simpleVideoView!!.setMediaController(mediaControls)

        // set the absolute path of the video file which is going to be played
        when (animal) {
            "crocodile" -> simpleVideoView!!.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.crocodile))
            "cancer" -> simpleVideoView!!.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.cancer))
            "stork" -> simpleVideoView!!.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.stork))
            "dolphin" -> simpleVideoView!!.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.dolphin))
            "flamengo" -> simpleVideoView!!.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.flamengo))
            "penguin" -> simpleVideoView!!.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.penguin))
            else -> {
                simpleVideoView!!.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.crocodile))
            }
        }

        simpleVideoView!!.requestFocus()

        // starting the video
        simpleVideoView!!.start()

        hideSystemUI()

    }

    override fun onDestroy() {
        super.onDestroy()
        simpleVideoView!!.suspend()
    }
}