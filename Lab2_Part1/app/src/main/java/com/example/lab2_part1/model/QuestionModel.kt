package com.example.lab2_part1.model

data class QuestionModel (
    val id: Int,
    val questionText: String,
    val options: List<String> = listOf("True", "False"),
    val rightAnswerIndex: Int
)