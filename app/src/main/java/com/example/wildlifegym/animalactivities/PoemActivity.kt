package com.example.wildlifegym.animalactivities

import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import com.example.wildlifegym.MainActivity
import com.example.wildlifegym.R

@RequiresApi(Build.VERSION_CODES.R)
class PoemActivity : MainActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poem)

        hideSystemUI()

        val buttonpoemback = this.findViewById<ImageButton>(R.id.image_button_poem_back)
        buttonpoemback.setOnClickListener {
            ButtonSound()

            finish()
        }

        val animal = intent.getStringExtra("animal")

        val buttonpoemplay = this.findViewById<ImageButton>(R.id.image_button_poem_play)
        buttonpoemplay.setOnClickListener {
            ButtonSound()

            when (animal) {
                "crocodile" -> playPoem(R.raw.crocodilemp3)
                "cancer" -> playPoem(R.raw.cancermp3)
                "stork" -> playPoem(R.raw.storkmp3)
                "dolphin" -> playPoem(R.raw.dolphinmp3)
                "flamengo" -> playPoem(R.raw.flamengomp3)
                "penguin" -> playPoem(R.raw.penguinmp3)
                else -> {
                    playPoem(R.raw.crocodilemp3)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        poemPlayer.release()
    }

    private val poemPlayer = MediaPlayer().apply {
        setOnPreparedListener { start() }
        setOnCompletionListener { reset() }
    }

    private fun playPoem(rawResId: Int) {
        val assetFileDescriptor = this.resources.openRawResourceFd(rawResId) ?: return
        poemPlayer.run {
            reset()
            setDataSource(assetFileDescriptor.fileDescriptor, assetFileDescriptor.startOffset, assetFileDescriptor.declaredLength)
            prepareAsync()
        }
    }
}