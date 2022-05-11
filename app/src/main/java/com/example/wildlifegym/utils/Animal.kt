package com.example.wildlifegym.utils

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * This class represents an Animal entity
 * It is a representation of the Animal.db based in the assets/database folder
 * All the numeric attributes except animalPoints have 3 states
 * * -1 for unavailable features
 * * 0 for available unfinished features
 * * 1 for available finished features
 *
 * @param animalName Represents the name of the animal as a String
 * @param animalPoints Represents the number of completed games
 * @param animalGameQuiz Represents the Quiz game state
 * @param animalGameShadow Represents the Shadow game state
 * @param animalGameDifference Represents the Difference game state
 * @param animalGameMemory Represents the Memory game state
 * @param animalGameVideo Represents the Video state
 * @param animalGamePoem Represents the Poem state
 * @param animalGameEncyclopedia Represents the Encyclopedia state
 */
@Entity
data class Animal(
    @NonNull @PrimaryKey val animalName: String,
    @NonNull val animalPoints: Int,
    @NonNull val animalGameQuiz: Int,
    @NonNull val animalGameShadow: Int,
    @NonNull val animalGameDifference: Int,
    @NonNull val animalGameMemory: Int,
    @NonNull val animalGameVideo: Int,
    @NonNull val animalGamePoem: Int,
    @NonNull val animalGameEncyclopedia: Int
)