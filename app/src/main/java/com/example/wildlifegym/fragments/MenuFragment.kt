package com.example.wildlifegym.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.wildlifegym.activities.MainActivity
import com.example.wildlifegym.R

/**
 * This is the Menu fragment
 * It is hosted by the main activity
 * It lets the user choose the animal to play with
 */
class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /** Inflate the view for this fragment */
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        /** Set up all the buttons available on this fragment */
        val buttonMenuBack = view.findViewById<ImageButton>(R.id.image_button_menu_back)
        val buttonMenuCrocodile = view.findViewById<ImageButton>(R.id.image_button_menu_crocodile)
        val buttonMenuStork = view.findViewById<ImageButton>(R.id.image_button_menu_stork)
        val buttonMenuFlamingo = view.findViewById<ImageButton>(R.id.image_button_menu_flamengo)
        val buttonMenuPenguin = view.findViewById<ImageButton>(R.id.image_button_menu_penguin)
        val buttonMenuCancer = view.findViewById<ImageButton>(R.id.image_button_menu_cancer)
        val buttonMenuDolphin = view.findViewById<ImageButton>(R.id.image_button_menu_dolphin)

        /** Hide the back button according to user preferences */
        (activity as MainActivity).hideButton(buttonMenuBack)

        /**
         * Set up the onClickListeners for all of the buttons available
         *
         * buttonMenuBack navigates back to the Intro Fragment
         * buttonMenuCrocodile navigates to the Animal fragment with the "crocodile" String as a bundle
         * buttonMenuStork navigates to the Animal fragment with the "stork" String as a bundle
         * buttonMenuFlamingo navigates to the Animal fragment with the "flamingo" String as a bundle
         * buttonMenuPenguin navigates to the Animal fragment with the "penguin" String as a bundle
         * buttonMenuCancer navigates to the Animal fragment with the "cancer" String as a bundle
         * buttonMenuDolphin navigates to the Animal fragment with the "dolphin" String as a bundle
         */
        buttonMenuBack.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            findNavController().navigate(R.id.action_menuFragment_to_introFragment)
        }
        buttonMenuCrocodile.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            val bundle = bundleOf("animal" to "crocodile")
            findNavController().navigate(R.id.action_menuFragment_to_animalFragment, bundle)
        }
        buttonMenuStork.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            val bundle = bundleOf("animal" to "stork")
            findNavController().navigate(R.id.action_menuFragment_to_animalFragment, bundle)
        }
        buttonMenuFlamingo.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            val bundle = bundleOf("animal" to "flamingo")
            findNavController().navigate(R.id.action_menuFragment_to_animalFragment, bundle)
        }
        buttonMenuPenguin.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            val bundle = bundleOf("animal" to "penguin")
            findNavController().navigate(R.id.action_menuFragment_to_animalFragment, bundle)
        }
        buttonMenuCancer.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            val bundle = bundleOf("animal" to "cancer")
            findNavController().navigate(R.id.action_menuFragment_to_animalFragment, bundle)
        }
        buttonMenuDolphin.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            val bundle = bundleOf("animal" to "dolphin")
            findNavController().navigate(R.id.action_menuFragment_to_animalFragment, bundle)
        }

        return view
    }
}