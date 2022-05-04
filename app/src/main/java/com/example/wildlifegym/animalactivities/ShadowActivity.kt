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

@RequiresApi(Build.VERSION_CODES.R)
class ShadowActivity : MainActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shadow)

        hideSystemUI()

        val buttonmemoryback = this.findViewById<ImageButton>(R.id.image_button_memory_back)
        buttonmemoryback.setOnClickListener {
            ButtonSound()

            finish()
        }

        val animal = intent.getStringExtra("animal")

        val buttons = arrayOf(findViewById<ImageButton>(R.id.image_button_shadow_01), findViewById(R.id.image_button_shadow_02), findViewById(R.id.image_button_shadow_03), findViewById(R.id.image_button_shadow_04))

        for(i in 0..3) {
            buttons[i].setOnClickListener {
                if (i == 2) {
                    Toast.makeText(applicationContext,"Hotovo!", Toast.LENGTH_SHORT).show()
                    addpoints(animal!!, "shadow", 0,0,1,0)
                    finish()
                } else {
                    Toast.makeText(applicationContext,"Špatně!", Toast.LENGTH_SHORT).show()
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