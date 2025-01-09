package com.esiea.quizzapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.esiea.quizzapp.data.AnswerEntity
import com.esiea.quizzapp.data.QuestionEntity
import com.esiea.quizzapp.data.QuestionWithAnswers
import com.esiea.quizzapp.repository.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val repository: QuizRepository
) : ViewModel() {


    val questionsWithAnswers: LiveData<List<QuestionWithAnswers>> =
        repository.allQuestionsWithAnswers.asLiveData()

    init {

        viewModelScope.launch {
            repository.initializeData()
        }
    }




}