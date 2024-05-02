package com.example.lab2_part1.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf

class ScoreModel {
    private var currScore = mutableIntStateOf(0)
    val score: Int by currScore

    private var answeredQuestions = mutableIntStateOf(0)
    val answered: Int by answeredQuestions

    fun answeredQuestion() {
        answeredQuestions.value += 1
    }

    fun updateScore() {
        currScore.value += 1
    }

    fun restart() {
        currScore.value = 0
        answeredQuestions.value = 0
    }
}