package com.fedor.populationguesser

import androidx.lifecycle.ViewModel

/* Holds the state and rules for a single quiz run, so the State here
survives Activity recreation unlike fields declared directly on an Activity. */
class QuizViewModel : ViewModel() {

    var lives = 3
        private set
    var score = 0
        private set
    var totalAnswered = 0
        private set

    lateinit var difficulty: Difficulty
        private set
    private lateinit var countryPool: List<Country>

    lateinit var countryA: Country
        private set
    lateinit var countryB: Country
        private set

    /* Prevents start() from resetting an in-progress run when onCreate()
    runs again after a configuration change. */
    private var started = false

    /* Initializes a new run for difficultyName.
    Can be called repeatedly since only the first call has any effect */
    fun start(difficultyName: String) {
        if (started) return
        difficulty = Difficulty.valueOf(difficultyName)
        countryPool = Countries.getByDifficulty(difficulty)
        loadNextQuestion()
        started = true
    }

    // Loads country pair from the same country pool
    fun loadNextQuestion() {
        val shuffled = countryPool.shuffled()
        countryA = shuffled[0]
        countryB = shuffled[1]
    }

    // Handles logic on user selection returning true if selected country is correct (higher pop)
    fun submitAnswer(selected: Country): Boolean {
        val correct = if (countryA.population > countryB.population) countryA else countryB
        val isCorrect = selected == correct
        totalAnswered++
        if (isCorrect) score++ else lives--
        return isCorrect
    }

    fun isGameOver(): Boolean {
        var result = false
        if (lives <= 0) {
            result = true
        }

        return result
    }
}