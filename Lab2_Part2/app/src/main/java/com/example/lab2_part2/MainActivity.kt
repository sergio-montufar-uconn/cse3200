package com.example.lab2_part2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.lab2_part2.ui.theme.Lab2_Part2Theme
import com.example.lab2_part2.controller.QuestionController
import com.example.lab2_part2.controller.LoginController
import com.example.lab2_part2.model.ScoreModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab2_Part2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val quiz = remember { mutableStateOf(false)}
                    val userController = remember { LoginController() }
                    val scoreModel = ScoreModel()
                    val questionController = QuestionController(scoreModel)
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    Lab2_Part1Theme {
//        val loginController = LoginController()
//    }
//}