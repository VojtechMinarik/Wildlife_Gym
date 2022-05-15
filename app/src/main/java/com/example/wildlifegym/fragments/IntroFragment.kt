package com.example.wildlifegym.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController
import com.example.wildlifegym.activities.MainActivity
import com.example.wildlifegym.R

/**
 * This is the Intro fragment
 * It is hosted by the main activity
 * It is the starting point for the user accessing this application
 * It allows user to move to the About fragment, Settings fragment and the Menu fragment
 */
class IntroFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /** Inflate the view for this fragment */
        val view = inflater.inflate(R.layout.fragment_intro, container, false)

        /** Set up all the buttons available on this fragment */
        val buttonIntroStart = view.findViewById<ImageButton>(R.id.image_button_intro_start)
        val buttonIntroAbout = view.findViewById<ImageButton>(R.id.image_button_intro_about)
        val buttonIntroSettings = view.findViewById<ImageButton>(R.id.image_button_intro_settings)

        /**
         * Set up the onClickListeners for all of the buttons available
         *
         * buttonIntroStart navigates to the Menu fragment
         * buttonIntroAbout navigates to the About fragment
         * buttonIntroSettings navigates to the Intro fragment
         */
        buttonIntroStart.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            findNavController().navigate(R.id.action_introFragment_to_menuFragment)
        }
        buttonIntroAbout.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            findNavController().navigate(R.id.action_introFragment_to_aboutFragment)
        }
        buttonIntroSettings.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            findNavController().navigate(R.id.action_introFragment_to_settingsFragment)
        }

        return view
    }
}