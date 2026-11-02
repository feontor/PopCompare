package com.fedor.populationguesser

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class QuizViewModelTest {

    private lateinit var viewModel: QuizViewModel

    @Before
    fun setup() {
        viewModel = QuizViewModel()
        viewModel.start("EASY")
    }

    private fun correctCountry(): Country =
        if (viewModel.countryA.population > viewModel.countryB.population) viewModel.countryA else viewModel.countryB

    private fun wrongCountry(): Country =
        if (viewModel.countryA.population > viewModel.countryB.population) viewModel.countryB else viewModel.countryA

    @Test
    fun correctAnswer_incrementsScore() {
        viewModel.submitAnswer(correctCountry())
        assertEquals(1, viewModel.score)
    }

    @Test
    fun correctAnswer_doesNotDecrementLives() {
        viewModel.submitAnswer(correctCountry())
        assertEquals(3, viewModel.lives)
    }

    @Test
    fun incorrectAnswer_decrementsLives() {
        viewModel.submitAnswer(wrongCountry())
        assertEquals(2, viewModel.lives)
    }

    @Test
    fun incorrectAnswer_doesNotIncrementScore() {
        viewModel.submitAnswer(wrongCountry())
        assertEquals(0, viewModel.score)
    }

    @Test
    fun submitAnswer_returnsTrue_onCorrectAnswer() {
        assertTrue(viewModel.submitAnswer(correctCountry()))
    }

    @Test
    fun submitAnswer_returnsFalse_onIncorrectAnswer() {
        assertFalse(viewModel.submitAnswer(wrongCountry()))
    }

    @Test
    fun submitAnswer_incrementsTotalAnswered_onCorrect() {
        viewModel.submitAnswer(correctCountry())
        assertEquals(1, viewModel.totalAnswered)
    }

    @Test
    fun submitAnswer_incrementsTotalAnswered_onIncorrect() {
        viewModel.submitAnswer(wrongCountry())
        assertEquals(1, viewModel.totalAnswered)
    }

    @Test
    fun threeIncorrectAnswers_triggersGameOver() {
        repeat(3) { viewModel.submitAnswer(wrongCountry()) }
        assertTrue(viewModel.isGameOver())
    }

    @Test
    fun fewerThanThreeIncorrectAnswers_doesNotTriggerGameOver() {
        viewModel.submitAnswer(wrongCountry())
        assertFalse(viewModel.isGameOver())
    }


    @Test
    fun start_calledAgain_keepsCurrentQuestion() {
        val firstA = viewModel.countryA
        val firstB = viewModel.countryB
        repeat(10) { viewModel.start("EASY") }
        assertEquals(firstA, viewModel.countryA)
        assertEquals(firstB, viewModel.countryB)
    }

    @Test
    fun loadNextQuestion_neverProducesTwoEqualCountries() {
        repeat(50) {
            viewModel.loadNextQuestion()
            assertNotEquals(viewModel.countryA, viewModel.countryB)
        }
    }
}