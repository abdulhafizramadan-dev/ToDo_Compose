package com.ahr.todo_compose.di

import android.content.Context
import androidx.room.Room
import com.ahr.todo_compose.data.TodoDao
import com.ahr.todo_compose.data.TodoDatabase
import com.ahr.todo_compose.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): TodoDatabase = Room.databaseBuilder(
        context,
        TodoDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideTodoDao(
        todoDatabase: TodoDatabase
    ): TodoDao = todoDatabase.todoDao()

}