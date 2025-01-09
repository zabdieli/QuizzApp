package com.esiea.quizzapp.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [QuestionEntity::class, AnswerEntity::class],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun questionDao(): QuestionDao
}
