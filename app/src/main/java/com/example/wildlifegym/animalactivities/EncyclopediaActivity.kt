package com.example.wildlifegym.animalactivities

import android.os.Build
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.wildlifegym.MainActivity
import com.example.wildlifegym.R

@RequiresApi(Build.VERSION_CODES.R)
class EncyclopediaActivity : MainActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encyclopedia)

        hideSystemUI()

        val buttonmemoryback = this.findViewById<ImageButton>(R.id.image_button_memory_back)
        buttonmemoryback.setOnClickListener {
            makeSound("button")

            finish()
        }

        val animal = intent.getStringExtra("animal")

        val buttons = arrayOf(findViewById<ImageButton>(R.id.image_button_encyclopedia_01), findViewById(R.id.image_button_encyclopedia_02), findViewById(R.id.image_button_encyclopedia_03), findViewById(R.id.image_button_encyclopedia_04))

        for(i in 0..3) {
            buttons[i].setOnClickListener {
                Toast.makeText(applicationContext,"Stisknuto!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onResume() {
        super.onResume()
        hideSystemUI()
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onStart() {
        super.onStart()
        hideSystemUI()
    }

}