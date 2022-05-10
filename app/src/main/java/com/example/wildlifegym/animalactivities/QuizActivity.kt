package com.example.wildlifegym.animalactivities

import android.os.Build
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.wildlifegym.MainActivity
import com.example.wildlifegym.R
import com.example.wildlifegym.utils.Animal
import com.example.wildlifegym.utils.AppDatabase

@RequiresApi(Build.VERSION_CODES.R)
class QuizActivity : MainActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        hideSystemUI()

        val buttonmemoryback = this.findViewById<ImageButton>(R.id.image_button_memory_back)
        buttonmemoryback.setOnClickListener {
            makeSound("button")
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
                        addpoints(animal!!, "quiz", 0,0,0,1)
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

    private fun addpoints(animal: String, currgame: String, pointmemory: Int, pointdifference: Int, pointshadow: Int, pointquiz: Int) {
        Thread {
            val db = AppDatabase.getDatabase(this)
            var currpoints = 0
            when (currgame) {
                "memory" -> currpoints = db.databaseDao().getResMemory(animal)
                "difference" -> currpoints = db.databaseDao().getResDifference(animal)
                "shadow" -> currpoints = db.databaseDao().getResShadow(animal)
                "quiz" -> currpoints = db.databaseDao().getResQuiz(animal)
            }
            if (currpoints == 0) {
                db.databaseDao().updateAnimal(Animal(db.databaseDao().getId(animal), animal, db.databaseDao().getPoints(animal) + 1, db.databaseDao().getResQuiz(animal) + pointquiz, db.databaseDao().getResShadow(animal) + pointshadow, db.databaseDao().getResDifference(animal) + pointdifference, db.databaseDao().getResMemory(animal) + pointmemory))
            }
        }.start()
    }
}