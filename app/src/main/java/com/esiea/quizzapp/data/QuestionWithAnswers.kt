package com.esiea.quizzapp.data

import androidx.room.Embedded
import androidx.room.Relation

data class QuestionWithAnswers(
    @Embedded val question: QuestionEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "questionId"
    ) val answers: List<AnswerEntity>
)
