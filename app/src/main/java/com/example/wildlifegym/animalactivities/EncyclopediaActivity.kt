package com.example.wildlifegym.animalactivities

import android.os.Build
import android.os.Bundle
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import com.example.wildlifegym.MainActivity
import com.example.wildlifegym.R

@RequiresApi(Build.VERSION_CODES.R)
class EncyclopediaActivity : MainActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encyclopedia)

        //requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        hideSystemUI()

        val buttonmemoryback = this.findViewById<ImageButton>(R.id.image_button_memory_back)
        buttonmemoryback.setOnClickListener {
            ButtonSound()

            finish()
        }

        val animal = intent.getStringExtra("animal")
    }
}