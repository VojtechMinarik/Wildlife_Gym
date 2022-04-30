package com.example.wildlifegym.utils

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Animal(
    @NonNull @PrimaryKey val id: Int,
    @NonNull val animalName: String,
    @NonNull val animalPoints: Int,
    @NonNull val animalGameQuiz: Int,
    @NonNull val animalGameShadow: Int,
    @NonNull val animalGameDifference: Int,
    @NonNull val animalGameMemory: Int
)