package com.example.wildlifegym.utils

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AnimalDao {
    @Query("SELECT id FROM Animal WHERE animalName = :animalname")
    fun getId(animalname: String): Int

    @Query("SELECT animalPoints FROM Animal WHERE animalName = :animalname")
    fun getPoints(animalname: String): Int

    @Query("SELECT animalGameQuiz FROM Animal WHERE animalName = :animalname")
    fun getResQuiz(animalname: String): Int

    @Query("SELECT animalGameShadow FROM Animal WHERE animalName = :animalname")
    fun getResShadow(animalname: String): Int

    @Query("SELECT animalGameDifference FROM Animal WHERE animalName = :animalname")
    fun getResDifference(animalname: String): Int

    @Query("SELECT animalGameMemory FROM Animal WHERE animalName = :animalname")
    fun getResMemory(animalname: String): Int

    @Insert
    fun insertAnimal(animal: Animal)

    @Update
    fun updateAnimal(animal: Animal)
}