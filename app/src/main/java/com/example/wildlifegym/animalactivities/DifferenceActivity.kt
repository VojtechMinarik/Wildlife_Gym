package com.example.wildlifegym.animalactivities

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.wildlifegym.MainActivity
import com.example.wildlifegym.R
import com.example.wildlifegym.utils.Animal
import com.example.wildlifegym.utils.AppDatabase

class DifferenceActivity : MainActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_difference)

        hideNavigationBar()

        val buttonmemoryback = this.findViewById<ImageButton>(R.id.image_button_memory_back)
        buttonmemoryback.setOnClickListener {
            makeSound("button")

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
                    makeSound("rightanswer")
                    addpoints(animal!!, "difference", 0,1,0,0)
                    finish()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        hideNavigationBar()
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