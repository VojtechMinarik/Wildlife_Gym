package com.example.wildlifegym.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController
import com.example.wildlifegym.MainActivity
import com.example.wildlifegym.R
import com.example.wildlifegym.utils.Animal
import com.example.wildlifegym.utils.AppDatabase

/**
 * This is the Settings fragment
 * It is hosted by the main activity
 * It gives user an opportunity to edit his preferences (sound, music, back button)
 * It allows the user to delete his progress
 */
class SettingsFragment : Fragment() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /** Inflate the view for this fragment */
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        /** Set up the current state of the preferences */
        var musicOff = (activity as MainActivity).checkPreferences("MUSICOFF")
        var soundOff = (activity as MainActivity).checkPreferences("SOUNDOFF")
        var backOff = (activity as MainActivity).checkPreferences("BACKOFF")

        /** Set up all the buttons available on this fragment */
        val buttonSettingsBack = view.findViewById<ImageButton>(R.id.image_button_settings_back)
        val buttonSettingsMusicSwitch = view.findViewById<ImageButton>(R.id.image_button_settings_music)
        val buttonSettingsSoundSwitch = view.findViewById<ImageButton>(R.id.image_button_settings_sound)
        val buttonSettingsBackSwitch = view.findViewById<ImageButton>(R.id.image_button_settings_backswitch)
        val buttonSettingsErase = view.findViewById<ImageButton>(R.id.image_button_settings_erase)

        /** Hide the back button according to user preferences */
        (activity as MainActivity).hideButton(buttonSettingsBack)

        /** Update the current state of the preference buttons */
        updateButtonState(musicOff, soundOff, backOff, buttonSettingsMusicSwitch, buttonSettingsSoundSwitch, buttonSettingsBackSwitch)

        /**
         * Set up the onClickListeners for all of the buttons available
         *
         * buttonSettingsBack navigates back to the Intro Fragment
         * buttonSettingsMusicSwitch edits the shared preferences and updates the music state
         * buttonSettingsSoundSwitch edits the shared preferences and the button sound state
         * buttonSettingsBackSwitch edits the shared preferences and updates the back button state
         * buttonSettingsErase restarts the activity progress back to the initial state
         */
        buttonSettingsBack.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            findNavController().navigate(R.id.action_settingsFragment_to_introFragment)
        }
        buttonSettingsMusicSwitch.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            editPreferences(musicOff, buttonSettingsMusicSwitch, "MUSICOFF", "settings_button_music")
            musicOff = !musicOff
            updateMusic(musicOff)
        }
        buttonSettingsSoundSwitch.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            editPreferences(soundOff, buttonSettingsSoundSwitch, "SOUNDOFF", "settings_button_sound")
            soundOff = !soundOff
        }
        buttonSettingsBackSwitch.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            editPreferences(backOff, buttonSettingsBackSwitch, "BACKOFF", "settings_button_back")
            backOff = !backOff
            (activity as MainActivity).hideButton(buttonSettingsBack)
        }
        buttonSettingsErase.setOnClickListener {
            (activity as MainActivity).makeSound("button")
            resetDatabase()
        }

        return view
    }

    /**
     * This method updates the source images of all the preference buttons
     *
     * @param music Current state of the MUSICOFF preference
     * @param sound Current state of the SOUNDOFF preference
     * @param back Current state of the BACKOFF preference
     * @param buttonSettingsMusicSwitch Music switch button
     * @param buttonSettingsSoundSwitch Sound switch button
     * @param buttonSettingsBackSwitch Back switch button
     */
    private fun updateButtonState(music: Boolean, sound: Boolean, back: Boolean,
                          buttonSettingsMusicSwitch: ImageButton,
                          buttonSettingsSoundSwitch: ImageButton,
                          buttonSettingsBackSwitch: ImageButton) {
        if (music) {
            buttonSettingsMusicSwitch.setImageResource(R.drawable.settings_button_music_off)
        }
        if (sound) {
            buttonSettingsSoundSwitch.setImageResource(R.drawable.settings_button_sound_off)
        }
        if (back) {
            buttonSettingsBackSwitch.setImageResource(R.drawable.settings_button_back_off)
        }
    }

    /**
     * This method turns the music on/off according to the user preferences
     *
     * @param music Current state of the MUSICOFF preference
     */
    private fun updateMusic(music: Boolean) {
        if (!music) {
            (activity as MainActivity).playBackgroundSound()
        } else {
            (activity as MainActivity).stopBackgroundSound()
        }
    }

    /**
     * This method edits the shared preference state for the given variable
     * It also changes the source image of the button according to users choice
     *
     * @param stateVar Current state of the changed variable
     * @param button Button assigned to this variable
     * @param prefName Name of the shared preference of this variable
     * @param resName Name of the image source file used fot this button
     */
    private fun editPreferences(stateVar: Boolean, button: ImageButton, prefName: String, resName: String) {
        sharedPreferences = requireContext().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        if (stateVar) {
            editor.putBoolean(prefName, false)
            button.setImageResource(resources.getIdentifier(resName, "drawable", context?.packageName))
        } else {
            editor.putBoolean(prefName, true)
            button.setImageResource(resources.getIdentifier("${resName}_off", "drawable", context?.packageName))
        }
        editor.apply()
    }

    /**
     * This method resets the database to the initial state
     * It uses the update method from the AnimalDao interface
     */
    private fun resetDatabase() {
        Thread {
            val db = AppDatabase.getDatabase(this.requireContext())
            db.databaseDao().updateAnimal(Animal( "crocodile", 0, -1, -1, -1, -1, 1, 1, -1))
            db.databaseDao().updateAnimal(Animal( "stork", 0, -1, -1, -1, -1, 1, 1, -1))
            db.databaseDao().updateAnimal(Animal( "cancer", 0, -1, -1, -1, -1, 1, 1, -1))
            db.databaseDao().updateAnimal(Animal( "penguin", 0, -1, -1, -1, -1, 1, 1, -1))
            db.databaseDao().updateAnimal(Animal( "flamingo", 0, 0, 0, 0, 0, 1, 1, 1))
            db.databaseDao().updateAnimal(Animal( "dolphin", 0, -1, -1, -1, -1, 1, 1, -1))
        }.start()
    }
}