package com.example.wildlifegym.animalactivities

import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import com.example.wildlifegym.MainActivity
import com.example.wildlifegym.R

/**
 * This is the Shadow activity
 * It lets the user play the shadow game
 */
class ShadowActivity : MainActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shadow)

        /**
         * Collect the argument passed as an intent from the Animal fragment
         * It contains the name of the "current" animal as String
         */
        val animal = intent.getStringExtra("animal")

        /** Hide the navigation bar */
        hideNavigationBar()

        /** Set up all the buttons available on this fragment */
        val buttonShadowBack = this.findViewById<ImageButton>(R.id.image_button_shadow_back)

        /**
         * Set up the onClickListeners for all of the buttons available
         *
         * buttonShadowBack navigates back to the Animal fragment
         */
        buttonShadowBack.setOnClickListener {
            makeSound("button")
            finish()
        }

        /** Start up the game */
        playShadowGame(animal!!)
    }

    /**
     * This method updates the answer button images according to the selected animal
     *
     * @param animal Name of the current animal as String
     */
    private fun updateShadowGame(animal: String, buttons: Array<ImageButton>) {
        for (i in 0..3) {
            buttons[i].setImageResource(resources.getIdentifier("shadow_button_${animal}_0${i+1}", "drawable", this.packageName))
        }
    }

    /**
     * This method sets up the playing environment
     * It sets up onClickListeners of all game buttons
     * It checks the correctness of the answer
     *
     * @param animal Name of the current animal as String
     */
    private fun playShadowGame(animal: String) {
        val buttons = arrayOf(findViewById<ImageButton>(R.id.image_button_shadow_01), findViewById(R.id.image_button_shadow_02), findViewById(R.id.image_button_shadow_03), findViewById(R.id.image_button_shadow_04))
        val rightAnswer = getRightAnswer(animal)
        updateShadowGame(animal, buttons)
        for(i in 0..3) {
            buttons[i].setOnClickListener {
                if (i == rightAnswer) {
                    Toast.makeText(applicationContext,"✅", Toast.LENGTH_SHORT).show()
                    makeSound("rightAnswer")
                    addPoints(animal, "shadow", 0,0,1,0)
                    finish()
                } else {
                    Toast.makeText(applicationContext,"❎", Toast.LENGTH_SHORT).show()
                    makeSound("wrongAnswer")
                    finish()
                }
            }
        }
    }

    /**
     * This method returns the right answer for this animal
     *
     * @param animal Name of the current animal as String
     * @return The index of the correct answer
     */
    private fun getRightAnswer(animal: String): Int {
        return when (animal) {
            "flamingo" -> 2
            "crocodile" -> 0
            "cancer" -> 0
            "penguin" -> 0
            "stork" -> 0
            "dolphin" -> 0
            else -> 0
        }
    }
}