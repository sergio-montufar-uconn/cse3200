package com.example.lab2_part1

import org.junit.Test
import org.junit.Before
import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Assert.assertEquals
import com.example.lab2_part1.model.ScoreModel
import com.example.lab2_part1.model.QuestionModel
import com.example.lab2_part1.controller.QuestionController


class QuestionTest {
    private lateinit var questionController: QuestionController
    @Before
    fun setup() {
        val scoreModel = ScoreModel()
        questionController = QuestionController(scoreModel)
    }

    @Test
    fun updateScoreTest() {
        questionController.answerProcess(1)
        assertEquals(1, questionController.currentScore)
        assertEquals(1, questionController.answeredQuestions)
    }

    @Test
    fun nextQuestionTest() {
        val questions = questionController.size
        questionController.nextQuestion()
        assertEquals(questions, questionController.size)
    }

    @Test
    fun restartTest() {
        questionController.answerProcess(1)
        questionController.answerProcess(0)
        questionController.restart()
        assertEquals(0, questionController.currentScore)
    }


}