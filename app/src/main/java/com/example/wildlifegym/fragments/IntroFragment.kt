package com.example.wildlifegym.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController
import com.example.wildlifegym.MainActivity
import com.example.wildlifegym.R

class IntroFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_intro, container, false)

        val buttonintrostart = view.findViewById<ImageButton>(R.id.image_button_intro_start)
        buttonintrostart.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            findNavController().navigate(R.id.action_introFragment_to_menuFragment)
        }

        val buttonintroabout = view.findViewById<ImageButton>(R.id.image_button_intro_about)
        buttonintroabout.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            findNavController().navigate(R.id.action_introFragment_to_aboutFragment)
        }

        val buttonintrosettings = view.findViewById<ImageButton>(R.id.image_button_intro_settings)
        buttonintrosettings.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            findNavController().navigate(R.id.action_introFragment_to_settingsFragment)
        }

        return view
    }
}