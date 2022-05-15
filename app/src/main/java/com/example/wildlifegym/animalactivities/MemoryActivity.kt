package com.example.wildlifegym.animalactivities

import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import com.example.wildlifegym.MainActivity
import com.example.wildlifegym.R

/**
 * This is the Memory activity
 * It lets the user play the memory game
 */
class MemoryActivity : MainActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory)

        /**
         * Collect the argument passed as an intent from the Animal fragment
         * It contains the name of the "current" animal as String
         */
        val animal = intent.getStringExtra("animal")

        /** Hide the navigation bar */
        hideNavigationBar()

        /** Set up all the buttons available on this fragment */
        val buttonMemoryBack = this.findViewById<ImageButton>(R.id.image_button_memory_back)

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
        playMemoryGame(animal!!)
    }

    /**
     * This method returns a list of button images specified by the current animal
     *
     * @param animal Name of the current animal as String
     * @return The mutable list of button images specified by the current animal
     */
    private fun getCards(animal: String): MutableList<Int> {
        val cards = when (animal) {
            "flamingo" -> mutableListOf(R.drawable.memory_button_card_flamingo_01, R.drawable.memory_button_card_flamingo_02, R.drawable.memory_button_card_flamingo_03, R.drawable.memory_button_card_flamingo_04, R.drawable.memory_button_card_flamingo_05, R.drawable.memory_button_card_flamingo_06, R.drawable.memory_button_card_flamingo_01, R.drawable.memory_button_card_flamingo_02, R.drawable.memory_button_card_flamingo_03, R.drawable.memory_button_card_flamingo_04, R.drawable.memory_button_card_flamingo_05, R.drawable.memory_button_card_flamingo_06)
            else -> mutableListOf(R.drawable.memory_button_card_flamingo_01, R.drawable.memory_button_card_flamingo_02, R.drawable.memory_button_card_flamingo_03, R.drawable.memory_button_card_flamingo_04, R.drawable.memory_button_card_flamingo_05, R.drawable.memory_button_card_flamingo_06, R.drawable.memory_button_card_flamingo_01, R.drawable.memory_button_card_flamingo_02, R.drawable.memory_button_card_flamingo_03, R.drawable.memory_button_card_flamingo_04, R.drawable.memory_button_card_flamingo_05, R.drawable.memory_button_card_flamingo_06)
        }
        return cards
    }

    /**
     * This method sets up the playing environment
     * It sets up onClickListeners for all buttons
     * If two cards are flipped, the pictures are compared
     * In case of same pictures the user can continue, otherwise he needs to try again
     *
     * @param animal Name of the current animal as String
     */
    private fun playMemoryGame(animal: String) {
        val images = getCards(animal)
        val cardBack = R.drawable.memory_button_card_back
        val buttons = arrayOf(findViewById<ImageButton>(R.id.image_button_poem_01), findViewById(R.id.image_button_poem_02), findViewById(R.id.image_button_poem_03), findViewById(R.id.image_button_poem_04), findViewById(R.id.image_button_poem_05), findViewById(R.id.image_button_poem_06), findViewById(R.id.image_button_poem_07), findViewById(R.id.image_button_poem_08), findViewById(R.id.image_button_poem_09), findViewById(R.id.image_button_poem_10), findViewById(R.id.image_button_poem_11), findViewById(R.id.image_button_poem_12))

        var clicked = 0; var turnOver = false; var lastClicked = -1; var finished = false

        /** Set up onClickListeners for all of the card buttons */
        images.shuffle()
        for(i in 0..11){
            buttons[i].setImageResource(cardBack)
            buttons[i].contentDescription = "cardBack"
            buttons[i].setOnClickListener {
                makeSound("button")
                /** Card is turned */
                if (buttons[i].contentDescription == "cardBack" && !turnOver) {
                    buttons[i].setImageResource(images[i])
                    buttons[i].contentDescription = images[i].toString()
                    if (clicked == 0) {
                        lastClicked = i
                    }
                    clicked++
                /** Card is turned back */
                } else if (buttons[i].contentDescription !in "cardBack" && turnOver) {
                    buttons[i].setImageResource(cardBack)
                    buttons[i].contentDescription = "cardBack"
                    clicked--
                }
                /** Two cards are turned */
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
                /** Check if all cards are already turned */
                finished = true
                buttons.forEach {
                    if (it.contentDescription == "cardBack") {
                        finished = false
                    }
                }
                /** Finish the game if all pairs are found */
                if (finished) {
                    Toast.makeText(applicationContext,"✅", Toast.LENGTH_SHORT).show()
                    makeSound("rightanswer")
                    addPoints(animal, "memory", 1,0,0,0)
                    finish()
                }
            }
        }
    }
}