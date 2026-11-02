package com.fedor.populationguesser

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnStart = findViewById<Button>(R.id.btnStart)
        val btnStats = findViewById<Button>(R.id.btnStats)
        val rgDifficulty = findViewById<RadioGroup>(R.id.rgDifficulty)

        btnStart.setOnClickListener {
            val difficulty = when (rgDifficulty.checkedRadioButtonId) {
                R.id.rbEasy -> "EASY"
                R.id.rbMedium -> "MEDIUM"
                R.id.rbHard -> "HARD"
                else -> null
            }

            if (difficulty == null) {
                Toast.makeText(this, "Select a difficulty first", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, QuizActivity::class.java)
                intent.putExtra("DIFFICULTY", difficulty)
                startActivity(intent)
            }
        }

        btnStats.setOnClickListener {
            val intent = Intent(this, StatsActivity::class.java)
            startActivity(intent)
        }
    }
}