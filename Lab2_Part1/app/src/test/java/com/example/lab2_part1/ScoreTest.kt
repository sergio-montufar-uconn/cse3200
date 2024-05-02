package com.example.lab2_part1

import org.junit.Test
import org.junit.Before
import org.junit.Assert.assertEquals
import com.example.lab2_part1.model.ScoreModel
import com.example.lab2_part1.controller.ScoreController

class ScoreTest {
    private lateinit var scoreController: ScoreController
    private lateinit var scoreModel: ScoreModel

    @Before
    fun setup() {
        scoreModel = ScoreModel()
        scoreController = ScoreController(scoreModel)
    }

    @Test
    fun rightAnswerTest() {
        scoreController.rightAnswer()
        assertEquals(1, scoreController.score)
    }

    @Test
    fun amountOfQuestionsAnswersTest() {
        scoreController.answerQuestion()
        scoreController.answerQuestion()
        scoreController.answerQuestion()
        assertEquals(3, scoreController.answeredQuestions)
    }

    @Test
    fun restartTest() {
        scoreController.answerQuestion()
        scoreController.rightAnswer()
        scoreController.restart()
        assertEquals(0, scoreController.score)
        assertEquals(0, scoreController.answeredQuestions)
    }
}