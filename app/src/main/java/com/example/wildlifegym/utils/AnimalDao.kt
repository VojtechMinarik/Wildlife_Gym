package com.example.wildlifegym.utils

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update

/**
 * This interface provides the methods to access the Animal database
 */
@Dao
interface AnimalDao {
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

    @Query("SELECT animalGameVideo FROM Animal WHERE animalName = :animalname")
    fun getResVideo(animalname: String): Int

    @Query("SELECT animalGamePoem FROM Animal WHERE animalName = :animalname")
    fun getResPoem(animalname: String): Int

    @Query("SELECT animalGameEncyclopedia FROM Animal WHERE animalName = :animalname")
    fun getResEncyclopedia(animalname: String): Int

    @Update
    fun updateAnimal(animal: Animal)
}