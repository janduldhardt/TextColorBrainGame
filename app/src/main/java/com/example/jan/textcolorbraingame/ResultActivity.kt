package com.example.jan.textcolorbraingame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val score = intent.getStringExtra(EXTRA_MESSAGE)
        textViewScore.setText(score.toString()+" Points")
        buttonPlayAgain.setOnClickListener {
            val intent = Intent(this@ResultActivity, MainActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onBackPressed() {
        val intent = Intent(this@ResultActivity, MainActivity::class.java)
        startActivity(intent)
    }



}
