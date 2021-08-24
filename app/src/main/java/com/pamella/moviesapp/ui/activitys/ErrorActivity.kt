package com.pamella.moviesapp.ui.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import com.pamella.moviesapp.R

class ErrorActivity : AppCompatActivity() {

    private lateinit var closeButton: ImageButton
    private lateinit var tryAgainButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.request_error)

        closeButton = findViewById(R.id.closeButton)
        tryAgainButton = findViewById(R.id.tryAgainBtn)

        closeButton.setOnClickListener { finish() }
        tryAgainButton.setOnClickListener { finish() }
    }
}