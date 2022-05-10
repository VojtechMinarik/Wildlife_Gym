package com.example.wildlifegym.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.wildlifegym.MainActivity
import com.example.wildlifegym.R

class MenuFragment : Fragment() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        sharedPreferences = requireContext().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        val buttonmenuback = view.findViewById<ImageButton>(R.id.image_button_menu_back)
        buttonmenuback.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            findNavController().navigate(R.id.action_menuFragment_to_introFragment)
        }

        val backoff = sharedPreferences.getBoolean("BACKOFF", false)
        if (backoff) {
            buttonmenuback.visibility = View.INVISIBLE
        }

        val buttonmenucrocodile = view.findViewById<ImageButton>(R.id.image_button_menu_crocodile)
        buttonmenucrocodile.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            val bundle = bundleOf("animal" to "crocodile")
            findNavController().navigate(R.id.action_menuFragment_to_animalFragment, bundle)
        }

        val buttonmenustork = view.findViewById<ImageButton>(R.id.image_button_menu_stork)
        buttonmenustork.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            val bundle = bundleOf("animal" to "stork")
            findNavController().navigate(R.id.action_menuFragment_to_animalFragment, bundle)
        }

        val buttonmenuflamengo = view.findViewById<ImageButton>(R.id.image_button_menu_flamengo)
        buttonmenuflamengo.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            val bundle = bundleOf("animal" to "flamengo")
            findNavController().navigate(R.id.action_menuFragment_to_animalFragment, bundle)
        }

        val buttonmenupenguin = view.findViewById<ImageButton>(R.id.image_button_menu_penguin)
        buttonmenupenguin.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            val bundle = bundleOf("animal" to "penguin")
            findNavController().navigate(R.id.action_menuFragment_to_animalFragment, bundle)
        }

        val buttonmenucancer = view.findViewById<ImageButton>(R.id.image_button_menu_cancer)
        buttonmenucancer.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            val bundle = bundleOf("animal" to "cancer")
            findNavController().navigate(R.id.action_menuFragment_to_animalFragment, bundle)
        }

        val buttonmenudolphin = view.findViewById<ImageButton>(R.id.image_button_menu_dolphin)
        buttonmenudolphin.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            val bundle = bundleOf("animal" to "dolphin")
            findNavController().navigate(R.id.action_menuFragment_to_animalFragment, bundle)
        }

        return view
    }

}