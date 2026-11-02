package com.fedor.populationguesser

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.os.Handler
import android.os.Looper
import android.graphics.Color
import androidx.core.content.ContextCompat

class QuizActivity : AppCompatActivity() {

    private val viewModel: QuizViewModel by viewModels()

    private lateinit var textLives: TextView
    private lateinit var textScore: TextView
    private lateinit var textPrompt: TextView
    private lateinit var buttonCountryA: Button
    private lateinit var buttonCountryB: Button
    private var defaultButtonColor = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textLives = findViewById(R.id.textLives)
        textScore = findViewById(R.id.textScore)
        textPrompt = findViewById(R.id.textPrompt)
        buttonCountryA = findViewById(R.id.buttonCountryA)
        buttonCountryB = findViewById(R.id.buttonCountryB)
        defaultButtonColor = ContextCompat.getColor(this, R.color.button_purple)

        val difficultyName = intent.getStringExtra("DIFFICULTY") ?: "EASY"
        viewModel.start(difficultyName)

        showCurrentQuestion()
        updateLivesAndScore()

        buttonCountryA.setOnClickListener{onAnswerSelected(viewModel.countryA)}
        buttonCountryB.setOnClickListener{onAnswerSelected(viewModel.countryB)}
    }

    private fun showCurrentQuestion() {
        buttonCountryA.text = viewModel.countryA.name
        buttonCountryB.text = viewModel.countryB.name
    }

    private fun onAnswerSelected(selected: Country) {
        val isCorrect = viewModel.submitAnswer(selected)
        val selectedButton = if (selected == viewModel.countryA) buttonCountryA else buttonCountryB

        buttonCountryA.isEnabled = false
        buttonCountryB.isEnabled = false
        selectedButton.setBackgroundColor(if (isCorrect) Color.GREEN else Color.RED)

        updateLivesAndScore()

        Handler(Looper.getMainLooper()).postDelayed({
            buttonCountryA.setBackgroundColor(defaultButtonColor)
            buttonCountryB.setBackgroundColor(defaultButtonColor)
            buttonCountryA.isEnabled = true
            buttonCountryB.isEnabled = true

            if (viewModel.isGameOver()) {
                val gameOverIntent = Intent(this, GameOverActivity::class.java)
                gameOverIntent.putExtra("FINAL_SCORE", viewModel.score)
                gameOverIntent.putExtra("TOTAL_ANSWERED", viewModel.totalAnswered)
                gameOverIntent.putExtra("DIFFICULTY", viewModel.difficulty.name)
                startActivity(gameOverIntent)
                finish()
            } else {
                viewModel.loadNextQuestion()
                showCurrentQuestion()
            }
        }, 800)
    }

    private fun updateLivesAndScore() {
        textLives.text = getString(R.string.lives_format, viewModel.lives)
        textScore.text = getString(R.string.score_format, viewModel.score)
    }
}