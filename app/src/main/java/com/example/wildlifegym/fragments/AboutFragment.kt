package com.example.wildlifegym.fragments

import android.content.Intent
import android.net.Uri
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
 * This is the About fragment
 * It is hosted by the main activity
 * It presents the basic information about the app and the company and provides a link to the company web
 */
class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /** Inflate the view for this fragment */
        val view = inflater.inflate(R.layout.fragment_about, container, false)

        /** Set up all the buttons available on this fragment */
        val buttonAboutBack = view.findViewById<ImageButton>(R.id.image_button_about_back)
        val buttonAboutWeb = view.findViewById<ImageButton>(R.id.image_button_about_web)

        /** Hide the back button according to user preferences */
        (activity as MainActivity).hideButton(buttonAboutBack)

        /**
         * Set up the onClickListeners for all of the buttons available
         *
         * buttonAboutBack navigates back to the Intro Fragment
         * buttonAboutWeb opens the company webpage
         */
        buttonAboutBack.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            findNavController().navigate(R.id.action_aboutFragment_to_introFragment)
        }
        buttonAboutWeb.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            openWebpage("https://www.mammacentrum.com")
        }

        return view
    }

    /**
     * This method opens a webpage given as a String parameter
     * The user chooses an app to open the webpage with
     *
     * @param url An url address of the webpage we want to open
     */
    fun openWebpage(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

}