package com.esiea.quizzapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "answers",
    foreignKeys = [ForeignKey(
        entity = QuestionEntity::class,
        parentColumns = ["id"],
        childColumns = ["questionId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class AnswerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var questionId: Int = 0,
    val answerText: String,
    val isCorrect: Boolean
)
