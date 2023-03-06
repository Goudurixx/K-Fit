package com.example.k_fit.presentation.models

data class WorkoutUIModel(
    var name: String,
    var type: String,
    var muscle: String,
    var equipment: String,
    var difficulty: String,
    var instructions: String,
    var image: Int = com.example.k_fit.R.drawable.example
)

