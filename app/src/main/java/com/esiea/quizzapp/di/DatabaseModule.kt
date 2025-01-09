package com.esiea.quizzapp.di

import android.content.Context
import androidx.room.Room
import com.esiea.quizzapp.data.AppDatabase
import com.esiea.quizzapp.data.QuestionDao
import com.esiea.quizzapp.repository.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase
    {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "quiz_database"
        ).fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    @Singleton
    fun provideQuestionDao(db: AppDatabase): QuestionDao {
        return db.questionDao()
    }
    @Provides
    @Singleton
    fun provideQuizRepository(questionDao: QuestionDao): QuizRepository {
        return QuizRepository(questionDao)
    }
}