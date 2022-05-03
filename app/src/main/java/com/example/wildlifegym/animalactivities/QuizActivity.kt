package com.example.wildlifegym.animalactivities

import android.os.Build
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.wildlifegym.MainActivity
import com.example.wildlifegym.R

@RequiresApi(Build.VERSION_CODES.R)
class QuizActivity : MainActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        hideSystemUI()

        val buttonmemoryback = this.findViewById<ImageButton>(R.id.image_button_memory_back)
        buttonmemoryback.setOnClickListener {
            ButtonSound()
            finish()
        }

        val animal = intent.getStringExtra("animal")

        val buttons = arrayOf(findViewById<ImageButton>(R.id.image_button_quiz_01), findViewById(R.id.image_button_quiz_02))

        var round = 0

        val answers = arrayOf(1, 0, 0)

        for(i in 0..1) {
            buttons[i].setOnClickListener {
                if (i == answers[round]) {
                    if (round == 2) {
                        Toast.makeText(applicationContext,"Hotovo!", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        round++
                        changeQuestion("flamingo", round)
                    }
                } else {
                    Toast.makeText(applicationContext,"Špatně!", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }

    private fun changeQuestion (name: String, round: Int) {
        val picture = findViewById<ImageView>(R.id.image_view_quiz)
        val button01 = findViewById<ImageButton>(R.id.image_button_quiz_01)
        val button02 = findViewById<ImageButton>(R.id.image_button_quiz_02)

        val roundplus = round+1

        picture.setImageResource(resources.getIdentifier("quiz_picture_flamingo_0${roundplus}", "drawable", this.packageName))
        button01.setImageResource(resources.getIdentifier("quiz_button_flamingo_0${roundplus}_01", "drawable", this.packageName))
        button02.setImageResource(resources.getIdentifier("quiz_button_flamingo_0${roundplus}_02", "drawable", this.packageName))
    }
}