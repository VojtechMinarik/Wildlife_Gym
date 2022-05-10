package com.example.wildlifegym.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.example.wildlifegym.MainActivity
import com.example.wildlifegym.R
import com.example.wildlifegym.animalactivities.*
import com.example.wildlifegym.utils.AppDatabase


class AnimalFragment : Fragment() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_animal, container, false)
        val animal = arguments?.getString("animal")
        val imageanimalview = view.findViewById<ImageView>(R.id.image_view_animal)
        var points: Int = 0

        sharedPreferences = requireContext().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        /*Thread {
            val db = AppDatabase.getDatabase(this.requireContext())

            points = db.databaseDao().getPoints(animal!!)

            val animalMemory = db.databaseDao().getResMemory(animal)
            val animalDifference = db.databaseDao().getResDifference(animal)
            val animalShadow = db.databaseDao().getResShadow(animal)
            val animalQuiz = db.databaseDao().getResQuiz(animal)

            activity?.runOnUiThread {
                when (animal) {
                    "crocodile" ->
                        imageanimalview.setImageResource(resources.getIdentifier("animal_picture_crocodile_0$points", "drawable", context?.packageName))
                    "stork" ->
                        imageanimalview.setImageResource(resources.getIdentifier("animal_picture_stork_0$points", "drawable", context?.packageName))
                    "cancer" ->
                        imageanimalview.setImageResource(resources.getIdentifier("animal_picture_cancer_0$points", "drawable", context?.packageName))
                    "penguin" ->
                        imageanimalview.setImageResource(resources.getIdentifier("animal_picture_penguin_0$points", "drawable", context?.packageName))
                    "flamengo" ->
                        imageanimalview.setImageResource(resources.getIdentifier("animal_picture_flamengo_0$points", "drawable", context?.packageName))
                    "dolphin" ->
                        imageanimalview.setImageResource(resources.getIdentifier("animal_picture_dolphin_0$points", "drawable", context?.packageName))
                    else -> {
                        imageanimalview.setImageResource(R.drawable.animal_picture_cancer_00)
                    }
                }

                val buttonanimalmemory = view.findViewById<ImageButton>(R.id.image_button_animal_memory)
                if (animalMemory == 0) {
                    buttonanimalmemory.setImageResource(resources.getIdentifier("animal_button_memory_off", "drawable", context?.packageName))
                }
                val buttonanimaldifference = view.findViewById<ImageButton>(R.id.image_button_animal_difference)
                if (animalDifference == 0) {
                    buttonanimaldifference.setImageResource(resources.getIdentifier("animal_button_difference_off", "drawable", context?.packageName))
                }
                val buttonanimalshadow = view.findViewById<ImageButton>(R.id.image_button_animal_shadow)
                if (animalShadow == 0) {
                    buttonanimalshadow.setImageResource(resources.getIdentifier("animal_button_shadow_off", "drawable", context?.packageName))
                }
                val buttonanimalquiz = view.findViewById<ImageButton>(R.id.image_button_animal_quiz)
                if (animalQuiz == 0) {
                    buttonanimalquiz.setImageResource(resources.getIdentifier("animal_button_quiz_off", "drawable", context?.packageName))
                }
            }
        }.start()*/

        val buttonanimalback = view.findViewById<ImageButton>(R.id.image_button_animal_back)
        buttonanimalback.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            findNavController().navigate(R.id.action_animalFragment_to_menuFragment)
        }

        val backoff = sharedPreferences.getBoolean("BACKOFF", false)
        if (backoff) {
            buttonanimalback.visibility = View.INVISIBLE
        }


        val buttonanimalmemory = view.findViewById<ImageButton>(R.id.image_button_animal_memory)
        buttonanimalmemory.setOnClickListener {
            (activity as MainActivity).makeSound("button")

            /*Thread {
                val db = AppDatabase.getDatabase(this.requireContext())
                val animalMemory = db.databaseDao().getResMemory(animal!!)
                if (animalMemory == 0) {
                    db.databaseDao().updateAnimal(Animal(db.databaseDao().getId(animal), animal, db.databaseDao().getPoints(animal) + 1, db.databaseDao().getResQuiz(animal), db.databaseDao().getResShadow(animal), db.databaseDao().getResDifference(animal), 1))
                }
            }.start()*/

            (activity as MainActivity).makeSound("button")
            val intent = Intent(context, MemoryActivity::class.java)
            intent.putExtra("animal", animal)
            startActivity(intent)
        }

        val buttonanimaldifference = view.findViewById<ImageButton>(R.id.image_button_animal_difference)
        buttonanimaldifference.setOnClickListener {
            (activity as MainActivity).makeSound("button")

            /*Thread {
                val db = AppDatabase.getDatabase(this.requireContext())
                val animalDifference = db.databaseDao().getResDifference(animal!!)
                if (animalDifference == 0) {
                    db.databaseDao().updateAnimal(Animal(db.databaseDao().getId(animal), animal, db.databaseDao().getPoints(animal) + 1, db.databaseDao().getResQuiz(animal), db.databaseDao().getResShadow(animal), 1, db.databaseDao().getResMemory(animal)))
                }
            }.start()*/

            (activity as MainActivity).makeSound("button")
            val intent = Intent(context, DifferenceActivity::class.java)
            intent.putExtra("animal", animal)
            startActivity(intent)
        }

        val buttonanimalshadow = view.findViewById<ImageButton>(R.id.image_button_animal_shadow)
        buttonanimalshadow.setOnClickListener {
            (activity as MainActivity).makeSound("button")

            /*Thread {
                val db = AppDatabase.getDatabase(this.requireContext())
                val animalShadow = db.databaseDao().getResShadow(animal!!)
                if (animalShadow == 0) {
                    db.databaseDao().updateAnimal(Animal(db.databaseDao().getId(animal), animal, db.databaseDao().getPoints(animal) + 1, db.databaseDao().getResQuiz(animal), 1, db.databaseDao().getResDifference(animal), db.databaseDao().getResMemory(animal)))
                }
            }.start()*/

            (activity as MainActivity).makeSound("button")
            val intent = Intent(context, ShadowActivity::class.java)
            intent.putExtra("animal", animal)
            startActivity(intent)
        }

        val buttonanimalquiz = view.findViewById<ImageButton>(R.id.image_button_animal_quiz)
        buttonanimalquiz.setOnClickListener {
            (activity as MainActivity).makeSound("button")

            /*Thread {
                val db = AppDatabase.getDatabase(this.requireContext())
                val animalQuiz = db.databaseDao().getResQuiz(animal!!)
                if (animalQuiz == 0) {
                    db.databaseDao().updateAnimal(Animal(db.databaseDao().getId(animal), animal, db.databaseDao().getPoints(animal) + 1, 1, db.databaseDao().getResShadow(animal), db.databaseDao().getResDifference(animal), db.databaseDao().getResMemory(animal)))
                }
            }.start()*/

            (activity as MainActivity).makeSound("button")
            val intent = Intent(context, QuizActivity::class.java)
            intent.putExtra("animal", animal)
            startActivity(intent)
        }

        val buttonanimalvideo = view.findViewById<ImageButton>(R.id.image_button_animal_video)
        buttonanimalvideo.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            val intent = Intent(context, VideoActivity::class.java)
            intent.putExtra("animal", animal)
            startActivity(intent)
        }

        val buttonanimalpoem = view.findViewById<ImageButton>(R.id.image_button_animal_poem)
        buttonanimalpoem.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            val intent = Intent(context, PoemActivity::class.java)
            intent.putExtra("animal", animal)
            startActivity(intent)
        }

        val buttonanimalencyclopedia = view.findViewById<ImageButton>(R.id.image_button_animal_encyclopedia)
        buttonanimalencyclopedia.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            val intent = Intent(context, EncyclopediaActivity::class.java)
            intent.putExtra("animal", animal)
            startActivity(intent)
        }

        return view
    }

    @RequiresApi(Build.VERSION_CODES.R)
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
}