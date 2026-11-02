package com.fedor.populationguesser

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class GameOverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game_over)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val finalScore = intent.getIntExtra("FINAL_SCORE", 0)
        val totalAnswered = intent.getIntExtra("TOTAL_ANSWERED", 0)
        val difficultyName = intent.getStringExtra("DIFFICULTY") ?: "EASY"

        val textFinalScore = findViewById<TextView>(R.id.textFinalScore)
        textFinalScore.text = getString(R.string.final_score_format, finalScore)

        saveResult(difficultyName, finalScore, totalAnswered)

        val buttonPlayAgain = findViewById<Button>(R.id.buttonPlayAgain)
        val buttonHome = findViewById<Button>(R.id.buttonHome)

        buttonPlayAgain.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("DIFFICULTY", difficultyName)
            startActivity(intent)
            finish()
        }

        buttonHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun saveResult(difficultyName: String, score: Int, totalAnswered: Int) {
        val dao = AppDatabase.getDatabase(applicationContext).gameResultDao()
        lifecycleScope.launch {
            dao.insert(GameResult(difficulty = difficultyName, score = score, totalAnswered = totalAnswered))
        }
    }
}