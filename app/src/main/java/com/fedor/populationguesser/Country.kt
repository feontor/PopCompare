package com.fedor.populationguesser

data class Country(
    val name: String,
    val population: Long,
    val difficulty: Difficulty
)

enum class Difficulty {
    EASY, MEDIUM, HARD
}