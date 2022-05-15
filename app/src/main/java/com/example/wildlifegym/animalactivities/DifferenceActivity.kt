package com.example.wildlifegym.animalactivities

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import com.example.wildlifegym.MainActivity
import com.example.wildlifegym.R

/**
 * This is the Difference activity
 * It lets the user play the difference game
 */
class DifferenceActivity : MainActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_difference)

        /**
         * Collect the argument passed as an intent from the Animal fragment
         * It contains the name of the "current" animal as String
         */
        val animal = intent.getStringExtra("animal")

        /** Hide the navigation bar */
        hideNavigationBar()

        /** Set up all the buttons available on this fragment */
        val buttonDifferenceBack = this.findViewById<ImageButton>(R.id.image_button_difference_back)

        /**
         * Set up the onClickListeners for all of the buttons available
         *
         * buttonDifferenceBack navigates back to the Animal fragment
         */
        buttonDifferenceBack.setOnClickListener {
            makeSound("button")
            finish()
        }

        /** Update the game interface */
        updateDifferenceGame(animal!!)

        /** Start up the game */
        playDifferenceGame(animal)
    }

    /**
     * This method hides the navigation bar after screen rotation
     */
    override fun onResume() {
        super.onResume()
        hideNavigationBar()
    }

    /**
     * This method updates the UI according to the currently selected animal
     * It updates the image used in both pictures
     * It changes the visibility of selected buttons
     *
     * @param animal Name of the current animal as String
     */
    private fun updateDifferenceGame(animal: String) {
        val pictureOne = findViewById<ImageView>(R.id.imageView1)
        val pictureTwo = findViewById<ImageView>(R.id.imageView2)
        val buttons = getButtons(animal)
        pictureOne.setImageResource(resources.getIdentifier("difference_picture_${animal}", "drawable", this.packageName))
        pictureTwo.setImageResource(resources.getIdentifier("difference_picture_${animal}", "drawable", this.packageName))
        for (i in 0..4) {
            buttons[i].visibility = View.VISIBLE
        }
    }

    /**
     * This method sets up the playing environment
     * It sets up onClickListeners for all buttons
     * If the difference is found, it disappears
     *
     * @param animal Name of the current animal as String
     */
    private fun playDifferenceGame(animal: String) {
        val buttons = getButtons(animal)
        var complete = 0
        for(i in 0..4) {
            buttons[i].setOnClickListener {
                buttons[i].visibility = View.INVISIBLE
                complete += 1
                if (complete > 4) {
                    Toast.makeText(applicationContext,"✅", Toast.LENGTH_SHORT).show()
                    makeSound("rightAnswer")
                    addPoints(animal, "difference", 0,1,0,0)
                    finish()
                }
            }
        }
    }

    /**
     * This method returns an array of buttons specified by the current animal
     *
     * @param animal Name of the current animal as String
     * @return The array of buttons specified by the current animal
     */
    private fun getButtons(animal: String): Array <ImageButton> {
        val buttons = when (animal) {
            "flamingo" -> arrayOf(findViewById<ImageButton>(R.id.image_button_difference_flamingo_01), findViewById<ImageButton>(R.id.image_button_difference_flamingo_02), findViewById<ImageButton>(R.id.image_button_difference_flamingo_03), findViewById<ImageButton>(R.id.image_button_difference_flamingo_04), findViewById<ImageButton>(R.id.image_button_difference_flamingo_05))
            else -> arrayOf(findViewById<ImageButton>(R.id.image_button_difference_flamingo_01), findViewById<ImageButton>(R.id.image_button_difference_flamingo_02), findViewById<ImageButton>(R.id.image_button_difference_flamingo_03), findViewById<ImageButton>(R.id.image_button_difference_flamingo_04), findViewById<ImageButton>(R.id.image_button_difference_flamingo_05))
        }
        return buttons
    }
}