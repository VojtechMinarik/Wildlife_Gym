package com.example.wildlifegym.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.wildlifegym.activities.MainActivity
import com.example.wildlifegym.R
import com.example.wildlifegym.activities.*
import com.example.wildlifegym.utils.AppDatabase

/**
 * This is the Animal fragment
 * It is hosted by the main activity
 * It is "unique" for each animal
 * It lets the user choose the activity to play
 */
class AnimalFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /** Inflate the view for this fragment */
        val view = inflater.inflate(R.layout.fragment_animal, container, false)

        /**
         * Collect the argument passed as a bundle from the Menu fragment
         * It contains the name of the "current" animal as String
         */
        val animal = arguments?.getString("animal")

        /** Set up all the buttons available on this fragment */
        val buttonAnimalBack = view.findViewById<ImageButton>(R.id.image_button_animal_back)
        val buttonAnimalMemory = view.findViewById<ImageButton>(R.id.image_button_animal_memory)
        val buttonAnimalDifference = view.findViewById<ImageButton>(R.id.image_button_animal_difference)
        val buttonAnimalShadow = view.findViewById<ImageButton>(R.id.image_button_animal_shadow)
        val buttonAnimalQuiz = view.findViewById<ImageButton>(R.id.image_button_animal_quiz)
        val buttonAnimalVideo = view.findViewById<ImageButton>(R.id.image_button_animal_video)
        val buttonAnimalPoem = view.findViewById<ImageButton>(R.id.image_button_animal_poem)
        val buttonAnimalEncyclopedia = view.findViewById<ImageButton>(R.id.image_button_animal_encyclopedia)

        /** Hide the back button according to user preferences */
        (activity as MainActivity).hideButton(buttonAnimalBack)

        /** Update the appearance of buttons according to users progress */
        updateUI(animal!!)

        /**
         * Set up the onClickListeners for all of the buttons available
         *
         * buttonAnimalBack navigates back to the Menu fragment
         * buttonAnimalMemory checks if the Memory activity is available and starts it with the added animal name as intent
         * buttonAnimalDifference checks if the Difference activity is available and starts it with the added animal name as intent
         * buttonAnimalShadow checks if the Shadow activity is available and starts it with the added animal name as intent
         * buttonAnimalQuiz checks if the Quiz activity is available and starts it with the added animal name as intent
         * buttonAnimalVideo checks if the Video activity is available and starts it with the added animal name as intent
         * buttonAnimalPoem checks if the Poem activity is available and starts it with the added animal name as intent
         * buttonAnimalEncyclopedia checks if the Encyclopedia activity is available and starts it with the added animal name as intent
         */
        buttonAnimalBack.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            findNavController().navigate(R.id.action_animalFragment_to_menuFragment)
        }
        buttonAnimalMemory.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            Thread {
                if (AppDatabase.getDatabase(this.requireContext()).databaseDao().getResMemory(animal) != -1) {
                    activity?.runOnUiThread {
                        val intent = Intent(context, MemoryActivity::class.java)
                        intent.putExtra("animal", animal)
                        startActivity(intent)
                    }
                }
            }.start()
        }
        buttonAnimalDifference.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            Thread {
                if (AppDatabase.getDatabase(this.requireContext()).databaseDao().getResDifference(animal) != -1) {
                    activity?.runOnUiThread {
                        val intent = Intent(context, DifferenceActivity::class.java)
                        intent.putExtra("animal", animal)
                        startActivity(intent)
                    }
                }
            }.start()
        }
        buttonAnimalShadow.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            Thread {
                if (AppDatabase.getDatabase(this.requireContext()).databaseDao().getResShadow(animal) != -1) {
                    activity?.runOnUiThread {
                        val intent = Intent(context, ShadowActivity::class.java)
                        intent.putExtra("animal", animal)
                        startActivity(intent)
                    }
                }
            }.start()
        }
        buttonAnimalQuiz.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            Thread {
                if (AppDatabase.getDatabase(this.requireContext()).databaseDao().getResQuiz(animal) != -1) {
                    activity?.runOnUiThread {
                        val intent = Intent(context, QuizActivity::class.java)
                        intent.putExtra("animal", animal)
                        startActivity(intent)
                    }
                }
            }.start()
        }
        buttonAnimalVideo.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            Thread {
                if (AppDatabase.getDatabase(this.requireContext()).databaseDao().getResVideo(animal) != -1) {
                    activity?.runOnUiThread {
                        val intent = Intent(context, VideoActivity::class.java)
                        intent.putExtra("animal", animal)
                        startActivity(intent)
                    }
                }
            }.start()
        }
        buttonAnimalPoem.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            Thread {
                if (AppDatabase.getDatabase(this.requireContext()).databaseDao().getResPoem(animal) != -1) {
                    activity?.runOnUiThread {
                        val intent = Intent(context, PoemActivity::class.java)
                        intent.putExtra("animal", animal)
                        startActivity(intent)
                    }
                }
            }.start()
        }
        buttonAnimalEncyclopedia.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            Thread {
                if (AppDatabase.getDatabase(this.requireContext()).databaseDao().getResEncyclopedia(animal) != -1) {
                    activity?.runOnUiThread {
                        val intent = Intent(context, EncyclopediaActivity::class.java)
                        intent.putExtra("animal", animal)
                        startActivity(intent)
                    }
                }
            }.start()
        }
        return view
    }

    /**
     * This method makes sure, that the UI is updated after the user finishes a game
     * This way, the results are visible without the need to restart this activity
     */
    override fun onResume() {
        super.onResume()

        val animal = arguments?.getString("animal")

        updateUI(animal!!)
    }

    /**
     * This method updates the UI of this activity according to the progress saved in the Animal database
     * It checks the state of each activity and changes the appearance of buttons and animal picture according to the results
     *
     * @param animal Name of current animal as a String
     */
    private fun updateUI(animal: String) {
        val pictureAnimal = view?.findViewById<ImageView>(R.id.image_view_animal)
        val buttonAnimalMemory = view?.findViewById<ImageButton>(R.id.image_button_animal_memory)
        val buttonAnimalDifference = view?.findViewById<ImageButton>(R.id.image_button_animal_difference)
        val buttonAnimalShadow = view?.findViewById<ImageButton>(R.id.image_button_animal_shadow)
        val buttonAnimalQuiz = view?.findViewById<ImageButton>(R.id.image_button_animal_quiz)
        val buttonAnimalVideo = view?.findViewById<ImageButton>(R.id.image_button_animal_video)
        val buttonAnimalPoem = view?.findViewById<ImageButton>(R.id.image_button_animal_poem)
        val buttonAnimalEncyclopedia = view?.findViewById<ImageButton>(R.id.image_button_animal_encyclopedia)

        Thread {
            val db = AppDatabase.getDatabase(this.requireContext())
            val animalPoints = db.databaseDao().getPoints(animal)
            val animalMemory = db.databaseDao().getResMemory(animal)
            val animalDifference = db.databaseDao().getResDifference(animal)
            val animalShadow = db.databaseDao().getResShadow(animal)
            val animalQuiz = db.databaseDao().getResQuiz(animal)
            val animalVideo = db.databaseDao().getResVideo(animal)
            val animalPoem = db.databaseDao().getResPoem(animal)
            val animalEncyclopedia = db.databaseDao().getResEncyclopedia(animal)

            activity?.runOnUiThread {
                pictureAnimal?.setImageResource(resources.getIdentifier("animal_picture_${animal}_0${animalPoints}", "drawable", context?.packageName))

                if (animalVideo <= 0) {
                    buttonAnimalVideo?.setImageResource(resources.getIdentifier("animal_button_video_off", "drawable", context?.packageName))
                } else {
                    buttonAnimalVideo?.setImageResource(resources.getIdentifier("animal_button_video", "drawable", context?.packageName))
                }

                if (animalPoem <= 0) {
                    buttonAnimalPoem?.setImageResource(resources.getIdentifier("animal_button_poem_off", "drawable", context?.packageName))
                } else {
                    buttonAnimalPoem?.setImageResource(resources.getIdentifier("animal_button_poem", "drawable", context?.packageName))
                }

                if (animalMemory <= 0) {
                    buttonAnimalMemory?.setImageResource(resources.getIdentifier("animal_button_memory_off", "drawable", context?.packageName))
                } else {
                    buttonAnimalMemory?.setImageResource(resources.getIdentifier("animal_button_memory", "drawable", context?.packageName))
                }

                if (animalDifference <= 0) {
                    buttonAnimalDifference?.setImageResource(resources.getIdentifier("animal_button_difference_off", "drawable", context?.packageName))
                } else {
                    buttonAnimalDifference?.setImageResource(resources.getIdentifier("animal_button_difference", "drawable", context?.packageName))
                }

                if (animalShadow <= 0) {
                    buttonAnimalShadow?.setImageResource(resources.getIdentifier("animal_button_shadow_off", "drawable", context?.packageName))
                } else {
                    buttonAnimalShadow?.setImageResource(resources.getIdentifier("animal_button_shadow", "drawable", context?.packageName))
                }

                if (animalQuiz <= 0) {
                    buttonAnimalQuiz?.setImageResource(resources.getIdentifier("animal_button_quiz_off", "drawable", context?.packageName))
                } else {
                    buttonAnimalQuiz?.setImageResource(resources.getIdentifier("animal_button_quiz", "drawable", context?.packageName))
                }

                if (animalEncyclopedia <= 0) {
                    buttonAnimalEncyclopedia?.setImageResource(resources.getIdentifier("animal_button_encyclopedia_off", "drawable", context?.packageName))
                } else {
                    buttonAnimalEncyclopedia?.setImageResource(resources.getIdentifier("animal_button_encyclopedia", "drawable", context?.packageName))
                }
            }
        }.start()
    }
}