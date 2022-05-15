package com.example.wildlifegym.activities

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import com.example.wildlifegym.R

/**
 * This is the Quiz activity
 * It lets the user play the quiz game
 */
class QuizActivity : MainActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        /**
         * Collect the argument passed as an intent from the Animal fragment
         * It contains the name of the "current" animal as String
         */
        val animal = intent.getStringExtra("animal")

        /** Hide the navigation bar */
        hideNavigationBar()

        /** Set up all the buttons available on this fragment */
        val buttonQuizBack = this.findViewById<ImageButton>(R.id.image_button_quiz_back)

        /**
         * Set up the onClickListeners for all of the buttons available
         *
         * buttonQuizBack navigates back to the Animal fragment
         */
        buttonQuizBack.setOnClickListener {
            makeSound("button")
            finish()
        }

        /** Start up the game */
        playQuizGame(animal!!)
    }

    /**
     * This method sets up the playing environment
     * It sets up onClickListeners of all game buttons
     * It checks the correctness of the answer and updates the UI to a new question
     *
     * @param animal Name of the current animal as String
     */
    private fun playQuizGame(animal: String) {
        val buttons = arrayOf(findViewById<ImageButton>(R.id.image_button_quiz_01), findViewById(R.id.image_button_quiz_02))
        var round = 0
        val answers = getRightAnswers(animal)
        for(i in 0..1) {
            buttons[i].setOnClickListener {
                if (i == answers[round]) {
                    if (round == 2) {
                        Toast.makeText(applicationContext,"✅", Toast.LENGTH_SHORT).show()
                        makeSound("rightAnswer")
                        addPoints(animal, "quiz", 0,0,0,1)
                        finish()
                    } else {
                        round++
                        makeSound("rightAnswer")
                        changeQuestion(animal, round)
                    }
                } else {
                    Toast.makeText(applicationContext,"❎", Toast.LENGTH_SHORT).show()
                    makeSound("wrongAnswer")
                    finish()
                }
            }
        }
    }

    /**
     * This method sets the UI to match current question
     *
     * @param animal Name of the current animal as String
     * @param round Current round of quiz
     */
    private fun changeQuestion (animal: String, round: Int) {
        val picture = findViewById<ImageView>(R.id.image_view_quiz)
        val button01 = findViewById<ImageButton>(R.id.image_button_quiz_01)
        val button02 = findViewById<ImageButton>(R.id.image_button_quiz_02)
        val roundPlus = round+1
        picture.setImageResource(resources.getIdentifier("quiz_picture_${animal}_0${roundPlus}", "drawable", this.packageName))
        button01.setImageResource(resources.getIdentifier("quiz_button_${animal}_0${roundPlus}_01", "drawable", this.packageName))
        button02.setImageResource(resources.getIdentifier("quiz_button_${animal}_0${roundPlus}_02", "drawable", this.packageName))
    }

    /**
     * This method returns the list of right answers for this animal
     *
     * @param animal Name of the current animal as String
     * @return The array of three correct answers
     */
    private fun getRightAnswers(animal: String): Array<Int> {
        return when (animal) {
            "flamingo" -> arrayOf(1, 0, 0)
            "crocodile" -> arrayOf(0, 0, 0)
            "cancer" -> arrayOf(0, 0, 0)
            "penguin" -> arrayOf(0, 0, 0)
            "stork" -> arrayOf(0, 0, 0)
            "dolphin" -> arrayOf(0, 0, 0)
            else -> arrayOf(0, 0, 0)
        }
    }
}