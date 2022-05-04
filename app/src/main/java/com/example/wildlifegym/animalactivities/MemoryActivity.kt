package com.example.wildlifegym.animalactivities

import android.os.Build
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.wildlifegym.MainActivity
import com.example.wildlifegym.R
import com.example.wildlifegym.utils.Animal
import com.example.wildlifegym.utils.AppDatabase

@RequiresApi(Build.VERSION_CODES.R)
class MemoryActivity : MainActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory)

        hideSystemUI()

        val buttonmemoryback = this.findViewById<ImageButton>(R.id.image_button_memory_back)
        buttonmemoryback.setOnClickListener {
            ButtonSound()

            finish()
        }

        val animal = intent.getStringExtra("animal")

        val images: MutableList<Int> =
            mutableListOf(
                R.drawable.memory_button_card_flamingo_01,
                R.drawable.memory_button_card_flamingo_02,
                R.drawable.memory_button_card_flamingo_03,
                R.drawable.memory_button_card_flamingo_04,
                R.drawable.memory_button_card_flamingo_05,
                R.drawable.memory_button_card_flamingo_06,
                R.drawable.memory_button_card_flamingo_01,
                R.drawable.memory_button_card_flamingo_02,
                R.drawable.memory_button_card_flamingo_03,
                R.drawable.memory_button_card_flamingo_04,
                R.drawable.memory_button_card_flamingo_05,
                R.drawable.memory_button_card_flamingo_06,)

        val buttons = arrayOf(findViewById<ImageButton>(R.id.image_button_poem_01), findViewById(R.id.image_button_poem_02), findViewById(R.id.image_button_poem_03), findViewById(R.id.image_button_poem_04), findViewById(R.id.image_button_poem_05), findViewById(R.id.image_button_poem_06), findViewById(R.id.image_button_poem_07), findViewById(R.id.image_button_poem_08), findViewById(R.id.image_button_poem_09), findViewById(R.id.image_button_poem_10), findViewById(R.id.image_button_poem_11), findViewById(R.id.image_button_poem_12))

        val cardBack = R.drawable.memory_button_card_back
        var clicked = 0
        var turnOver = false
        var lastClicked = -1
        var finished = false

        images.shuffle()
        for(i in 0..11){
            buttons[i].setImageResource(cardBack)
            buttons[i].contentDescription = "cardBack"
            buttons[i].setOnClickListener {
                ButtonSound()

                if (buttons[i].contentDescription == "cardBack" && !turnOver) {
                    buttons[i].setImageResource(images[i])
                    buttons[i].contentDescription = images[i].toString()
                    if (clicked == 0) {
                        lastClicked = i
                    }
                    clicked++
                } else if (buttons[i].contentDescription !in "cardBack" && turnOver) {
                    buttons[i].setImageResource(cardBack)
                    buttons[i].contentDescription = "cardBack"
                    clicked--
                }

                if (clicked == 2) {
                    turnOver = true
                    if (buttons[i].contentDescription == buttons[lastClicked].contentDescription) {
                        buttons[i].isClickable = false
                        buttons[lastClicked].isClickable = false
                        turnOver = false
                        clicked = 0
                    }
                } else if (clicked == 0) {
                    turnOver = false
                }

                finished = true
                buttons.forEach {
                    if (it.contentDescription == "cardBack") {
                        finished = false
                    }
                }
                if (finished) {
                    Toast.makeText(applicationContext,"Hotovo!", Toast.LENGTH_SHORT).show()
                    addpoints(animal!!, "memory", 1,0,0,0)
                    finish()
                }
            }
        }
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