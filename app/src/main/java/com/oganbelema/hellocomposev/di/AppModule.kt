package com.oganbelema.hellocomposev.di

import android.content.Context
import androidx.room.Room
import com.oganbelema.hellocomposev.dao.NoteDao
import com.oganbelema.hellocomposev.database.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideNoteDao(noteDatabase: NoteDatabase) : NoteDao = noteDatabase.noteDao()

    @Singleton
    @Provides
    fun provideNoteDatabase(@ApplicationContext context: Context): NoteDatabase {
        return Room
            .databaseBuilder(context, NoteDatabase::class.java, "notes_db")
            .fallbackToDestructiveMigration()
            .build()
    }
}