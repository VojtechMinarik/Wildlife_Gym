package com.example.wildlifegym.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController
import com.example.wildlifegym.MainActivity
import com.example.wildlifegym.R


class AboutFragment : Fragment() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_about, container, false)

        sharedPreferences = requireContext().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        val buttonaboutback = view.findViewById<ImageButton>(R.id.image_button_about_back)
        buttonaboutback.setOnClickListener {
            (activity as MainActivity).makeSound("button")

            findNavController().navigate(R.id.action_aboutFragment_to_introFragment)
        }

        val backoff = sharedPreferences.getBoolean("BACKOFF", false)
        if (backoff) {
            buttonaboutback.visibility = View.INVISIBLE
        }

        val buttonaboutweb = view.findViewById<ImageButton>(R.id.image_button_about_web)
        buttonaboutweb.setOnClickListener {
            (activity as MainActivity).makeSound("button")

            val url = "https://www.mammacentrum.com"
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }

        return view
    }

}