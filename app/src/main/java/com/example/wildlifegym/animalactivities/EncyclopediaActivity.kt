package com.example.wildlifegym.animalactivities

import android.os.Bundle
import android.widget.ImageButton
import com.example.wildlifegym.MainActivity
import com.example.wildlifegym.R

/**
 * This is the Encyclopedia activity
 * It lets the user explore the items connected to the animal
 */
class EncyclopediaActivity : MainActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encyclopedia)

        /**
         * Collect the argument passed as an intent from the Animal fragment
         * It contains the name of the "current" animal as String
         */
        val animal = intent.getStringExtra("animal")

        /** Hide the navigation bar */
        hideNavigationBar()

        /** Set up all the buttons available on this fragment */
        val buttonMemoryBack = this.findViewById<ImageButton>(R.id.image_button_encyclopedia_back)

        /**
         * Set up the onClickListeners for all of the buttons available
         *
         * buttonMemoryBack navigates back to the Animal fragment
         */
        buttonMemoryBack.setOnClickListener {
            makeSound("button")
            finish()
        }

        /** Start up the game */
        playEncyclopedia(animal!!)
    }

    override fun onResume() {
        super.onResume()
        hideNavigationBar()
    }

    /**
     * This method returns an array of buttons specified by the current animal
     *
     * @param animal Name of the current animal as String
     * @return The array of buttons specified by the current animal
     */
    private fun getButtons(animal: String): Array <ImageButton> {
        val buttons = when (animal) {
            "flamingo" -> arrayOf(findViewById<ImageButton>(R.id.image_button_encyclopedia_flamingo_01), findViewById<ImageButton>(R.id.image_button_encyclopedia_flamingo_02), findViewById<ImageButton>(R.id.image_button_encyclopedia_flamingo_03), findViewById<ImageButton>(R.id.image_button_encyclopedia_flamingo_04))
            else -> arrayOf(findViewById<ImageButton>(R.id.image_button_encyclopedia_flamingo_01), findViewById<ImageButton>(R.id.image_button_encyclopedia_flamingo_02), findViewById<ImageButton>(R.id.image_button_encyclopedia_flamingo_03), findViewById<ImageButton>(R.id.image_button_encyclopedia_flamingo_04))
        }
        return buttons
    }

    /**
     * This method starts the encyclopedia interactive part
     *
     * @param animal Name of the current animal as String
     */
    private fun playEncyclopedia(animal: String) {
        val buttons = getButtons(animal)
        for(i in 0..3) {
            buttons[i].setOnClickListener {
                playPicture(animal, i)
            }
        }
    }

    /**
     * This method plays the corresponding sound of the clicked picture
     *
     * @param animal Name of the current animal as String
     * @param pictureNumber The number of picture in the image
     */
    private fun playPicture(animal: String, pictureNumber: Int) {
        val sounds = when (animal) {
            "flamingo" -> arrayOf(R.raw.flamingo_enc_04, R.raw.flamingo_enc_03, R.raw.flamingo_enc_01, R.raw.flamingo_enc_02)
            else -> arrayOf(R.raw.buttonclick, R.raw.buttonclick, R.raw.buttonclick, R.raw.buttonclick)
        }
        playSound(sounds[pictureNumber])
    }
}