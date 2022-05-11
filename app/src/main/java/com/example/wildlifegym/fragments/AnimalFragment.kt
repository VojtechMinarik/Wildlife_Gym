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
import com.example.wildlifegym.MainActivity
import com.example.wildlifegym.R
import com.example.wildlifegym.animalactivities.*
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

        /**
         * Set up the onClickListeners for all of the buttons available
         *
         * buttonAnimalBack navigates back to the Menu Fragment
         * buttonAnimalMemory
         * buttonAnimalDifference
         * buttonAnimalShadow
         * buttonAnimalQuiz
         * buttonAnimalVideo
         * buttonAnimalPoem
         * buttonAnimalEncyclopedia
         */
        buttonAnimalBack.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            findNavController().navigate(R.id.action_animalFragment_to_menuFragment)
        }
        buttonAnimalMemory.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            val intent = Intent(context, MemoryActivity::class.java)
            intent.putExtra("animal", animal)
            startActivity(intent)
        }
        buttonAnimalDifference.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            val intent = Intent(context, DifferenceActivity::class.java)
            intent.putExtra("animal", animal)
            startActivity(intent)
        }
        buttonAnimalShadow.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            val intent = Intent(context, ShadowActivity::class.java)
            intent.putExtra("animal", animal)
            startActivity(intent)
        }
        buttonAnimalQuiz.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            val intent = Intent(context, QuizActivity::class.java)
            intent.putExtra("animal", animal)
            startActivity(intent)
        }
        buttonAnimalVideo.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            val intent = Intent(context, VideoActivity::class.java)
            intent.putExtra("animal", animal)
            startActivity(intent)
        }
        buttonAnimalPoem.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            val intent = Intent(context, PoemActivity::class.java)
            intent.putExtra("animal", animal)
            startActivity(intent)
        }
        buttonAnimalEncyclopedia.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            val intent = Intent(context, EncyclopediaActivity::class.java)
            intent.putExtra("animal", animal)
            startActivity(intent)
        }
        return view
    }

    override fun onResume() {
        super.onResume()

        val animal = arguments?.getString("animal")
        val imageanimalview = view?.findViewById<ImageView>(R.id.image_view_animal)

        Thread {
            val db = AppDatabase.getDatabase(this.requireContext())

            val points = db.databaseDao().getPoints(animal!!)

            val animalMemory = db.databaseDao().getResMemory(animal)
            val animalDifference = db.databaseDao().getResDifference(animal)
            val animalShadow = db.databaseDao().getResShadow(animal)
            val animalQuiz = db.databaseDao().getResQuiz(animal)

            activity?.runOnUiThread {
                when (animal) {
                    "crocodile" ->
                        imageanimalview?.setImageResource(resources.getIdentifier("animal_picture_crocodile_0$points", "drawable", context?.packageName))
                    "stork" ->
                        imageanimalview?.setImageResource(resources.getIdentifier("animal_picture_stork_0$points", "drawable", context?.packageName))
                    "cancer" ->
                        imageanimalview?.setImageResource(resources.getIdentifier("animal_picture_cancer_0$points", "drawable", context?.packageName))
                    "penguin" ->
                        imageanimalview?.setImageResource(resources.getIdentifier("animal_picture_penguin_0$points", "drawable", context?.packageName))
                    "flamengo" ->
                        imageanimalview?.setImageResource(resources.getIdentifier("animal_picture_flamengo_0$points", "drawable", context?.packageName))
                    "dolphin" ->
                        imageanimalview?.setImageResource(resources.getIdentifier("animal_picture_dolphin_0$points", "drawable", context?.packageName))
                    else -> {
                        imageanimalview?.setImageResource(R.drawable.animal_picture_cancer_00)
                    }
                }

                val buttonanimalmemory = view?.findViewById<ImageButton>(R.id.image_button_animal_memory)
                if (animalMemory == 0) {
                    buttonanimalmemory!!.setImageResource(resources.getIdentifier("animal_button_memory_off", "drawable", context?.packageName))
                } else {
                    buttonanimalmemory!!.setImageResource(resources.getIdentifier("animal_button_memory", "drawable", context?.packageName))
                }
                val buttonanimaldifference = view?.findViewById<ImageButton>(R.id.image_button_animal_difference)
                if (animalDifference == 0) {
                    buttonanimaldifference!!.setImageResource(resources.getIdentifier("animal_button_difference_off", "drawable", context?.packageName))
                } else {
                    buttonanimaldifference!!.setImageResource(resources.getIdentifier("animal_button_difference", "drawable", context?.packageName))
                }
                val buttonanimalshadow = view?.findViewById<ImageButton>(R.id.image_button_animal_shadow)
                if (animalShadow == 0) {
                    buttonanimalshadow!!.setImageResource(resources.getIdentifier("animal_button_shadow_off", "drawable", context?.packageName))
                } else {
                    buttonanimalshadow!!.setImageResource(resources.getIdentifier("animal_button_shadow", "drawable", context?.packageName))
                }
                val buttonanimalquiz = view?.findViewById<ImageButton>(R.id.image_button_animal_quiz)
                if (animalQuiz == 0) {
                    buttonanimalquiz!!.setImageResource(resources.getIdentifier("animal_button_quiz_off", "drawable", context?.packageName))
                } else {
                    buttonanimalquiz!!.setImageResource(resources.getIdentifier("animal_button_quiz", "drawable", context?.packageName))
                }
            }
        }.start()
    }

    fun updateButtonAppearence() {
        val buttonAnimalMemory = requireView().findViewById<ImageButton>(R.id.image_button_animal_memory)
        val buttonAnimalDifference = requireView().findViewById<ImageButton>(R.id.image_button_animal_difference)
        val buttonAnimalShadow = requireView().findViewById<ImageButton>(R.id.image_button_animal_shadow)
        val buttonAnimalQuiz = requireView().findViewById<ImageButton>(R.id.image_button_animal_quiz)
        val buttonAnimalVideo = requireView().findViewById<ImageButton>(R.id.image_button_animal_video)
        val buttonAnimalPoem = requireView().findViewById<ImageButton>(R.id.image_button_animal_poem)
        val buttonAnimalEncyclopedia = requireView().findViewById<ImageButton>(R.id.image_button_animal_encyclopedia)
    }
}