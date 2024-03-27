package com.example.lab2_part1.model

data class ScoreModel (
    val id: Int,
    val questionText: String,
    val answerOptions: List<String> = listOf("True", "False")
//    val difficulty: Difficulty
)