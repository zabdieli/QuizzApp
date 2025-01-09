package com.esiea.quizzapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: QuestionEntity) : Long


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnswer(answer: AnswerEntity)


    @Transaction
    @Query("SELECT * FROM question")
    fun getQuestionsWithAnswers(): Flow<List<QuestionWithAnswers>>


    @Query("SELECT COUNT(*) FROM question")
    suspend fun getQuestionsCount(): Int

}