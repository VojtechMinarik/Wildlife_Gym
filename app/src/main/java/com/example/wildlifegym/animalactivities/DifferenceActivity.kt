package com.example.wildlifegym.animalactivities

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.wildlifegym.MainActivity
import com.example.wildlifegym.R

@RequiresApi(Build.VERSION_CODES.R)
class DifferenceActivity : MainActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_difference)

        hideSystemUI()

        val buttonmemoryback = this.findViewById<ImageButton>(R.id.image_button_memory_back)
        buttonmemoryback.setOnClickListener {
            ButtonSound()

            finish()
        }

        val animal = intent.getStringExtra("animal")

        val buttons = arrayOf(findViewById<ImageButton>(R.id.image_button_difference_01), findViewById(R.id.image_button_difference_02), findViewById(R.id.image_button_difference_03), findViewById(R.id.image_button_difference_04), findViewById(R.id.image_button_difference_05))

        var complete = 0

        for(i in 0..4) {
            buttons[i].setOnClickListener {
                buttons[i].visibility = View.INVISIBLE
                complete += 1

                if (complete > 4) {
                    Toast.makeText(applicationContext,"Hotovo!", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }


        }
    }
}