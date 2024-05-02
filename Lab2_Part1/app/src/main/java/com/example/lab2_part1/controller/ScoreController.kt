package com.example.lab2_part1.controller

import com.example.lab2_part1.model.ScoreModel
class ScoreController(val scoreModel: ScoreModel) {
    val score = scoreModel.score
    val answeredQuestions = scoreModel.answered

    fun rightAnswer() {
        scoreModel.updateScore()
    }
    fun answerQuestion() {
        scoreModel.answeredQuestion()
    }
    fun restart() {
        scoreModel.restart()
    }
}