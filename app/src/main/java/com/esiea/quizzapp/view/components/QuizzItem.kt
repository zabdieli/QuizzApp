package com.esiea.quizzapp.view.components


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.esiea.quizzapp.viewModel.QuizViewModel

@Composable
fun QuizApp(viewModel: QuizViewModel = hiltViewModel()) {

    val questionsWithAnswers by viewModel.questionsWithAnswers.observeAsState(initial = emptyList())

    var currentQuestionIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var quizCompleted by remember { mutableStateOf(false) }
    var selectedAnswer: Boolean? by remember { mutableStateOf(null) }
    if (questionsWithAnswers.isNotEmpty()) {
        if (quizCompleted) {
            FinalScoreScreen(score = score)
        } else {
            val questionWithAnswers = questionsWithAnswers[currentQuestionIndex]

            QuizScreen(
                question = questionWithAnswers.question.questionText,
                answers = questionWithAnswers.answers,
                score = score,
                onAnswerSelected = { isCorrect ->
                    selectedAnswer = isCorrect
                },
                onNextClicked = {

                    if (selectedAnswer != null) {
                        if (selectedAnswer == true) {
                            score++
                        }
                        if (currentQuestionIndex < questionsWithAnswers.size - 1) {
                            currentQuestionIndex++
                        } else {
                            quizCompleted = true
                        }
                        selectedAnswer = null
                    }
                }
            )
        }
    } else {
        LoadingScreen()
    }
}





