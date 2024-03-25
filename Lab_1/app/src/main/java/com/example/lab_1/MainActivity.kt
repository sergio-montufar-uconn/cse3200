package com.example.lab_1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : ComponentActivity() {

    private val setVar = "PGB_LOG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(setVar, "onCreate")
        setContentView(R.layout.activity_main)

//        val layoutInflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val view = layoutInflater.inflate(R.layout.activity_main, null)
        setContentView(R.layout.activity_main)

        val textView: TextView = findViewById(R.id.textView2)
        val button: Button = findViewById(R.id.button)
        button.setOnClickListener(
            View.OnClickListener {
                textView.text = "Good Job"
            }
        )
    }

    override fun onStart() {
        super.onStart()
        Log.d(setVar, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(setVar, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(setVar, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(setVar, "onStop")
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d(setVar, "onDestroy")
//    }
}