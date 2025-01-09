package com.esiea.quizzapp.view.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.esiea.quizzapp.data.QuestionWithAnswers
import com.esiea.quizzapp.viewModel.QuizViewModel

@Composable
fun QuestionWithAnswersItem(questionWithAnswers: QuestionWithAnswers) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = questionWithAnswers.question.questionText,
            modifier = Modifier.padding(bottom = 8.dp)
        )


        if (questionWithAnswers.answers.isEmpty()) {
            Text(text = "Aucune réponse disponible", color = Color.Red)
        }


        questionWithAnswers.answers.forEach { answer ->
            Text(
                text = answer.answerText,
                modifier = Modifier.padding(start = 16.dp, bottom = 4.dp)
            )
        }
    }
}


@Composable
fun QuestionList(questionsWithAnswers: List<QuestionWithAnswers>) {
    LazyColumn {
        items(questionsWithAnswers) { questionWithAnswers ->
            QuestionWithAnswersItem(questionWithAnswers = questionWithAnswers)
        }
    }
}

@Composable
fun QuizScreen(quizViewModel: QuizViewModel = viewModel()) {

    val questionsWithAnswers by quizViewModel.questionsWithAnswers.observeAsState(initial = emptyList())


    LaunchedEffect(questionsWithAnswers) {
        Log.d("QuizScreen", "Questions with answers: $questionsWithAnswers")
    }


    QuestionList(questionsWithAnswers = questionsWithAnswers)
}


