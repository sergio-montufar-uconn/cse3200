package com.example.lab2_part1.controller

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import com.example.lab2_part1.model.QuestionModel
import com.example.lab2_part1.model.ScoreModel
class QuestionController (
    private val scoreModel: ScoreModel
) {
    private var currQuestionIndex by mutableIntStateOf(0)
    private var questions = listOf(
        QuestionModel(1,"Is water wet?", listOf("False", "True"), 1),
        QuestionModel(2,"Does 2 + 2 = 5?", listOf("False", "True"), 0),
        QuestionModel(3,"Can you become a chicken?", listOf("False", "True"), 1),
        QuestionModel(4,"Is Pluto a planet?", listOf("False", "True"), 1),
        QuestionModel(5,"Does your breath stink?", listOf("False", "True"), 0)
    )
    val size = questions.size
    var quizOver = false
    var lastQuestion = currQuestionIndex == size - 1
    val currentScore = scoreModel.score
    val answeredQuestions = scoreModel.answered
    val currentQuestion = if (size > currQuestionIndex) questions[currQuestionIndex] else null


    fun answerProcess(answerIndex: Int) {
        if (quizOver || (lastQuestion && answeredQuestions >= size)) return

        currentQuestion?.let {
            scoreModel.answeredQuestion()
            if (it.rightAnswerIndex == answerIndex) scoreModel.updateScore()
        }

        if (!lastQuestion) nextQuestion()
    }

    fun nextQuestion() {
        if (currQuestionIndex < size - 1) {
            currQuestionIndex += 1
        }
    }

    fun restart() {
        currQuestionIndex = 0
        quizOver = false
        scoreModel.restart()
    }

    fun scoreCheck() {
        if (lastQuestion)
            quizOver = true
    }

}

