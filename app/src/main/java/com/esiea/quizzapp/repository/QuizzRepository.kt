package com.esiea.quizzapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.esiea.quizzapp.data.AnswerEntity
import com.esiea.quizzapp.data.QuestionDao
import com.esiea.quizzapp.data.QuestionEntity
import com.esiea.quizzapp.data.QuestionWithAnswers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuizRepository @Inject constructor(
    private val questionDao: QuestionDao
) {
    val allQuestionsWithAnswers: Flow<List<QuestionWithAnswers>> =
        questionDao.getQuestionsWithAnswers()

    suspend fun addQuestionWithAnswers(question: QuestionEntity, answers: List<AnswerEntity>) {
        val questionId = questionDao.insertQuestion(question)
        answers.forEach { answer ->
            answer.questionId = questionId.toInt()
            questionDao.insertAnswer(answer)
        }
    }






    suspend fun initializeData() {
        if (questionDao.getQuestionsCount() == 0) {
            val questions = listOf(
                QuestionEntity(questionText = "Quel est le plus grand pays du monde par superficie ?"),
                QuestionEntity(questionText = "Qui a écrit Les Misérables ?"),
                QuestionEntity(questionText = "Quelle est la capitale de l'Italie ?"),
                QuestionEntity(questionText = "Quel est l'élément chimique représenté par le symbole  'O' ?")
            )
            val answers = listOf(
                listOf(
                    AnswerEntity(answerText = "Canada", isCorrect = false),
                    AnswerEntity(answerText = "Chine", isCorrect = false),
                    AnswerEntity(answerText = "États-Unis", isCorrect = false),
                    AnswerEntity(answerText = "Russie", isCorrect = true)
                ),
                listOf(
                    AnswerEntity(answerText = "Victor Hugo", isCorrect = true),
                    AnswerEntity(answerText = "Charles Dickens", isCorrect = false),
                    AnswerEntity(answerText = "Émile Zola", isCorrect = false),
                    AnswerEntity(answerText = "Jules Verne", isCorrect = false)
                ),
                listOf(
                    AnswerEntity(answerText = "Madrid", isCorrect = false),
                    AnswerEntity(answerText = "Rome", isCorrect = true),
                    AnswerEntity(answerText = "Venise", isCorrect = false),
                    AnswerEntity(answerText = "Paris", isCorrect = false)
                ),
                listOf(
                    AnswerEntity(answerText = "Or", isCorrect = false),
                    AnswerEntity(answerText = "Oxygène", isCorrect = true),
                    AnswerEntity(answerText = "Osmium", isCorrect = false),
                    AnswerEntity(answerText = "Oléum", isCorrect = false)
                )
            )
            questions.forEachIndexed { index, question ->
                addQuestionWithAnswers(question, answers[index])
            }
            val allQuestionsWithAnswers = questionDao.getQuestionsWithAnswers() // Récupérez les données pour vérifier
            Log.d("QuizRepository", "Questions with answers: $allQuestionsWithAnswers")
        }
    }
}

