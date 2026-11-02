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

class StatsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_stats)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textHighScore = findViewById<TextView>(R.id.textHighScore)
        val textGamesPlayed = findViewById<TextView>(R.id.textGamesPlayed)
        val textAccuracy = findViewById<TextView>(R.id.textAccuracy)

        val dao = AppDatabase.getDatabase(applicationContext).gameResultDao()
        lifecycleScope.launch {
            val results = dao.getAll()

            val highScore = results.maxOfOrNull { it.score } ?: 0
            val gamesPlayed = results.size
            val totalCorrect = results.sumOf { it.score }
            val totalAnswered = results.sumOf { it.totalAnswered }
            val accuracy =
                if (totalAnswered > 0)
                    (totalCorrect * 100.0) / totalAnswered
                else 0.0

            textHighScore.text = getString(R.string.high_score_format, highScore)
            textGamesPlayed.text = getString(R.string.games_played_format, gamesPlayed)
            textAccuracy.text = getString(R.string.accuracy_format, accuracy)
        }

        val buttonBackHome = findViewById<Button>(R.id.buttonBackHome)
        buttonBackHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}