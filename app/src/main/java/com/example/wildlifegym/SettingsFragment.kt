package com.example.wildlifegym

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.wildlifegym.utils.Animal
import com.example.wildlifegym.utils.AppDatabase


class SettingsFragment : Fragment() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        sharedPreferences = requireContext().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        // Initialize buttons
        val buttonsettingsback = view.findViewById<ImageButton>(R.id.image_button_settings_back)
        val buttonsettingsmusic = view.findViewById<ImageButton>(R.id.image_button_settings_music)
        val buttonsettingssound = view.findViewById<ImageButton>(R.id.image_button_settings_sound)
        val buttonsettingsbackswitch = view.findViewById<ImageButton>(R.id.image_button_settings_backswitch)
        val buttonsettingserase = view.findViewById<ImageButton>(R.id.image_button_settings_erase)

        buttonsettingsback.setOnClickListener {
            (activity as MainActivity).ButtonSound()
            findNavController().navigate(R.id.action_settingsFragment_to_introFragment)
        }

        var musicoff = sharedPreferences.getBoolean("MUSICOFF", false)
        if (musicoff) {
            buttonsettingsmusic.setImageResource(R.drawable.settings_button_music_off)
        }

        var soundoff = sharedPreferences.getBoolean("SOUNDOFF", false)
        if (soundoff) {
            buttonsettingssound.setImageResource(R.drawable.settings_button_sound_off)
        }

        var backoff = sharedPreferences.getBoolean("BACKOFF", false)
        if (backoff) {
            buttonsettingsbackswitch.setImageResource(R.drawable.settings_button_back_off)
        }

        if (backoff) {
            buttonsettingsback.visibility = View.INVISIBLE
        }

        buttonsettingsmusic.setOnClickListener {
            (activity as MainActivity).ButtonSound()

            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            if (musicoff) {
                editor.putBoolean("MUSICOFF", false)
                musicoff = false
                buttonsettingsmusic.setImageResource(R.drawable.settings_button_music)
            } else {
                editor.putBoolean("MUSICOFF", true)
                musicoff = true
                buttonsettingsmusic.setImageResource(R.drawable.settings_button_music_off)
            }
            editor.apply()
            if (!musicoff) {
                (activity as MainActivity).PlayBackgroundSound()
            } else {
                (activity as MainActivity).StopBackgroundSound()
            }
        }

        buttonsettingssound.setOnClickListener {
            (activity as MainActivity).ButtonSound()

            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            if (soundoff) {
                editor.putBoolean("SOUNDOFF", false)
                soundoff = false
                buttonsettingssound.setImageResource(R.drawable.settings_button_sound)
            } else {
                editor.putBoolean("SOUNDOFF", true)
                soundoff = true
                buttonsettingssound.setImageResource(R.drawable.settings_button_sound_off)
            }
            editor.apply()
        }

        buttonsettingsbackswitch.setOnClickListener {
            (activity as MainActivity).ButtonSound()

            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            if (backoff) {
                editor.putBoolean("BACKOFF", false)
                backoff = false
                buttonsettingsback.visibility = View.VISIBLE
                buttonsettingsbackswitch.setImageResource(R.drawable.settings_button_back)
            } else {
                editor.putBoolean("BACKOFF", true)
                backoff = true
                buttonsettingsback.visibility = View.INVISIBLE
                buttonsettingsbackswitch.setImageResource(R.drawable.settings_button_back_off)
            }
            editor.apply()
        }

        buttonsettingserase.setOnClickListener {
            (activity as MainActivity).ButtonSound()

            Thread {
                val db = AppDatabase.getDatabase(this.requireContext())

                db.databaseDao().updateAnimal(Animal(0, "crocodile", 0, 0, 0, 0, 0))
                db.databaseDao().updateAnimal(Animal(1, "stork", 0, 0, 0, 0, 0))
                db.databaseDao().updateAnimal(Animal(2, "cancer", 0, 0, 0, 0, 0))
                db.databaseDao().updateAnimal(Animal(3, "penguin", 0, 0, 0, 0, 0))
                db.databaseDao().updateAnimal(Animal(4, "flamengo", 0, 0, 0, 0, 0))
                db.databaseDao().updateAnimal(Animal(5, "dolphin", 0, 0, 0, 0, 0))
            }.start()
        }

        return view
    }
}