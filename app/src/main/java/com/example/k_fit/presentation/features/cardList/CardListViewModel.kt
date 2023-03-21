package com.example.k_fit.presentation.features.cardList

import com.example.k_fit.R
import com.example.k_fit.presentation.base.BaseViewModel
import com.example.k_fit.presentation.models.CardEntryList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CardListViewModel @Inject constructor(
    //TODO private var CardRepository
) : BaseViewModel()  {

    var cardList: List<CardEntryList> = listOf(
        CardEntryList(cardTitle = "Incline hammer curls", cardSubtitle = "Strength", workoutDifficulty = selectCardIcon("Intermediate"), workoutImage = R.drawable.example ),
        CardEntryList(cardTitle = "Wide-grip barbell curl ", cardSubtitle = "Strength", workoutDifficulty = selectCardIcon("Beginner"), workoutImage = R.drawable.example ),
        CardEntryList(cardTitle = "Cheat curl", cardSubtitle = "Strength", workoutDifficulty = selectCardIcon("Expert"), workoutImage = R.drawable.example )

    )

    private fun selectCardIcon(difficulty:String): Int{
        return when(difficulty){
            "Beginner" -> R.drawable.beginner
            "Intermediate" -> R.drawable.intermediate
            "Expert" -> R.drawable.expert
            else -> 0
        }
    }
}

